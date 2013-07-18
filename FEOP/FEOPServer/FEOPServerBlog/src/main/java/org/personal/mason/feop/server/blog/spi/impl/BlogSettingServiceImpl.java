package org.personal.mason.feop.server.blog.spi.impl;

import org.personal.mason.feop.server.blog.domain.BlogSetting;
import org.personal.mason.feop.server.blog.repository.BlogSettingRepository;
import org.personal.mason.feop.server.blog.spi.BlogSettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BlogSettingServiceImpl implements BlogSettingService {

	private BlogSettingRepository blogSettingRepository;

	@Autowired
	public void setBlogSettingRepository(BlogSettingRepository blogSettingRepository) {
		this.blogSettingRepository = blogSettingRepository;
	}

	@Override
	@Transactional
	public BlogSetting findById(Long id) {
		return blogSettingRepository.findOne(id);
	}

	@Override
	@Transactional
	public BlogSetting update(BlogSetting setting) {
		return blogSettingRepository.saveAndFlush(setting);
	}

	@Override
	@Transactional
	public void delete(BlogSetting setting) {
		blogSettingRepository.delete(setting);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		blogSettingRepository.delete(id);
	}
}
