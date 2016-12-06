package ru.store.controllers.portal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.store.dao.interfaces.SubPartitionDAO;

import javax.servlet.http.HttpServletRequest;

/**
 *
 */
@Controller
public class SubPartitionController {

    @Autowired
    private SubPartitionDAO subPartitionDAO;

    @RequestMapping(value = "/subPartition/*", method = RequestMethod.GET)
    public ModelAndView subPartition(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();

        String[] splitResult = request.getRequestURL().toString().split("subPartition/");

        int subPartitionId;
        if (splitResult.length == 2 && splitResult[1].matches("\\d+")) {
            subPartitionId = Integer.valueOf(splitResult[1]);
        } else {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }
        if (subPartitionDAO.getSubPartitionById(subPartitionId) == null) {
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        }

        modelAndView.addObject("subPartitionId", subPartitionId);

        modelAndView.addObject("prefix", "../");
        modelAndView.setViewName("portal/subpartition");
        return modelAndView;
    }

}
