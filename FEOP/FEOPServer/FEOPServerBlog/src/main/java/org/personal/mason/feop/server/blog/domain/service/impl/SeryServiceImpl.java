package org.personal.mason.feop.server.blog.domain.service.impl;

import java.util.List;

import org.personal.mason.feop.server.blog.domain.model.Category;
import org.personal.mason.feop.server.blog.domain.model.Sery;
import org.personal.mason.feop.server.blog.domain.repository.SeryRepository;
import org.personal.mason.feop.server.blog.domain.service.SeryService;
import org.personal.mason.feop.server.blog.utils.PageableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class SeryServiceImpl implements SeryService {

	private SeryRepository seryRepository;

	@Autowired
	public void setSeryRepository(SeryRepository seryRepository) {
		this.seryRepository = seryRepository;
	}

	@Override
	@Transactional
	public void save(Sery sery) {
		seryRepository.save(sery);
	}

	@Override
	@Transactional
	public Sery update(Sery sery) {
		return seryRepository.saveAndFlush(sery);
	}

	@Override
	@Transactional
	public Sery findById(Long id) {
		return seryRepository.findOne(id);
	}

	@Override
	@Transactional
	public void delete(Sery sery) {
		seryRepository.delete(sery);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		seryRepository.delete(id);
	}

	@Override
	@Transactional
	public List<Sery> findByCategory(Category category) {
		return seryRepository.findByCategory(category);
	}

	@Override
	@Transactional
	public List<Sery> findByCategory(Category category, int page, int size) {
		return seryRepository.findByCategory(category, PageableUtils.getPageable(page, size));
	}

	@Override
	@Transactional
	public List<Sery> findByCategory(Category category, Sort sort) {
		return seryRepository.findByCategory(category, sort);
	}

	@Override
	public List<Sery> findByName(String name, int page, int size) {
		return seryRepository.findBySeriesNameLike(name, PageableUtils.getPageable(page, size));
	}
}
