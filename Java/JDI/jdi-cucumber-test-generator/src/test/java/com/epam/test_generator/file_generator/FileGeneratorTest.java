package com.epam.test_generator.file_generator;


import com.epam.test_generator.entities.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileGeneratorTest extends Assert{

    private FileGenerator fileGenerator;

    private Suit suit;
    private List<Case> cases;
    private Set<Tag> tags;

    @Before
    public void prepareFileGenerator(){
        fileGenerator= new FileGenerator();
    }

    @Test
    public void suitWithCasesTest1() throws IOException {
        String result;
        suit = new Suit();
        suit.setId(1L);
        suit.setName("suit1");
        suit.setDescription("description1");
        suit.setPriority(1);
        suit.setTags("");

        cases = new ArrayList<>();
        Case caze1 = new Case();
        caze1.setId(1L);
        caze1.setDescription("case1");
        caze1.setPriority(1);
        caze1.setTags(null);
        Case caze2 = new Case();
        caze2.setId(2L);
        caze2.setDescription("case2");
        caze2.setPriority(1);
        tags = new HashSet<>();
        tags.add(new Tag("@tags"));
        caze2.setTags(tags);

        List<Step> steps=new ArrayList<>();
        Step step1 = new Step();
        step1.setId(1L);
        step1.setDescription("given1");
        step1.setRowNumber(1);
        step1.setType(StepType.GIVEN.ordinal());

        Step step2 = new Step();
        step2.setId(2L);
        step2.setRowNumber(2);
        step2.setDescription("when1");
        step2.setType(StepType.WHEN.ordinal());

        Step step3 = new Step();
        step3.setId(3L);
        step3.setRowNumber(3);
        step3.setDescription("then1");
        step3.setType(StepType.THEN.ordinal());

        steps.add(step1);
        steps.add(step2);
        steps.add(step3);
        caze1.setSteps(steps);

        Step step4= new Step();
        step4.setId(4L);
        step4.setRowNumber(4);
        step4.setDescription("given2");
        step4.setType(StepType.GIVEN.ordinal());
        ArrayList<Step> steps2 = new ArrayList<>();
        steps2.add(step4);
        caze2.setSteps(steps2);

        cases.add(caze1);
        cases.add(caze2);
        suit.setCases(cases);

        File expectedFile = new File("src/test/resources/FileGeneratorTest1");
        String realResult = fileGenerator.generate(suit,cases);
        String expectedResult = new Scanner(expectedFile).useDelimiter("\\Z").next();
        assertEquals(expectedResult.trim(),realResult.trim());
    }

    @Test
    public void suitWithCaseWithoutSteps() throws IOException {

        String result;
        suit = new Suit();
        suit.setId(1L);
        suit.setName("suit1");
        suit.setDescription("description1");
        suit.setPriority(1);
        suit.setTags("");

        cases = new ArrayList<>();
        Case caze1 = new Case();
        caze1.setId(1L);
        caze1.setDescription("case3");
        caze1.setPriority(1);
        caze1.setTags(null);
        cases.add(caze1);
        suit.setCases(cases);

        File expectedFile = new File("src/test/resources/FileGeneratorTest2");

        String realResult = fileGenerator.generate(suit,cases);
        String expectedResult = new Scanner(expectedFile).useDelimiter("\\Z").next();
        assertEquals(expectedResult.trim(),realResult.trim());

    }

    @Test(expected = NullPointerException.class)
    public void nullPointerSuitTest() throws IOException{
        fileGenerator.generate(null,new ArrayList<>());
    }

    @Test(expected = NullPointerException.class)
    public void nullPointerCasesTest() throws IOException{
        String result;
        suit = new Suit();
        suit.setId(1L);
        suit.setName("suit1");
        suit.setDescription("description1");
        suit.setPriority(1);
        suit.setTags("");
        fileGenerator.generate(suit,null);
    }

    @Test
    public void suitWithoutInnerCasesTest() throws IOException{
        suit = new Suit();
        suit.setId(1L);
        suit.setName("suit1");
        suit.setDescription("description1");
        suit.setPriority(1);
        suit.setTags("");

        cases = new ArrayList<>();
        Case caze1 = new Case();
        caze1.setId(1L);
        caze1.setDescription("case3");
        caze1.setPriority(1);
        caze1.setTags(null);
        cases.add(caze1);

        File expectedFile = new File("src/test/resources/FileGeneratorTest2");
        String realResult = fileGenerator.generate(suit,cases);
        String expectedResult = new Scanner(expectedFile).useDelimiter("\\Z").next();
        assertEquals(expectedResult.trim(),realResult.trim());

    }
}