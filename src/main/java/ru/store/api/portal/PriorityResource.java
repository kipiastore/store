package ru.store.api.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.store.dao.interfaces.PackageDAO;
import ru.store.entities.Package;

import java.util.*;

/**
 *
 */
@RestController
public class PriorityResource {

    @Autowired
    private PackageDAO packageDAO;

    @RequestMapping(value = "/api/portal/resource/v1/company/priority/", method = RequestMethod.GET)
    public List<PriorityModel> priorityHandler() {
        List<PriorityModel> result = new ArrayList<>();
        List<Package> packages = packageDAO.getPackages();
        Set<Integer> prioritySet = new TreeSet<>();
        for (Package aPackages : packages) {
            prioritySet.add(aPackages.getPriority());
        }
        int step = 100 / (prioritySet.size() + 1) + 1;
        int counter;
        PriorityModel priorityModel;
        for (Package aPackages : packages) {
            counter = 0;
            for (Integer priority : prioritySet) {
                counter++;
                if (Objects.equals(aPackages.getPriority(), priority)) {
                    priorityModel = new PriorityModel();
                    priorityModel.setPackageId(aPackages.getId());
                    priorityModel.setPriority(step * counter - step);
                    result.add(priorityModel);
                }
            }
        }
        return result;
    }

    public static class PriorityModel {
        private Integer packageId;
        private Integer priority;

        public Integer getPackageId() {
            return packageId;
        }
        public void setPackageId(Integer packageId) {
            this.packageId = packageId;
        }
        public Integer getPriority() {
            return priority;
        }
        public void setPriority(Integer priority) {
            this.priority = priority;
        }
    }

}
