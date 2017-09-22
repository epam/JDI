package com.epam.test_generator.file_generator;

import com.epam.test_generator.entities.Case;
import com.epam.test_generator.entities.StepType;
import com.epam.test_generator.entities.Suit;
import com.epam.test_generator.services.SuitService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.StringWriter;
import java.util.*;

@Component
public class FileGenerator {

    public String generate(Suit suit, List<Case> cases)  throws IOException {

        if(suit==null || cases==null) throw new NullPointerException(suit==null?"suit is null":"cases list is null");

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_23);
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

        try (StringWriter stringWriter = new StringWriter()) {
            template.process(input, stringWriter);
            stringWriter.flush();
            return stringWriter.toString();
        } catch (TemplateException e) {
            e.printStackTrace();
            return "";
        }
    }
}
