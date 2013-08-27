package org.personal.mason.feop.server.blog.mvc.controllers;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSection;
import org.personal.mason.feop.server.blog.domain.model.Comment;
import org.personal.mason.feop.server.blog.domain.service.BlogSectionService;
import org.personal.mason.feop.server.blog.domain.service.BlogService;
import org.personal.mason.feop.server.blog.domain.service.CommentService;
import org.personal.mason.feop.server.blog.mvc.model.CommentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

	private CommentService commentService;
	private BlogService blogService;
	private BlogSectionService blogSectionService;

	@Autowired
	public void setcommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	@Autowired
	public void setBlogSectionService(BlogSectionService blogSectionService) {
		this.blogSectionService = blogSectionService;
	}

	@Autowired
	public void setBlogService(BlogService blogService) {
		this.blogService = blogService;
	}

	@RequestMapping(value = "/comment/create", method = RequestMethod.GET)
	public void createComment(Model model) {
		model.addAttribute("newcomment", new CommentModel());
	}

	@RequestMapping(value = "/comment/save", method = RequestMethod.POST)
	public void saveComment(@Validated CommentModel commentModel, BindingResult result) {
		if (result.hasErrors()) {
			return;
		}

		if (commentModel.getBlogId() == null && commentModel.getBlogSectionId() == null && commentModel.getCommentId() == null) {
			result.rejectValue("*", "comment.create.invalid", "comment should at blog, blogsection or comment");
			return;
		}

		Comment comment = CommentModel.convert(commentModel);
		if (commentModel.getBlogId() != null) {
			Blog blog = blogService.findById(commentModel.getBlogId());
			comment.setBlog(blog);
		}

		if (commentModel.getBlogSectionId() != null) {
			BlogSection section = blogSectionService.findById(commentModel.getBlogSectionId());
			comment.setBlogSection(section);
			comment.setBlog(section.getBlog());
		}

		if (commentModel.getCommentId() != null) {
			Comment pc = commentService.findById(commentModel.getCommentId());
			comment.setComment(pc);
			comment.setBlogSection(pc.getBlogSection());
			comment.setBlog(pc.getBlog());
		}

		commentService.save(comment);
	}

	@RequestMapping(value = "/comment/b/list", method = RequestMethod.GET)
	public void findBlogComments(@RequestParam("bid") Long blogId, @RequestParam("p") Integer page, @RequestParam("s") Integer size, Model model) {
		Blog blog = blogService.findById(blogId);
		List<Comment> comments = commentService.findByBlog(blog, page, size);
		Long count = commentService.count(blog);
		model.addAttribute("comments", comments);
		model.addAttribute("count", count);
		model.addAttribute("cpage", page);
	}

	@RequestMapping(value = "/comment/s/list", method = RequestMethod.GET)
	public void findBlogSectionComments(@RequestParam("sid") Long sectionId, @RequestParam("p") Integer page, @RequestParam("s") Integer size,
			Model model) {
		BlogSection section = blogSectionService.findById(sectionId);
		List<Comment> comments = commentService.findByBlogSection(section, page, size);
		Long count = commentService.count(section);
		model.addAttribute("comments", comments);
		model.addAttribute("count", count);
		model.addAttribute("cpage", page);
	}

	@RequestMapping(value = "/comment/c/list", method = RequestMethod.GET)
	public void findCommentComments(@RequestParam("cid") Long commentId, @RequestParam("p") Integer page, @RequestParam("s") Integer size, Model model) {
		Comment pc = commentService.findById(commentId);
		List<Comment> comments = commentService.findByComment(pc, page, size);
		Long count = commentService.count(pc);
		model.addAttribute("comments", comments);
		model.addAttribute("count", count);
		model.addAttribute("cpage", page);
	}
}