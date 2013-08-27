package org.personal.mason.feop.server.blog.domain.repository;

import org.personal.mason.feop.server.blog.domain.model.SystemSettings;
import org.springframework.data.jpa.repository.JpaRepository;
import java.lang.String;
import java.util.List;

public interface SystemSettingsRepository extends JpaRepository<SystemSettings, Long> {

	List<SystemSettings> findByProfile(String profile);
	
}
