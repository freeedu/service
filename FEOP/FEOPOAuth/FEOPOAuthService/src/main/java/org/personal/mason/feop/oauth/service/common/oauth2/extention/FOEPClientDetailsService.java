package org.personal.mason.feop.oauth.service.common.oauth2.extention;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthClientDetail;
import org.personal.mason.feop.oauth.service.domain.service.oauth.FeopClientDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.*;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/1/13
 * Time: 7:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class FOEPClientDetailsService implements ClientDetailsService, ClientRegistrationService {

    private static final Log logger = LogFactory.getLog(FOEPClientDetailsService.class);

    private PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();

    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private FeopClientDetailService feopClientDetailService;

    @Autowired
    public void setFeopClientDetailService(FeopClientDetailService feopClientDetailService) {
        this.feopClientDetailService = feopClientDetailService;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        ClientDetails details;
        try {
            OauthClientDetail cd = feopClientDetailService.findByClientId(clientId);
            if (cd != null) {
                details = ClientDetailsConvertor.toClientDetails(cd);
            } else {
                throw new EmptyResultDataAccessException(1);
            }
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchClientException("No client with requested id: " + clientId);
        }

        return details;
    }

    @Override
    public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
        if (feopClientDetailService.isExist(clientDetails.getClientId())) {
            throw new ClientAlreadyExistsException("Client already exists: " + clientDetails.getClientId());
        }
        OauthClientDetail ocd = ClientDetailsConvertor.toOauthClientDetail(clientDetails);
        ocd.setClientSecret(clientDetails.getClientSecret() != null ? passwordEncoder.encode(clientDetails.getClientSecret()) : null);
        feopClientDetailService.createApplication(ocd);
    }

    @Override
    public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
        OauthClientDetail ocd = feopClientDetailService.findByClientId(clientDetails.getClientId());
        if (ocd == null) {
            throw new NoSuchClientException("No client found with id = " + clientDetails.getClientId());
        }

        ClientDetailsConvertor.mergeToOauthClientDetail(ocd, clientDetails);
        feopClientDetailService.updateApplication(ocd);
    }

    @Override
    public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
        OauthClientDetail ocd = feopClientDetailService.findByClientId(clientId);
        if (ocd == null) {
            throw new NoSuchClientException("No client found with id = " + clientId);
        }

        ocd.setClientSecret(passwordEncoder.encode(secret));
        feopClientDetailService.updateApplication(ocd);
    }

    @Override
    public void removeClientDetails(String clientId) throws NoSuchClientException {
        OauthClientDetail ocd = feopClientDetailService.findByClientId(clientId);
        if (ocd == null) {
            throw new NoSuchClientException("No client found with id = " + clientId);
        }

        feopClientDetailService.deleteApplication(ocd);
    }

    @Override
    public List<ClientDetails> listClientDetails() {
        List<ClientDetails> cds = new ArrayList<>();
        List<OauthClientDetail> clientDetails = feopClientDetailService.findAllOauthClientDetails();
        for (OauthClientDetail ocd : clientDetails) {
            cds.add(ClientDetailsConvertor.toClientDetails(ocd));
        }
        return cds;
    }

    public static class ClientDetailsConvertor {
        private static final Log logger = LogFactory.getLog(ClientDetailsConvertor.class);

        private static ObjectMapper mapper = new ObjectMapper();

        public static final ClientDetails toClientDetails(OauthClientDetail oauthClientDetail) {
            if (oauthClientDetail == null) {
                return null;
            }

            BaseClientDetails clientDetails = new BaseClientDetails(oauthClientDetail.getClientId(),
                    oauthClientDetail.getResourceIds(),
                    oauthClientDetail.getScope(),
                    oauthClientDetail.getAuthorizedGrantTypes(),
                    oauthClientDetail.getAuthorities(),
                    oauthClientDetail.getWebServerRedirectUri());
            clientDetails.setClientSecret(oauthClientDetail.getClientSecret());

            if (oauthClientDetail.getAccessTokenValidity() != null) {
                clientDetails.setAccessTokenValiditySeconds(oauthClientDetail.getAccessTokenValidity());
            }

            if (oauthClientDetail.getRefreshTokenValidity() != null) {
                clientDetails.setRefreshTokenValiditySeconds(oauthClientDetail.getRefreshTokenValidity());
            }

            String json = oauthClientDetail.getAdditionalInformation();
            if (json != null) {
                try {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> additionalInformation = mapper.readValue(json, Map.class);
                    clientDetails.setAdditionalInformation(additionalInformation);
                } catch (Exception e) {
                    logger.warn("Could not decode JSON for additional information: " + clientDetails, e);
                }
            }
            return clientDetails;
        }

        public final static OauthClientDetail toOauthClientDetail(ClientDetails clientDetails) {
            if (clientDetails == null) {
                return null;
            }
            OauthClientDetail ocd = new OauthClientDetail();

            String json = null;
            try {
                json = mapper.writeValueAsString(clientDetails.getAdditionalInformation());
            } catch (Exception e) {
                logger.warn("Could not serialize additional information: " + clientDetails, e);
            }

            ocd.setResourceIds(clientDetails.getResourceIds() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails.getResourceIds()) : null);
            ocd.setScope(clientDetails.getScope() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails.getScope()) : null);
            ocd.setAuthorizedGrantTypes(clientDetails.getAuthorizedGrantTypes() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails.getAuthorizedGrantTypes()) : null);
            ocd.setWebServerRedirectUri(clientDetails.getRegisteredRedirectUri() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails.getRegisteredRedirectUri()) : null);
            ocd.setAuthorities(clientDetails.getAuthorities() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails.getAuthorities()) : null);
            ocd.setAccessTokenValidity(clientDetails.getAccessTokenValiditySeconds());
            ocd.setRefreshTokenValidity(clientDetails.getRefreshTokenValiditySeconds());
            ocd.setAdditionalInformation(json);
            ocd.setClientId(clientDetails.getClientId());
            ocd.setClientSecret(clientDetails.getClientSecret());

            return ocd;
        }

        public final static void mergeToOauthClientDetail(OauthClientDetail ocd, ClientDetails clientDetails) {
            String json = null;
            try {
                json = mapper.writeValueAsString(clientDetails.getAdditionalInformation());
            } catch (Exception e) {
                logger.warn("Could not serialize additional information: " + clientDetails, e);
            }

            ocd.setResourceIds(clientDetails.getResourceIds() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails.getResourceIds()) : null);
            ocd.setScope(clientDetails.getScope() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails.getScope()) : null);
            ocd.setAuthorizedGrantTypes(clientDetails.getAuthorizedGrantTypes() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails.getAuthorizedGrantTypes()) : null);
            ocd.setWebServerRedirectUri(clientDetails.getRegisteredRedirectUri() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails.getRegisteredRedirectUri()) : null);
            ocd.setAuthorities(clientDetails.getAuthorities() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails.getAuthorities()) : null);
            ocd.setAccessTokenValidity(clientDetails.getAccessTokenValiditySeconds());
            ocd.setRefreshTokenValidity(clientDetails.getRefreshTokenValiditySeconds());
            ocd.setAdditionalInformation(json);
        }
    }
}
