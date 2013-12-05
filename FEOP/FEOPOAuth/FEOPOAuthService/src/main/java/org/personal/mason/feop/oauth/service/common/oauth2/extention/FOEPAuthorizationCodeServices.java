package org.personal.mason.feop.oauth.service.common.oauth2.extention;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.codehaus.jackson.map.ObjectMapper;
import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthCode;
import org.personal.mason.feop.oauth.service.domain.service.oauth.FeopAuthCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.util.RandomValueStringGenerator;
import org.springframework.security.oauth2.common.util.SerializationUtils;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.AuthorizationRequestHolder;
import org.springframework.security.oauth2.provider.code.RandomValueAuthorizationCodeServices;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/1/13
 * Time: 7:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class FOEPAuthorizationCodeServices implements AuthorizationCodeServices {
    private static final Log logger = LogFactory.getLog(FOEPAuthorizationCodeServices.class);

    private RandomValueStringGenerator generator = new RandomValueStringGenerator(32);

    private FeopAuthCodeService feopAuthCodeService;

    @Autowired
    public void setFeopAuthCodeService(FeopAuthCodeService feopAuthCodeService) {
        this.feopAuthCodeService = feopAuthCodeService;
    }

    public void setGenerator(RandomValueStringGenerator generator) {
        this.generator = generator;
    }

    protected void store(String code, AuthorizationRequestHolder authentication) {
        logger.debug("store authentication");
        OauthCode oauthCode = new OauthCode();
        oauthCode.setCode(code);
        oauthCode.setAuthentication(SerializationUtils.serialize(authentication));
        feopAuthCodeService.save(oauthCode);
    }

    protected AuthorizationRequestHolder remove(String code) {
        logger.debug("remove authentication");
        AuthorizationRequestHolder authentication ;
        try {
            OauthCode oauthCode = feopAuthCodeService.findOauthCodeByCode(code);

            if(oauthCode != null){
                authentication = SerializationUtils.deserialize(oauthCode.getAuthentication());
            } else{
                authentication = null;
            }
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        if (authentication != null) {
            feopAuthCodeService.deleteByCode(code);
        }

        return authentication;
    }

    @Override
    public String createAuthorizationCode(AuthorizationRequestHolder authentication) {
        String code = generator.generate();
        store(code, authentication);
        return code;
    }
    @Override
    public AuthorizationRequestHolder consumeAuthorizationCode(String code)
            throws InvalidGrantException {
        AuthorizationRequestHolder auth = this.remove(code);
        if (auth == null) {
            throw new InvalidGrantException("Invalid authorization code: " + code);
        }
        return auth;
    }
}
