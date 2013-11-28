package org.personal.mason.feop.server.blog.domain.service;

import org.personal.mason.feop.server.blog.domain.model.Subscribe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubscribeService {

    void save(Subscribe subscribe);

    Subscribe update(Subscribe subscribe);

    Subscribe findById(Long id);

    void delete(Subscribe subscribe);

    void delete(Long id);

    Subscribe findBySubscribe(String uid);

    List<Subscribe> findAll();

    Page<Subscribe> findAll(Pageable pageable);
}
