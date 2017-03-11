package ru.store.dao.interfaces;

import ru.store.entities.User;
import ru.store.entities.UserRole;

import java.util.List;

/**
 *
 */
public interface UserDAO {

    void createUser(User user);

    void updateUser(User user);

    void deleteUser(String username);

    List<User> getUsers();

    User getUser(String username);

}