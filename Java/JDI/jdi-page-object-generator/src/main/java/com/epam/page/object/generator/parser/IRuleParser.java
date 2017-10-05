package com.epam.page.object.generator.parser;

import com.epam.page.object.generator.rule.ISearchRule;
import org.json.simple.JSONObject;

public interface IRuleParser {

	boolean canParse(String type);

	ISearchRule parse(JSONObject jsonObject);

}