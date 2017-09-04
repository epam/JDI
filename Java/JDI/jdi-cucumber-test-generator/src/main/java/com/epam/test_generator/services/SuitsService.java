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

    public List<Suit> getSuits() {
        return suitDAO.getAllEntities();
    }

    public Suit getTestSuit(long id) {
        return suitDAO.getEntity(id);
    }

    public void editSuit(Suit suit) {
        suitDAO.editEntity(suit);
    }

    public void removeSuit(long id) {
        suitDAO.removeEntity(id);
    }

    public Suit addSuit(Suit suit) {
        return suitDAO.addEntity(suit);
    }
}
