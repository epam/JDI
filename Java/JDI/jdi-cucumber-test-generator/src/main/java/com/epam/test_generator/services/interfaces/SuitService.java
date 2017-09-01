package com.epam.test_generator.services.interfaces;

import com.epam.test_generator.entities.Suit;

import java.util.List;

public interface SuitService {

       List<Suit> getTestSuits();

       Suit getTestSuit(long id);

       void editTestSuit(Suit testSuit);

       void removeTestSuit(long id);

       Suit addTestSuit(Suit suit);
}
