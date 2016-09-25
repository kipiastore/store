package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.PackageDAO;
import ru.store.entities.Package;

import java.util.List;

/**
 *
 */
@Service
public class PackageService {

    @Autowired
    private PackageDAO packageDAO;

    public void createPackage(Package aPackage) {
        packageDAO.createPackage(aPackage);
    }

    public void deletePackage(Integer id) {
        packageDAO.deletePackage(id);
    }

    public List<Package> getPackages() {
        return packageDAO.getPackages();
    }

    public Package getPackage(String name) {
        return packageDAO.getPackage(name);
    }
}
