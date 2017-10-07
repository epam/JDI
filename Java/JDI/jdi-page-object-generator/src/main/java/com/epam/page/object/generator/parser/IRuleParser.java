package com.epam.page.object.generator.parser;

import com.epam.page.object.generator.model.SearchRule;
import org.json.simple.JSONObject;

public interface IRuleParser {

	boolean canParse(String type);

	SearchRule parse(JSONObject jsonObject);

}