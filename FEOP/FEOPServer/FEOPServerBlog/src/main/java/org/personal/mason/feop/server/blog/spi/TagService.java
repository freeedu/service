package org.personal.mason.feop.server.blog.spi;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.Tag;

public interface TagService {

	void save(Tag tag);

	void findById(Long id);

	Tag update(Tag tag);

	void delete(Tag tag);

	void delete(Long id);

	List<Tag> findByTagNameLike(String tagname);

	Tag findByTagName(String tagname);
}
