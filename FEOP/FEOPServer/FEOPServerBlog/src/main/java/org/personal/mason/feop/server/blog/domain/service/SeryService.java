package org.personal.mason.feop.server.blog.domain.service;

import org.personal.mason.feop.server.blog.domain.model.Category;
import org.personal.mason.feop.server.blog.domain.model.Sery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface SeryService {

    void save(Sery sery);

    Sery update(Sery sery);

    Sery findById(Long id);

    void delete(Sery sery);

    void delete(Long id);

    List<Sery> findByCategory(Category category);

    Page<Sery> findByCategory(Category category, Pageable pageable);

    List<Sery> findByCategory(Category category, Sort sort);

    List<Sery> findByName(String name);

    Page<Sery> findByName(String name, Pageable pageable);
}
