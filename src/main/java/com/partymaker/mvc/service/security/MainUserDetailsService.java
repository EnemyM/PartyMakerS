package com.partymaker.mvc.service.security;

import com.partymaker.mvc.dao.user.UserDao;
import com.partymaker.mvc.dao.user.UserRoleDao;
import com.partymaker.mvc.model.user.User;
import com.partymaker.mvc.model.user.role.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by anton on 10/10/16.
 */
@Service("userDetailsService")
public class MainUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user = (User) userDao.findByField("email", s);

        List<GrantedAuthority> authorities = buildUserAuthority(userRoleDao.findAllRoles());
        return buildUserForAuthentication(user, authorities);
    }

    /*
    Convert model.User into security.User
    */
    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                user.isEnabled(), true, true, true, authorities);
    }


    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> roles) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        roles.forEach((v) -> setAuths.add(new SimpleGrantedAuthority(v.getRole())));

        return new ArrayList<GrantedAuthority>(setAuths);
    }
}
