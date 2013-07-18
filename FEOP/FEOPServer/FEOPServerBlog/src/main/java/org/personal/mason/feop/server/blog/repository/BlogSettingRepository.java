package org.personal.mason.feop.server.blog.repository;

import org.personal.mason.feop.server.blog.domain.BlogSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogSettingRepository extends JpaRepository<BlogSetting, Long> {

}
