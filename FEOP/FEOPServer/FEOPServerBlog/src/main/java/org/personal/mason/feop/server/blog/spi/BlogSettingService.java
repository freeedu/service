package org.personal.mason.feop.server.blog.spi;

import org.personal.mason.feop.server.blog.domain.BlogSetting;

public interface BlogSettingService {

	BlogSetting findById(Long id);

	BlogSetting update(BlogSetting setting);

	void delete(BlogSetting setting);

	void delete(Long id);
}
