package org.personal.mason.feop.oauth.service.domain.service.oauth.impl;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthRefreshToken;
import org.personal.mason.feop.oauth.service.domain.repository.oauth.OauthRefreshTokenRepository;
import org.personal.mason.feop.oauth.service.domain.service.oauth.FeopRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeopRefreshTokenServiceImpl implements FeopRefreshTokenService {

    private OauthRefreshTokenRepository oauthRefreshTokenRepository;

    @Autowired
    public void setOauthRefreshTokenRepository(OauthRefreshTokenRepository oauthRefreshTokenRepository) {
        this.oauthRefreshTokenRepository = oauthRefreshTokenRepository;
    }

    @Override
    @Transactional
    public void saveRefreshToken(OauthRefreshToken token) {
        oauthRefreshTokenRepository.save(token);
    }

    @Override
    @Transactional
    public OauthRefreshToken findRefreshTokenWithTokenId(String tokenId) {
        List<OauthRefreshToken> tokens = oauthRefreshTokenRepository.findByTokenId(tokenId);
        return tokens.isEmpty() ? null : tokens.get(0);
    }

    @Override
    @Transactional
    public void removeRefreshTokenByTokenId(String tokenId) {
        List<OauthRefreshToken> tokens = oauthRefreshTokenRepository.findByTokenId(tokenId);
        oauthRefreshTokenRepository.deleteInBatch(tokens);
    }

}
