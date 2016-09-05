package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import ru.store.dao.interfaces.UserDetailsDAO;
import ru.store.entities.User;

import java.util.List;

public class UserDetailsDAOImpl implements UserDetailsDAO {

    public SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public User findByUserName(String username) {

        List<User> users = getSessionFactory()
                .getCurrentSession()
                .createQuery("from User where username=?")
                .setParameter(0, username)
                .list();

        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }

    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}
