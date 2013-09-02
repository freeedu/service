package org.personal.mason.feop.server.blog.mvc.controllers;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSection;
import org.personal.mason.feop.server.blog.domain.model.Comment;
import org.personal.mason.feop.server.blog.domain.service.BlogSectionService;
import org.personal.mason.feop.server.blog.domain.service.BlogService;
import org.personal.mason.feop.server.blog.domain.service.CommentService;
import org.personal.mason.feop.server.blog.mvc.model.CommentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public String createComment(@RequestParam(value = "b", required = false) Long blogId,
			@RequestParam(value = "s", required = false) Long sectionId, @RequestParam(value = "c", required = false) Long commentId, Model model) {
		CommentModel comment = new CommentModel();

		if (blogId != null) {
			comment.setBlogId(blogId);
		}
		if (sectionId != null) {
			comment.setBlogSectionId(sectionId);
		}
		if (commentId != null) {
			comment.setCommentId(commentId);
		}

		model.addAttribute("newcomment", comment);
		return "app.blog.comment.new";
	}

	@RequestMapping(value = "/comment/save", method = RequestMethod.POST)
	public String saveComment(@Validated CommentModel commentModel, BindingResult result) {
		if (result.hasErrors()) {
			return null;
		}

		if (commentModel.getBlogId() == null && commentModel.getBlogSectionId() == null && commentModel.getCommentId() == null) {
			result.rejectValue("*", "comment.create.invalid", "comment should at blog, blogsection or comment");
			return null;
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
		StringBuilder builder = new StringBuilder();
		builder.append("redirect:%s?id=%d");

		return String.format(builder.toString(), "/blog/view", comment.getBlog().getId());
	}

	@RequestMapping(value = "/comment/b/list", method = RequestMethod.GET)
	public void findBlogComments(@RequestParam("bid") Long blogId, Pageable pageable, Model model) {
		Blog blog = blogService.findById(blogId);
		Page<Comment> comments = commentService.findByBlog(blog, pageable);
		Long count = commentService.count(blog);
		model.addAttribute("comments", comments);
		model.addAttribute("count", count);
		model.addAttribute("cpage", pageable.getPageNumber());
	}

	@RequestMapping(value = "/comment/s/list", method = RequestMethod.GET)
	public void findBlogSectionComments(@RequestParam("sid") Long sectionId, Pageable pageable, Model model) {
		BlogSection section = blogSectionService.findById(sectionId);
		Page<Comment> comments = commentService.findByBlogSection(section, pageable);
		Long count = commentService.count(section);
		model.addAttribute("comments", comments);
		model.addAttribute("count", count);
		model.addAttribute("cpage", pageable.getPageNumber());
	}

	@RequestMapping(value = "/comment/c/list", method = RequestMethod.GET)
	public void findCommentComments(@RequestParam("cid") Long commentId, Pageable pageable, Model model) {
		Comment pc = commentService.findById(commentId);
		Page<Comment> comments = commentService.findByComment(pc, pageable);
		Long count = commentService.count(pc);
		model.addAttribute("comments", comments);
		model.addAttribute("count", count);
		model.addAttribute("cpage", pageable.getPageNumber());
	}
}