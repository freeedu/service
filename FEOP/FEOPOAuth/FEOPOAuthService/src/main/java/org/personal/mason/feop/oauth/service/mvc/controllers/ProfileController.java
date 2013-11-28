package org.personal.mason.feop.oauth.service.mvc.controllers;

import org.personal.mason.feop.oauth.service.common.mail.EmailSender;
import org.personal.mason.feop.oauth.service.common.mail.MailBodyGenerator;
import org.personal.mason.feop.oauth.service.domain.model.common.EmailTemplate;
import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthUser;
import org.personal.mason.feop.oauth.service.domain.model.oauth.PasswordReset;
import org.personal.mason.feop.oauth.service.domain.service.common.EmailTemplateService;
import org.personal.mason.feop.oauth.service.domain.service.oauth.FeopUserService;
import org.personal.mason.feop.oauth.service.domain.service.oauth.PasswordResetService;
import org.personal.mason.feop.oauth.service.mvc.model.ChangePasswordForm;
import org.personal.mason.feop.oauth.service.mvc.model.PasswordResetForm;
import org.personal.mason.feop.oauth.service.mvc.model.ResetPasswordForm;
import org.personal.mason.feop.oauth.service.mvc.model.UserForm;
import org.personal.mason.feop.oauth.service.utils.StringGenerator;
import org.personal.mason.feop.oauth.service.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
public class ProfileController {

    private FeopUserService feopUserService;
    private PasswordResetService passwordResetService;
    private EmailTemplateService emailTemplateService;
    private EmailSender emailSender;

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        // binder.registerCustomEditor(Date.class, new DateEditor());
        // DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    @Autowired
    private ServletContext servletContext;

    @Autowired
    public void setEmailTemplateService(EmailTemplateService emailTemplateService) {
        this.emailTemplateService = emailTemplateService;
    }

    @Autowired
    public void setFeopUserService(FeopUserService feopUserService) {
        this.feopUserService = feopUserService;
    }

    @Autowired
    public void setPasswordResetService(PasswordResetService passwordResetService) {
        this.passwordResetService = passwordResetService;
    }

    @Autowired
    public void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @RequestMapping(value = {"/account/resetpassword"}, method = RequestMethod.GET)
    public String resetPassword() {
        return "app.resetpassword";
    }

    @RequestMapping(value = {"/account/resetpassword"}, method = RequestMethod.POST)
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
            String servletPath = "/account/findpassword";
            String resetPasswordUrl = String.format("%s?token=%s", buildUrlWithRequest(request, servletPath), reset.getToken());
            props.put("resetPasswordUrl", resetPasswordUrl);

            String body = MailBodyGenerator.buildEmailBody(template.getContent(), props);
            emailSender.sendEmail(reset.getEmail(), template.getSubject(), body);
        }
        model.addAttribute("msg", "An reset Email has send to you.");
        return "app.resetpassword";
    }

    @RequestMapping(value = {"/account/findpassword"}, method = RequestMethod.GET)
    public String updateSecurityWithToken(@RequestParam String token, Model model) {
        model.addAttribute("tkn", token);
        return "app.findpassword";
    }

    @RequestMapping(value = {"/account/findpassword"}, method = RequestMethod.POST)
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

    @RequestMapping(value = {"/account/changepwd"}, method = RequestMethod.GET)
    public String changePassword(@ModelAttribute ChangePasswordForm changePasswordForm) {
        return "app.profile.updatesecret";
    }

    @RequestMapping(value = {"/account/changesecret"}, method = RequestMethod.POST)
    public String updatePassword(@Valid ChangePasswordForm changePasswordForm, BindingResult result, ModelMap map, Principal principal) {
        if (result.hasErrors()) {
            return "app.profile.updatesecret";
        }

        if (!changePasswordForm.getNewPassword().equals(changePasswordForm.getRepeatPassword())) {
            result.rejectValue("repeatPassword", "errors.changesecret.repeatPassword", "New Password does not match.");
            return "app.profile.updatesecret";
        }

        String email = principal.getName();
        OauthUser ouser = feopUserService.findByEmailOrUsername(email);
        String oldPassword = changePasswordForm.getOldPassword();
        if (!feopUserService.validate(oldPassword, ouser)) {
            result.rejectValue("oldPassword", "errors.changesecret.oldPassowrd", "Your password is not correct.");
            return "app.profile.updatesecret";
        }

        if (ouser == null) {
            result.rejectValue("", "errors.changesecret", "You do not have privilege for this operation.");
            return "app.profile.updatesecret";
        }

        feopUserService.updatePassword(ouser, changePasswordForm.getNewPassword());
        map.addAttribute("msg", "You have update your password successfully.");

        return "app.profile.updatesecret";
    }

    @RequestMapping(value = {"/account/", "/account/profile"}, method = RequestMethod.GET)
    public String myProfile(ModelMap map, Principal principal) {
        String princ = principal.getName();

        OauthUser ouser = feopUserService.findByEmailOrUsername(princ);
        if (ouser == null) {
            return "redirect:/oauth/logout";
        }

        UserForm uf = new UserForm();

        uf.setUserName(ouser.getUserName());
        uf.setEmail(ouser.getEmail());
        uf.setPhone(ouser.getPhone());
        uf.setUserId(ouser.getUserId());
        uf.setActivated(ouser.getActivated());
        uf.setFirstName(ouser.getFirstName());
        uf.setLastName(ouser.getLastName());
        uf.setGender(ouser.getGender());
        uf.setLocation(ouser.getLocation());
        uf.setBirth(ouser.getBirth());
        uf.setProfileImageUri(ouser.getProfileImageUri());

        map.addAttribute("userForm", uf);

        return "app.profile";
    }

    @RequestMapping(value = {"/account/update"}, method = RequestMethod.GET)
    public String updateProfile(ModelMap map, Principal principal) {
        String princ = principal.getName();

        OauthUser ouser = feopUserService.findByEmailOrUsername(princ);
        if (ouser == null) {
            return "redirect:/oauth/logout";
        }

        UserForm uf = new UserForm();
        uf.setUserName(ouser.getUserName());
        uf.setEmail(ouser.getEmail());
        uf.setPhone(ouser.getPhone());
        uf.setUserId(ouser.getUserId());
        uf.setActivated(ouser.getActivated());
        uf.setFirstName(ouser.getFirstName());
        uf.setLastName(ouser.getLastName());
        uf.setGender(ouser.getGender());
        uf.setLocation(ouser.getLocation());
        uf.setBirth(ouser.getBirth());
        uf.setProfileImageUri(ouser.getProfileImageUri());
        map.addAttribute("userForm", uf);

        return "app.profile.update";
    }

    @RequestMapping(value = {"/account/update"}, method = RequestMethod.POST)
    public String changeProfile(@Valid UserForm userForm, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "app.profile.update";
        }

        String princ = principal.getName();

        OauthUser ouser = feopUserService.findByEmailOrUsername(princ);
        if (ouser == null) {
            return "redirect:/oauth/login";
        }

        ouser.setPhone(userForm.getPhone());
        ouser.setUserName(userForm.getUserName());

        feopUserService.update(ouser);

        return "redirect:/account/profile";
    }

    private String buildUrlWithRequest(HttpServletRequest request, String servletPath) {
        String url = request.getRequestURL().toString();
        String oldServletPath = request.getServletPath();
        return url.replace(oldServletPath, servletPath);
    }

}
