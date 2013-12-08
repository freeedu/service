package org.personal.mason.feop.oauth.common.client;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/3/13
 * Time: 1:07 AM
 * To change this template use File | Settings | File Templates.
 */
public enum  LoginStatus {
    REDIRECT_LOGIN,
    REQUEST_AUTH,
    ACCESS_TOKEN,
    ACCESS_ERROR,
    AUTHENTICATED;
}
