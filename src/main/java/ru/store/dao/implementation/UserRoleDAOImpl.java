package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.UserRoleDAO;
import ru.store.entities.UserRole;

import java.util.List;

/**
 *
 */
@Repository
public class UserRoleDAOImpl implements UserRoleDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void createUserRole(UserRole userRole) {
        sessionFactory.getCurrentSession().save(userRole);
    }

    @Override
    @Transactional
    public void updateUserRole(UserRole userRole) {
        sessionFactory.getCurrentSession().update(userRole);
    }

    @Override
    @Transactional
    public void deleteUserRole(String username) {
        String hql = "delete from UserRole where user.username =:username";
        sessionFactory.getCurrentSession().createQuery(hql).setString("username", username).executeUpdate();
    }

    @Override
    @Transactional
    public UserRole getUserRole(String username) {
        String hql = "from UserRole where user.username =?";
        List<UserRole> userRoles = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, username).list();
        if (userRoles != null && userRoles.size() > 0)
            return userRoles.get(0);
        else
            return null;
    }
}
