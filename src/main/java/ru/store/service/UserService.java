package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.UserDAO;
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

    public void createUser(User user, String role) {
        userDAO.createUser(user,role);
    }
    public void deleteUser(String username) {
        userDAO.deleteUser(username);
    }
    public List<UserRole> getUser() {
        return userDAO.getUser();
    }
}
