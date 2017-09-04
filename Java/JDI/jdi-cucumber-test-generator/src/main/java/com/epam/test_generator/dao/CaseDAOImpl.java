package com.epam.test_generator.dao;

import com.epam.test_generator.dao.interfaces.EntitiesDAO;
import com.epam.test_generator.entities.Case;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CaseDAOImpl implements EntitiesDAO<Case>{

    @Override
    public Case addEntity(Case ts) {

    }

    @Override
    public List<Case> getAllEntities() {
        return null;
    }

    @Override
    public Case getEntity(Long id) {
        return null;
    }

    @Override
    public void removeEntity(Long id) {

    }
    
    @Override
    public void editEntity(Case ts) {

    } 
}
