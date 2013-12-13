package org.personal.mason.feop.oauth.service.domain.service.common.impl;

import org.personal.mason.feop.oauth.service.domain.model.common.FoepAuthority;
import org.personal.mason.feop.oauth.service.domain.model.common.FoepGroup;
import org.personal.mason.feop.oauth.service.domain.model.common.FoepUser;
import org.personal.mason.feop.oauth.service.domain.repository.common.FoepGroupRepository;
import org.personal.mason.feop.oauth.service.domain.service.common.FoepGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/12/13
 * Time: 1:18 AM
 * To change this template use File | Settings | File Templates.
 */

@Service
public class FoepGroupServiceImpl implements FoepGroupService {
    private FoepGroupRepository foepGroupRepository;

    @Autowired
    public void setFoepGroupRepository(FoepGroupRepository foepGroupRepository) {
        this.foepGroupRepository = foepGroupRepository;
    }

    @Override
    public List<FoepGroup> findAll() {
        return foepGroupRepository.findAll();
    }

    @Override
    public List<String> findUsersInGroup(String groupName) {
        List<String> users = new ArrayList<>();
        FoepGroup foepGroup = findGroupByName(groupName);
        if (foepGroup != null) {
            for (FoepUser user : foepGroup.getUsers()) {
                if (user.getEmail() != null) {
                    users.add(user.getEmail());
                    continue;
                }

                if (user.getPhone() != null) {
                    users.add(user.getPhone());
                    continue;
                }

                if (user.getUserName() != null) {
                    users.add(user.getUserName());
                    continue;
                }
            }
        }
        return users;
    }

    @Override
    public void save(FoepGroup group) {
        foepGroupRepository.save(group);
    }

    @Override
    public FoepGroup findGroupByName(String groupName) {
        List<FoepGroup> foepGroups = foepGroupRepository.findByGroupName(groupName);
        if (foepGroups != null && foepGroups.size() > 0) {
            return foepGroups.get(0);
        }
        return null;
    }

    @Override
    public void update(FoepGroup foepGroup) {
        foepGroupRepository.saveAndFlush(foepGroup);
    }

    @Override
    public List<GrantedAuthority> findGroupAuthorities(String groupName) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        FoepGroup foepGroup = findGroupByName(groupName);
        if (foepGroup != null) {
            for (FoepAuthority authority : foepGroup.getRoles()) {
                if (authority.getEnabled()) {
                    authorities.add(new SimpleGrantedAuthority(authority.getName()));
                }
            }
        }
        return authorities;
    }

    @Override
    public boolean existGroupWithName(String newName) {
        return findGroupByName(newName) == null;
    }

    @Override
    public void deleteGroupWithName(String groupName) {
        FoepGroup foepGroup = findGroupByName(groupName);
        if (foepGroup != null) {
            foepGroupRepository.delete(foepGroup);
        }
    }
}
