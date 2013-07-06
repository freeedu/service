package org.personal.mason.feop.oauth.service.oauth2;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.personal.mason.feop.oauth.service.domain.OauthClientDetail;
import org.personal.mason.feop.oauth.service.spi.OClientDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.BaseClientDetails;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.util.StringUtils;

public class OAuth2ClientDetailsService implements ClientDetailsService, ClientRegistrationService {
	private static final Log logger = LogFactory.getLog(OAuth2ClientDetailsService.class);
	private ObjectMapper mapper = new ObjectMapper();

	private OClientDetailService clientDetailService;
	private PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();

	@Autowired
	public void setClientDetailService(OClientDetailService clientDetailService) {
		this.clientDetailService = clientDetailService;
	}

	@Autowired
	public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		OauthClientDetail client = clientDetailService.findByClientId(clientId);
		// 客户端为空，则提示OAuth2 ERROR
		if (null == client) {
			throw new ClientRegistrationException("invalid_client");
		}

		BaseClientDetails clientDetails = oauthClientDetailToClientDetails(client);
		// BaseClientDetails clientDetails = new BaseClientDetails();
		// clientDetails.setClientId(clientId);
		// clientDetails.setClientSecret(trim(client.getClientSecret()));
		// String scope = trim(client.getScope());
		// Set<String> scopeSet = this.getClientConfigInfo(scope);
		// clientDetails.setScope(scopeSet);
		// String registeredRedirectUris =
		// trim(client.getWebServerRedirectUri());
		// Set<String> uriSet =
		// this.getClientConfigInfo(registeredRedirectUris);
		// clientDetails.setRegisteredRedirectUri(uriSet);
		// String authoritieStr = trim(client.getAuthorities());
		// Set<String> authoritySet = this.getClientConfigInfo(authoritieStr);
		// Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		// for (String item : authoritySet) {
		// authorities.add(new SimpleGrantedAuthority(item));
		// }
		// clientDetails.setAuthorities(authorities);
		// String grantTypes = trim(client.getAuthorizedGrantTypes());
		// Set<String> grantTypeSet = this.getClientConfigInfo(grantTypes);
		// clientDetails.setAuthorizedGrantTypes(grantTypeSet);
		// clientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
		// clientDetails.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
		// String resourceIds = trim(client.getResourceIds());
		// Set<String> resourceIdSet = this.getClientConfigInfo(resourceIds);
		// clientDetails.setResourceIds(resourceIdSet);
		return clientDetails;
	}

	// private Set<String> getClientConfigInfo(String config) {
	// if (config == null || config.trim().isEmpty()) {
	// return null;
	// }
	//
	// String[] configArr = config.split(",");
	// Set<String> configSet = new HashSet<String>();
	// for (String item : configArr) {
	// configSet.add(item);
	// }
	//
	// return configSet;
	// }

	@Override
	public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
		try {
			OauthClientDetail oauthClientDetail = new OauthClientDetail();
			setOrUpdateClientDetails(oauthClientDetail, clientDetails);
			oauthClientDetail.setClientId(clientDetails.getClientId());
			oauthClientDetail.setClientSecret(clientDetails.getClientSecret() != null ? passwordEncoder.encode(clientDetails.getClientSecret())
					: null);

			clientDetailService.createApplication(oauthClientDetail);
		} catch (DuplicateKeyException e) {
			throw new ClientAlreadyExistsException("Client already exists: " + clientDetails.getClientId(), e);
		}
	}

	@Override
	public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
		OauthClientDetail oauthClientDetail = clientDetailService.findByClientId(clientDetails.getClientId());
		if (oauthClientDetail == null) {
			throw new NoSuchClientException("No client found with id = " + clientDetails.getClientId());
		}

		setOrUpdateClientDetails(oauthClientDetail, clientDetails);
		clientDetailService.updateApplication(oauthClientDetail);
	}

	@Override
	public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
		OauthClientDetail oauthClientDetail = clientDetailService.findByClientId(clientId);
		if (oauthClientDetail == null) {
			throw new NoSuchClientException("No client found with id = " + clientId);
		}
		oauthClientDetail.setClientSecret(secret != null ? passwordEncoder.encode(secret) : null);

		clientDetailService.updateApplication(oauthClientDetail);
	}

	@Override
	public void removeClientDetails(String clientId) throws NoSuchClientException {
		OauthClientDetail oauthClientDetail = clientDetailService.findByClientId(clientId);
		if (oauthClientDetail == null) {
			throw new NoSuchClientException("No client found with id = " + clientId);
		}
		clientDetailService.deleteApplication(oauthClientDetail);
	}

	@Override
	public List<ClientDetails> listClientDetails() {
		List<OauthClientDetail> allClients = clientDetailService.findAllOauthClientDetails();
		List<ClientDetails> clientDetails = new ArrayList<ClientDetails>();
		for (OauthClientDetail client : allClients) {
			BaseClientDetails clientDetail = oauthClientDetailToClientDetails(client);
			clientDetails.add(clientDetail);
		}
		return clientDetails;
	}

	private BaseClientDetails oauthClientDetailToClientDetails(OauthClientDetail client) {
		BaseClientDetails clientDetail = new BaseClientDetails(client.getClientId(), client.getResourceIds(), client.getScope(),
				client.getAuthorizedGrantTypes(), client.getAuthorities(), client.getWebServerRedirectUri());
		clientDetail.setClientSecret(client.getClientSecret());
		if (client.getAccessTokenValidity() != null) {
			clientDetail.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
		}
		if (client.getRefreshTokenValidity() != null) {
			clientDetail.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
		}
		if (client.getAdditionalInformation() != null) {
			try {
				@SuppressWarnings("unchecked")
				Map<String, Object> additionalInformation = mapper.readValue(client.getAdditionalInformation(), Map.class);
				clientDetail.setAdditionalInformation(additionalInformation);
			} catch (Exception e) {
				logger.warn("Could not decode JSON for additional information: " + clientDetail, e);
			}
		}
		return clientDetail;
	}

	private void setOrUpdateClientDetails(OauthClientDetail oauthClientDetail, ClientDetails clientDetails) {
		oauthClientDetail.setResourceIds(processFieldsContent(clientDetails.getResourceIds()));
		oauthClientDetail.setScope(processFieldsContent(clientDetails.getScope()));
		oauthClientDetail.setAuthorizedGrantTypes(processFieldsContent(clientDetails.getAuthorizedGrantTypes()));
		oauthClientDetail.setWebServerRedirectUri(processFieldsContent(clientDetails.getRegisteredRedirectUri()));
		oauthClientDetail.setAuthorities(processFieldsContent(clientDetails.getAuthorities()));
		oauthClientDetail.setAccessTokenValidity(clientDetails.getAccessTokenValiditySeconds());
		oauthClientDetail.setRefreshTokenValidity(clientDetails.getRefreshTokenValiditySeconds());
		oauthClientDetail.setAdditionalInformation("json");
	}

	private String processFieldsContent(Collection<GrantedAuthority> authorities) {
		if (authorities == null) {
			return null;
		}
		return StringUtils.collectionToCommaDelimitedString(authorities);
	}

	private String processFieldsContent(Set<?> resourceIds) {
		if (resourceIds == null) {
			return null;
		}
		return StringUtils.collectionToCommaDelimitedString(resourceIds);
	}

}
