package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.CountingDAO;
import ru.store.entities.CountingPortalPage;

/**
 * Created by Akex on 27.03.2017.
 */
@Service
public class CountingService {

    @Autowired
    private CountingDAO countingDAO;

    public void addCountPortalPage(CountingPortalPage c) {
        countingDAO.addCountPortalPage(c);
    }

    public CountingPortalPage getCountPortalPage() {
        return countingDAO.getCountPortalPage();
    }

}
