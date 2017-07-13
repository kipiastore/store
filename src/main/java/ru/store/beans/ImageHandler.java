package ru.store.beans;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.store.entities.Image;
import ru.store.exceptions.NotSupportedFormat;
import ru.store.servlets.DownloadImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;

/**
 *
 */
@Component
public class ImageHandler {

    private BufferedImage convertToBufferedImage(java.awt.Image image) {
        BufferedImage newImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = newImage.createGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return newImage;
    }

    public Image handle(MultipartFile multipartFile) throws IOException {
        String[] tmp = multipartFile.getOriginalFilename().split("\\.");
        if (tmp.length >= 2) {
            if (DownloadImage.FILE_TYPE_TO_CONTENT_TYPE.get(tmp[tmp.length-1]) == null) {
                throw new NotSupportedFormat("Этот формат не поддерживается.");
            }
            Image image = new Image();
            BufferedImage imageData = ImageIO.read(new ByteArrayInputStream(multipartFile.getBytes()));
            int currentHeight = imageData.getHeight();
            int currentWidth = imageData.getWidth();
            /*
            if (currentHeight > 250) {
                double newWidth = currentWidth * 250 / currentHeight;
                java.awt.Image scaled = imageData.getScaledInstance((int) newWidth, 250, java.awt.Image.SCALE_SMOOTH);

                Graphics2D g = imageData.createGraphics();
                g.drawImage(scaled, 0, 0, null);
                g.dispose();

                BufferedImage bufferedImage = convertToBufferedImage(scaled);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(bufferedImage, tmp[tmp.length-1], baos);
                baos.flush();
                image.setFile(baos.toByteArray());
                baos.close();
            } else { */
                image.setFile(multipartFile.getBytes());
            //}
            image.setName(multipartFile.getOriginalFilename());
            return image;
        }
        return null;
    }

}
