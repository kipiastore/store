package ru.store.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.store.dao.interfaces.UserDetailsDAO;
import ru.store.entities.UserRole;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class MyUserDetailsService implements  UserDetailsService {

    private UserDetailsDAO userDAO;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

        ru.store.entities.User user = userDAO.findByUserName(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());

        return buildUserForAuthentication(user, authorities);

    }

    // Converts User user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(ru.store.entities.User user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(),user.isEnabled(),true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }

    public UserDetailsDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDetailsDAO userDAO) {
        this.userDAO = userDAO;
    }

}
