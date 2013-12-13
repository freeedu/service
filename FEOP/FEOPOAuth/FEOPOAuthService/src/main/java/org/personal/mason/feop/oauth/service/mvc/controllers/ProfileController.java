package org.personal.mason.feop.oauth.service.mvc.controllers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.personal.mason.feop.oauth.service.common.oauth2.extention.FOEPUserDetailsService;
import org.personal.mason.feop.oauth.service.common.oauth2.extention.FoepBasicUser;
import org.personal.mason.feop.oauth.service.common.oauth2.extention.FoepUserDetails;
import org.personal.mason.feop.oauth.service.domain.model.common.FoepUser;
import org.personal.mason.feop.oauth.service.domain.service.common.FoepUserService;
import org.personal.mason.feop.oauth.service.mvc.model.ChangePasswordForm;
import org.personal.mason.feop.oauth.service.mvc.model.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class ProfileController {
    private static final Log LOG = LogFactory.getLog(ProfileController.class);
//    private FoepUserService foepUserService;

    private FOEPUserDetailsService foepUserDetailsService;

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        // binder.registerCustomEditor(Date.class, new DateEditor());
        // DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    @Autowired
    public void setFoepUserDetailsService(FOEPUserDetailsService foepUserDetailsService){
        this.foepUserDetailsService = foepUserDetailsService;
    }

//    @Autowired
//    public void setFoepUserService(FoepUserService foepUserService) {
//        this.foepUserService = foepUserService;
//    }

    @RequestMapping(value = {"/account/pwd/edit"}, method = RequestMethod.GET)
    public String changePassword(@ModelAttribute ChangePasswordForm changePasswordForm) {
        return "app.profile.updatesecret";
    }

    @RequestMapping(value = {"/account/pwd/update"}, method = RequestMethod.POST)
    public String updatePassword(@Valid ChangePasswordForm changePasswordForm, BindingResult result, ModelMap map, Principal principal) {
        if (result.hasErrors()) {
            return "app.profile.updatesecret";
        }

        if (!changePasswordForm.getNewPassword().equals(changePasswordForm.getRepeatPassword())) {
            result.rejectValue("repeatPassword", "errors.changesecret.repeatPassword", "New Password does not match.");
            return "app.profile.updatesecret";
        }

        try {
            foepUserDetailsService.changePassword(changePasswordForm.getOldPassword(), changePasswordForm.getNewPassword());//foepUserService.findByEmailOrUsername(email);
        } catch (EmptyResultDataAccessException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find foep user");
            }
            result.rejectValue("", "errors.changesecret", "You do not have privilege for this operation.");
            return "app.profile.updatesecret";
        } catch (IllegalArgumentException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Password validate failed.");
            }
            result.rejectValue("oldPassword", "errors.changesecret.oldPassowrd", "Your password is not correct.");
            return "app.profile.updatesecret";
        }

        map.addAttribute("msg", "You have update your password successfully.");
        return "app.profile.updatesecret";
    }

    @RequestMapping(value = {"/account/profile"}, method = RequestMethod.GET)
    public String myProfile(ModelMap map, Principal principal) {
        String princ = principal.getName();

        FoepUser ouser = foepUserDetailsService.findByEmailOrUsernameOrPhone(princ);
        if (ouser == null) {
            return "redirect:/oauth/logout";
        }

        UserForm uf = new UserForm();

        uf.setUserName(ouser.getUserName());
        uf.setEmail(ouser.getEmail());
        uf.setPhone(ouser.getPhone());
        uf.setUserId(ouser.getUserId());
        uf.setEnabled(ouser.getEnabled());

        map.addAttribute("userForm", uf);
        return "app.profile";
    }

    @RequestMapping(value = {"/account/edit"}, method = RequestMethod.GET)
    public String updateProfile(ModelMap map, Principal principal) {
        String princ = principal.getName();

        FoepUser ouser = foepUserDetailsService.findByEmailOrUsernameOrPhone(princ);
        if (ouser == null) {
            return "redirect:/oauth/logout";
        }

        UserForm uf = new UserForm();
        uf.setUserName(ouser.getUserName());
        uf.setEmail(ouser.getEmail());
        uf.setPhone(ouser.getPhone());
        uf.setUserId(ouser.getUserId());
        uf.setEnabled(ouser.getEnabled());
        map.addAttribute("userForm", uf);

        return "app.profile.update";
    }

    @RequestMapping(value = {"/account/update"}, method = RequestMethod.POST)
    public String changeProfile(UserForm userForm, Principal principal) {
        String princ = principal.getName();

        FoepUser ouser = foepUserDetailsService.findByEmailOrUsernameOrPhone(princ);
        if (ouser == null) {
            return "redirect:/oauth/login";
        }

        ouser.setPhone(userForm.getPhone());
        ouser.setUserName(userForm.getUserName());

        /**
         *
         * @param id
         * @param email
         * @param phone
         * @param authorities
         * @param password
         * @param username
         * @param accountNonExpired
         * @param accountNonLocked
         * @param credentialsNonExpired
         * @param enabled
         */
        FoepUserDetails userDetails = new FoepBasicUser(ouser.getId(), ouser.getEmail(),
                userForm.getPhone(), null, ouser.getPassword(), userForm.getUserName(),
                ouser.getAccountNonExpired(), ouser.getAccountNonLocked(),
                ouser.getCredentialsNonExpired(), ouser.getEnabled());
        foepUserDetailsService.updateUser(userDetails);

        return "redirect:/account/profile";
    }

}
