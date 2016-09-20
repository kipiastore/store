package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.UserDAO;
import ru.store.entities.User;
import ru.store.entities.UserRole;

import java.util.List;

/**
 *
 */
@Repository
public class UserDAOImp implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public User createUser(User user, String role) {
        sessionFactory.getCurrentSession().save(user);
        UserRole userRole = new UserRole(user, role);
        sessionFactory.getCurrentSession().save(userRole);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return null;
    }

    @Transactional
    public String deleteUser(String username) {
        String hql = "delete from UserRole where user.username= :username";
        sessionFactory.getCurrentSession().createQuery(hql).setString("username", username).executeUpdate();
        hql = "delete from User where username= :username";
        sessionFactory.getCurrentSession().createQuery(hql).setString("username", username).executeUpdate();
        return null;

    }

    @Transactional
    public List<UserRole> getUser() {
        String hql = " from UserRole where role ='ROLE_MANAGER'or role='ROLE_OPERATOR'or role='ROLE_CLIENT'";
        List result = sessionFactory.getCurrentSession().createQuery(hql).list();
        return result;
    }
}

