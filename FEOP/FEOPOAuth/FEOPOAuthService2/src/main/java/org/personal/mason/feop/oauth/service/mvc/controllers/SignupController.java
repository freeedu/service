package org.personal.mason.feop.oauth.service.mvc.controllers;

import java.util.List;

import javax.validation.Valid;

import org.personal.mason.feop.oauth.service.domain.OauthUser;
import org.personal.mason.feop.oauth.service.domain.SystemSettings;
import org.personal.mason.feop.oauth.service.mvc.model.SignupForm;
import org.personal.mason.feop.oauth.service.spi.FeopUserService;
import org.personal.mason.feop.oauth.service.spi.InvitingCodeService;
import org.personal.mason.feop.oauth.service.spi.SystemSettingsService;
import org.personal.mason.feop.oauth.service.utils.SystemSettingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignupController {
	private static String INVITE_PERIOD_KEY = "invite_registration";
	private static String INVITE_PERIOD_VALUE = "true";

	private FeopUserService feopUserService;
	private SystemSettingsService systemSettingsService;
	private InvitingCodeService invitingCodeService;

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

	@RequestMapping(value = { "/signup/", "/signup/form" })
	public String signup(@ModelAttribute SignupForm signupForm, Model model) {
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
		List<SystemSettings> settings = systemSettingsService.findByKey(INVITE_PERIOD_KEY);
		if (SystemSettingUtils.getValue(settings).equals(INVITE_PERIOD_VALUE)) {
			String inviteCode = signupForm.getInviteCode();
			if (inviteCode != null && invitingCodeService.findWithCode(inviteCode) != null) {
			} else {
				result.rejectValue("inviteCode", "errors.signup.inviteCode", "Invilad Inviting Code.");
				return "app.regist";
			}
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

		OauthUser user = feopUserService.createUser(signupForm);
		feopUserService.regist(user);

		redirectAttributes.addFlashAttribute("message", "You have successfully signed up and logged in.");
		return "redirect:/";
	}
}
