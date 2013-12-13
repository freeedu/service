package org.personal.mason.feop.oauth.service.common.oauth2.extention;

import org.personal.mason.feop.oauth.service.domain.model.common.FoepUser;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/12/13
 * Time: 11:20 PM
 * To change this template use File | Settings | File Templates.
 */
public interface FoepUserService {

    public void buildAdditionalParams(FoepUser foepUser, UserDetails user);

    FoepUser findByEmailOrUsernameOrPhone(String princ);
}
