package org.personal.mason.feop.oauth.service.spi;

import org.junit.Test;
import org.personal.mason.feop.oauth.service.domain.model.common.FoepAuthority;
import org.personal.mason.feop.oauth.service.domain.repository.common.FoepAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@WebAppConfiguration
public class FEOPUserServiceTest {

    @Autowired
    private FoepAuthorityRepository foepAuthorityRepository;

    @Test
    public void test() {
        List<FoepAuthority> defaultUserRoles = foepAuthorityRepository.getDefaultUserRoles();
        assert (defaultUserRoles.size() == 1);
        String abc = "ROLE_USER,  ROLE_DEV,ROLE_ADMIN";
        Object[] roleNames = null;
        roleNames = abc.split(",[\\s]*");
        defaultUserRoles = foepAuthorityRepository.getDefaultUserRoles(roleNames);
        assert (defaultUserRoles.size() == 3);
    }
}
