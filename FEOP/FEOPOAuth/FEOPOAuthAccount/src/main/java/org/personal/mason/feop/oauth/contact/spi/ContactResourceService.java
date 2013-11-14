package org.personal.mason.feop.oauth.contact.spi;

import org.personal.mason.feop.oauth.contact.mvc.model.Resource;

public interface ContactResourceService {

	Resource createResource(Long accountId, Long contactId, Resource resource);

	Resource updateResource(Long accountId, Long contactId, Resource resource);

	void deleteResource(Long accountId, Long contactId, Resource resource);

}
