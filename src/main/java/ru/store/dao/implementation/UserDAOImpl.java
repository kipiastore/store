package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.UserDAO;
import ru.store.entities.User;

import java.util.List;

/**
 *
 */
@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void createUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    @Transactional
    public void deleteUser(String username) {
        String hql = "delete from User where username =:username";
        sessionFactory.getCurrentSession().createQuery(hql).setString("username", username).executeUpdate();
    }

    @Override
    @Transactional
    public List<User> getUsers() {
        String hql = "from User order by fullName asc";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    @Transactional
    public User getUser(String username) {
        String hql = "from User where username =?";
        List<User> users = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, username).list();
        if (users != null && users.size() > 0)
            return users.get(0);
        else
            return null;
    }
}

