package ru.store.dao.implementation;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.store.dao.interfaces.CompanyAddressDAO;
import ru.store.entities.CompanyAddress;
import sun.tools.tree.ShiftRightExpression;

import java.util.List;

/**
 *
 */
@Repository
public class CompanyAddressDAOImpl implements CompanyAddressDAO {

    @Autowired
    private SessionFactory sessionFactory;
    
    @Override
    @Transactional
    public void createCompanyAddress(CompanyAddress companyAddress) {
        sessionFactory.getCurrentSession().save(companyAddress);
    }

    @Override
    @Transactional
    public void createCompanyAddress(List<CompanyAddress> companyAddresses) {
        for (CompanyAddress companyAddress : companyAddresses)
            sessionFactory.getCurrentSession().save(companyAddress);
    }

    @Override
    @Transactional
    public void updateCompanyAddress(CompanyAddress companyAddress) {
        sessionFactory.getCurrentSession().update(companyAddress);
    }

    @Override
    @Transactional
    public void updateCompanyAddresses(List<CompanyAddress> companyAddresses) {
        for (CompanyAddress companyAddress : companyAddresses) {
            if (companyAddress.getId() == null)
                sessionFactory.getCurrentSession().save(companyAddress);
            else
                sessionFactory.getCurrentSession().update(companyAddress);
        }
    }

    @Override
    @Transactional
    public void deleteCompanyAddress(Integer id) {
        String hql = "delete from CompanyAddress where id =:id";
        sessionFactory.getCurrentSession().createQuery(hql).setInteger("id", id).executeUpdate();
    }

    @Override
    @Transactional
    public void deleteCompanyAddress(String[] idList) {
        for (String id : idList) {
            if (!id.isEmpty()) {
                deleteCompanyAddress(Integer.valueOf(id));
            }
        }
    }

    @Override
    @Transactional
    public void deleteCompanyAddressByCompany(Integer companyId) {
        String hql = "delete from CompanyAddress where companyId =:companyId";
        sessionFactory.getCurrentSession().createQuery(hql).setInteger("companyId", companyId).executeUpdate();
    }

    @Override
    @Transactional
    public CompanyAddress getCompanyAddress(Integer id) {
        String hql = "from CompanyAddress where id =?";
        List<CompanyAddress> companyAddresses = sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, id).list();
        if (companyAddresses != null && companyAddresses.size() > 0)
            return companyAddresses.get(0);
        else
            return null;
    }

    @Override
    @Transactional
    public List<CompanyAddress> getCompanyAddresses() {
        String hql = "from CompanyAddress";
        return sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @Override
    @Transactional
    public List<CompanyAddress> getCompanyAddresses(Integer companyId) {
        String hql = "from CompanyAddress where companyId =?";
        return sessionFactory.getCurrentSession().createQuery(hql).setParameter(0, companyId).list();
    }

}
