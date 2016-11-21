package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.FileDAO;
import ru.store.entities.File;

import java.util.List;

/**
 *
 */
@Service
public class FileService {

    @Autowired
    private FileDAO fileDAO;

    public void createFile(File file) {
        fileDAO.createFile(file);
    }

    public void deleteFile(Integer id) {
        fileDAO.deleteFile(id);
    }

    public void updateFile(File file) {
        fileDAO.updateFile(file);
    }

    public List<File> getFiles() {
        return fileDAO.getFiles();
    }

    public File getFile(Integer id) {
        return fileDAO.getFile(id);
    }
}
