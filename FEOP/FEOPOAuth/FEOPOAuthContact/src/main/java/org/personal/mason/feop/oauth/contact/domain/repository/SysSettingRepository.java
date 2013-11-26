package org.personal.mason.feop.oauth.contact.domain.repository;

import org.personal.mason.feop.oauth.contact.domain.model.SysSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 11/27/13
 * Time: 12:20 AM
 * To change this template use File | Settings | File Templates.
 */
public interface SysSettingRepository extends JpaRepository<SysSetting, Long> {
    List<SysSetting> findByKey(String key);
}
