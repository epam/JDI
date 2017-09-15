package com.epam.test_generator.services;

import com.epam.test_generator.dao.interfaces.SuitDAO;
import com.epam.test_generator.dto.DozerMapper;
import com.epam.test_generator.dto.SuitDTO;
import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.Step;
import com.epam.test_generator.entities.StepType;
import com.epam.test_generator.entities.Suit;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;

@Transactional
@Service
public class SuitService {

    @Autowired
    private SuitDAO suitDAO;

    @Autowired
    private DozerMapper mapper;


    public static void main(String[] args) throws Exception {
        final File DEST = new File("C:\\Users\\Serj\\Desktop\\result.feature");

//        Configuration for freemarker templates
        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setLocale(Locale.US);

//        Where do we load the templates from:
//        (Need to find the way to specify relative path to .ftl file)
        configuration.setDirectoryForTemplateLoading(new File("C:\\Users\\Serj\\IdeaProjects\\JDI\\Java\\JDI\\jdi-cucumber-test-generator\\src\\main\\resources\\"));
//        setClassForTemplateLoading(SuitService.class, "resorces");

        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

//        Prepare the template input:
        Map<String, Object> input = new HashMap<>();

        input.put("title", "Title example");
//        Step step = new Step(1L, 1, "description", StepType.ANY);
//
//        input.put("exampleObject", step);
        StepType[] type = StepType.values();

        List<Step> steps = new ArrayList<>();
        steps.add(new Step(2L, 2, "description", StepType.WHEN));
        steps.add(new Step(3L, 3, "description", StepType.BUT));
        steps.add(new Step(4L, 4, "description", StepType.AND));
        steps.add(new Step(5L, 5, "description", StepType.THEN));


        Suit suit = new Suit();
        suit.setId(1L);
        suit.setDescription("this is a suit feature description");
        suit.setName("Suit 1");
        suit.setPriority(1);
        suit.setTags("@tags");
        suit.setCreationDate("09/15/2017");

        Case case1 = new Case(1L, "case description1", null, 1, "@caseTags1");
        Case case2 = new Case(5L, "case description2", null, 1, "@caseTags2");
        case1.setSteps(steps);
        case2.setSteps(steps);

        List<Case> cases = new ArrayList<>();
        cases.add(case1);
        cases.add(case2);

        suit.setCases(cases);

        input.put("suit", suit);
        input.put("types", type);

        Template template = configuration.getTemplate("testFeature.ftl");

        Writer consoleWriter = new OutputStreamWriter(System.out);
        Writer fileWriter = new FileWriter(DEST);
        try {
            template.process(input, consoleWriter);
            template.process(input, fileWriter);
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            if (consoleWriter != null){
                consoleWriter.close();
            }
            if (fileWriter != null){
                fileWriter.close();
            }
        }

//        END OF FREEMARKER TEST

    }

    public List<SuitDTO> getSuits() {
        List<SuitDTO> suitDTOlist = new ArrayList<>();

        for(Suit suit: suitDAO.findAll()){
            SuitDTO suitDTO = new SuitDTO();

            mapper.map(suit, suitDTO);
            suitDTOlist.add(suitDTO);
        }

        return suitDTOlist;
    }

    public SuitDTO getSuit(long id) {
        SuitDTO suitDTO = new SuitDTO();

        mapper.map(
          suitDAO.findOne(id), suitDTO);

        return suitDTO;
    }

    public SuitDTO updateSuit(SuitDTO suitDTO) {
        Suit suit = suitDAO.getOne(suitDTO.getId());
        List<Case> cases = suit.getCases();

        mapper.map(suitDTO, suit);
        suit.setCases(cases);
        mapper.map(suitDAO.save(suit), suitDTO);

        return suitDTO;
    }

    public void removeSuit(long id) {
        suitDAO.delete(id);
    }

    public SuitDTO addSuit(SuitDTO suitDTO) {
        Suit suit = new Suit();

        mapper.map(suitDTO,suit);
        suit = suitDAO.save(suit);
        mapper.map(suit, suitDTO);

        return suitDTO;
    }

    public String generateStream(Long suitId, List<Long> CaseIds) throws IOException {

        Suit suit = suitDAO.findOne(suitId);
        StringBuilder output = new StringBuilder("Feature: ");
            output.append(suit.getName());
            output.append('\n');
            for (Case caze : suit.getCases()) {
                if (CaseIds.contains(caze.getId())) {
                    output.append("Scenario: ");
                    output.append(caze.getDescription());
                    output.append('\n');

                    for (Step step: caze.getSteps()) {
                        output.append('\t');
                        output.append(String.valueOf(StepType.values()[step.getType() - 1]));
                        output.append(' ');
                        output.append(step.getDescription());
                        output.append('\n');
                    }
                }
            }

        return output.toString();
    }
}