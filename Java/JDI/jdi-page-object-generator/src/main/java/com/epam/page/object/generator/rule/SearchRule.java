package com.epam.page.object.generator.rule;

import com.epam.page.object.generator.model.ElementAttribute;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SearchRule {

	private String tag;
    private boolean searchText;
    private List<String> classes;
    private List<ElementAttribute> attributes;

    public SearchRule() {
    }

    public SearchRule(String tag, boolean searchText, List<String> classes, List<ElementAttribute> attributes) {
        this.tag = tag;
        this.searchText = searchText;
        this.classes = classes;
        this.attributes = attributes;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean getSearchText() {
        return searchText;
    }

    public void setSearchText(boolean searchText) {
        this.searchText = searchText;
    }

    public List<String> getClasses() {
        return classes;
    }

    public void setClasses(List<String> classes) {
        this.classes = classes;
    }

    public List<ElementAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<ElementAttribute> attributes) {
        this.attributes = attributes;
    }

    public Elements extractElementsFromWebSite(List<String> urls){
        Elements searchResults = new Elements();

        for (String currentURL : urls) {
            searchResults.addAll(extractElementsFromWebSite(currentURL));
        }

        return searchResults;
    };

    public Elements extractElementsFromWebSite(String url) {
        Elements searchResults = new Elements();
        Document document = getURLConnection(url);

        searchResults.addAll(searchElementsByTag(document));
        searchResults.retainAll(searchElementsByClasses(document));
        searchResults.retainAll(searchElementsByAttributes(document));

        return new Elements(searchResults);
    }

    private Elements searchElementsByTag(Document document) {
        Elements searchResults;

        if (tag != null) {
            searchResults = document.select(tag);
        } else {
            searchResults = document.getAllElements();
        }

        return searchResults;
    }

    private Elements searchElementsByClasses(Document document) {
        Elements searchResults;

        if (!classesAreEmpty()) {
            searchResults = document.select(prepareCSSQuerySelector());
        } else {
            searchResults = document.getAllElements();
        }

        return searchResults;
    }

    private Elements searchElementsByAttributes(Document document) {
        Elements searchResults;

        if (!attributesAreEmpty()) {
            searchResults = new Elements(document.getAllElements()
				.stream().filter(this::elementAttributesMatch).collect(Collectors.toList()));
        } else {
            searchResults = document.getAllElements();
        }

        return searchResults;
    }

    private boolean elementAttributesMatch(Element element) {
        return attributes.stream().noneMatch(elementAttribute -> element.attr(elementAttribute.getAttributeName()) == null
                || !element.attr(elementAttribute.getAttributeName()).equals(elementAttribute.getAttributeValue()));
    }

    private String prepareCSSQuerySelector() {
        StringBuilder selector = new StringBuilder();

        classes.forEach(clazz -> selector.append(".").append(clazz));

        return selector.toString();
    }

    private Document getURLConnection(String url) {
        Document document = null;

        try {
            document = Jsoup.connect(url).get();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return document;
    }

    private boolean classesAreEmpty() {
        return classes == null || classes.isEmpty();
    }

    private boolean attributesAreEmpty() {
        return attributes == null || attributes.isEmpty();
    }

}