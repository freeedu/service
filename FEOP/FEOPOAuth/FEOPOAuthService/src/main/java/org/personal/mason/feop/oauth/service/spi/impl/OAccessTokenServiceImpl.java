package org.personal.mason.feop.oauth.service.spi.impl;

import java.util.List;

import org.personal.mason.feop.oauth.service.dao.OauthAccessTokenDao;
import org.personal.mason.feop.oauth.service.domain.OauthAccessToken;
import org.personal.mason.feop.oauth.service.spi.OAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OAccessTokenServiceImpl implements OAccessTokenService {

	private OauthAccessTokenDao accessTokenDao;

	@Autowired
	public void setAccessTokenDao(OauthAccessTokenDao accessTokenDao) {
		if (accessTokenDao == null) {
			throw new IllegalArgumentException("access token dao cannot be null");
		}
		this.accessTokenDao = accessTokenDao;
	}

	@Override
	public OauthAccessToken findAccessTokenWithTokenId(String tokenId) {
		return accessTokenDao.findAccessTokenWithTokenId(tokenId);
	}

	@Override
	@Transactional
	public void saveObject(OauthAccessToken accessToken) {
		accessTokenDao.saveObject(accessToken);
	}

	@Override
	@Transactional
	public void removeAccessTokenByTokenId(String tokenId) {
		accessTokenDao.removeAccessTokenByTokenId(tokenId);
	}

	@Override
	@Transactional
	public void removeAccessTokenByRefreshToken(String refreshToken) {
		accessTokenDao.removeAccessTokenByRefreshToken(refreshToken);
	}

	@Override
	@Transactional
	public OauthAccessToken findAccessTokenWithAuthenticationId(String key) {
		return accessTokenDao.findAccessTokenWithAuthenticationId(key);
	}

	@Override
	@Transactional
	public List<OauthAccessToken> findAccessTokenWithUsername(String userName) {
		return accessTokenDao.findAccessTokenWithUsername(userName);
	}

	@Override
	@Transactional
	public List<OauthAccessToken> findAccessTokenWithClientId(String clientId) {
		return accessTokenDao.findAccessTokenWithClientId(clientId);
	}

}
