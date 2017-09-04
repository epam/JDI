package com.epam.test_generator.services;

import com.epam.test_generator.dao.SuitDAOImpl;
import com.epam.test_generator.entities.Suit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuitsService {

    @Autowired
    private SuitDAOImpl suitDAO;

    public List<Suit> getTestSuits() {
        return suitDAO.getAllTestEntities();
    }

    public Suit getTestSuit(long id) {
        return suitDAO.getEntity(id);
    }

    public void editTestSuit(Suit testSuit) {
        suitDAO.editTestEntity(testSuit);
    }

    public void removeTestSuit(long id) {
        suitDAO.removeTestEntity(id);
    }

    public Suit addTestSuit(Suit suit) {
        return suitDAO.addTestEntity(suit);
    }
}
