package com.epam.page.object.generator.parser;

import com.epam.page.object.generator.model.ElementAttribute;
import java.util.ArrayList;
import java.util.List;

import com.epam.page.object.generator.rule.ButtonSearchRule;
import com.epam.page.object.generator.rule.ISearchRule;
import org.json.simple.JSONObject;

public class ButtonRuleParser implements IRuleParser {

	private static final String BUTTON_TYPE = "button";

	@Override
	public boolean canParse(String type) {
		return type.toLowerCase().equals(BUTTON_TYPE);
	}

	@Override
	public ISearchRule parse(JSONObject jsonObject) {
		String name = (String) jsonObject.get("name");
		String rules = (String) jsonObject.get("rules");

		ISearchRule searchRule = new ButtonSearchRule();
		String[] attributes = rules.split(";");
		List<String> classes = new ArrayList<>();
		List<ElementAttribute> elementAttributes = new ArrayList<>();

		if (name.equals("text")) {
			searchRule.setSearchText(true);
		} else {
			searchRule.setSearchText(false);
		}

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