package org.personal.mason.feop.oauth.service.mvc.controllers;

import org.personal.mason.feop.oauth.common.domain.model.SystemSetting;
import org.personal.mason.feop.oauth.common.spi.SettingsHolder;
import org.personal.mason.feop.oauth.service.common.oauth2.extention.FOEPUserDetailsService;
import org.personal.mason.feop.oauth.service.common.oauth2.extention.FoepBasicUser;
import org.personal.mason.feop.oauth.service.common.oauth2.extention.FoepUserDetails;
import org.personal.mason.feop.oauth.service.domain.model.common.InvitingCode;
import org.personal.mason.feop.oauth.service.domain.service.common.InvitingCodeService;
import org.personal.mason.feop.oauth.service.mvc.model.SignupForm;
import org.personal.mason.feop.oauth.service.utils.SettingKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import java.util.HashSet;
import java.util.Set;

@Controller
public class SignupController {

    private InvitingCodeService invitingCodeService;
    private SettingsHolder settingsHolder;
    private FOEPUserDetailsService foepUserDetailsService;
    private DefaultAuthorities defaultAuthorities;

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        // binder.registerCustomEditor(Date.class, new DateEditor());
        // DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    @Autowired
    public void setFoepUserDetailsService(FOEPUserDetailsService foepUserDetailsService) {
        this.foepUserDetailsService = foepUserDetailsService;
    }

    @Autowired
    public void setSettingsHolder(SettingsHolder settingsHolder) {
        this.settingsHolder = settingsHolder;
        this.defaultAuthorities = new DefaultAuthorities(settingsHolder);
    }

    @Autowired
    public void setInvitingCodeService(InvitingCodeService invitingCodeService) {
        this.invitingCodeService = invitingCodeService;
    }

    @RequestMapping(value = {"/signup/new"}, method = RequestMethod.GET)
    public String signup(@RequestParam(value = "redirect_uri", required = false) String redirectUri, @ModelAttribute SignupForm signupForm,
                         Model model) {
        if (redirectUri != null) {
            model.addAttribute("redirect_uri", redirectUri);
        }

        if (!SettingKeys.INVITE_PERIOD_VALUE.equals(settingsHolder.findWithKey(SettingKeys.INVITE_PERIOD_KEY))) {
            model.addAttribute("requireInvite", true);
        }
        return "app.regist";
    }

    @RequestMapping(value = "/signup/save", method = RequestMethod.POST)
    public String signup(@Valid SignupForm signupForm, BindingResult result, RedirectAttributes redirectAttributes, Model model) {
        if (result.hasErrors()) {
            return signup(signupForm.getRedirectUrl(), signupForm, model);
        }

        if (!signupForm.getPassword().equals(signupForm.getRepeatPassword())) {
            result.rejectValue("repeatPassword", "errors.changesecret.repeatPassword", "New Password does not match.");
            return signup(signupForm.getRedirectUrl(), signupForm, model);
        }

        String email = signupForm.getEmail();

        if (foepUserDetailsService.findByEmailOrUsernameOrPhone(email) != null) {
            result.rejectValue("email", "errors.signup.email", "Account with this email address already exist.");
            return signup(signupForm.getRedirectUrl(), signupForm, model);
        }

        if (!SettingKeys.INVITE_PERIOD_VALUE.equals(settingsHolder.findWithKey(SettingKeys.INVITE_PERIOD_KEY))) {
            String inviteCode = signupForm.getInviteCode();
            InvitingCode invitingCode = invitingCodeService.findWithCode(inviteCode);
            if (inviteCode != null && invitingCode != null) {
                invitingCodeService.delete(invitingCode);
            } else {
                result.rejectValue("inviteCode", "errors.signup.inviteCode", "Invilad Inviting Code.");
                return signup(signupForm.getRedirectUrl(), signupForm, model);
            }
        }

        Set<GrantedAuthority> authorities = defaultAuthorities.getDefaultAuthorities();
        FoepUserDetails userDetails = new FoepBasicUser(null, signupForm.getEmail(),
                signupForm.getPhone(), authorities, signupForm.getPassword(), signupForm.getUserName(),
                true, true, true, true);

        foepUserDetailsService.createUser(userDetails);

        if (signupForm.getRedirectUrl() != null) {
            return String.format("redirect:%s", signupForm.getRedirectUrl());
        }

        redirectAttributes.addFlashAttribute("message", "You have successfully signed up.");
        return "redirect:/";
    }

    private class DefaultAuthorities {
        private SettingsHolder settingsHolder;

        private Set<GrantedAuthority> authorities;

        protected DefaultAuthorities(SettingsHolder settingsHolder) {
            this.settingsHolder = settingsHolder;
        }

        synchronized Set<GrantedAuthority> getDefaultAuthorities() {
            if (authorities == null) {
                authorities = new HashSet<>();
                SystemSetting authoritiesSetting = settingsHolder.findWithKey("foep.regist.authorities.default");
                if (authoritiesSetting != null) {
                    String value = authoritiesSetting.getValue();
                    if (value == null || value.trim().isEmpty()) {
                        value = "ROLE_USER";
                    }
                    value = value.toUpperCase();
                    String[] as = value.split(",");
                    for (String str : as) {
                        authorities.add(new SimpleGrantedAuthority(str.trim()));
                    }
                }
            }
            return authorities;
        }
    }
}
