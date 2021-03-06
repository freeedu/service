package org.personal.mason.feop.oauth.service.mvc.controllers;

import org.personal.mason.feop.oauth.common.domain.model.SystemSetting;
import org.personal.mason.feop.oauth.common.spi.SystemSettingService;
import org.personal.mason.feop.oauth.service.mvc.model.SystemSettingForm;
import org.personal.mason.feop.oauth.service.utils.PageableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class SystemSettingsController {

    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) throws Exception {
        // binder.registerCustomEditor(Date.class, new DateEditor());
        // DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        CustomDateEditor editor = new CustomDateEditor(df, false);
        binder.registerCustomEditor(Date.class, editor);
    }

    private SystemSettingService systemSettingService;

    @Autowired
    public void setSystemSettingService(SystemSettingService systemSettingService) {
        this.systemSettingService = systemSettingService;
    }

    @RequestMapping(value = {"/admin/settings/new"}, method = RequestMethod.GET)
    public String newSystemSetting() {
        return "app.settings.new";
    }

    @RequestMapping(value = {"/admin/settings/save"}, method = RequestMethod.POST)
    public String saveSystemSetting(@Valid SystemSettingForm systemSettingForm, BindingResult result, Model model) {
        SystemSetting settings = new SystemSetting();
        settings.setKey(systemSettingForm.getKey());
        settings.setValue(systemSettingForm.getValue());
        settings.setStartDate(systemSettingForm.getStartDate());
        settings.setEndDate(systemSettingForm.getEndDate());
        settings.setDisabled(systemSettingForm.getDisabled() == null ? false : systemSettingForm.getDisabled());

        systemSettingService.save(settings);

        model.addAttribute("setting", settings);
        return "app.settings.view";
    }

    @RequestMapping(value = {"/admin/settings/edit"}, method = RequestMethod.GET)
    public String updateSystemSetting(@RequestParam("id") Long id, Model model) {
        SystemSetting setting = systemSettingService.findById(id);
        if (setting == null) {
            return null;
        }

        model.addAttribute("setting", SystemSettingForm.revert(setting));
        return "app.settings.update";
    }

    @RequestMapping(value = {"/admin/settings/update"}, method = RequestMethod.POST)
    public String mergeSystemSetting(@Valid SystemSettingForm setting, BindingResult result, Model model) {
        SystemSetting settings = systemSettingService.findById(setting.getId());
        settings.setKey(setting.getKey());
        settings.setStartDate(setting.getStartDate());
        settings.setEndDate(setting.getEndDate());
        settings.setDisabled(setting.getDisabled());

        model.addAttribute("setting", SystemSettingForm.revert(settings));
        return "app.settings.view";
    }

    @RequestMapping(value = {"/admin/settings/list"}, method = RequestMethod.GET)
    public String findSystemSettings(@RequestParam(value = "p", required = false) Integer page,
                                     @RequestParam(value = "l", required = false) Integer size, Model model) {
        if (page == null) {
            page = 0;
        }
        if (size == null) {
            size = 10;
        }
        Page<SystemSetting> settings = systemSettingService.findAll(PageableUtils.getPageable(page, size));
        if(settings != null){
            model.addAttribute("settings", settings.getContent());
        }
        return "app.settings.list";
    }

    @RequestMapping(value = {"/admin/settings/delete"}, method = RequestMethod.GET)
    public String deleteSetting(@RequestParam("id") Long id) {
        SystemSetting setting = systemSettingService.findById(id);
        if (setting != null) {
            systemSettingService.delete(setting);
        }
        return "redirect:/admin/settings/list";
    }

    @RequestMapping(value = {"/admin/settings/view"}, method = RequestMethod.GET)
    public String viewSetting(@RequestParam("id") Long id, Model model) {
        SystemSetting setting = systemSettingService.findById(id);
        if (setting == null) {
            return null;
        }

        model.addAttribute("setting", SystemSettingForm.revert(setting));
        return "app.settings.view";
    }
}
