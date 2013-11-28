package org.personal.mason.feop.server.blog.domain.service.impl;

import org.personal.mason.feop.server.blog.domain.model.Tag;
import org.personal.mason.feop.server.blog.domain.repository.TagRepository;
import org.personal.mason.feop.server.blog.domain.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class TagServiceImpl implements TagService {

    private TagRepository tagRepository;

    @Autowired
    public void setTagRepository(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    @Transactional
    public void save(Tag tag) {
        tagRepository.save(tag);
    }

    @Override
    @Transactional
    public void findById(Long id) {
        tagRepository.findOne(id);
    }

    @Override
    @Transactional
    public Tag update(Tag tag) {
        return tagRepository.saveAndFlush(tag);
    }

    @Override
    @Transactional
    public void delete(Tag tag) {
        tagRepository.delete(tag);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        tagRepository.delete(id);
    }

    @Override
    @Transactional
    public List<Tag> findByTagNameLike(String tagname) {
        return tagRepository.findByTagNameLike(tagname);
    }

    @Override
    public Tag findByTagName(String tagname) {
        List<Tag> tags = tagRepository.findByTagName(tagname);
        return tags.isEmpty() ? null : tags.get(0);
    }

    @Override
    public List<Tag> findOrCreateWithNames(String[] names) {
        if (names.length > 0) {
            Set<Tag> utags = new HashSet<>();
            for (String name : names) {
                List<Tag> tags = tagRepository.findByTagName(name);
                if (tags.isEmpty()) {
                    Tag tag = new Tag();
                    tag.setTagName(name);
                    tagRepository.save(tag);
                    utags.add(tag);
                } else {
                    utags.addAll(tags);
                }
            }
            return new ArrayList<>(utags);
        }
        return Collections.emptyList();
    }
}
