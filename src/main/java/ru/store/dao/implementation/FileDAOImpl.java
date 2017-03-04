package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.FileDAO;
import ru.store.entities.File;

import java.util.List;

/**
 *
 */
@Repository
public class FileDAOImpl implements FileDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void createFile(File file) {
        sessionFactory.getCurrentSession().save(file);
    }

    @Override
    @Transactional
    public void deleteFile(Integer id) {
        String hql = "delete from File where id =:id";
        sessionFactory.getCurrentSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }

    @Override
    @Transactional
    public void updateFile(File file) {
        sessionFactory.getCurrentSession().update(file);
    }

    @Override
    @Transactional
    public List<File> getFiles() {
        String hql = "from File";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    @Transactional
    public File getFile(Integer id) {
        String hql = "from File where id =?";
        List<File> files = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).list();
        if (files != null && files.size() > 0)
            return files.get(0);
        else
            return null;
    }
}
