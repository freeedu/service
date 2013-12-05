package org.personal.mason.feop.oauth.service.common.oauth2.extention;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.GroupManager;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/1/13
 * Time: 7:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class FOEPUserDetailsService implements UserDetailsService, GroupManager {

    @Override
    public List<String> findAllGroups() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<String> findUsersInGroup(String groupName) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void createGroup(String groupName, List<GrantedAuthority> authorities) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void deleteGroup(String groupName) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void renameGroup(String oldName, String newName) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addUserToGroup(String username, String group) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeUserFromGroup(String username, String groupName) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<GrantedAuthority> findGroupAuthorities(String groupName) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void addGroupAuthority(String groupName, GrantedAuthority authority) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void removeGroupAuthority(String groupName, GrantedAuthority authority) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
