package ru.store.dao.implementation;

import ru.store.dao.interfaces.BestResourceDAO;
import ru.store.entities.BestResource;
import ru.store.entities.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asura on 14.09.2016.
 */
public class BestResourceDAOImpl implements BestResourceDAO {

    @Override
    public List<BestResource> getBestResources() {
        List<BestResource> bestResourceList = new ArrayList<>();

        // Mock
        for (int i = 0; i < 18; i++) {
            BestResource bestResource = new BestResource();
            bestResource.setId(i+"");
            bestResource.setImageUrl("testLogo.jpg");
            bestResource.setTitle("Test");
            bestResource.setResource(new Resource(i+""));
            bestResourceList.add(bestResource);
        }

        return bestResourceList;
    }
}
