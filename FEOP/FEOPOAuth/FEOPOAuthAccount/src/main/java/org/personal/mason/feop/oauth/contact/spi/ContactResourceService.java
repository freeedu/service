package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.domain.model.ContactResource;
import org.personal.mason.feop.oauth.contact.mvc.model.ResourceVO;

public interface ContactResourceService extends BaseService<ResourceVO, ContactResource> {

ResourceVO createResource(ResourceVO resource);

ResourceVO updateResource(ResourceVO resource);

void deleteResource(ResourceVO resource);

ResourceVO findWithId(Long id);

}
