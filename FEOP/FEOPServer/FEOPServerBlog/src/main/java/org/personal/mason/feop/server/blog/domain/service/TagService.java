package org.personal.mason.feop.server.blog.domain.service;

import org.personal.mason.feop.server.blog.domain.model.Tag;

import java.util.List;

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
