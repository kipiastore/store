package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.UserDetailsDAO;
import ru.store.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Repository
public class UserDetailsDAOImp implements UserDetailsDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @SuppressWarnings("unchecked")
    public User findByUserName(String username) {
        List<User> users = sessionFactory.getCurrentSession().createQuery("from User where username=?")
                .setParameter(0, username).list();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }
}
