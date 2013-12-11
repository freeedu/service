package org.personal.mason.feop.oauth.service.common.oauth2.extention;

import org.personal.mason.feop.oauth.service.domain.model.common.FoepGroup;
import org.personal.mason.feop.oauth.service.domain.model.common.FoepUser;
import org.personal.mason.feop.oauth.service.domain.service.common.FeopUserService;
import org.personal.mason.feop.oauth.service.domain.service.common.FoepGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.GroupManager;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/1/13
 * Time: 7:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class FOEPUserDetailsService implements UserDetailsService, GroupManager {
    private FeopUserService feopUserService;
    private FoepGroupService foepGroupService;

    @Autowired
    public void setFeopUserService(FeopUserService feopUserService) {
        this.feopUserService = feopUserService;
    }

    @Autowired
    public void setFoepGroupService(FoepGroupService foepGroupService){
        this.foepGroupService = foepGroupService;
    }

    @Override
    public List<String> findAllGroups() {
        List<FoepGroup> groups = foepGroupService.findAll();
        List<String> groupNames = new ArrayList<>();
        if(groups != null){
            for (FoepGroup group : groups){
                groupNames.add(group.getGroupName());
            }
        }
        return groupNames;
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
        FoepUser user = feopUserService.findByEmailOrUsername(username);
        System.out.println("UserDetails:" + user);

        if (null == user) {
            throw new UsernameNotFoundException("Invalid User");
        }

        Collection<GrantedAuthority> grantedAuths = this.obtionGrantedAuthorities(user);


        User userDetail = new User(username,
                user.getPassword(),
                user.getEnabled(),
                user.getAccountNonExpired(),
                user.getCredentialsNonExpired(),
                user.getAccountNonLocked(),
                grantedAuths);

        return userDetail;
    }

    private Set<GrantedAuthority> obtionGrantedAuthorities(FoepUser user) {

        if (null == user) {
            return null;
        }

        Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();

        List<String> roles = feopUserService.findUserRoles(user);

        for (String role : roles) {
            authSet.add(new SimpleGrantedAuthority(role));
        }

        return authSet;
    }
}
