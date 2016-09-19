package ru.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 */
@Controller
public class SubPartitionController {

    @RequestMapping(value = "/subPartition/*", method = RequestMethod.GET)
    public String subPartition() {
        return "subPartition";
    }

}
