package ru.store.dao.interfaces;

import ru.store.entities.User;
import ru.store.entities.UserRole;

import java.util.List;

/**
 *
 */
public interface UserDAO {

    User createUser(User user,String role);
    String deleteUser(String username);
    List<UserRole> getUser();

}