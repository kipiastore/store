package ru.store.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import ru.store.dao.interfaces.FileDAO;
import ru.store.dao.interfaces.ImageDAO;
import ru.store.entities.File;
import ru.store.entities.Image;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Component
public class DownloadImage implements HttpRequestHandler {

    public static final Map<String, String> FILE_TYPE_TO_CONTENT_TYPE;

    static {
        FILE_TYPE_TO_CONTENT_TYPE = new HashMap<>();
        FILE_TYPE_TO_CONTENT_TYPE.put("gif", "image/gif");
        FILE_TYPE_TO_CONTENT_TYPE.put("jpeg", "image/jpeg");
        FILE_TYPE_TO_CONTENT_TYPE.put("jpg", "image/jpeg");
        FILE_TYPE_TO_CONTENT_TYPE.put("png", "image/png");
        FILE_TYPE_TO_CONTENT_TYPE.put("bmp", "image/bmp");
    }

    @Autowired
    private ImageDAO imageDAO;

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String id = httpServletRequest.getParameter("id");
        Image image = imageDAO.getImage(Integer.valueOf(id));
        String[] tmp = image.getName().split("\\.");
        if (tmp.length > 1 && FILE_TYPE_TO_CONTENT_TYPE.get(tmp[tmp.length-1]) != null)
            httpServletResponse.setContentType(FILE_TYPE_TO_CONTENT_TYPE.get(tmp[tmp.length-1]));
        httpServletResponse.setHeader("Content-disposition","attachment; filename=" + image.getName());

        OutputStream out = httpServletResponse.getOutputStream();
        InputStream in = new ByteArrayInputStream(image.getFile());
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0){
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();
        // todo validation and exception handler
    }

}
