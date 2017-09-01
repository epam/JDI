package com.epam.test_generator.services;

import com.epam.test_generator.dao.SuitDAOImpl;
import com.epam.test_generator.dao.interfaces.EntitiesDAO;
import com.epam.test_generator.entities.Suit;
import com.epam.test_generator.services.interfaces.EntitiesService;
import com.epam.test_generator.services.interfaces.SuitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SuitsService implements SuitService {

    @Autowired
    private SuitDAOImpl suitDAO;

    @Override
    public List<Suit> getTestSuits() {
        return suitDAO.getAllTestEntities();
    }

    @Override
    public Suit getTestSuit(long id) {
        return suitDAO.getEntity(id);
    }

    @Override
    public void editTestSuit(Suit testSuit) {
        suitDAO.editTestEntity(testSuit);
    }

    @Override
    public void removeTestSuit(long id) {
        suitDAO.removeTestEntity(id);
    }

    @Override
    public Suit addTestSuit(Suit suit) {
        return suitDAO.addTestEntity(suit);
    }
}
