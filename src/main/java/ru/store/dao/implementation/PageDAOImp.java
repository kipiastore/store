package ru.store.dao.implementation;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.PageDAO;
import ru.store.entities.Page;
import ru.store.entities.User;

import java.util.List;

@Component
public class PageDAOImp implements PageDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private ProjectionList pageProjection;

    public PageDAOImp() {
        pageProjection = Projections.projectionList();
        pageProjection.add(Projections.property("id"), "id");
        pageProjection.add(Projections.property("type"), "type");
        pageProjection.add(Projections.property("status"), "status");
        pageProjection.add(Projections.property("title"), "title");
        pageProjection.add(Projections.property("description"), "description");
        pageProjection.add(Projections.property("count"), "count");
        pageProjection.add(Projections.property("createTime"), "createTime");
        pageProjection.add(Projections.property("userByUserId"), "userByUserId");
    }

    @Transactional
    @Override
    public List<Page> getPages() {
        List<Page> pages = createPageList(createPageCriteria());
        return pages;
    }

    @Transactional
    @Override
    public List<Page> getPages(User userByUserId) {
        List<Page> pages = createPageList(createPageCriteria()
                .add(Restrictions.ilike("userByUserId.userName", userByUserId.getUserName(), MatchMode.EXACT)));
        return pages;
    }

    @Transactional
    @Override
    public List<Page> getPages(String title) {
        List<Page> pages = createPageList(createPageCriteria()
                .add(Restrictions.ilike("b.title", title, MatchMode.ANYWHERE)));
        return pages;
    }

    @Transactional
    @Override
    public Page getPage(int id) {
        return (Page) sessionFactory.getCurrentSession().get(Page.class, id);
    }

    @Transactional
    @Override
    public List<User> getUsers() {
        List<User> users = (List<User>) sessionFactory.getCurrentSession()
                .createCriteria(User.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
        return users;
    }

    @Transactional
    @Override
    public void createPage(Page page) {
        sessionFactory.getCurrentSession().save(page);
    }

    private DetachedCriteria createPageCriteria(){
        DetachedCriteria pageListCriteria = DetachedCriteria.forClass(Page.class, "b");
        createAliases(pageListCriteria);
        return pageListCriteria;
    }

    private void createAliases(DetachedCriteria criteria) {
        criteria.createAlias("b.userByUserId", "userByUserId");
    }

    private List<Page> createPageList(DetachedCriteria pageListCriteria) {
        Criteria criteria = pageListCriteria.getExecutableCriteria(sessionFactory.getCurrentSession());
        criteria.addOrder(Order.desc("b.createTime"))
                .setProjection(pageProjection).setResultTransformer(Transformers.aliasToBean(Page.class));
        return criteria.list();
    }
}
