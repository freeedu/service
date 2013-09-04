package org.personal.mason.feop.server.blog.mvc.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.personal.mason.feop.server.blog.domain.model.Blog;
import org.personal.mason.feop.server.blog.domain.model.BlogSection;
import org.personal.mason.feop.server.blog.domain.model.Comment;
import org.personal.mason.feop.server.blog.domain.service.BlogSectionService;
import org.personal.mason.feop.server.blog.domain.service.BlogService;
import org.personal.mason.feop.server.blog.domain.service.CommentService;
import org.personal.mason.feop.server.blog.mvc.model.CommentModel;
import org.personal.mason.feop.server.blog.mvc.utils.Pager;
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

	@RequestMapping(value = "/comment/list", method = RequestMethod.GET)
	public String findComments(@RequestParam(value = "b", required = false) Long blogId, @RequestParam(value = "s", required = false) Long sectionId,
			@RequestParam(value = "c", required = false) Long commentId, Pageable pageable, Model model, HttpServletRequest request) {
		Page<Comment> comments = null;

		if (blogId != null) {
			Blog blog = blogService.findById(blogId);
			comments = commentService.findByBlog(blog, pageable);
		} else if (sectionId != null) {
			BlogSection section = blogSectionService.findById(sectionId);
			comments = commentService.findByBlogSection(section, pageable);
		} else if (commentId != null) {
			Comment pc = commentService.findById(commentId);
			comments = commentService.findByComment(pc, pageable);
		}

		List<CommentModel> models = new ArrayList<>();

		if (comments != null) {
			for (Comment comment : comments) {
				CommentModel cm = CommentModel.revert(comment);
				models.add(cm);
			}

			model.addAttribute("pager", Pager.getPager(comments.getNumber(), comments.getTotalPages(), request));

		}
		model.addAttribute("comments", models);

		return "app.blog.comment.list";
	}
}