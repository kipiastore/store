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

    public static final Map<String, String> fileTypeToContentType;

    static {
        fileTypeToContentType = new HashMap<>();
        fileTypeToContentType.put("txt", "text/plain");
        fileTypeToContentType.put("pdf", "application/pdf");
        fileTypeToContentType.put("doc", "application/msword");
        fileTypeToContentType.put("xls", "application/vnd.ms-excel");
        fileTypeToContentType.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        fileTypeToContentType.put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", "docx");
        fileTypeToContentType.put("rar", "application/x-rar-compressed");
        fileTypeToContentType.put("7z", "application/x-7z-compressed");
        fileTypeToContentType.put("gif", "image/gif");
        fileTypeToContentType.put("jpeg", "image/jpeg");
        fileTypeToContentType.put("jpg", "image/jpeg");
        fileTypeToContentType.put("png", "image/png");
        fileTypeToContentType.put("bmp", "image/bmp");
    }

    @Autowired
    private FileDAO fileDAO;

    @Override
    public void handleRequest(HttpServletRequest httpServletRequest,
                              HttpServletResponse httpServletResponse) throws ServletException, IOException {
        String id = httpServletRequest.getParameter("id");
        File file = fileDAO.getFile(Integer.valueOf(id));
        String[] tmp = file.getName().split("\\.");
        if (tmp.length > 1 && fileTypeToContentType.get(tmp[tmp.length-1]) != null)
            httpServletResponse.setContentType(fileTypeToContentType.get(tmp[tmp.length-1]));
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
