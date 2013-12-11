package org.personal.mason.feop.oauth.service.domain.repository.oauth;

import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthApproval;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/11/13
 * Time: 12:12 AM
 * To change this template use File | Settings | File Templates.
 */
public interface OauthApprovalRepository extends JpaRepository<OauthApproval, Long> {

}
