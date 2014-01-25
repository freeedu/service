package org.personal.mason.feop.server.blog.mvc.controllers;

import org.personal.mason.feop.server.blog.domain.model.*;
import org.personal.mason.feop.server.blog.domain.service.BlogService;
import org.personal.mason.feop.server.blog.domain.service.CategoryService;
import org.personal.mason.feop.server.blog.domain.service.SeryService;
import org.personal.mason.feop.server.blog.domain.service.TagService;
import org.personal.mason.feop.server.blog.mvc.model.BlogModel;
import org.personal.mason.feop.server.blog.mvc.utils.AuthenticationUtils;
import org.personal.mason.feop.server.blog.mvc.utils.Pager;
import org.personal.mason.feop.server.blog.mvc.utils.ViewMapper;
import org.personal.mason.feop.server.blog.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogController {

    private BlogService blogService;
    private CategoryService categoryService;
    private SeryService seryService;
    private TagService tagService;

    @Autowired
    public void setBlogService(BlogService blogService) {
        this.blogService = blogService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setSeryService(SeryService seryService) {
        this.seryService = seryService;
    }

    @Autowired
    public void setTagService(TagService tagService) {
        this.tagService = tagService;
    }

    /* Public Section of Blog operations */
    @RequestMapping(value = "/blog/view", method = RequestMethod.GET)
    public String viewBlog(@RequestParam("id") Long id, Model model) {
        Blog blog = blogService.findById(id);
        BlogModel blogModel = BlogModel.revert(blog);
        model.addAttribute("blog", blogModel);
        return ViewMapper.Blog_View.getViewName();
    }

    @RequestMapping(value = "/blog/newest")
    public String getNewestBlogs(Pageable pageable, Model model, HttpServletRequest request) {
        Page<Blog> blogs = blogService.findAll(pageable);

        List<BlogModel> models = new ArrayList<>();
        if (blogs != null) {
            for (Blog blog : blogs) {
                BlogModel blogModel = BlogModel.revert(blog);
                models.add(blogModel);
            }
        }

        model.addAttribute("blogs", models);
        model.addAttribute("pager", Pager.getPager(blogs.getNumber(), blogs.getTotalPages(), request));

        return ViewMapper.Index.getViewName();
    }

    @RequestMapping(value = "/blog/search")
    public String searchBlogs(@RequestParam("q") String query, Pageable pageable, Model model, HttpServletRequest request) {

        Page<Blog> blogs = blogService.findAll(pageable);

        List<BlogModel> models = new ArrayList<>();
        if (blogs != null) {
            for (Blog blog : blogs) {
                BlogModel blogModel = BlogModel.revert(blog);
                models.add(blogModel);
            }
        }

        model.addAttribute("blogs", models);
        model.addAttribute("pager", Pager.getPager(blogs.getNumber(), blogs.getTotalPages(), request));

        return ViewMapper.Blog_List.getViewName();
    }

    @RequestMapping(value = {"/blog", "/blog/list"}, method = RequestMethod.GET)
    public String findBlog(Pageable pageable, @RequestParam(value = "c", required = false) Long categoryId,
                           @RequestParam(value = "s", required = false) Long seryId, Model model, HttpServletRequest request) {
        Page<Blog> blogs = null;
        if (categoryId != null) {
            Category cat = categoryService.findById(categoryId);
            blogs = blogService.findByCategory(cat, pageable);
        } else if (seryId != null) {
            Sery sery = seryService.findById(seryId);
            blogs = blogService.findBySery(sery, pageable);
        } else {
            blogs = blogService.findAll(pageable);
        }

        List<BlogModel> models = new ArrayList<>();
        if (blogs != null) {
            for (Blog blog : blogs) {
                BlogModel blogModel = BlogModel.revert(blog);
                models.add(blogModel);
            }
        }

        model.addAttribute("blogs", models);
        model.addAttribute("pager", Pager.getPager(blogs.getNumber(), blogs.getTotalPages(), request));

        return ViewMapper.Blog_List.getViewName();
    }

    /* Private Section of Blog Operations */
    @RequestMapping(value = "/my/blog/create", method = RequestMethod.GET)
    public String createBlog(@ModelAttribute BlogModel blogModel) {
        return ViewMapper.Blog_New.getViewName();
    }

    @RequestMapping(value = "/my/blog/create", method = RequestMethod.POST)
    public String saveBlog(@Validated BlogModel blogModel, BindingResult result, HttpServletRequest request) {
        String uid = AuthenticationUtils.getUid(request);
        if (uid == null) {
            return String.format("redirect:%s", "/user/login");
        }

        if (result.hasErrors()) {
            return null;
        }

        Blog blog = new Blog();
        blog.setCreateDate(TimeUtils.getCurrentTimestamp());
        BlogModel.merge(blog, blogModel);
        blog.setBlogSetting(new BlogSetting());
        if (blogModel.getCategory() != null && blogModel.getCategory().getId() != null) {
            Category category = categoryService.findById(blogModel.getCategory().getId());
            blog.setCategory(category);
        }

        if (blogModel.getSery() != null && blogModel.getSery().getId() != null) {
            Sery sery = seryService.findById(blogModel.getSery().getId());
            blog.setSery(sery);
        }
        if (blogModel.getTagNames() != null) {
            String tagNames = blogModel.getTagNames().trim();
            String[] names = tagNames.split(",\\s*");
            List<Tag> tags = tagService.findOrCreateWithNames(names);
            blog.setTags(tags);
        }

        blog.setAuthorName(AuthenticationUtils.getUserName(request));
        blog.setAuthorUid(AuthenticationUtils.getUid(request));
        blogService.save(blog);

        return "redirect:/blog/view?id=" + blog.getId();
    }

    @RequestMapping(value = "/my/blog/update", method = RequestMethod.GET)
    public String updateBlog(@RequestParam("id") Long id, Model model) {
        Blog blog = blogService.findById(id);

        BlogModel blogModel = BlogModel.revert(blog);
        model.addAttribute("blog", blogModel);
        return ViewMapper.Blog_Edit.getViewName();
    }

    @RequestMapping(value = "/my/blog/update", method = RequestMethod.POST)
    public String updateBlog(@Validated BlogModel blogModel, BindingResult result, HttpServletRequest request, Model model) {
        String uid = AuthenticationUtils.getUid(request);
        if (uid == null) {
            return String.format("redirect:%s", "/user/login");
        }

        if (result.hasErrors()) {
            return ViewMapper.Blog_Edit.getViewName();
        }

        Blog blog = blogService.findById(blogModel.getId());
        BlogModel.merge(blog, blogModel);
        blog.setAuthorName(AuthenticationUtils.getUserName(request));
        blog.setAuthorUid(AuthenticationUtils.getUid(request));
        if (blogModel.getCategory() != null && blogModel.getCategory().getId() != null) {
            Category category = categoryService.findById(blogModel.getCategory().getId());
            blog.setCategory(category);
        }

        if (blogModel.getSery() != null && blogModel.getSery().getId() != null) {
            Sery sery = seryService.findById(blogModel.getSery().getId());
            blog.setSery(sery);
        }
        if (blogModel.getTagNames() != null) {
            String tagNames = blogModel.getTagNames().trim();
            String[] names = tagNames.split(",\\s*");
            List<Tag> tags = tagService.findOrCreateWithNames(names);
            blog.setTags(tags);
        }

        Blog updatedBlog = blogService.update(blog);
        BlogModel updatedBlogModel = BlogModel.revert(updatedBlog);
        model.addAttribute("blog", updatedBlogModel);
        return "redirect:/blog/view?id=" + blog.getId();
    }

    @RequestMapping(value = "/my/blog/delete", method = RequestMethod.GET)
    public String deleteBlog(HttpServletRequest request, @RequestParam("id") Long id) {
        String uid = AuthenticationUtils.getUid(request);
        if (uid != null)
            blogService.delete(id);

        return "redirect:/my/blog/list";
    }

    @RequestMapping(value = {"/my/blog/list"}, method = RequestMethod.GET)
    public String findMyBlog(HttpServletRequest request, Model model, Pageable pageable) {

        String uid = AuthenticationUtils.getUid(request);
        Page<Blog> blogs = blogService.findByAuthorUid(uid, pageable);
        List<BlogModel> models = new ArrayList<>();
        if (blogs != null) {
            for (Blog blog : blogs) {
                BlogModel blogModel = BlogModel.revert(blog);
                models.add(blogModel);
            }
        }

        model.addAttribute("blogs", models);
        model.addAttribute("pager", Pager.getPager(blogs.getNumber(), blogs.getTotalPages(), request));

        return ViewMapper.Blog_My_List.getViewName();
    }

}
