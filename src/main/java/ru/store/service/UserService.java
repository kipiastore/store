package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.UserDAO;
import ru.store.dao.interfaces.UserRoleDAO;
import ru.store.entities.User;
import ru.store.entities.UserRole;

import java.util.List;

/**
 *
 */
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;

    public void createUser(User user, String role) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        userDAO.createUser(user);
        userRoleDAO.createUserRole(new UserRole(user, role));
    }
    public void updateUser(User user, String role) {
        if (user.getPassword().isEmpty()) {
            user.setPassword(userDAO.getUser(user.getUsername()).getPassword());
        } else {
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(hashedPassword);
            userDAO.updateUser(user);
        }
        UserRole userRole = userRoleDAO.getUserRole(user.getUsername());
        if (userRole == null) {
            userRoleDAO.createUserRole(new UserRole(user, role));
        } else if (!userRole.getRole().equals(role)) {
            userRole.setRole(role);
            userRoleDAO.updateUserRole(userRole);
        }
    }

    public void deleteUser(String username) {
        userRoleDAO.deleteUserRole(username);
        userDAO.deleteUser(username);
    }

    public List<User> getUsers() { return userDAO.getUsers(); }

    public User getUser(String username) { return userDAO.getUser(username); }
}
