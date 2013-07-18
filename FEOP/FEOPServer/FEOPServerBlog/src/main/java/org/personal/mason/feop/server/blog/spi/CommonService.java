package org.personal.mason.feop.server.blog.spi;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.Blog;
import org.personal.mason.feop.server.blog.domain.BlogSection;
import org.personal.mason.feop.server.blog.domain.Common;
import org.springframework.data.domain.Sort;

public interface CommonService {

	void save(Common common);

	Common findById(Long id);

	void delete(Common common);

	void delete(Long id);

	List<Common> findByBlog(Blog blog);

	List<Common> findByBlog(Blog blog, int page, int size);

	List<Common> findByBlog(Blog blog, Sort sort);

	List<Common> findByBlogSection(BlogSection blogsection);

	List<Common> findByBlogSection(BlogSection blogsection, int page, int size);

	List<Common> findByBlogSection(BlogSection blogsection, Sort sort);

	List<Common> findByAuthor(String author);

	List<Common> findByAuthor(String author, int page, int size);

	List<Common> findByAuthor(String author, Sort sort);

	List<Common> findByCommon(Common common);

	List<Common> findByCommon(Common common, int page, int size);

	List<Common> findByCommon(Common common, Sort sort);

	Long count(Blog blog);

	Long count(BlogSection section);

	Long count(Common pc);

}
