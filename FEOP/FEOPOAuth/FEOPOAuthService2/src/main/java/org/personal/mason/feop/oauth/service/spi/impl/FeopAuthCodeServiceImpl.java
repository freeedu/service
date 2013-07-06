package org.personal.mason.feop.oauth.service.spi.impl;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.OauthCode;
import org.personal.mason.feop.oauth.service.repository.OauthCodeRepository;
import org.personal.mason.feop.oauth.service.spi.FeopAuthCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeopAuthCodeServiceImpl implements FeopAuthCodeService {

	private OauthCodeRepository oauthCodeRepository;

	@Autowired
	public void setOauthCodeRepository(OauthCodeRepository oauthCodeRepository) {
		this.oauthCodeRepository = oauthCodeRepository;
	}

	@Override
	@Transactional
	public void save(OauthCode oauthCode) {
		oauthCodeRepository.save(oauthCode);
	}

	@Override
	@Transactional
	public OauthCode findOauthCodeByCode(String code) {
		List<OauthCode> codes = oauthCodeRepository.findByCode(code);
		return codes.isEmpty() ? null : codes.get(0);
	}

	@Override
	@Transactional
	public void delete(OauthCode oauthCode) {
		oauthCodeRepository.delete(oauthCode);
	}

}
