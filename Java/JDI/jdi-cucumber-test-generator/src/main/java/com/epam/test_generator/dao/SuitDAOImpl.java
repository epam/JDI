package com.epam.test_generator.dao;

import com.epam.test_generator.dao.interfaces.EntitiesDAO;
import com.epam.test_generator.entities.Suit;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SuitDAOImpl implements EntitiesDAO<Suit>{


    @Override
    public void addTestEntity(Suit ts) {

    }

    @Override
    public List<Suit> getAllTestEntities() {
        return null;
    }

    @Override
    public Suit getEntity(Long id) {
        return null;
    }

    @Override
    public void removeTestEntity(Long id) {

    }

}
