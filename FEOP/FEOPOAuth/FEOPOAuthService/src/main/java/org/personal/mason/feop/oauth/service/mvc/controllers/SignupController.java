package org.personal.mason.feop.oauth.service.mvc.controllers;

import org.personal.mason.feop.oauth.service.domain.model.common.SystemSettings;
import org.personal.mason.feop.oauth.service.domain.model.oauth.InvitingCode;
import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthUser;
import org.personal.mason.feop.oauth.service.domain.service.common.SystemSettingsService;
import org.personal.mason.feop.oauth.service.domain.service.oauth.FeopUserService;
import org.personal.mason.feop.oauth.service.domain.service.oauth.InvitingCodeService;
import org.personal.mason.feop.oauth.service.mvc.model.SignupForm;
import org.personal.mason.feop.oauth.service.utils.SystemSettingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class SignupController {
    private static String INVITE_PERIOD_KEY = "invite_registration";
    private static String INVITE_PERIOD_VALUE = "true";

    private FeopUserService feopUserService;
    private SystemSettingsService systemSettingsService;
    private InvitingCodeService invitingCodeService;

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        // binder.registerCustomEditor(Date.class, new DateEditor());
        // DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    @Autowired
    public void setFeopUserService(FeopUserService feopUserService) {
        this.feopUserService = feopUserService;
    }

    @Autowired
    public void setSystemSettingsService(SystemSettingsService systemSettingsService) {
        this.systemSettingsService = systemSettingsService;
    }

    @Autowired
    public void setInvitingCodeService(InvitingCodeService invitingCodeService) {
        this.invitingCodeService = invitingCodeService;
    }

    @RequestMapping(value = {"/signup/", "/signup/form"})
    public String signup(@RequestParam(value = "redirect_uri", required = false) String redirectUri, @ModelAttribute SignupForm signupForm,
                         Model model) {
        if (redirectUri != null) {
            model.addAttribute("redirect_uri", redirectUri);
        }
        List<SystemSettings> settings = systemSettingsService.findByKey(INVITE_PERIOD_KEY);
        if (SystemSettingUtils.getValue(settings).equals(INVITE_PERIOD_VALUE)) {
            model.addAttribute("requireInvite", true);
        }
        return "app.regist";
    }

    @RequestMapping(value = "/signup/new", method = RequestMethod.POST)
    public String signup(@Valid SignupForm signupForm, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "app.regist";
        }

        if (!signupForm.getPassword().equals(signupForm.getRepeatPassword())) {
            result.rejectValue("repeatPassword", "errors.changesecret.repeatPassword", "New Password does not match.");
            return "app.regist";
        }

        String email = signupForm.getEmail();
        if (feopUserService.findByEmailOrUsername(email) != null) {
            result.rejectValue("email", "errors.signup.email", "Account with this email address already exist.");
            return "app.regist";
        }

        List<SystemSettings> settings = systemSettingsService.findByKey(INVITE_PERIOD_KEY);
        if (SystemSettingUtils.getValue(settings).equals(INVITE_PERIOD_VALUE)) {
            String inviteCode = signupForm.getInviteCode();
            InvitingCode invitingCode = invitingCodeService.findWithCode(inviteCode);
            if (inviteCode != null && invitingCode != null) {
                invitingCodeService.delete(invitingCode);
            } else {
                result.rejectValue("inviteCode", "errors.signup.inviteCode", "Invilad Inviting Code.");
                return "app.regist";
            }
        }

        OauthUser user = feopUserService.createUser(signupForm);
        feopUserService.regist(user);

        if (signupForm.getRedirectUrl() != null) {
            return String.format("redirect:%s", signupForm.getRedirectUrl());
        }

        redirectAttributes.addFlashAttribute("message", "You have successfully signed up and logged in.");
        return "redirect:/";
    }
}
