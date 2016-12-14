package ru.store.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.store.dao.interfaces.ActDAO;
import ru.store.dao.interfaces.FileDAO;
import ru.store.entities.Act;

import java.util.List;

/**
 *
 */
@Service
public class ActService {

    @Autowired
    private ActDAO actDAO;
    @Autowired
    private FileDAO fileDAO;

    public void createAct(Act report) {
        actDAO.createAct(report);
    }

    public void updateAct(Act report) {
        actDAO.updateAct(report);
    }

    public void deleteAct(Integer id) {
        fileDAO.deleteFile(actDAO.getAct(id).getFileId());
        actDAO.deleteAct(id);
    }

    public List<Act> getActs() {
        return actDAO.getActs();
    }

    public Act getAct(Integer id) {
        return actDAO.getAct(id);
    }

    public List<Act> getActsByCompanyId(Integer companyId) {
        return actDAO.getActsByCompanyId(companyId);
    }
}
