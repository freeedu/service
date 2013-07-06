package org.personal.mason.feop.oauth.service.spi.impl;

import org.personal.mason.feop.oauth.service.dao.OauthRefreshTokenDao;
import org.personal.mason.feop.oauth.service.domain.OauthRefreshToken;
import org.personal.mason.feop.oauth.service.spi.ORefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ORefreshTokenServiceImpl implements ORefreshTokenService {

	private OauthRefreshTokenDao refreshTokenDao;

	@Autowired
	public void setRefreshTokenDao(OauthRefreshTokenDao refreshTokenDao) {
		if (refreshTokenDao == null) {
			throw new IllegalArgumentException("refresh token dao cannot be null");
		}
		this.refreshTokenDao = refreshTokenDao;
	}

	@Override
	@Transactional
	public void saveObject(OauthRefreshToken token) {
		refreshTokenDao.saveObject(token);
	}

	@Override
	@Transactional
	public OauthRefreshToken findRefreshTokenWithTokenId(String tokenId) {
		return refreshTokenDao.findRefreshTokenWithTokenId(tokenId);
	}

	@Override
	@Transactional
	public void removeRefreshTokenByTokenId(String tokenId) {
		refreshTokenDao.removeRefreshTokenByTokenId(tokenId);
	}

}
