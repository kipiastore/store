package ru.store.api.director;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.store.entities.Package;
import ru.store.service.PackageService;

/**
 *
 */
@RestController
public class DirectorPackageResource {

    @Autowired
    private PackageService packageService;

    @RequestMapping(value = "/api/mainAdmin/resource/v1/package/{id}", method = RequestMethod.GET)
    public Package getPackage(@PathVariable String id) {
        return packageService.getPackage(Integer.valueOf(id));
    }

}
