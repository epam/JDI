package com.epam.test_generator.dao;

import com.epam.test_generator.dao.interfaces.EntitiesDAO;
import com.epam.test_generator.entities.TestSuit;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestSuitDAOImpl implements EntitiesDAO<TestSuit>{


    @Override
    public void addTestEntity(TestSuit ts) {

    }

    @Override
    public List<TestSuit> getAllTestEntities() {
        return null;
    }

    @Override
    public TestSuit getEntity(Long id) {
        return null;
    }

    @Override
    public void removeTestEntity(Long id) {

    }

}
