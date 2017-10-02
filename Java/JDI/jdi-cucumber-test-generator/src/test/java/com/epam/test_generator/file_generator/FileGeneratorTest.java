package com.epam.test_generator.file_generator;


import com.epam.test_generator.dto.CaseDTO;
import com.epam.test_generator.dto.StepDTO;
import com.epam.test_generator.dto.SuitDTO;
import com.epam.test_generator.dto.TagDTO;
import com.epam.test_generator.entities.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class FileGeneratorTest extends Assert{

    private FileGenerator fileGenerator;

    private SuitDTO suit;
    private List<CaseDTO> cases;
    private Set<TagDTO> tags;

    @Before
    public void prepareFileGenerator(){
        fileGenerator= new FileGenerator();
    }

    @Test
    public void suitWithCasesTest1() throws IOException {
        String result;
        suit = new SuitDTO();
        suit.setId(1L);
        suit.setName("suit1");
        suit.setDescription("description1");
        suit.setPriority(1);
        suit.setTags("");

        cases = new ArrayList<>();
        CaseDTO caze1 = new CaseDTO();
        caze1.setId(1L);
        caze1.setDescription("case1");
        caze1.setPriority(1);
        caze1.setTags(null);
        CaseDTO caze2 = new CaseDTO();
        caze2.setId(2L);
        caze2.setDescription("case2");
        caze2.setPriority(1);
        tags = new HashSet<>();
        tags.add(new TagDTO("@tags"));
        caze2.setTags(tags);

        List<StepDTO> steps=new ArrayList<>();
        StepDTO step1 = new StepDTO();
        step1.setId(1L);
        step1.setDescription("given1");
        step1.setRowNumber(1);
        step1.setType(StepType.GIVEN.ordinal());

        StepDTO step2 = new StepDTO();
        step2.setId(2L);
        step2.setRowNumber(2);
        step2.setDescription("when1");
        step2.setType(StepType.WHEN.ordinal());

        StepDTO step3 = new StepDTO();
        step3.setId(3L);
        step3.setRowNumber(3);
        step3.setDescription("then1");
        step3.setType(StepType.THEN.ordinal());

        steps.add(step1);
        steps.add(step2);
        steps.add(step3);
        caze1.setSteps(steps);

        StepDTO step4= new StepDTO();
        step4.setId(4L);
        step4.setRowNumber(4);
        step4.setDescription("given2");
        step4.setType(StepType.GIVEN.ordinal());
        ArrayList<StepDTO> steps2 = new ArrayList<>();
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
        suit = new SuitDTO();
        suit.setId(1L);
        suit.setName("suit1");
        suit.setDescription("description1");
        suit.setPriority(1);
        suit.setTags("");

        cases = new ArrayList<>();
        CaseDTO caze1 = new CaseDTO();
        caze1.setId(1L);
        caze1.setDescription("case3");
        caze1.setPriority(1);
        caze1.setTags(null);
        caze1.setSteps(new ArrayList<StepDTO>());
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
        suit = new SuitDTO();
        suit.setId(1L);
        suit.setName("suit1");
        suit.setDescription("description1");
        suit.setPriority(1);
        suit.setTags("");
        fileGenerator.generate(suit,null);
    }

    @Test
    public void suitWithoutInnerCasesTest() throws IOException{
        suit = new SuitDTO();
        suit.setId(1L);
        suit.setName("suit1");
        suit.setDescription("description1");
        suit.setPriority(1);
        suit.setTags("");

        cases = new ArrayList<>();
        CaseDTO caze1 = new CaseDTO();
        caze1.setId(1L);
        caze1.setDescription("case3");
        caze1.setPriority(1);
        caze1.setTags(null);
        caze1.setSteps(new ArrayList<>());
        cases.add(caze1);

        File expectedFile = new File("src/test/resources/FileGeneratorTest2");
        String realResult = fileGenerator.generate(suit,cases);
        String expectedResult = new Scanner(expectedFile).useDelimiter("\\Z").next();
        assertEquals(expectedResult.trim(),realResult.trim());

    }
}