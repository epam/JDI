package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.EntitiesDAO;
import com.epam.test_generator.entities.Case;
import com.epam.test_generator.services.interfaces.EntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CasesService implements EntitiesService<Case>{

    @Autowired
    private EntitiesDAO<Case> caseDAO;

    @Override
    @Transactional
    public void addEntity(Case ts) {
        caseDAO.addEntity(ts);

    }

    @Override
    @Transactional
    public List<Case> getAllEntities() {
        return caseDAO.getAllEntities();
    }

    @Override
    public Case getEntity(Long id) {
        return caseDAO.getEntity(id);
    }

    @Override
    @Transactional
    public void removeEntity(Long id) {
        caseDAO.removeEntity(id);
    }
}
