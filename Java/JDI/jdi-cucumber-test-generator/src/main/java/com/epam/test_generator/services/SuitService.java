package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.SuitDAO;
import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.Suit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
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

    public ByteArrayInputStream generateStream(Long suitId, List<Long> CaseIds) throws IOException {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Suit suit = suitDAO.findOne(suitId);

        try (PrintWriter writer = new PrintWriter(stream)) {
            writer.print("Feature: ");
            writer.print(suit.getName());
            writer.println();
            for (Case caze : suit.getCases()) {
                if (CaseIds.contains(caze.getId())) {
                    writer.print("Scenario: ");
                    writer.print(caze.getDescription());
                    writer.println();
//            For new Step from branch 112
//
//            for (Step step: caze.getSteps()) {
//                buf = new StringBuilder(step.getKeyword());
//                buf.append(" ");
//                buf.append(step.getDescription);
//                outputFile.write(buf.toString());
//                outputFile.newLine();
//            }
                }
            }
        }

        stream.close();
        return new ByteArrayInputStream(stream.toByteArray());
    }
}