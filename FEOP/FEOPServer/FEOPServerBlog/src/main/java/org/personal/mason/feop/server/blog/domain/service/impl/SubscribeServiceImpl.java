package org.personal.mason.feop.server.blog.domain.service.impl;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Subscribe;
import org.personal.mason.feop.server.blog.domain.repository.SubscribeRepository;
import org.personal.mason.feop.server.blog.domain.service.SubscribeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubscribeServiceImpl implements SubscribeService {

	private SubscribeRepository subscribeRepository;

	@Autowired
	public void setSubscribeRepository(SubscribeRepository subscribeRepository) {
		this.subscribeRepository = subscribeRepository;
	}

	@Override
	@Transactional
	public void save(Subscribe subscribe) {
		subscribeRepository.save(subscribe);
	}

	@Override
	@Transactional
	public Subscribe update(Subscribe subscribe) {
		return subscribeRepository.saveAndFlush(subscribe);
	}

	@Override
	@Transactional
	public Subscribe findById(Long id) {
		return subscribeRepository.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Subscribe subscribe) {
		subscribeRepository.delete(subscribe);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		subscribeRepository.delete(id);
	}

	@Override
	@Transactional
	public Subscribe findBySubscribe(String uid) {
		List<Subscribe> subscribes = subscribeRepository.findBySubscriberUid(uid);
		return subscribes.isEmpty() ? null : subscribes.get(0);
	}

	@Override
	public List<Subscribe> findAll() {
		return subscribeRepository.findAll();
	}

	@Override
	public Page<Subscribe> findAll(Pageable pageable) {
		return subscribeRepository.findAll(pageable);
	}
}
