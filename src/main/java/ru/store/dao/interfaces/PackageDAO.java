package ru.store.dao.interfaces;

import ru.store.entities.Package;

import java.util.List;

/**
 *
 */
public interface PackageDAO {

    void createPackage(Package aPackage);

    void deletePackage(Integer id);

    List<Package> getPackages();

    Package getPackage(String name);

}
