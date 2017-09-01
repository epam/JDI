package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.EntitiesDAO;
import com.epam.test_generator.entities.TestSuit;
import com.epam.test_generator.services.interfaces.EntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TestSuitsService implements EntitiesService<TestSuit>{

    @Autowired
    private EntitiesDAO<TestSuit> testSuitDAO;

    @Override
    @Transactional
    public void addTestEntity(TestSuit ts) {
        testSuitDAO.addTestEntity(ts);
    }

    @Override
    @Transactional
    public List<TestSuit> getAllTestEntities() {
        return testSuitDAO.getAllTestEntities();
    }

    @Override
    public TestSuit getEntity(Long id) {
        return testSuitDAO.getEntity(id);
    }

    @Override
    @Transactional
    public void removeTestEntity(Long id) {
        testSuitDAO.removeTestEntity(id);
    }
}
