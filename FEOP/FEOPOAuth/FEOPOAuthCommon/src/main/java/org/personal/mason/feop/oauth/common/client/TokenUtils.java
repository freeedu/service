package org.personal.mason.feop.oauth.common.client;

import org.personal.mason.feop.oauth.common.client.oauth.FEOPAuthentication;
import org.personal.mason.feop.oauth.common.client.oauth.code.AuthorizationCodeAuthentication;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/5/13
 * Time: 12:18 AM
 * To change this template use File | Settings | File Templates.
 */
public interface TokenUtils {
    boolean validate(String token);

    FEOPAuthentication getAuthentication(String token);

    void persist(FEOPAuthentication feopAuthentication);
}
