package org.personal.mason.feop.oauth.service.spi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.personal.mason.feop.oauth.service.domain.model.oauth.OauthRole;
import org.personal.mason.feop.oauth.service.domain.repository.oauth.OauthRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@WebAppConfiguration
public class FEOPUserServiceTest {

    @Autowired
    private OauthRoleRepository oauthRoleRepository;

    @Test
    public void test() {
        List<OauthRole> defaultUserRoles = oauthRoleRepository.getDefaultUserRoles();
        assert (defaultUserRoles.size() == 1);
        String abc = "ROLE_USER,  ROLE_DEV,ROLE_ADMIN";
        Object[] roleNames = null;
        roleNames = abc.split(",[\\s]*");
        defaultUserRoles = oauthRoleRepository.getDefaultUserRoles(roleNames);
        assert (defaultUserRoles.size() == 3);
    }
}
