package ru.store.controllers.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.Company;
import ru.store.service.CompanyService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Akex on 18.11.2016.
 */
@Component
public class SearchByPage {

    @Autowired
    private CompanyService companyService;

    private Boolean IsComments=false;

    int choice;

    public   List<Company> search(MultiValueMap<String, String> searchMap, String selectSearchCompanyByType,String selectSearchCompanyByPaymentStatus,ModelAndView modelAndView){
        List<Company> companies=new ArrayList<>();
        try {
            String value;
            for (String key : searchMap.keySet()) {
                value = (searchMap.get(key) + "").replace("[", "").replace("]", "").trim();
                if (!key.equals("_csrf") && !value.isEmpty()) {
                    if (key.equals("name") && selectSearchCompanyByType.equals("searchAllCompany")) {
                        companies = companyService.findCompaniesByNameAndSearchPaymentStatus(value,selectSearchCompanyByPaymentStatus);
                        IsComments =false;
                        break;
                    }
                    if (key.equals("phone") && selectSearchCompanyByType.equals("searchAllCompany")) {
                        companies = companyService.findCompaniesByPhoneAndSearchPaymentStatus(value,selectSearchCompanyByPaymentStatus);
                        IsComments =false;
                        break;
                    }
                    if (key.equals("email") && selectSearchCompanyByType.equals("searchAllCompany")) {
                        companies = companyService.findCompaniesByEmailAndSearchPaymentStatus(value,selectSearchCompanyByPaymentStatus);
                        IsComments =false;
                    }
                    if (key.equals("contractNum") && selectSearchCompanyByType.equals("searchAllCompany")) {
                        //companies = companyService.findCompaniesByContractAndSearchPaymentStatus(value,selectSearchCompanyByPaymentStatus);
                        IsComments =false;
                        break;

                    }
                    if (key.equals("name") && selectSearchCompanyByType.equals("withCommentCompany")) {
                        IsComments =true;
                        choice=1;
                        companies = companyService.findCompaniesByName(value);
                        break;

                    }
                    if (key.equals("phone") && selectSearchCompanyByType.equals("withCommentCompany")) {
                        IsComments =true;
                        choice=1;
                        companies = companyService.findCompaniesByPhone(value);
                        break;

                    }
                    if (key.equals("email") && selectSearchCompanyByType.equals("withCommentCompany")) {
                        IsComments =true;
                        choice=1;
                        companies = companyService.findCompaniesByEmail(value);
                        break;

                    }
                    if (key.equals("contractNum") && selectSearchCompanyByType.equals("withCommentCompany")) {
                        choice=1;
                        //companies = companyService.findCompaniesByContractNumber(value);
                        IsComments =true;
                        break;

                    }
                    if(key.equals("name") && selectSearchCompanyByType.equals("noCommentCompany")) {
                        IsComments =true;
                        choice=2;
                        companies = companyService.findCompaniesByName(value);
                        break;

                }
                    if(key.equals("contractNum") && selectSearchCompanyByType.equals("noCommentCompany")) {
                        //companies = companyService.findCompaniesByContractNumber(value);
                        IsComments =true;
                        choice=2;
                        break;

                    }
                    if(key.equals("phone") && selectSearchCompanyByType.equals("noCommentCompany")) {
                        IsComments =true;
                        choice=2;
                        companies = companyService.findCompaniesByPhone(value);
                        break;

                    }
                    if(key.equals("email") && selectSearchCompanyByType.equals("noCommentCompany")) {
                        IsComments =true;
                        choice=2;
                        companies = companyService.findCompaniesByEmail(value);
                        break;

                    }
                }
                if (selectSearchCompanyByType.equals("searchAllCompany")){
                    companies = companyService.getCompaniesByPaymentStatus(selectSearchCompanyByPaymentStatus);
                    IsComments =false;
                    break;
                }
                if(selectSearchCompanyByType.equals("withCommentCompany")) {
                    IsComments =true;
                    choice=1;
                    companies = companyService.getCompanies();
                    break;
                }
                if(selectSearchCompanyByType.equals("noCommentCompany")) {
                    IsComments =true;
                    choice=2;
                    companies = companyService.getCompanies();
                    break;

                }
            }
        } catch (Exception ex) {
            modelAndView.addObject("deleteError", "Возникла ошибка. " + ex.getMessage());
            ex.printStackTrace();
        }
        return companies;
    }
    public Boolean getIsShowAllCompanyWithComments() {
        return IsComments;
    }
    public int getChoice() {
        return choice;
    }

}
