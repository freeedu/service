package org.personal.mason.feop.oauth.service.common.oauth2.extention;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthAccessToken;
import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthRefreshToken;
import org.personal.mason.feop.oauth.service.domain.service.oauth.FeopAccessTokenService;
import org.personal.mason.feop.oauth.service.domain.service.oauth.FeopRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/1/13
 * Time: 7:46 PM
 * To change this template use File | Settings | File Templates.
 */
public class FOEPTokenStore implements TokenStore {
    private static final Log LOG = LogFactory.getLog(FOEPTokenStore.class);

    private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

    private FeopAccessTokenService feopAccessTokenService;
    private FeopRefreshTokenService feopRefreshTokenService;

    @Autowired
    public void setFeopAccessTokenService(FeopAccessTokenService feopAccessTokenService) {
        this.feopAccessTokenService = feopAccessTokenService;
    }

    @Autowired
    public void setFeopRefreshTokenService(FeopRefreshTokenService feopRefreshTokenService) {
        this.feopRefreshTokenService = feopRefreshTokenService;
    }

    public void setAuthenticationKeyGenerator(AuthenticationKeyGenerator authenticationKeyGenerator) {
        this.authenticationKeyGenerator = authenticationKeyGenerator;
    }

    @Override
    public OAuth2Authentication readAuthentication(OAuth2AccessToken token) {
        return readAuthentication(token.getValue());
    }

    @Override
    public OAuth2Authentication readAuthentication(String token) {
        OAuth2Authentication authentication = null;

        try {
            OauthAccessToken oauthAccessToken = feopAccessTokenService.findAccessTokenWithTokenId(extractTokenKey(token));
            if (oauthAccessToken == null) {
                throw new EmptyResultDataAccessException(1);
            }

            authentication = SerializationUtils.deserialize(oauthAccessToken.getAuthentication());
        } catch (EmptyResultDataAccessException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find access token for token " + token);
            }
        } catch (IllegalArgumentException e) {
            LOG.warn("Failed to deserialize authentication for " + token);
            removeAccessToken(token);
        }

