package com.epam.test_generator.dao;

import com.epam.test_generator.dao.interfaces.EntitiesDAO;
import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.Suit;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class SuitDAOImpl implements EntitiesDAO<Suit>{


    List<Suit> suitList =  Arrays.asList(new Suit(new Long(2), "Mai suit", "First suit", new ArrayList<>()),
            new Suit(new Long(1), "Mai suit", "First suit", new ArrayList<>()));

    Long counter = new Long(2);

    @Override
    public Suit addTestEntity(Suit ts) {
        ts.setId(++counter);
        suitList.add(ts);
        return ts;
    }

    @Override
    public List<Suit> getAllTestEntities() {
        return suitList;
    }

    @Override
    public Suit getEntity(Long id) {
        return suitList.get(0);
    }

    @Override
    public void removeTestEntity(Long id) {
        suitList.remove(id);
    }

    @Override
    public void editTestEntity(Suit ts) {
        suitList.set(Math.toIntExact(ts.getId()), ts);
    }
}
