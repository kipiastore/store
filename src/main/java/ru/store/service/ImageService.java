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

    public void createImage(Image image) {
        image.setName(image.getName().replaceAll(",", "").replaceAll("_", "").replaceAll(" ", ""));
        imageDAO.createImage(image);
    }

    public void deleteImage(Integer id) {
        imageDAO.deleteImage(id);
    }

    public void updateImage(Image image) {
        image.setName(image.getName().replaceAll(",", "").replaceAll("_", "").replaceAll(" ", ""));
        imageDAO.updateImage(image);
    }

    public List<Image> getImages() {
        return imageDAO.getImages();
    }

    public Image getImage(Integer id) {
        return imageDAO.getImage(id);
    }

    public void deleteImage(List<Integer> imageIds) {
        imageDAO.deleteImage(imageIds);
    }

}
