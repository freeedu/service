package org.personal.mason.feop.oauth.service.common.oauth2.extention;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.personal.mason.feop.oauth.service.domain.model.common.FoepAuthority;
import org.personal.mason.feop.oauth.service.domain.model.common.FoepGroup;
import org.personal.mason.feop.oauth.service.domain.model.common.FoepUser;
import org.personal.mason.feop.oauth.service.domain.repository.common.FoepUserRepository;
import org.personal.mason.feop.oauth.service.domain.service.common.FoepAuthorityService;
import org.personal.mason.feop.oauth.service.domain.service.common.FoepGroupService;
import org.personal.mason.feop.oauth.service.utils.StringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.GroupManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: mason
 * Date: 12/1/13
 * Time: 7:55 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FOEPUserDetailsService implements UserDetailsService, GroupManager, UserDetailsManager, FoepUserService {
    private static final Log LOG = LogFactory.getLog(FOEPUserDetailsService.class);

    private FoepUserRepository foepUserRepository;
    private FoepGroupService foepGroupService;
    private FoepAuthorityService foepAuthorityService;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setFoepUserRepository(FoepUserRepository foepUserRepository) {
        this.foepUserRepository = foepUserRepository;
    }

    @Autowired
    public void setFoepGroupService(FoepGroupService foepGroupService) {
        this.foepGroupService = foepGroupService;
    }

    @Autowired
    public void setFoepAuthorityService(FoepAuthorityService foepAuthorityService) {
        this.foepAuthorityService = foepAuthorityService;
    }

    @Override
    public List<String> findAllGroups() {
        List<FoepGroup> groups = foepGroupService.findAll();
        List<String> groupNames = new ArrayList<>();
        if (groups != null) {
            for (FoepGroup group : groups) {
                groupNames.add(group.getGroupName());
            }
        }
        return groupNames;
    }

    @Override
    public List<String> findUsersInGroup(String groupName) {
        Assert.hasText(groupName);
        List<String> users = foepGroupService.findUsersInGroup(groupName);
        return users;
    }

    @Override
    public void createGroup(String groupName, List<GrantedAuthority> authorities) {
        Assert.hasText(groupName);
        FoepGroup group = new FoepGroup();
        group.setGroupName(groupName);
        group.setRoles(foepAuthorityService.findOrCreateAuthorities(authorities));
        foepGroupService.save(group);
    }

    @Override
    public void deleteGroup(String groupName) {
        Assert.hasText(groupName);
        foepGroupService.deleteGroupWithName(groupName);
    }

    @Override
    public void renameGroup(String oldName, String newName) {
        Assert.hasText(oldName);
        Assert.hasText(newName);
        FoepGroup foepGroup = foepGroupService.findGroupByName(oldName);
        boolean exist = foepGroupService.existGroupWithName(newName);

        if (foepGroup != null && !exist) {
            foepGroup.setGroupName(newName);
            foepGroupService.update(foepGroup);
        }
    }

    @Override
    public void addUserToGroup(String username, String group) {
        Assert.hasText(username);
        Assert.hasText(group);
        FoepGroup foepGroup = foepGroupService.findGroupByName(group);
        FoepUser foepUser = findByEmailOrUsernameOrPhone(username);
        if (foepGroup != null && foepUser != null) {
            foepGroup.getUsers().add(foepUser);
            foepGroupService.update(foepGroup);
        }
    }

    @Override
    public void removeUserFromGroup(String username, String groupName) {
        Assert.hasText(username);
        Assert.hasText(groupName);
        FoepGroup foepGroup = foepGroupService.findGroupByName(groupName);
        FoepUser foepUser = findByEmailOrUsernameOrPhone(username);
        if (foepGroup != null && foepUser != null) {
            if (foepGroup.getUsers().contains(foepUser)) {
                foepGroup.getUsers().remove(foepUser);
                foepGroupService.update(foepGroup);
            }
        }
    }

    @Override
    public List<GrantedAuthority> findGroupAuthorities(String groupName) {
        Assert.hasText(groupName);
        List<GrantedAuthority> roles = foepGroupService.findGroupAuthorities(groupName);
        return roles;
    }

    @Override
    public void addGroupAuthority(String groupName, GrantedAuthority authority) {
        Assert.notNull(authority);
        Assert.hasText(groupName);
        FoepGroup foepGroup = foepGroupService.findGroupByName(groupName);
        FoepAuthority foepAuthority = foepAuthorityService.findByName(authority.getAuthority());

        if (foepGroup != null && foepAuthority != null) {
            if (!foepGroup.getRoles().contains(foepAuthority)) {
                foepGroup.getRoles().add(foepAuthority);
                foepGroupService.update(foepGroup);
            }
        }
    }

    @Override
    public void removeGroupAuthority(String groupName, GrantedAuthority authority) {
        Assert.notNull(authority);
        Assert.hasText(groupName);
        FoepGroup foepGroup = foepGroupService.findGroupByName(groupName);
        FoepAuthority foepAuthority = foepAuthorityService.findByName(authority.getAuthority());

        if (foepGroup != null && foepAuthority != null) {
            if (foepGroup.getRoles().contains(authority)) {
                foepGroup.getRoles().remove(foepAuthority);
                foepGroupService.update(foepGroup);
            }
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        FoepUser user = findByEmailOrUsernameOrPhone(username);

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

        if (user.getRoles() != null && !user.getRoles().isEmpty()) {
            authSet.addAll(renderRoles(user));
        }

        return authSet;
    }

    private List<GrantedAuthority> renderRoles(FoepUser user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (user != null && user.getRoles() != null) {
            for (FoepAuthority authority : user.getRoles()) {
                if (authority.getEnabled()) {
                    authorities.add(new SimpleGrantedAuthority(authority.getName()));
                }
            }
        }
        return authorities;
    }

    @Override
    public void createUser(UserDetails user) {
        FoepUser foepUser = new FoepUser();
        foepUser.setUserName(user.getUsername());
        foepUser.setPassword(user.getPassword());
        foepUser.setAccountNonExpired(user.isAccountNonExpired());
        foepUser.setAccountNonLocked(user.isAccountNonLocked());
        foepUser.setCredentialsNonExpired(user.isCredentialsNonExpired());
        foepUser.setEnabled(user.isEnabled());
        foepUser.setRoles(foepAuthorityService.findOrCreateAuthorities(user.getAuthorities()));
        buildAdditionalParams(foepUser, user);
        foepUser.setUserId(StringGenerator.generateUniqueString());
        foepUserRepository.save(foepUser);
    }

    @Override
    public void updateUser(UserDetails user) {
        FoepUser foepUser = findByEmailOrUsernameOrPhone(user.getUsername());
        if (foepUser != null) {
            foepUser.setUserName(user.getUsername());
            foepUser.setAccountNonExpired(user.isAccountNonExpired());
            foepUser.setAccountNonLocked(user.isAccountNonLocked());
            foepUser.setCredentialsNonExpired(user.isCredentialsNonExpired());
            foepUser.setEnabled(user.isEnabled());
            //foepUser.setRoles(foepAuthorityService.findOrCreateAuthorities(user.getAuthorities()));
            buildAdditionalParams(foepUser, user);
            foepUserRepository.saveAndFlush(foepUser);
        }
    }

    @Override
    public void deleteUser(String username) {
        FoepUser foepUser = findByEmailOrUsernameOrPhone(username);
        if (foepUser != null) {
            foepUserRepository.delete(foepUser);
        }
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();

        if (currentUser == null) {
            // This would indicate bad coding somewhere
            throw new AccessDeniedException("Can't change password as no Authentication object found in context " +
                    "for current user.");
        }

        String username = currentUser.getName();

        FoepUser foepUser = findByEmailOrUsernameOrPhone(username);

        if (foepUser == null) {
            throw new EmptyResultDataAccessException(1);
        }

        if (passwordEncoder.matches(oldPassword, foepUser.getPassword())) {
            foepUser.setPassword(passwordEncoder.encode(newPassword));
            foepUserRepository.saveAndFlush(foepUser);
        } else {
            throw new IllegalArgumentException("Password not matched");
        }

        LOG.debug("Changing password for user '" + username + "'");

        SecurityContextHolder.getContext().setAuthentication(createNewAuthentication(currentUser, newPassword));
    }

    @Override
    public boolean userExists(String username) {
        return findByEmailOrUsernameOrPhone(username) != null;
    }

    protected Authentication createNewAuthentication(Authentication currentAuth, String newPassword) {
        UserDetails user = loadUserByUsername(currentAuth.getName());

        UsernamePasswordAuthenticationToken newAuthentication =
                new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        newAuthentication.setDetails(currentAuth.getDetails());

        return newAuthentication;
    }

    @Override
    public void buildAdditionalParams(FoepUser foepUser, UserDetails user) {
        Assert.notNull(foepUser);
        Assert.notNull(user);

        if (user instanceof FoepUserDetails) {
            FoepUserDetails foepUserDetails = (FoepUserDetails) user;
            foepUser.setEmail(foepUserDetails.getUserEmail());
            foepUser.setPhone(foepUserDetails.getUserPhone());
        }
    }

    @Override
    public FoepUser findByEmailOrUsernameOrPhone(String princ) {
        List<FoepUser> foepUsers = foepUserRepository.findByEmailOrPhoneOrUserName(princ);
        if (foepUsers != null && !foepUsers.isEmpty()) {
            return foepUsers.get(0);
        }
        return null;
    }
}
