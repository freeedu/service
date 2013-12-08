package org.personal.mason.feop.oauth.common.client;

import org.personal.mason.feop.oauth.common.client.oauth.FOEPAuthentication;

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
    private Map<String, FOEPAuthentication> authenticationMap = new ConcurrentHashMap<>(50);

    @Override
    public boolean validate(String token) {
        if(!authenticationMap.containsKey(token)){
            return false;
        }
        if(authenticationMap.get(token).isTokenExpired()){
            return false;
        }
        return true;
    }

    @Override
    public FOEPAuthentication getAuthentication(String token) {
        if(authenticationMap.containsKey(token)){
            return authenticationMap.get(token);
        }
        return null;
    }

    @Override
    public synchronized void persist(FOEPAuthentication FOEPAuthentication) {
        authenticationMap.put(FOEPAuthentication.getAccessToken(), FOEPAuthentication);
    }

    @Override
    public void removeAuthentication(String token) {
        if(authenticationMap.containsKey(token)){
            authenticationMap.remove(token);
        }
    }
}
