package ru.store.api.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.store.entities.CompanyAddress;
import ru.store.service.CompanyAddressService;


import java.util.*;

/**
 *
 */
@RestController
public class AddressResource {

    @Autowired
    private CompanyAddressService companyAddressService;

    @RequestMapping(value = "/api/portal/resource/v1/company/address/{companyIdList}", method = RequestMethod.GET)
    public List<AddressModel> getAddress(@PathVariable String companyIdList) {
        List<AddressModel> addressModels = new ArrayList<>();
        AddressModel addressModel;
        List<Integer> companyIds = new ArrayList<>();
        for (String value : companyIdList.split(",")) {
            companyIds.add(Integer.valueOf(value));
        }
        List<CompanyAddress> companyAddresses = companyAddressService.getCompanyAddresses(companyIds);
        Map<Integer, List<CompanyAddress>> tmpMap = new HashMap<>();
        List<CompanyAddress> tmpList;
        for (CompanyAddress companyAddress : companyAddresses) {
            if (tmpMap.get(companyAddress.getCompanyId()) == null) {
                tmpList = new ArrayList<>();
                tmpList.add(companyAddress);
                tmpMap.put(companyAddress.getCompanyId(), tmpList);
            } else {
                tmpMap.get(companyAddress.getCompanyId()).add(companyAddress);
            }
        }
        for (Integer key : tmpMap.keySet()) {
            addressModel = new AddressModel();
            addressModel.setCompanyId(key);
            addressModel.setCompanyAddresses(tmpMap.get(key));
            addressModels.add(addressModel);
        }
        return addressModels;
    }

    public static class AddressModel {
        private Integer companyId;
        private List<CompanyAddress> companyAddresses;

        public Integer getCompanyId() {
            return companyId;
        }
        public void setCompanyId(Integer companyId) {
            this.companyId = companyId;
        }
        public List<CompanyAddress> getCompanyAddresses() {
            return companyAddresses;
        }
        public void setCompanyAddresses(List<CompanyAddress> companyAddresses) {
            this.companyAddresses = companyAddresses;
        }
    }
}
