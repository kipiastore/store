package ru.store.api.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.store.entities.Act;
import ru.store.service.ActService;

import java.util.List;

/**
 *
 */
@RestController
public class ActResource {

    @Autowired
    private ActService actService;

    @RequestMapping(value = "/api/manager/resource/v1/act/company/{id}", method = RequestMethod.GET)
    public List<Act> getAct(@PathVariable String id) {
        return actService.getActsByCompanyId(Integer.valueOf(id));
    }

    @RequestMapping(value = "/api/manager/resource/v1/act/erase/{id}", method = RequestMethod.GET)
    public void eraseAct(@PathVariable String id) {
        actService.deleteAct(Integer.valueOf(id));
    }

}
