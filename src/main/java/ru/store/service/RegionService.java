package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.RegionDAO;
import ru.store.entities.Region;

import java.util.List;

/**
 *
 */
@Service
public class RegionService {

    @Autowired
    private RegionDAO regionDAO;

    public void createRegion(Region region) {
        region.setName(region.getName().toUpperCase());
        regionDAO.createRegion(region);
    }

    public void deleteRegion(Integer id) {
        regionDAO.deleteRegion(id);
    }

    public List<Region> getRegions() {
        return regionDAO.getRegions();
    }

    public Region getRegion(String name) {
        return regionDAO.getRegion(name);
    }


}
