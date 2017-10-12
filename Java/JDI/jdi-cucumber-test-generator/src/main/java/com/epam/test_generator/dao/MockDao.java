package com.epam.test_generator.dao;

import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.Suit;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MockDao {

    static long counter = 3;

    static List<Suit> list = new ArrayList<>();
    static List<Case> caseList1 = new ArrayList<>();

    static {
        Suit suit1 = new Suit(1,"Suit 1", "Some description");
        Suit suit2 = new Suit(2,"Suit 2", "Some description");
        Suit suit3 = new Suit(3,"Suit 3", "Some description");

        suit1.setCases(caseList1);
        list.add(suit1);
        list.add(suit2);
        list.add(suit3);
    }

    public List<Suit> getSuits() {
        return list;
    }

    public Suit getSuit(long id){
        for (Suit suit : list) {
            if (suit.getId() == id) {
				return suit;
			}
        }

        return null;
    }

    public void editSuit(Suit suit){
        Suit suitFromList = getSuit(suit.getId());

        suitFromList.setDescription(suit.getDescription());
        suitFromList.setName(suit.getName());

        int i = list.indexOf(suitFromList);

        list.set(i, suitFromList);
    }

    public void removeSuit(long id){
        Suit suitFromList = getSuit(id);

        list.remove(suitFromList);
    }

    public Suit addSuit(Suit suit) {
        suit.setId(++counter);
        list.add(suit);

        return suit;
    }

}