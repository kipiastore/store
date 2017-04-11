package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.CompanySubpartitionContentDAO;
import ru.store.entities.CompanySubpartitionContent;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Repository
public class CompanySubpartitionContentDAOImpl implements CompanySubpartitionContentDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void createCompanySubpartitionContent(CompanySubpartitionContent companySubpartitionContent) {
        sessionFactory.getCurrentSession().save(companySubpartitionContent);
    }

    @Override
    @Transactional
    public void deleteCompanySubpartitionContent(Integer id) {
        String hql = "delete from CompanySubpartitionContent where id =:id";
        sessionFactory.getCurrentSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }

    @Override
    @Transactional
    public void updateCompanySubpartitionContent(CompanySubpartitionContent companySubpartitionContent) {
        sessionFactory.getCurrentSession().update(companySubpartitionContent);
    }

    @Override
    @Transactional
    public List<CompanySubpartitionContent> getCompanySubpartitionContents() {
        String hql = "from CompanySubpartitionContent";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    @Transactional
    public CompanySubpartitionContent getCompanySubpartitionContent(Integer id) {
        String hql = "from CompanySubpartitionContent where id =?";
        List<CompanySubpartitionContent> companySubpartitionContents = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).list();
        if (companySubpartitionContents != null && companySubpartitionContents.size() > 0)
            return companySubpartitionContents.get(0);
        else
            return null;
    }

    @Override
    @Transactional
    public List<CompanySubpartitionContent> getCompanySubpartitionContents(Integer companyId) {
        String hql = "from CompanySubpartitionContent where companyId =?";
        return sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, companyId).list();
    }

    @Override
    @Transactional
    public void deleteCompanySubpartitionContent(List<Integer> companySubpartitionIds) {
        if (companySubpartitionIds == null || companySubpartitionIds.size() == 0)
            return;
        String hql = "delete from CompanySubpartitionContent where companySubpartitionId in (:companySubpartitionIds)";
        sessionFactory.getCurrentSession().createQuery(hql).setParameterList("companySubpartitionIds", companySubpartitionIds).executeUpdate();
    }

    @Override
    @Transactional
    public List<CompanySubpartitionContent> getCompanySubpartitionContents(List<Integer> companySubpartitionIds) {
        if (companySubpartitionIds == null || companySubpartitionIds.size() == 0)
            return new ArrayList<>();
        String hql = "from CompanySubpartitionContent where companySubpartitionId in (:companySubpartitionIds)";
        return sessionFactory.getCurrentSession().createQuery(hql).setParameterList("companySubpartitionIds", companySubpartitionIds).list();
    }

}
