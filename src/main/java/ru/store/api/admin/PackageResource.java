package ru.store.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.store.dao.interfaces.PackageDAO;
import ru.store.entities.Package;

/**
 *
 */
@RestController
public class PackageResource {

    @Autowired
    private PackageDAO packageDAO;

    @RequestMapping(value = "/api/admin/resource/v1/package/{id}", method = RequestMethod.GET)
    public Package getPackage(@PathVariable String id) {
        return packageDAO.getPackage(Integer.valueOf(id));
    }

}
