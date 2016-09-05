package ru.store.dao.interfaces;

import ru.store.entities.User;

public interface UserDetailsDAO {

    User findByUserName(String username);

}
