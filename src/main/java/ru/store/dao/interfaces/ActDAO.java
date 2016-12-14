package ru.store.dao.interfaces;

import ru.store.entities.Act;

import java.util.List;

/**
 *
 */
public interface ActDAO {

    void createAct(Act report);

    void updateAct(Act report);

    void deleteAct(Integer id);

    List<Act> getActs();

    Act getAct(Integer id);

    List<Act> getActsByCompanyId(Integer companyId);

}
