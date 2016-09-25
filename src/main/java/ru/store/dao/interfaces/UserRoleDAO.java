package ru.store.dao.interfaces;

import ru.store.entities.UserRole;

/**
 *
 */
public interface UserRoleDAO {

    void createUserRole(UserRole userRole);

    void updateUserRole(UserRole userRole);

    void deleteUserRole(String username);

    UserRole getUserRole(String username);

}
