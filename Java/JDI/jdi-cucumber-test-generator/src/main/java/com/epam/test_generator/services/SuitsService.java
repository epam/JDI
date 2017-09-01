package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.EntitiesDAO;
import com.epam.test_generator.entities.Suit;
import com.epam.test_generator.services.interfaces.EntitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SuitsService implements EntitiesService<Suit>{

    @Autowired
    private EntitiesDAO<Suit> suitDAO;

    @Override
    @Transactional
    public void addTestEntity(Suit ts) {
        suitDAO.addTestEntity(ts);
    }

    @Override
    @Transactional
    public List<Suit> getAllTestEntities() {
        return suitDAO.getAllTestEntities();
    }

    @Override
    public Suit getEntity(Long id) {
        return suitDAO.getEntity(id);
    }

    @Transactional
    void editTestSuite (TestSuit testSuit) {
        TestSuit ts = testSuitDAO.getEntity(testSuit.getId());
        if (!((ts.getName().equals(testSuit.getName())) && (ts.getDescription().equals(testSuit.getDescription())))) {
            testSuitDAO.addTestEntity(ts);
        }
    }

    @Override
    @Transactional
    public void removeTestEntity(Long id) {
        suitDAO.removeTestEntity(id);
    }
}
