package org.personal.mason.feop.server.blog.domain.service;

import org.personal.mason.feop.server.blog.domain.model.Subscribe;

public interface SubscribeService {

	void save(Subscribe subscribe);

	Subscribe update(Subscribe subscribe);

	Subscribe findById(Long id);

	void delete(Subscribe subscribe);

	void delete(Long id);

	Subscribe findBySubscribe(String uid);
}
