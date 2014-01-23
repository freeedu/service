package org.personal.mason.feop.server.blog.mvc.controllers;

import org.personal.mason.feop.server.blog.domain.model.BlogSetting;
import org.personal.mason.feop.server.blog.domain.service.BlogSettingService;
import org.personal.mason.feop.server.blog.mvc.model.BlogSettingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BlogSettingController {

    private BlogSettingService blogSettingService;

    @Autowired
    public void setBlogSettingService(BlogSettingService blogSettingService) {
        this.blogSettingService = blogSettingService;
    }

    @RequestMapping(value = "/setting", method = RequestMethod.GET)
    public String findSettingWithId(@RequestParam("id") Long id, Model model) {
        BlogSetting setting = blogSettingService.findById(id);

        BlogSettingModel settingModel = BlogSettingModel.revert(setting);
        model.addAttribute("/setting", settingModel);
        return "blog.setting.view";
    }

    @RequestMapping(value = "/setting/update", method = RequestMethod.GET)
    public String update(@RequestParam("id") Long id, Model model) {
        BlogSetting setting = blogSettingService.findById(id);
        BlogSettingModel updatedSettingModel = BlogSettingModel.revert(setting);
        model.addAttribute("/setting", updatedSettingModel);
        return "blog.setting.update";
    }

    @RequestMapping(value = "/setting/update", method = RequestMethod.POST)
    public String update(@Validated BlogSettingModel blogSettingModel, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return null;
        }

        BlogSetting setting = BlogSettingModel.convert(blogSettingModel);
        BlogSetting updatedSetting = blogSettingService.update(setting);

        BlogSettingModel updatedSettingModel = BlogSettingModel.revert(updatedSetting);
        model.addAttribute("setting", updatedSettingModel);
        return "blog.setting.view";
    }
}
