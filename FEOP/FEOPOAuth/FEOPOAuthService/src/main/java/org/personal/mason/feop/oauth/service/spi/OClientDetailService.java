package org.personal.mason.feop.oauth.service.spi;

import java.util.List;

import org.personal.mason.feop.oauth.service.domain.OauthClientDetail;

public interface OClientDetailService {

	OauthClientDetail findByClientId(String clientId);

	void decorateClientBy(OauthClientDetail client, String clientType);

	void createApplication(OauthClientDetail client);

	void updateApplication(OauthClientDetail oauthClientDetail);

	void deleteApplication(OauthClientDetail oauthClientDetail);

	List<OauthClientDetail> findAllOauthClientDetails();

	List<OauthClientDetail> findAllOauthClientDetailsByUser(String currentUser);

}
