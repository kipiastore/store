package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.UserDetailsDAO;
import ru.store.entities.UserRole;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDetailsDAO userDetailsDAO;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        // Programmatic transaction management
		/*
		return transactionTemplate.execute(new TransactionCallback<UserDetails>() {
			public UserDetails doInTransaction(TransactionStatus status) {
				User user = userDao.findByUserName(username);
				List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
				return buildUserForAuthentication(user, authorities);
			}
		});*/
        ru.store.entities.User user = userDetailsDAO.findByUserName(username);
        List<GrantedAuthority> authorities = buildUserAuthority(user.getUserRole());
        return buildUserForAuthentication(user, authorities);
    }

    // Converts User user to
    // org.springframework.security.core.userdetails.User
    private User buildUserForAuthentication(ru.store.entities.User user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Set<UserRole> userRoles) {
        Set<GrantedAuthority> authoritySet = new HashSet<>();
        // Build user's authorities
        for (UserRole userRole : userRoles) {
            authoritySet.add(new SimpleGrantedAuthority(userRole.getRole()));
        }
        return new ArrayList<>(authoritySet);
    }
}
