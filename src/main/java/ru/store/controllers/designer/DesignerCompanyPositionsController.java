package ru.store.controllers.designer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 */
@Controller
public class DesignerCompanyPositionsController {

    @RequestMapping(value = "/designer/positions/company/{id}", method = RequestMethod.GET)
    public ModelAndView positions(@PathVariable String id) {
        ModelAndView modelAndView = new ModelAndView();
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    @RequestMapping(value = "/designer/addsubpartitioninfos", method = RequestMethod.POST)
    public ModelAndView updateCompany(@RequestParam("positions") String positions) {
        ModelAndView modelAndView = new ModelAndView();
        try {

            modelAndView.addObject("successMessage", "Обновление проведено успешно.");
        } catch (Exception ex) {
            modelAndView.addObject("updateError", "Возникла ошибка. " + ex.getMessage());
            ex.printStackTrace();
        }
        Model model = new Model();
        loadPage(model, modelAndView);
        return modelAndView;
    }

    private void loadPage(Model model, ModelAndView modelAndView) {
        model.selectedPageNum = 2;
        loadCompanies(model);
        modelAndView.addObject("model", model);
        modelAndView.addObject("prefix", "");
        modelAndView.setViewName("designer/companypositions");
    }

    private void loadCompanies(Model model) {

    }

    private String getNormalName(String name) {
        if (name != null && name.length() > 24)
            return name.substring(0, 24) + "..";
        else
            return name;
    }

    @RequestMapping(value = "/designer/addsubpartitioninfos", method = RequestMethod.GET)
    public String redirect1() {
        return "redirect:/designer/positions";
    }
    @RequestMapping(value = "/designer/companypositionsearchcompany", method = RequestMethod.GET)
    public String redirect2() {
        return "redirect:/designer/positions";
    }

    public static class Model {
        public int selectedPageNum;
        public String message;

        public int getSelectedPageNum() {
            return selectedPageNum;
        }
        public String getMessage() {
            return message;
        }



    }
}
