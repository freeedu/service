package org.personal.mason.feop.server.blog.mvc.rest;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("api")
public class CommonApi {
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

	/**
	 * 
	 * @param commentModel
	 * @return
	 */
	@RequestMapping(value = "/comment/save", method = RequestMethod.POST)
	@ResponseBody
	public CommentModel saveComment(CommentModel commentModel) {
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

		return CommentModel.revert(comment);
	}

	/**
	 * 
	 * @param blogId
	 * @param sectionId
	 * @param commentId
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/comment/list", method = RequestMethod.GET)
	@ResponseBody
	public List<CommentModel> findCommonts(@RequestParam(value = "bid", required = false) Long blogId,
			@RequestParam(value = "sid", required = false) Long sectionId, @RequestParam(value = "cid", required = false) Long commentId,
			Pageable pageable) {

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
				CommentModel model = CommentModel.revert(comment);
				models.add(model);
			}
		}
		return models;
	}
}
