package org.personal.mason.feop.oauth.service.common.oauth2.extention;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/12/13
 * Time: 11:27 PM
 * To change this template use File | Settings | File Templates.
 */
public interface FoepUserDetails extends UserDetails {
    public Long getId();

    public String getUserEmail();

    public String getUserPhone();

}
