package ru.store.dao.interfaces;

import ru.store.entities.CountingPortalPage;

/**
 * Created by Akex on 27.03.2017.
 */
public interface CountingDAO {

    void addCountPortalPage(CountingPortalPage c);

    CountingPortalPage getCountPortalPage();

}
