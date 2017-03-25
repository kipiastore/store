package ru.store.dao.interfaces;

import ru.store.entities.Region;

import java.util.List;

/**
 *
 */
public interface RegionDAO {

    void createRegion(Region region);

    void deleteRegion(Integer id);

    List<Region> getRegions();

    Region getRegion(String name);

}
