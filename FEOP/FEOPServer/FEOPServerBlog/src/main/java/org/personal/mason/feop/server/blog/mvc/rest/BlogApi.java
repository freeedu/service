package org.personal.mason.feop.server.blog.mvc.rest;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSetting;
import org.personal.mason.feop.server.blog.domain.model.Category;
import org.personal.mason.feop.server.blog.domain.model.Sery;
import org.personal.mason.feop.server.blog.domain.service.BlogService;
import org.personal.mason.feop.server.blog.domain.service.CategoryService;
import org.personal.mason.feop.server.blog.domain.service.SeryService;
import org.personal.mason.feop.server.blog.mvc.model.BlogModel;
import org.personal.mason.feop.server.blog.mvc.utils.AuthenticationUtils;
import org.personal.mason.feop.server.blog.utils.TimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping(value = {"api"})
public class BlogApi {
    private BlogService blogService;
    private CategoryService categoryService;
    private SeryService seryService;

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

	/* Public Section of Blog operations */

    /**
     * @param id
     * @return
     */
    @RequestMapping(value = "/blog", method = RequestMethod.GET)
    @ResponseBody
    public BlogModel viewBlog(@RequestParam("id") Long id) {
        Blog blog = blogService.findById(id);
        BlogModel model = BlogModel.revert(blog);
        return model;
    }

    /**
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/blog/newest")
    @ResponseBody
    public Page<BlogModel> getNewestBlogs(Pageable pageable) {
        Page<Blog> blogs = blogService.findAll(pageable);
        List<BlogModel> modelList = Collections.emptyList();

        for (Blog blog : blogs) {
            BlogModel model = BlogModel.revert(blog);
            modelList.add(model);
        }
        return new PageImpl<>(modelList, pageable, blogs.getTotalElements());

    }

    /**
     * @param query
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/blog/search")
    @ResponseBody
    public List<BlogModel> searchBlogs(@RequestParam("q") String query, Pageable pageable) {

        Page<Blog> blogs = blogService.findAll(pageable);
        List<BlogModel> models = new ArrayList<>();

        for (Blog blog : blogs) {
            BlogModel model = BlogModel.revert(blog);
            models.add(model);
        }
        return models;
    }

    /**
     * @param page
     * @param size
     * @param categoryId
     * @param seryId
     * @return
     */
    @RequestMapping(value = {"/blog/list"}, method = RequestMethod.GET)
    @ResponseBody
    public List<BlogModel> findBlog(Pageable pageable, @RequestParam(value = "c", required = false) Long categoryId,
                                    @RequestParam(value = "s", required = false) Long seryId) {

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

        for (Blog blog : blogs) {
            BlogModel model = BlogModel.revert(blog);
            models.add(model);
        }
        return models;
    }

    /**
     * @param request
     * @param model
     * @return
     */
    @RequestMapping(value = "/my/blog/create", method = RequestMethod.POST)
    @ResponseBody
    public BlogModel saveBlog(HttpServletRequest request, BlogModel model) {
        String uid = AuthenticationUtils.getUid(request);
        if (uid == null) {
            return null;
        }

        Blog blog = new Blog();
        blog.setCreateDate(TimeUtils.getCurrentTimestamp());
        BlogModel.merge(blog, model);
        blog.setBlogSetting(new BlogSetting());
        if (model.getCategory() != null) {
            Category category = categoryService.findById(model.getCategory().getId());
            blog.setCategory(category);
        }

        if (model.getSery() != null) {
            Sery sery = seryService.findById(model.getSery().getId());
            blog.setSery(sery);
        }

        blog.setAuthorName(AuthenticationUtils.getUserName(request));
        blog.setAuthorUid(AuthenticationUtils.getUid(request));
        blogService.save(blog);

        return BlogModel.revert(blog);
    }

    /**
     * @param blogModel
     * @param request
     * @return
     */
    @RequestMapping(value = "/my/blog/update", method = RequestMethod.PUT)
    @ResponseBody
    public BlogModel updateBlog(BlogModel blogModel, HttpServletRequest request) {
        String uid = AuthenticationUtils.getUid(request);
        if (uid == null) {
            return null;
        }

        Blog blog = new Blog();
        BlogModel.merge(blog, blogModel);
        blog.setAuthorName(AuthenticationUtils.getUserName(request));
        blog.setAuthorUid(AuthenticationUtils.getUid(request));
        Blog updatedBlog = blogService.update(blog);
        BlogModel updatedBlogModel = BlogModel.revert(updatedBlog);
        return updatedBlogModel;
    }

    /**
     * @param request
     * @param id
     */
    @RequestMapping(value = "/my/blog/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteBlog(HttpServletRequest request, @RequestParam("id") Long id) {
        String uid = AuthenticationUtils.getUid(request);
        if (uid != null)
            blogService.delete(id);
    }

    /**
     * @param request
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = {"/my/blog/list"}, method = RequestMethod.GET)
    @ResponseBody
    public List<BlogModel> findMyBlog(HttpServletRequest request, Pageable pageable) {

        String uid = AuthenticationUtils.getUid(request);
        Page<Blog> blogs = blogService.findByAuthorUid(uid, pageable);
        List<BlogModel> models = new ArrayList<>();

        for (Blog blog : blogs) {
            BlogModel model = BlogModel.revert(blog);
            models.add(model);
        }
        return models;
    }
}
