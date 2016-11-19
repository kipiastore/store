package ru.store.dao.interfaces;

import ru.store.entities.Package;

import java.util.List;

/**
 *
 */
public interface PackageDAO {

    void createPackage(Package aPackage);

    void updatePackage(Package aPackage);

    void deletePackage(Integer id);

    List<Package> getPackages();

    Package getPackage(Integer id);

    Package getPackage(String name);

}
