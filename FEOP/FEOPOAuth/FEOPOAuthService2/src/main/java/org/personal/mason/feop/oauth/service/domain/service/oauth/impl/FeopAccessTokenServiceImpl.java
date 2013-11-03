package org.personal.mason.feop.oauth.service.domain.service.oauth.impl;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthAccessToken;
import org.personal.mason.feop.oauth.service.domain.repository.oauth.OauthAccessTokenRepository;
import org.personal.mason.feop.oauth.service.domain.service.oauth.FeopAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeopAccessTokenServiceImpl implements FeopAccessTokenService {

	private OauthAccessTokenRepository oauthAccessTokenRepository;

	@Autowired
	public void setOauthAccessTokenRepository(OauthAccessTokenRepository oauthAccessTokenRepository) {
		this.oauthAccessTokenRepository = oauthAccessTokenRepository;
	}

	@Override
	public OauthAccessToken findAccessTokenWithTokenId(String tokenId) {
		List<OauthAccessToken> tokens = oauthAccessTokenRepository.findByTokenId(tokenId);
		return tokens.isEmpty() ? null : tokens.get(0);
	}

	@Override
	@Transactional
	public void saveObject(OauthAccessToken accessToken) {
		oauthAccessTokenRepository.save(accessToken);
	}

	@Override
	@Transactional
	public void removeAccessTokenByTokenId(String tokenId) {
		List<OauthAccessToken> tokens = oauthAccessTokenRepository.findByTokenId(tokenId);
		oauthAccessTokenRepository.delete(tokens);
	}

	@Override
	@Transactional
	public void removeAccessTokenByRefreshToken(String refreshToken) {
		List<OauthAccessToken> tokens = oauthAccessTokenRepository.findByRefreshToken(refreshToken);
		oauthAccessTokenRepository.delete(tokens);
	}

	@Override
	@Transactional
	public OauthAccessToken findAccessTokenWithAuthenticationId(String key) {
		List<OauthAccessToken> tokens = oauthAccessTokenRepository.findByAuthenticationId(key);
		return tokens.isEmpty() ? null : tokens.get(0);
	}

	@Override
	@Transactional
	public List<OauthAccessToken> findAccessTokenWithUsername(String userName) {
		return oauthAccessTokenRepository.findByUserName(userName);
	}

	@Override
	@Transactional
	public List<OauthAccessToken> findAccessTokenWithClientId(String clientId) {
		return oauthAccessTokenRepository.findByClientId(clientId);
	}

}
