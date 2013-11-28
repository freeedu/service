package org.personal.mason.feop.server.blog.domain.service;

import org.personal.mason.feop.server.blog.domain.model.BlogSetting;

public interface BlogSettingService {

    BlogSetting findById(Long id);

    BlogSetting update(BlogSetting setting);

    void delete(BlogSetting setting);

    void delete(Long id);
}
