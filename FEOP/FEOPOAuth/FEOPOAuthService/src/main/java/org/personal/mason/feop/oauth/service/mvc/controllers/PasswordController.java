package org.personal.mason.feop.oauth.service.mvc.controllers;

import org.personal.mason.feop.oauth.service.common.mail.EmailSender;
import org.personal.mason.feop.oauth.service.common.mail.MailBodyGenerator;
import org.personal.mason.feop.oauth.service.domain.model.common.EmailTemplate;
import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthUser;
import org.personal.mason.feop.oauth.service.domain.model.oauth.PasswordReset;
import org.personal.mason.feop.oauth.service.domain.service.common.EmailTemplateService;
import org.personal.mason.feop.oauth.service.domain.service.oauth.FeopUserService;
import org.personal.mason.feop.oauth.service.domain.service.oauth.PasswordResetService;
import org.personal.mason.feop.oauth.service.mvc.model.PasswordResetForm;
import org.personal.mason.feop.oauth.service.mvc.model.ResetPasswordForm;
import org.personal.mason.feop.oauth.service.utils.StringGenerator;
import org.personal.mason.feop.oauth.service.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: domei
 * Date: 12/6/13
 * Time: 7:54 PM
 * Usage: Only for public find out password
 */

@Controller
public class PasswordController {
    private PasswordResetService passwordResetService;
    private FeopUserService feopUserService;
    private EmailTemplateService emailTemplateService;
    private EmailSender emailSender;

    @Autowired
    public void setPasswordResetService(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @Autowired
    public void setFeopUserService(FeopUserService feopUserService) {
        this.feopUserService = feopUserService;
    }

    @Autowired
    public void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Autowired
    public void setEmailTemplateService(EmailTemplateService emailTemplateService) {
        this.emailTemplateService = emailTemplateService;
    }

    @RequestMapping(value = {"/resetpassword"}, method = RequestMethod.GET)
    public String resetPassword() {
        return "app.resetpassword";
    }

    @RequestMapping(value = {"/resetpassword/confirm"}, method = RequestMethod.POST)
    public String resetPassword(@ModelAttribute ResetPasswordForm resetPasswordForm, HttpServletRequest request, Model model) {
        String email = resetPasswordForm.getEmail();
        OauthUser user = feopUserService.findByEmailOrUsername(email);
        if (user == null) {
            model.addAttribute("errorMsg", "Invalid email account");
            return null;
        }

        PasswordReset reset = new PasswordReset();
        reset.setEmail(email);
        reset.setRequestTime(TimeUtils.getCurrentTimestamp());
        reset.setToken(StringGenerator.genereateUniqueToken());
        passwordResetService.save(reset);
        // TODO: mail
        final String RESET_PASSWORD_TEMPLATE = "RESET_PASSWORD_TEMPLATE";
        EmailTemplate template = emailTemplateService.findLatestTemplateWithName(RESET_PASSWORD_TEMPLATE);
        if (template != null) {
            Map<String, String> props = new HashMap<String, String>();
            String servletPath = "/findpassword";
            String resetPasswordUrl = String.format("%s?token=%s", buildUrlWithRequest(request, servletPath), reset.getToken());
            props.put("resetPasswordUrl", resetPasswordUrl);

            String body = MailBodyGenerator.buildEmailBody(template.getContent(), props);
            emailSender.sendEmail(reset.getEmail(), template.getSubject(), body);
        }
        model.addAttribute("msg", "An reset Email has send to you.");
        return "app.resetpassword";
    }

    @RequestMapping(value = {"/findpassword"}, method = RequestMethod.GET)
    public String updateSecurityWithToken(@RequestParam String token, Model model) {
        model.addAttribute("tkn", token);
        return "app.findpassword";
    }

    @RequestMapping(value = {"/findpassword/update"}, method = RequestMethod.POST)
    public String updateSecretWithToken(@Valid PasswordResetForm passwordResetForm, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "app.findpassword";
        }

        if (!passwordResetForm.getNewPassword().equals(passwordResetForm.getRepeatPassword())) {
            result.rejectValue("repeatPassword", "errors.changesecret.repeatPassword", "New Password does not match.");
            return "app.findpassword";
        }

        PasswordReset passwordReset = passwordResetService.findByToken(passwordResetForm.getToken());
        if (passwordReset != null) {
            String email = passwordReset.getEmail();
            OauthUser user = feopUserService.findByEmailOrUsername(email);

            feopUserService.updatePassword(user, passwordResetForm.getNewPassword());
            passwordResetService.delete(passwordReset);
        }
        model.addAttribute("msg", "Password reset success!");
        return "app.findpassword";
    }


    private String buildUrlWithRequest(HttpServletRequest request, String servletPath) {
        String url = request.getRequestURL().toString();
        String oldServletPath = request.getServletPath();
        return url.replace(oldServletPath, servletPath);
    }

}
