package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.SearchRequestDAO;
import ru.store.entities.SearchRequest;

import java.util.List;

/**
 *
 */
@Repository
public class SearchRequestDAOImpl implements SearchRequestDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void createSearchRequest(SearchRequest searchRequest) {
        sessionFactory.getCurrentSession().save(searchRequest);
    }

    @Override
    @Transactional
    public void updateSearchRequest(SearchRequest searchRequest) {
        sessionFactory.getCurrentSession().update(searchRequest);
    }

    @Override
    @Transactional
    public SearchRequest findSearchRequest(String value) {
        String hql = "from SearchRequest where lower(value) LIKE lower(:value)";
        List<SearchRequest> searchRequests = sessionFactory.getCurrentSession().createQuery(hql).setString("value", value).list();
        if (searchRequests != null && searchRequests.size() > 0)
            return searchRequests.get(0);
        else
            return null;
    }

    @Override
    @Transactional
    public List<SearchRequest> findSearchRequests(String value) {
        String hql = "from SearchRequest where lower(value) LIKE lower(:value) order by counter desc";
        return sessionFactory.getCurrentSession().createQuery(hql).setMaxResults(10).setString("value", value + "%").list();
    }
}
