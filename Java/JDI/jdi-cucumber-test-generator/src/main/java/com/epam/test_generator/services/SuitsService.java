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
    public void addEntity(Suit ts) {
        suitDAO.addEntity(ts);
    }

    @Override
    @Transactional
    public List<Suit> getAllEntities() {
        return suitDAO.getAllEntities();
    }

    @Override
    public Suit getEntity(Long id) {
        return suitDAO.getEntity(id);
    }

    @Override
    @Transactional
    public void removeEntity(Long id) {
        suitDAO.removeEntity(id);
    }
}
