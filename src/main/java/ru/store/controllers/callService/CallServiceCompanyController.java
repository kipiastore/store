package ru.store.controllers.callService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.store.controllers.callService.models.CallServiceCompanyModel;
import ru.store.dao.interfaces.CompanyDAO;
import ru.store.entities.Company;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@Controller
public class CallServiceCompanyController {

    @Autowired
    private CompanyDAO companyDAO;

    @RequestMapping(value = "/callService/company/*", method = RequestMethod.GET)
    public String company(HttpServletRequest request, Model model) {
        String[] splitResult = request.getRequestURL().toString().split("company/");
        int companyId;
        if (splitResult.length == 2 && splitResult[1].matches("\\d+"))
            companyId = Integer.valueOf(splitResult[1]);
        else
            return "redirect:/callService";
        Company company = companyDAO.getCompanyById(companyId);
        CallServiceCompanyModel callServiceCompanyModel = new CallServiceCompanyModel();
        callServiceCompanyModel.setCompanyName(company.getName());
        callServiceCompanyModel.setCompanyId(company.getId());
        model.addAttribute("callServiceCompanyModel", callServiceCompanyModel);

        model.addAttribute("prefix", "../");
        return "callService/callServiceCompany";
    }

}
