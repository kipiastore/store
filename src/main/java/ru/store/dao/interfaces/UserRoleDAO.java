package ru.store.dao.interfaces;

import ru.store.entities.UserRole;

import java.util.List;

/**
 *
 */
public interface UserRoleDAO {

    void createUserRole(UserRole userRole);

    void updateUserRole(UserRole userRole);

    void deleteUserRole(String username);

    UserRole getUserRole(String username);

    List<UserRole> getUserRoles(String role);

}
