package com.epam.test_generator.dao;

import com.epam.test_generator.dao.interfaces.EntitiesDAO;
import com.epam.test_generator.entities.Case;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CaseDAOImpl implements EntitiesDAO<Case>{

    @Override
    public Case addTestEntity(Case ts) {
        return null;
    }

    @Override
    public List<Case> getAllTestEntities() {
        return null;
    }

    @Override
    public Case getEntity(Long id) {
        return null;
    }

    @Override
    public void removeTestEntity(Long id) {

    }

    @Override
    public void editTestEntity(Case ts) {

    }
}
