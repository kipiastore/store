package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.store.dao.interfaces.UserCreateDAO;
import ru.store.entities.User;
import ru.store.entities.UserRole;

public class UserCreateDAOImpl implements UserCreateDAO {

    public SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    public User createUser(User user) {
        sessionFactory.getCurrentSession().save(user);
        UserRole userRole=new UserRole(user,"ROLE_USER");
        sessionFactory.getCurrentSession().save(userRole);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return null;
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

}

