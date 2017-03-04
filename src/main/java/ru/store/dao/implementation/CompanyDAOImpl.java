package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.CompanyDAO;
import ru.store.entities.Company;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Repository
public class CompanyDAOImpl implements CompanyDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public void createCompany(Company company) {
        sessionFactory.getCurrentSession().save(company);
    }

    @Override
    @Transactional
    public void updateCompany(Company company) {
        sessionFactory.getCurrentSession().update(company);
    }

    @Override
    @Transactional
    public void deleteCompany(int id) {
        String hql = "delete from Company where id =:id";
        sessionFactory.getCurrentSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }

    @Override
    @Transactional
    public Company getCompany(int id) {
        String hql = "from Company where id =?";
        List<Company> company = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).list();
        if (company != null && company.size() > 0)
            return company.get(0);
        else
            return null;
    }

    @Override
    @Transactional
    public List<Company> getCompanies() {
        String hql = "from Company order by name";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    @Transactional
    public List<Integer> getOptimizationCompanies() {
        String hql = "select id from Company where isShowForSite = true and isOffPosition = false";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    @Transactional
    public List<Company> getCompaniesByLastUpdate() {
        String hql = "from Company where lastModifiedDate != createdDate order by lastModifiedDate desc";
        return sessionFactory.getCurrentSession().createQuery(hql).setMaxResults(15).list();
    }
    //15.02.2017
    @Override
    @Transactional
    public List<Company> findCompaniesByName(String name) {
        String hql = "from Company where lower(name) LIKE lower(:name) order by name desc";
        return sessionFactory.getCurrentSession().createQuery(hql).setString("name", name + "%").list();
    }
    //15.02.2017
    @Override
    @Transactional
    public List<Company> findCompaniesByKeyword(String keywords) {
        String hql = "from Company where lower(keywords) LIKE lower(:keywords) order by name desc";
        return sessionFactory.getCurrentSession().createQuery(hql).setString("keywords",keywords + "%").list();
    }
    //15.02.2017
    @Override
    @Transactional
    public List<Company> findCompaniesByLegalName(String legalName) {
        String hql = "from Company where lower(legalName) LIKE lower(:legalName) order by name desc";
        return sessionFactory.getCurrentSession().createQuery(hql).setString("legalName",legalName + "%").list();
    }
    //15.02.2017
    @Override
    @Transactional
    public List<Company> findCompaniesByPhone(String phone) {
        String hql = "from Company where lower(phone) LIKE lower(:phone) order by name desc";
        return sessionFactory.getCurrentSession().createQuery(hql).setString("phone",phone + "%").list();
    }
    //15.02.2017
    @Override
    @Transactional
    public List<Company> findCompaniesByEmail(String email) {
        String hql = "from Company where lower(email) LIKE lower(:email) order by name desc";
        return sessionFactory.getCurrentSession().createQuery(hql).setString("email",email + "%").list();
    }

    @Override
    @Transactional
    public List<Company> getCompanies(List<Integer> companyIds) {
        if (companyIds.size() == 0)
            return new ArrayList<>();
        String hql = "from Company where id IN (:companyIds) and isShowForOperator = true";
        return sessionFactory.getCurrentSession().createQuery(hql).setParameterList("companyIds", companyIds).list();
    }
    //15.02.2017
    @Override
    @Transactional
    public List<Company> getPortalCompanies(List<Integer> companyIds) {
        if (companyIds.size() == 0)
            return new ArrayList<>();
        String hql = "from Company where id IN (:companyIds) and isShowForSite = true and isOffPosition = false";
        return sessionFactory.getCurrentSession().createQuery(hql).setParameterList("companyIds", companyIds).list();
    }
    //15.02.2017
    @Override
    @Transactional
    public List<Company> findPortalCompaniesByName(String name) {
        String hql = "from Company where isOffPosition = false and isShowForSite = true and lower(name) LIKE lower(:name) order by name desc";
        return sessionFactory.getCurrentSession().createQuery(hql).setString("name",name + "%").list();
    }
    //15.02.2017
    @Override
    @Transactional
    public List<Company> findPortalCompaniesByKeyword(String keywords) {
        String hql = "from Company where isOffPosition = false and isShowForSite = true and lower(keywords) LIKE lower(:keywords) order by name desc";
        return sessionFactory.getCurrentSession().createQuery(hql).setString("keywords",keywords + "%").list();
    }

}
