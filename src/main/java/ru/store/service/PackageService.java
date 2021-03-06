package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.PackageDAO;
import ru.store.entities.Package;
import ru.store.exceptions.DuplicateException;
import ru.store.exceptions.NotFoundException;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 */
@Service
public class PackageService {

    @Autowired
    private PackageDAO packageDAO;

    public void createPackage(Package aPackage) {
        if (getPackage(aPackage.getName()) == null)
            packageDAO.createPackage(aPackage);
        else
            throw new DuplicateException("Пакет с тиким именем уже существует!");
    }

    public void updatePackage(Package aPackage) {
        Package oldPackage = getPackage(aPackage.getId());
        if (oldPackage == null) {
            throw new NotFoundException("Фирма не найдена.");
        }
        aPackage.setCreatedDate(oldPackage.getCreatedDate());
        aPackage.setLastModifiedDate(new Timestamp(new java.util.Date().getTime()));
        aPackage.setOwner(oldPackage.getOwner());
        packageDAO.updatePackage(aPackage);
    }

    public void deletePackage(Integer id) {
        packageDAO.deletePackage(id);
    }

    public List<Package> getPackages() {
        return packageDAO.getPackages();
    }

    public Package getPackage(Integer id) {
        return packageDAO.getPackage(id);
    }

    public Package getPackage(String name) {
        return packageDAO.getPackage(name);
    }
}
