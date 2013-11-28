package org.personal.mason.feop.oauth.service.domain.service.oauth.impl;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthClientDetail;
import org.personal.mason.feop.oauth.service.domain.repository.oauth.OauthClientDetailRepository;
import org.personal.mason.feop.oauth.service.domain.service.oauth.FeopClientDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class FeopClientDetailServiceImpl implements FeopClientDetailService {

    private OauthClientDetailRepository oauthClientDetailRepository;

    @Autowired
    public void setOauthClientDetailRepository(OauthClientDetailRepository oauthClientDetailRepository) {
        this.oauthClientDetailRepository = oauthClientDetailRepository;
    }

    @Override
    @Transactional
    public OauthClientDetail findByClientId(String clientId) {
        List<OauthClientDetail> details = oauthClientDetailRepository.findByClientId(clientId);
        return details.isEmpty() ? null : details.get(0);
    }

    @Override
    public void decorateClientBy(OauthClientDetail client, String clientTypeName) {
        AuthorizationType clientType = AuthorizationType.getClientTypeWithName(clientTypeName);
        client.setScope(clientType.getScope());
        client.setAuthorizedGrantTypes(clientType.getGrantType());
        client.setAuthorities(clientType.getAuthorities());
        client.setAccessTokenValidity(clientType.getAccessTokenValidity());
        client.setRefreshTokenValidity(clientType.getRefreshTokenValidity());
    }

    @Override
    @Transactional
    public void createApplication(OauthClientDetail client) {
        oauthClientDetailRepository.save(client);
    }

    @Override
    @Transactional
    public void updateApplication(OauthClientDetail oauthClientDetail) {
        oauthClientDetailRepository.saveAndFlush(oauthClientDetail);
    }

    @Override
    @Transactional
    public void deleteApplication(OauthClientDetail oauthClientDetail) {
        oauthClientDetailRepository.delete(oauthClientDetail);
    }

    @Override
    @Transactional
    public List<OauthClientDetail> findAllOauthClientDetails() {
        return oauthClientDetailRepository.findAll();
    }

    @Override
    public List<OauthClientDetail> findAllOauthClientDetailsByUser(String currentUser) {
        return oauthClientDetailRepository.findByOwner(currentUser);
    }

}
