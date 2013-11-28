package org.personal.mason.feop.server.blog.mvc.controllers;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.service.BlogService;
import org.personal.mason.feop.server.blog.mvc.model.BlogModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ServerController {
    private final static Pageable DEFAULT_PAGEABLE = new PageRequest(0, 6);

    private BlogService blogService;

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @RequestMapping(value = {"/", "index"}, method = RequestMethod.GET)
    public String index(Model model) {
        Page<Blog> blogs = blogService.findAll(DEFAULT_PAGEABLE);

        List<BlogModel> models = new ArrayList<>();
        if (blogs != null) {
            for (Blog blog : blogs) {
                BlogModel blogModel = BlogModel.revert(blog);
                models.add(blogModel);
            }
        }

        model.addAttribute("blogs", models);

        return "app.index";
    }

    @RequestMapping(value = {"errorPage"})
    public String error(@RequestParam(value = "error", required = false) String error, Model model) {
        model.addAttribute("error", error);
        return "app.error";
    }

    @RequestMapping(value = {"contactus"})
    public String contactUs() {
        return "app.contact";
    }

    @RequestMapping(value = {"aboutme"})
    public String aboutMe() {
        return "app.aboutme";
    }
}
