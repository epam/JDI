package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.SuitDAO;
import com.epam.test_generator.entities.Suit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuitService {

    @Autowired
    private SuitDAO suitDAO;

    public List<Suit> getSuits() {
        return suitDAO.findAll();
    }

    public Suit getSuit(long id) {
        return suitDAO.findOne(id);
    }

    public void editSuit(Suit suit) {
        suitDAO.save(suit);
    }

    public void removeSuit(long id) {
        suitDAO.delete(id);
    }

    public Suit addSuit(Suit suit) {
        return suitDAO.save(suit);
    }

    public Suit getSuitByName(String name) {
        return suitDAO.getSuitByName(name);
    }
}