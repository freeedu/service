package org.personal.mason.feop.oauth.service.mvc.controllers;

import java.security.Principal;

import javax.validation.Valid;

import org.personal.mason.feop.oauth.service.domain.OauthUser;
import org.personal.mason.feop.oauth.service.mvc.model.ChangePasswordForm;
import org.personal.mason.feop.oauth.service.mvc.model.UserForm;
import org.personal.mason.feop.oauth.service.spi.FeopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProfileController {

	private FeopUserService feopUserService;

	@Autowired
	public void setFeopUserService(FeopUserService feopUserService) {
		this.feopUserService = feopUserService;
	}

	@RequestMapping(value = { "/profile/changepwd" }, method = RequestMethod.GET)
	public String changePassword(@ModelAttribute ChangePasswordForm changePasswordForm) {
		return "profile/updatesecret";
	}

	@RequestMapping(value = { "/profile/changesecret" }, method = RequestMethod.POST)
	public String updatePassword(@Valid ChangePasswordForm changePasswordForm, BindingResult result, ModelMap map, Principal principal) {
		if (result.hasErrors()) {
			return "profile/updatesecret";
		}

		if (!changePasswordForm.getNewPassword().equals(changePasswordForm.getRepeatPassword())) {
			result.rejectValue("repeatPassword", "errors.changesecret.repeatPassword", "New Password does not match.");
			return "profile/updatesecret";
		}

		String email = principal.getName();
		OauthUser ouser = feopUserService.findByEmailOrUsername(email);
		if (ouser == null) {
			result.rejectValue("", "errors.changesecret", "You do not have privilege for this operation.");
			return "profile/updatesecret";
		}

		feopUserService.updatePassword(ouser, changePasswordForm.getNewPassword());
		map.addAttribute("msg", "You have update your password successfully.");

		return "profile/updatesecret";
	}

	@RequestMapping(value = { "/profile/", "/profile/profile" }, method = RequestMethod.GET)
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

		map.addAttribute("userForm", uf);

		return "profile/profile";
	}

	@RequestMapping(value = { "/profile/update" }, method = RequestMethod.GET)
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
		map.addAttribute("userForm", uf);

		return "profile/updateprofile";
	}

	@RequestMapping(value = { "/profile/update" }, method = RequestMethod.POST)
	public String changeProfile(@Valid UserForm userForm, BindingResult result, Principal principal) {
		if (result.hasErrors()) {
			return "profile/updateprofile";
		}

		String princ = principal.getName();

		OauthUser ouser = feopUserService.findByEmailOrUsername(princ);
		if (ouser == null) {
			return "redirect:/oauth/logout";
		}

		ouser.setPhone(userForm.getPhone());
		ouser.setUserName(userForm.getUserName());

		feopUserService.update(ouser);

		return "redirect:/profile/profile";
	}

}
