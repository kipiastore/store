package ru.store.service;

import ru.store.dao.interfaces.UserCreateDAO;
import ru.store.entities.User;

public class CreateUserService {

    private UserCreateDAO userCreateDAO;

    public void createUser(User user) {
        userCreateDAO.createUser(user);
    }
    public UserCreateDAO getUserCreateDAO() {
        return userCreateDAO;
    }

    public void setUserCreateDAO(UserCreateDAO userCreateDAO) {
        this.userCreateDAO = userCreateDAO;
    }
}
