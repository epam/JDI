package com.epam.test_generator;

import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.Suit;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FeatureFileGenerator {

    public static void main(String[] args) {
        Suit suit = new Suit("New suit", "suit description");
        List<Case> caseList = new ArrayList<>();

        Case caze = new Case();
        caze.setDescription("case #1 description");
        caze.setSteps("step #1");
        caseList.add(caze);

        caze = new Case();
        caze.setDescription("case #2 description");
        caze.setSteps("step #3");
        caseList.add(caze);

        suit.setCases(caseList);

        try {
            new FeatureFileGenerator().generateFile(suit, "C:\\Users\\Arsenii_Nazarov\\Feature\\");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void generateFile(Suit suit, String path) throws IOException {
        StringBuilder buf = new StringBuilder(path);
        buf.append(suit.getName());
        buf.append(".feature");

        BufferedWriter outputFile = new BufferedWriter(new FileWriter(buf.toString()));

        buf = new StringBuilder("Feature: ");
        buf.append(suit.getName());
        outputFile.write(buf.toString());
        outputFile.newLine();

        outputFile.write(suit.getDescription());
        outputFile.newLine();

        for (Case caze: suit.getCases()) {
            buf = new StringBuilder("Scenario: ");
            buf.append(caze.getDescription());
            outputFile.write(buf.toString());
            outputFile.newLine();

//            For new Step from branch 112
//
//            for (Step step: caze.getSteps()) {
//                buf = new StringBuilder(step.getKeyword());
//                buf.append(" ");
//                buf.append(step.getDescription);
//                outputFile.write(buf.toString());
//                outputFile.newLine();
//            }

            outputFile.write(caze.getSteps());

            outputFile.newLine();
        }

        outputFile.flush();
        outputFile.close();
    }
}
