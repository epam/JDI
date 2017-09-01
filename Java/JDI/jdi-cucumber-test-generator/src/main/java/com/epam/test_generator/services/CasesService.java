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
    public void addTestEntity(Case ts) {
        caseDAO.addTestEntity(ts);

    }

    @Override
    @Transactional
    public List<Case> getAllTestEntities() {
        return caseDAO.getAllTestEntities();
    }

    @Override
    public Case getEntity(Long id) {
        return caseDAO.getEntity(id);
    }

    @Override
    @Transactional
    public void removeTestEntity(Long id) {
        caseDAO.removeTestEntity(id);
    }
}
