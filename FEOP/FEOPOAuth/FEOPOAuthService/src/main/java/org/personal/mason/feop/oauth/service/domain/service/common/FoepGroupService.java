package org.personal.mason.feop.oauth.service.domain.service.common;

import org.personal.mason.feop.oauth.service.domain.model.common.FoepGroup;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/12/13
 * Time: 1:18 AM
 * To change this template use File | Settings | File Templates.
 */
public interface FoepGroupService {

    List<FoepGroup> findAll();

    List<String> findUsersInGroup(String groupName);

    void save(FoepGroup group);

    FoepGroup findGroupByName(String group);

    void update(FoepGroup foepGroup);

    List<GrantedAuthority> findGroupAuthorities(String groupName);

    boolean existGroupWithName(String newName);

    void deleteGroupWithName(String groupName);
}
