package org.personal.mason.feop.server.blog.mvc.rest;

import org.personal.mason.feop.server.blog.domain.model.BlogSetting;
import org.personal.mason.feop.server.blog.domain.service.BlogSettingService;
import org.personal.mason.feop.server.blog.mvc.model.BlogSettingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api")
public class BlogSettingApi {

    private BlogSettingService blogSettingService;

    @Autowired
    public void setBlogSettingService(BlogSettingService blogSettingService) {
        this.blogSettingService = blogSettingService;
    }

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/setting", method = RequestMethod.GET)
    @ResponseBody
    public BlogSettingModel findSettingWithId(@RequestParam("id") Long id) {
        BlogSetting setting = blogSettingService.findById(id);
        BlogSettingModel settingModel = BlogSettingModel.revert(setting);
        return settingModel;
    }

    /**
     * @param blogSettingModel
     * @return
     */
    @RequestMapping(value = "/setting/update", method = RequestMethod.PUT)
    @ResponseBody
    public BlogSettingModel update(BlogSettingModel blogSettingModel) {
        BlogSetting setting = BlogSettingModel.convert(blogSettingModel);
        BlogSetting updatedSetting = blogSettingService.update(setting);

        BlogSettingModel updatedSettingModel = BlogSettingModel.revert(updatedSetting);
        return updatedSettingModel;
    }
}
