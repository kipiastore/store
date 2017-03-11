package ru.store.dao.interfaces;

import ru.store.entities.File;

import java.util.List;

/**
 *
 */
public interface FileDAO {

    void createFile(File file);

    void deleteFile(Integer id);

    void updateFile(File file);

    List<File> getFiles();

    File getFile(Integer id);

}
