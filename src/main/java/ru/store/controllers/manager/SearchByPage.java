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

    private int choice;


    public   List<Company> search(MultiValueMap<String, String> searchMap, String selectSearchCompanyByType,String selectSearchCompanyByPaymentStatus,ModelAndView modelAndView){
        List<Company> companies=new ArrayList<>();

        try {
            String value;
            for (String key : searchMap.keySet()) {
                value = (searchMap.get(key) + "").replace("[", "").replace("]", "").trim();
                if (!key.equals("_csrf") && !value.isEmpty()) {
                    if (key.equals("name") && selectSearchCompanyByType.equals("searchAllCompany")||
                            key.equals("name") && selectSearchCompanyByType.equals("withCommentCompany")||
                            key.equals("name") && selectSearchCompanyByType.equals("noCommentCompany") ) {
                        companies = companyService.findCompaniesByNameAndSearchPaymentStatus(value,selectSearchCompanyByPaymentStatus);
                        isNeedComment(selectSearchCompanyByType);
                        break;
                    }
                    if (key.equals("phone") && selectSearchCompanyByType.equals("searchAllCompany")||
                            key.equals("phone") && selectSearchCompanyByType.equals("withCommentCompany")||
                            key.equals("phone") && selectSearchCompanyByType.equals("noCommentCompany") ) {
                        companies = companyService.findCompaniesByPhoneAndSearchPaymentStatus(value,selectSearchCompanyByPaymentStatus);
                        isNeedComment(selectSearchCompanyByType);
                        break;
                    }
                    if (key.equals("email") && selectSearchCompanyByType.equals("searchAllCompany")||
                            key.equals("email") && selectSearchCompanyByType.equals("withCommentCompany")||
                            key.equals("email") && selectSearchCompanyByType.equals("noCommentCompany") ) {
                        companies = companyService.findCompaniesByEmailAndSearchPaymentStatus(value,selectSearchCompanyByPaymentStatus);
                        isNeedComment(selectSearchCompanyByType);
                        break;
                    }
                    if (key.equals("contractNum") && selectSearchCompanyByType.equals("searchAllCompany")||
                            key.equals("contractNum") && selectSearchCompanyByType.equals("withCommentCompany")||
                            key.equals("contractNum") && selectSearchCompanyByType.equals("noCommentCompany") ) {
                        //companies = companyService.findCompaniesByContractAndSearchPaymentStatus(value,selectSearchCompanyByPaymentStatus);
                        isNeedComment(selectSearchCompanyByType);
                        break;
                    }
                }
                else{
                    if(selectSearchCompanyByType.equals("searchAllCompany")) {
                        companies = companyService.getCompaniesByPaymentStatus(selectSearchCompanyByPaymentStatus);
                        isNeedComment(selectSearchCompanyByType);
                    }
                    else{
                        companies = companyService.getCompaniesByPaymentStatus(selectSearchCompanyByPaymentStatus);
                        isNeedComment(selectSearchCompanyByType);
                    }
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

    private void isNeedComment(String selectSearchCompanyByType){
        if(selectSearchCompanyByType.equals("searchAllCompany")) {
            IsComments = false;
        }
        else{
            IsComments = true;
            if(selectSearchCompanyByType.equals("withCommentCompany")){
                choice=1;
            }
            else{
                choice=2;
            }
        }
    }

    }
