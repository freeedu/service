package org.personal.mason.feop.server.blog.domain.repository;

import org.personal.mason.feop.server.blog.domain.model.BlogSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogSettingRepository extends JpaRepository<BlogSetting, Long> {

}
