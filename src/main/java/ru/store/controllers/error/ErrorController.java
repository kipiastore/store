package ru.store.controllers.error;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.store.entities.CountingPortalPage;
import ru.store.service.CountingService;

@Controller
public class ErrorController {

    @Autowired
    private CountingService countingService;

    @RequestMapping(value = "/404", method = RequestMethod.GET)
    public ModelAndView errorPage404() {
        ModelAndView modelAndView = new ModelAndView();

        //calculating portal visitors
        CountingPortalPage countingPortalPage = countingService.getCountPortalPage();
        countingPortalPage.setCountPortal();
        countingService.addCountPortalPage(countingPortalPage);
        modelAndView.addObject("countInfo","ресурса");
        modelAndView.addObject("portalCount",countingPortalPage.getCountPortal());

        modelAndView.setViewName("error/404");
        return modelAndView;
    }

}
