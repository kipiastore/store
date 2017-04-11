package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.ImageDAO;
import ru.store.entities.Image;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Repository
public class ImageDAOImpl implements ImageDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void createImage(Image image) {
        sessionFactory.getCurrentSession().save(image);
    }

    @Override
    @Transactional
    public void deleteImage(Integer id) {
        String hql = "delete from Image where id =:id";
        sessionFactory.getCurrentSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }

    @Override
    @Transactional
    public void updateImage(Image image) {
        sessionFactory.getCurrentSession().update(image);
    }

    @Override
    @Transactional
    public List<Image> getImages() {
        String hql = "from Image";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    @Transactional
    public Image getImage(Integer id) {
        String hql = "from Image where id =?";
        List<Image> images = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).list();
        if (images != null && images.size() > 0)
            return images.get(0);
        else
            return null;
    }

    @Override
    @Transactional
    public void deleteImage(List<Integer> imageIds) {
        if (imageIds == null || imageIds.size() == 0)
            return;
        String hql = "delete from Image where id in (:imageIds)";
        sessionFactory.getCurrentSession().createQuery(hql).setParameterList("imageIds", imageIds).executeUpdate();
    }

}
