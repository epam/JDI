package com.epam.page.object.generator.finder;

import static com.epam.page.object.generator.finder.ElementsFinder.searchElementsByRulesOnURLs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.epam.page.object.generator.model.ElementAttribute;
import com.epam.page.object.generator.model.SearchRule;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

class ElementsFinderTest {

	@Test
	void searchElementsByRulesOnURLs_notFoundURL() {
		List<String> urls = new ArrayList<>();

		urls.add("https://www.goooogle.ru");

		assertThrows(IOException.class, () -> searchElementsByRulesOnURLs(Collections.emptyList(), urls));
	}

	@Test
	void searchElementsByRulesOnURLs_wrongURLFormat() {
		List<String> urls = new ArrayList<>();

		urls.add("google");

		assertThrows(IllegalArgumentException.class, () -> searchElementsByRulesOnURLs(Collections.emptyList(), urls));
	}

	@Test
	void searchElementsByRulesOnURLs_fineURL() throws Exception {
		List<ElementAttribute> attributes = new ArrayList<>();
		List<SearchRule> searchRules = new ArrayList<>();
		List<String> urls = new ArrayList<>();
		SearchRule searchRule = new SearchRule("a", true, Collections.emptyList(), attributes);

		attributes.add(new ElementAttribute("href", "/jobs/"));
		searchRules.add(searchRule);
		urls.add("http://en.cdprojektred.com");

		List<String> results = new ArrayList<>();

		results.add("Jobs");
		results.add("Jobs");
		results.add("Jobs");
		results.add("See all career opportunities");

		assertEquals(results, searchElementsByRulesOnURLs(searchRules, urls).get(searchRule).eachText());
	}

}