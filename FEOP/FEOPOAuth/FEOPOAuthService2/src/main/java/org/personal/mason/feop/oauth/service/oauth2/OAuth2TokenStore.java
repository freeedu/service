package org.personal.mason.feop.oauth.service.oauth2;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.personal.mason.feop.oauth.service.domain.OauthAccessToken;
import org.personal.mason.feop.oauth.service.domain.OauthRefreshToken;
import org.personal.mason.feop.oauth.service.spi.FeopAccessTokenService;
import org.personal.mason.feop.oauth.service.spi.FeopRefreshTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.DefaultAuthenticationKeyGenerator;
import org.springframework.security.oauth2.provider.token.TokenStore;

public class OAuth2TokenStore implements TokenStore {
	private static final Log LOG = LogFactory.getLog(OAuth2TokenStore.class);

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

	private AuthenticationKeyGenerator authenticationKeyGenerator = new DefaultAuthenticationKeyGenerator();

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
			OauthAccessToken accessToken = feopAccessTokenService.findAccessTokenWithTokenId(extractTokenKey(token));

			if (accessToken != null) {
				authentication = deserializeAuthentication(accessToken.getAuthentication());
			} else {
				throw new EmptyResultDataAccessException(1);
			}
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
		String refreshToken = null;
		if (token.getRefreshToken() != null) {
			refreshToken = token.getRefreshToken().getValue();
		}

		OauthAccessToken accessToken = new OauthAccessToken();
		accessToken.setTokenId(extractTokenKey(token.getValue()));
		accessToken.setToken(serializeAccessToken(token));
		accessToken.setAuthenticationId(authenticationKeyGenerator.extractKey(authentication));
		if (authentication.isClientOnly()) {
			accessToken.setUserName(authentication.getName());
		}
		accessToken.setClientId(authentication.getAuthorizationRequest().getClientId());
		accessToken.setAuthentication(serializeAuthentication(authentication));
		accessToken.setRefreshToken(extractTokenKey(refreshToken));

		feopAccessTokenService.saveObject(accessToken);
	}

	@Override
	public OAuth2AccessToken readAccessToken(String tokenValue) {
		OAuth2AccessToken accessToken = null;

		try {

			OauthAccessToken token = feopAccessTokenService.findAccessTokenWithTokenId(extractTokenKey(tokenValue));

			if (token != null) {
				accessToken = deserializeAccessToken(token.getToken());
			} else {
				throw new EmptyResultDataAccessException(1);
			}
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
		feopAccessTokenService.removeAccessTokenByTokenId(extractTokenKey(tokenValue));
	}

	@Override
	public void storeRefreshToken(OAuth2RefreshToken refreshToken, OAuth2Authentication authentication) {
		OauthRefreshToken token = new OauthRefreshToken();
		token.setTokenId(extractTokenKey(refreshToken.getValue()));
		token.setToken(serializeRefreshToken(refreshToken));
		token.setAuthentication(serializeAuthentication(authentication));
		feopRefreshTokenService.saveObject(token);
	}

	@Override
	public OAuth2RefreshToken readRefreshToken(String tokenValue) {
		OAuth2RefreshToken refreshToken = null;

		try {
			OauthRefreshToken token = feopRefreshTokenService.findRefreshTokenWithTokenId(extractTokenKey(tokenValue));

			if (token != null) {
				refreshToken = deserializeRefreshToken(token.getToken());
			} else {
				throw new EmptyResultDataAccessException(1);
			}
		} catch (EmptyResultDataAccessException e) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Failed to find refresh token for token " + tokenValue);
			}
		} catch (IllegalArgumentException e) {
			LOG.warn("Failed to deserialize refresh token for token " + tokenValue);
			removeRefreshToken(tokenValue);
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
			OauthRefreshToken refreshToken = feopRefreshTokenService.findRefreshTokenWithTokenId(extractTokenKey(value));

			if (refreshToken != null) {
				authentication = deserializeAuthentication(refreshToken.getAuthentication());
			} else {
				throw new EmptyResultDataAccessException(1);
			}
		} catch (EmptyResultDataAccessException e) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Failed to find access token for token " + value);
			}
		} catch (IllegalArgumentException e) {
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
		feopRefreshTokenService.removeRefreshTokenByTokenId(extractTokenKey(token));
	}

	@Override
	public void removeAccessTokenUsingRefreshToken(OAuth2RefreshToken refreshToken) {
		removeAccessTokenUsingRefreshToken(refreshToken.getValue());
	}

	public void removeAccessTokenUsingRefreshToken(String refreshToken) {
		feopAccessTokenService.removeAccessTokenByRefreshToken(extractTokenKey(refreshToken));
	}

	@Override
	public OAuth2AccessToken getAccessToken(OAuth2Authentication authentication) {
		OAuth2AccessToken accessToken = null;

		String key = authenticationKeyGenerator.extractKey(authentication);
		try {
			OauthAccessToken token = feopAccessTokenService.findAccessTokenWithAuthenticationId(key);

			if (token != null) {
				accessToken = deserializeAccessToken(token.getToken());
			} else {
				throw new EmptyResultDataAccessException(1);
			}
		} catch (EmptyResultDataAccessException e) {
			if (LOG.isInfoEnabled()) {
				LOG.debug("Failed to find access token for authentication " + authentication);
			}
		} catch (IllegalArgumentException e) {
			LOG.error("Could not extract access token for authentication " + authentication);
		}

		if (accessToken != null && !key.equals(authenticationKeyGenerator.extractKey(readAuthentication(accessToken.getValue())))) {
			removeAccessToken(accessToken.getValue());
			// Keep the store consistent (maybe the same user is represented by
			// this authentication but the details have changed)
			storeAccessToken(accessToken, authentication);
		}
		return accessToken;
	}

	@Override
	public Collection<OAuth2AccessToken> findTokensByUserName(String userName) {
		List<OAuth2AccessToken> accessTokens = new ArrayList<OAuth2AccessToken>();

		try {
			List<OauthAccessToken> tokens = feopAccessTokenService.findAccessTokenWithUsername(userName);

			for (OauthAccessToken token : tokens) {
				if (token.getToken() != null) {
					accessTokens.add(deserializeAccessToken(token.getToken()));
				}
			}
		} catch (EmptyResultDataAccessException e) {
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
			List<OauthAccessToken> tokens = feopAccessTokenService.findAccessTokenWithClientId(clientId);
			for (OauthAccessToken token : tokens) {
				if (token.getToken() != null) {
					accessTokens.add(deserializeAccessToken(token.getToken()));
				}
			}
		} catch (EmptyResultDataAccessException e) {
			if (LOG.isInfoEnabled()) {
				LOG.info("Failed to find access token for clientId " + clientId);
			}
		}

		return accessTokens;
	}

	protected byte[] serializeAccessToken(OAuth2AccessToken token) {
		return SerializationUtils.serialize(token);
	}

	protected OAuth2AccessToken deserializeAccessToken(byte[] token) {
		return SerializationUtils.deserialize(token);
	}

	protected byte[] serializeAuthentication(OAuth2Authentication authentication) {
		return SerializationUtils.serialize(authentication);
	}

	protected OAuth2Authentication deserializeAuthentication(byte[] authentication) {
		return SerializationUtils.deserialize(authentication);
	}

	protected byte[] serializeRefreshToken(OAuth2RefreshToken token) {
		return SerializationUtils.serialize(token);
	}

	protected OAuth2RefreshToken deserializeRefreshToken(byte[] token) {
		return SerializationUtils.deserialize(token);
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
}
