package org.personal.mason.feop.server.blog.domain.service;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Tag;

public interface TagService {

	void save(Tag tag);

	void findById(Long id);

	Tag update(Tag tag);

	void delete(Tag tag);

	void delete(Long id);

	List<Tag> findByTagNameLike(String tagname);

	Tag findByTagName(String tagname);

	List<Tag> findOrCreateWithNames(String[] names);
}
