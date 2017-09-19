package com.epam.test_generator.file_generator;


import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.Step;
import com.epam.test_generator.entities.StepType;
import com.epam.test_generator.entities.Suit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileGeneratorTest extends Assert{

    private FileGenerator fileGenerator;

    private Suit suit;
    private List<Case> cases;

    @Before
    public void prepareFileGenerator(){
        fileGenerator= new FileGenerator();
        String result;
        suit = new Suit();
        suit.setName("Suit1");
        suit.setDescription("Description");
        suit.setPriority(1);
        suit.setTags("");

        cases = new ArrayList<>();
        Case caze1 = new Case();
        caze1.setDescription("Case1");
        caze1.setPriority(1);
        caze1.setTags("");
        Case caze2 = new Case();
        caze2.setDescription("Case2");
        caze2.setPriority(1);
        caze2.setTags("");

        List<Step> steps=new ArrayList<>();
        Step step1 = new Step();
        step1.setDescription("given1");
        step1.setType(StepType.GIVEN.ordinal());

        Step step2 = new Step();
        step2.setDescription("when1");
        step2.setType(StepType.WHEN.ordinal());

        Step step3 = new Step();
        step3.setDescription("then1");
        step3.setType(StepType.THEN.ordinal());

        steps.add(step1);
        steps.add(step2);
        steps.add(step3);
        caze1.setSteps(steps);

        Step step4= new Step();
        step4.setDescription("given2");
        step4.setType(StepType.GIVEN.ordinal());
        ArrayList<Step> steps2 = new ArrayList<>();
        steps2.add(step4);
        caze2.setSteps(steps2);

        cases.add(caze1);
        cases.add(caze2);
        suit.setCases(cases);
    }

    @Ignore
    @Test
    public void suitWithCasesTest1() throws IOException {
        File expectedFile = new File("src/test/resources/FileGeneratorTest1");
        String realResult = fileGenerator.generate(suit,cases);
        String expectedResult=new Scanner(expectedFile).useDelimiter("\\Z").next().trim();
        assertSame(expectedResult,realResult);
    }
}