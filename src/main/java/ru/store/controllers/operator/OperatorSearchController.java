package ru.store.controllers.operator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 */
@Controller
public class OperatorSearchController {

    @RequestMapping(value = "/operator/search", method = RequestMethod.GET)
    public String search(Model model, HttpServletRequest request) {
        String byKeyword = request.getParameter("byKeyword");
        String byCompany = request.getParameter("byCompany");
        String byAddress = request.getParameter("byAddress");

        CallServiceSearchModel.CompanyItem companyItem;
        //for () {}
        CallServiceSearchModel callServiceSearchModel = new CallServiceSearchModel();

        model.addAttribute("prefix", "../operator/");
        return "operator/search";
    }

    public static class CallServiceSearchModel {
        private List<CompanyItem> companyItems;

        public List<CompanyItem> getCompanyItems() {
            return companyItems;
        }
        public void setCompanyItems(List<CompanyItem> companyItems) {
            this.companyItems = companyItems;
        }

        public static class CompanyItem {
            private int companyId;
            private String companyName;

            public int getCompanyId() {
                return companyId;
            }
            public void setCompanyId(int companyId) {
                this.companyId = companyId;
            }
            public String getCompanyName() {
                return companyName;
            }
            public void setCompanyName(String companyName) {
                this.companyName = companyName;
            }
        }
    }
}
