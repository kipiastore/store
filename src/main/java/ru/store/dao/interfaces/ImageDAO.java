package ru.store.dao.interfaces;

import ru.store.entities.Image;

import java.util.List;

/**
 *
 */
public interface ImageDAO {

    void createImage(Image image);

    void deleteImage(Integer id);

    void updateImage(Image image);

    List<Image> getImages();

    Image getImage(Integer id);

}
