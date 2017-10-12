package com.epam.page.object.generator.parser;

import com.epam.page.object.generator.model.ElementAttribute;
import com.epam.page.object.generator.model.ElementType;
import com.epam.page.object.generator.model.SearchRule;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CommonElementsRuleParser implements IRuleParser {

    enum CommonElementType {
        BUTTON, TEXT, CHECKBOX, IMAGE
    }

    @Override
    public boolean canParse(String type) {
        try {
            CommonElementType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            return false;
        }

        return true;
    }

    @Override
    public SearchRule parse(JSONObject jsonObject) {
        String type = (String) jsonObject.get("type");
        String name = (String) jsonObject.get("name");
        String rules = (String) jsonObject.get("rules");

        SearchRule searchRule = new SearchRule();
        String[] attributes = rules.split(";");
        List<String> classes = new ArrayList<>();
        List<ElementAttribute> elementAttributes = new ArrayList<>();

        searchRule.setElementType(ElementType.valueOf(type.toUpperCase()));

        searchRule.setRequiredAttribute(name);

        for (String attribute : attributes) {
            String[] singleAttribute = attribute.split("=");

            switch (singleAttribute[0]) {
                case "tag":
                    searchRule.setTag(singleAttribute[1]);
                    break;
                case "class":
                    classes.add(singleAttribute[1]);
                    break;
                default:
                    elementAttributes.add(new ElementAttribute(singleAttribute[0], singleAttribute[1]));
                    break;
            }
        }

        searchRule.setClasses(classes);
        searchRule.setAttributes(elementAttributes);

        return searchRule;
    }

}
