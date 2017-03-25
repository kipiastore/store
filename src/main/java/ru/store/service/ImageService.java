package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.ImageDAO;
import ru.store.entities.Image;

import java.util.List;

/**
 *
 */
@Service
public class ImageService {

    @Autowired
    private ImageDAO imageDAO;

    void createImage(Image image) {
        imageDAO.createImage(image);
    }

    void deleteImage(Integer id) {
        imageDAO.deleteImage(id);
    }

    void updateImage(Image image) {
        imageDAO.updateImage(image);
    }

    List<Image> getImages() {
        return imageDAO.getImages();
    }

    Image getImage(Integer id) {
        return imageDAO.getImage(id);
    }

}
