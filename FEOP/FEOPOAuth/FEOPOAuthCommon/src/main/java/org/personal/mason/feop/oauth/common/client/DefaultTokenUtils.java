package org.personal.mason.feop.oauth.common.client;

import org.personal.mason.feop.oauth.common.client.oauth.FEOPAuthentication;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/5/13
 * Time: 12:50 AM
 * To change this template use File | Settings | File Templates.
 */
public class DefaultTokenUtils implements TokenUtils {
    private Map<String, FEOPAuthentication> authenticationMap = new ConcurrentHashMap<>(50);

    @Override
    public boolean validate(String token) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public FEOPAuthentication getAuthentication(String token) {
        if(authenticationMap.containsKey(token)){
            return authenticationMap.get(token);
        }
        return null;
    }

    @Override
    public synchronized void persist(FEOPAuthentication feopAuthentication) {
        authenticationMap.put(feopAuthentication.getAccessToken(), feopAuthentication);
    }
}