        return authentication;

    }

    @Override
    public void storeAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        OauthAccessToken accessToken = toOauthAccessToken(token, authentication);

        feopAccessTokenService.deleteWithAuthenticationId(accessToken.getAuthenticationId());
        feopAccessTokenService.deleteWithTokenId(accessToken.getTokenId());

        feopAccessTokenService.saveAccessToken(accessToken);

    }

    @Override
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        OAuth2AccessToken accessToken = null;

        try {
            String tokenId = extractTokenKey(tokenValue);
            OauthAccessToken oauthAccessToken = feopAccessTokenService.findAccessTokenWithTokenId(tokenId);

            if (oauthAccessToken == null) {
                throw new EmptyResultDataAccessException(1);
            }

            accessToken = SerializationUtils.deserialize(oauthAccessToken.getToken());
        } catch (EmptyResultDataAccessException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find access token for token " + tokenValue);
            }
        } catch (IllegalArgumentException e) {
            LOG.warn("Failed to deserialize access token for " + tokenValue);
            removeAccessToken(tokenValue);
        }

        return accessToken;

    }

    @Override
    public void removeAccessToken(OAuth2AccessToken token) {
        removeAccessToken(token.getValue());
    }

    public void removeAccessToken(String tokenValue) {
        String tokenId = extractTokenKey(tokenValue);
        feopAccessTokenService.deleteWithTokenId(tokenId);
    }

    protected String extractTokenKey(String value) {
        if (value == null) {
            return null;
        }
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MD5 algorithm not available.  Fatal (should be in the JDK).");
        }

        try {
            byte[] bytes = digest.digest(value.getBytes("UTF-8"));
            return String.format("%032x", new BigInteger(1, bytes));
        } catch (UnsupportedEncodingException e) {
            throw new IllegalStateException("UTF-8 encoding not available.  Fatal (should be in the JDK).");
        }
    }

    @Override
    public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
        OauthRefreshToken oauthRefreshToken = new OauthRefreshToken();
        oauthRefreshToken.setTokenId(extractTokenKey(refreshToken.getValue()));
        oauthRefreshToken.setToken(SerializationUtils.serialize(refreshToken));
        oauthRefreshToken.setAuthentication(SerializationUtils.serialize(authentication));
        feopRefreshTokenService.saveRefreshToken(oauthRefreshToken);
    }

    @Override
    public OAuth2RefreshToken readRefreshToken(String token) {
        OAuth2RefreshToken refreshToken = null;

        try {
            String tokenId = extractTokenKey(token);
            OauthRefreshToken oauthRefreshToken = feopRefreshTokenService.findRefreshTokenWithTokenId(tokenId);
            if (oauthRefreshToken == null) {
                throw new EmptyResultDataAccessException(1);
            }

            refreshToken = SerializationUtils.deserialize(oauthRefreshToken.getToken());
        } catch (EmptyResultDataAccessException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find refresh token for token " + token);
            }
        } catch (IllegalArgumentException e) {
            LOG.warn("Failed to deserialize refresh token for token " + token);
            removeRefreshToken(token);
        }

        return refreshToken;
    }

    @Override
    public OAuth2Authentication readAuthenticationForRefreshToken(OAuth2RefreshToken token) {
        return readAuthenticationForRefreshToken(token.getValue());
    }

    public OAuth2Authentication readAuthenticationForRefreshToken(String value) {
        OAuth2Authentication authentication = null;

        try {
            String tokenId = extractTokenKey(value);
            OauthRefreshToken oauthRefreshToken = feopRefreshTokenService.findRefreshTokenWithTokenId(tokenId);
            if(oauthRefreshToken == null){
                throw new EmptyResultDataAccessException(1);
            }

            authentication = SerializationUtils.deserialize(oauthRefreshToken.getAuthentication());
        }
        catch (EmptyResultDataAccessException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find access token for token " + value);
            }
        }
        catch (IllegalArgumentException e) {
            LOG.warn("Failed to deserialize access token for " + value);
            removeRefreshToken(value);
        }

        return authentication;
    }

    @Override
    public void removeRefreshToken(OAuth2RefreshToken token) {
        removeRefreshToken(token.getValue());
    }

    public void removeRefreshToken(String token) {
        String tokenId = extractTokenKey(token);
        feopRefreshTokenService.removeRefreshTokenByTokenId(tokenId);
    }

    @Override
    public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
        removeAccessTokenUsingRefreshToken(refreshToken.getValue());
    }

    public void removeAccessTokenUsingRefreshToken(String refreshToken) {
        String refreshTokenId = extractTokenKey(refreshToken);
        feopAccessTokenService.deleteWithRefreshToken(refreshTokenId);
    }

    @Override
    public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
        OAuth2AccessToken accessToken = null;

        String key = authenticationKeyGenerator.extractKey(authentication);
        try {
            OauthAccessToken oauthAccessToken = feopAccessTokenService.findAccessTokenWithAuthenticationId(key);

            if (oauthAccessToken == null) {
                throw new EmptyResultDataAccessException(1);
            }

            accessToken = toOAuth2AccessToken(oauthAccessToken);
        } catch (EmptyResultDataAccessException e) {
            if (LOG.isInfoEnabled()) {
                LOG.debug("Failed to find access token for authentication " + authentication);
            }
        } catch (IllegalArgumentException e) {
            LOG.error("Could not extract access token for authentication " + authentication);
        }

        if (accessToken != null && !key.equals(authenticationKeyGenerator.extractKey(readAuthentication(accessToken.getValue())))) {
            removeAccessToken(accessToken.getValue());
            storeAccessToken(accessToken, authentication);
        }

        return accessToken;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByUserName(String userName) {
        List<OAuth2AccessToken> accessTokens = new ArrayList<OAuth2AccessToken>();

        try {
            List<OauthAccessToken> oauthAccessTokens = feopAccessTokenService.findAccessTokenWithUsername(userName);
            if(oauthAccessTokens.isEmpty()){
                throw new EmptyResultDataAccessException(1);
            }

            for (OauthAccessToken token : oauthAccessTokens){
                OAuth2AccessToken oAuth2AccessToken = toOAuth2AccessToken(token);
                if (oAuth2AccessToken != null) {
                    accessTokens.add(oAuth2AccessToken);
                }
            }
        }
        catch (EmptyResultDataAccessException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find access token for userName " + userName);
            }
        }

        return accessTokens;
    }

    @Override
    public Collection<OAuth2AccessToken> findTokensByClientId(String clientId) {
        List<OAuth2AccessToken> accessTokens = new ArrayList<OAuth2AccessToken>();

        try {
            List<OauthAccessToken> oauthAccessTokens = feopAccessTokenService.findAccessTokenWithClientId(clientId);
            if(oauthAccessTokens.isEmpty()){
                throw new EmptyResultDataAccessException(1);
            }

            for (OauthAccessToken token : oauthAccessTokens){
                OAuth2AccessToken oAuth2AccessToken = toOAuth2AccessToken(token);
                if (oAuth2AccessToken != null) {
                    accessTokens.add(oAuth2AccessToken);
                }
            }
        }
        catch (EmptyResultDataAccessException e) {
            if (LOG.isInfoEnabled()) {
                LOG.info("Failed to find access token for clientId " + clientId);
            }
        }

        return accessTokens;
    }


    private OAuth2AccessToken toOAuth2AccessToken(OauthAccessToken oauthAccessToken) {
        return SerializationUtils.deserialize(oauthAccessToken.getToken());
    }

    private OauthAccessToken toOauthAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        String refreshToken = null;
        if(token.getRefreshToken() != null){
            refreshToken = token.getRefreshToken().getValue();
        }

        OauthAccessToken at = new OauthAccessToken();
        at.setTokenId(extractTokenKey(token.getValue()));
        at.setToken(SerializationUtils.serialize(token));

        at.setAuthenticationId(authenticationKeyGenerator.extractKey(authentication));
        at.setUserName(authentication.isClientOnly()? null : authentication.getName());
        at.setClientId(authentication.getAuthorizationRequest().getClientId());
        at.setAuthentication(SerializationUtils.serialize(authentication));
        at.setRefreshToken(extractTokenKey(refreshToken));

        return at;
    }
}
