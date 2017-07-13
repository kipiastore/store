package ru.store.api.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.store.entities.Company;
import ru.store.entities.CompanySubPartition;
import ru.store.service.CompanyService;
import ru.store.service.CompanySubPartitionService;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@RestController
public class PortalCompanyLightResource {

    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanySubPartitionService companySubPartitionService;

    @RequestMapping(value = "/api/portal/resource/v1/company/SubPartition/{subPartitionId}/{position}",
            method = RequestMethod.GET)
    public List<CompanyModel> getCompany(@PathVariable String subPartitionId, @PathVariable String position) {

        List<CompanySubPartition> companySubPartitions =
                companySubPartitionService.findCompanySubpartitionBySubPartitionId(Integer.valueOf(subPartitionId));
        List<Integer> companyId = new ArrayList<>();
        for (CompanySubPartition companySubPartition : companySubPartitions) {
            companyId.add(companySubPartition.getCompanyId());
        }

        int positionCounter = Integer.valueOf(position);
        int maxPosition = positionCounter + 10;
        int minPosition = positionCounter;
        int counter = 0;
        List<Company> tmpCompanies = new ArrayList<>();
        List<Company> companies = companyService.getPortalCompanies(companyId);
        for (Company company : companies) {
            company.setDescription(company.getDescription().replaceAll("\n","<br/>").replaceAll("script",""));
            if (counter == minPosition) {
                tmpCompanies.add(company);
                positionCounter++;
            } else {
                counter++;
            }
            if (positionCounter == maxPosition)
                break;
        }

        List<CompanyModel> result = new ArrayList<>();
        CompanyModel companyModel;
        for (Company company : tmpCompanies) {
            companyModel = new CompanyModel();
            companyModel.id = company.getId();
            companyModel.name = company.getName();
            companyModel.description = company.getDescription();
            companyModel.countCompany = company.getCountCompany();
            companyModel.imageId = company.getImageId();
            companyModel.isPaid = company.getIsPaid();
            companyModel.companyPackageId = company.getCompanyPackageId();
            result.add(companyModel);
        }
        return result;
    }

    public static class CompanyModel {
        public int id;
        public String name;
        public String description;
        public Integer countCompany;
        public Integer imageId;
        public Boolean isPaid;
        public Integer companyPackageId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getCountCompany() {
            return countCompany;
        }

        public void setCountCompany(Integer countCompany) {
            this.countCompany = countCompany;
        }

        public Integer getImageId() {
            return imageId;
        }

        public void setImageId(Integer imageId) {
            this.imageId = imageId;
        }

        public Boolean getPaid() {
            return isPaid;
        }

        public void setPaid(Boolean paid) {
            isPaid = paid;
        }

        public Integer getCompanyPackageId() {
            return companyPackageId;
        }

        public void setCompanyPackageId(Integer companyPackageId) {
            this.companyPackageId = companyPackageId;
        }
    }

}
