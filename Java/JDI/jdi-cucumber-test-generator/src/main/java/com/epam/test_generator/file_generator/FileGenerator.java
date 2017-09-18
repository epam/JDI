package com.epam.test_generator.file_generator;

import com.epam.test_generator.dao.interfaces.SuitDAO;
import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.StepType;
import com.epam.test_generator.entities.Suit;
import com.epam.test_generator.services.SuitService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

@Component
public class FileGenerator {

    @Autowired
    private SuitDAO suitDAO;

    public String generate(Suit suit, List<Case> cases)  throws IOException {

        Configuration configuration = new Configuration();
        configuration.setDefaultEncoding("UTF-8");
        configuration.setLocale(Locale.getDefault());

        configuration.setClassForTemplateLoading(SuitService.class, "/templates/");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);

        Map<String, Object> input = new HashMap<>();

        StepType[] type = StepType.values();
        input.put("types", type);

        suit.setCases(cases);
        input.put("suit", suit);

        Template template = configuration.getTemplate("featureFileTemplate.ftl");

        StringWriter stringWriter = new StringWriter();

        try {
            template.process(input, stringWriter);

            stringWriter.flush();
            return stringWriter.toString();
        } catch (TemplateException e) {
            return "";
        } finally {
            if (stringWriter != null){
                stringWriter.close();
            }
        }
    }
}
