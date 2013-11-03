package org.personal.mason.feop.oauth.common.domain.spi;

import java.util.List;

import org.personal.mason.feop.oauth.common.domain.model.AccountUser;
import org.personal.mason.feop.oauth.common.domain.model.UserResource;

public interface UserResourceService {

	List<UserResource> findAllResources(AccountUser account);

	void saveResource(UserResource userResource);

	UserResource findResourceById(Long resourceId);

	void deleteResource(UserResource record);

	UserResource updateUserResource(UserResource email);

}
