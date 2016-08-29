package ru.store.dao.interfaces;


import ru.store.entities.Page;
import ru.store.entities.User;

import java.util.List;

public interface PageDAO {

    List<Page> getPages();
    List<Page> getPages(User userByUserId);
    List<Page> getPages(String title);
    Page getPage(int id);
    List<User> getUsers();
    void createPage(Page page);

}
