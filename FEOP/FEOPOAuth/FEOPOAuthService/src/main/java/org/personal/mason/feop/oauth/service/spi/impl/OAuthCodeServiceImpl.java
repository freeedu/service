package org.personal.mason.feop.oauth.service.spi.impl;

import org.personal.mason.feop.oauth.service.dao.OauthCodeDao;
import org.personal.mason.feop.oauth.service.domain.OauthCode;
import org.personal.mason.feop.oauth.service.spi.OAuthCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OAuthCodeServiceImpl implements OAuthCodeService {

	private OauthCodeDao codeDao;

	@Autowired
	public void setCodeDao(OauthCodeDao codeDao) {
		if (codeDao == null) {
			throw new IllegalArgumentException("code detail dao cannot be null");
		}
		this.codeDao = codeDao;
	}

	@Override
	@Transactional
	public void save(OauthCode oauthCode) {
		codeDao.saveObject(oauthCode);
	}

	@Override
	@Transactional
	public OauthCode findOauthCodeByCode(String code) {
		return codeDao.findOauthCodeByCode(code);
	}

	@Override
	@Transactional
	public void delete(OauthCode oauthCode) {
		codeDao.removeObject(oauthCode);
	}

}
