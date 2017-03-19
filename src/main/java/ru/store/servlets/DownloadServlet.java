package ru.store.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import ru.store.dao.interfaces.FileDAO;
import ru.store.entities.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
@Component
public class DownloadServlet implements HttpRequestHandler {

    public static final Map<String, String> FILE_TYPE_TO_CONTENT_TYPE;

    static {
        FILE_TYPE_TO_CONTENT_TYPE = new HashMap<>();
        FILE_TYPE_TO_CONTENT_TYPE.put("txt", "text/plain");
        FILE_TYPE_TO_CONTENT_TYPE.put("pdf", "application/pdf");
        FILE_TYPE_TO_CONTENT_TYPE.put("doc", "application/msword");
        FILE_TYPE_TO_CONTENT_TYPE.put("xls", "application/vnd.ms-excel");
        FILE_TYPE_TO_CONTENT_TYPE.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        FILE_TYPE_TO_CONTENT_TYPE.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        FILE_TYPE_TO_CONTENT_TYPE.put("rar", "application/x-rar-compressed");
        FILE_TYPE_TO_CONTENT_TYPE.put("7z", "application/x-7z-compressed");
        FILE_TYPE_TO_CONTENT_TYPE.put("gif", "image/gif");
        FILE_TYPE_TO_CONTENT_TYPE.put("jpeg", "image/jpeg");
        FILE_TYPE_TO_CONTENT_TYPE.put("jpg", "image/jpeg");
        FILE_TYPE_TO_CONTENT_TYPE.put("png", "image/png");
        FILE_TYPE_TO_CONTENT_TYPE.put("bmp", "image/bmp");
    }

    @Autowired
    private FileDAO fileDAO;

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String id = httpServletRequest.getParameter("id");
        File file = fileDAO.getFile(Integer.valueOf(id));
        String[] tmp = file.getName().split("\\.");
        if (tmp.length > 1 && FILE_TYPE_TO_CONTENT_TYPE.get(tmp[tmp.length-1]) != null)
            httpServletResponse.setContentType(FILE_TYPE_TO_CONTENT_TYPE.get(tmp[tmp.length-1]));
        httpServletResponse.setHeader("Content-disposition","attachment; filename=" + file.getName());

        OutputStream out = httpServletResponse.getOutputStream();
        InputStream in = new ByteArrayInputStream(file.getFile());
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
