package com.epam.page.object.generator.model;

import com.epam.page.object.generator.model.ElementAttribute;
import com.epam.page.object.generator.model.ElementType;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class SearchRule {

    private ElementType elementType;
	private String tag;
    private boolean isSearchingText;
    private List<String> classes;
    private List<ElementAttribute> attributes;

    public SearchRule() {
    }

    public SearchRule(ElementType elementType, String tag, boolean isSearchingText, List<String> classes, List<ElementAttribute> attributes) {
        this.elementType = elementType;
        this.tag = tag;
        this.isSearchingText = isSearchingText;
        this.classes = classes;
        this.attributes = attributes;
    }

    public ElementType getElementType() {
        return elementType;
    }

    public void setElementType(ElementType elementType) {
        this.elementType = elementType;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public boolean isSearchingText() {
        return isSearchingText;
    }

    public void setSearchingText(boolean searchingText) {
        isSearchingText = searchingText;
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

    public boolean classesAreEmpty() {
        return classes == null || classes.isEmpty();
    }

    public boolean attributesAreEmpty() {
        return attributes == null || attributes.isEmpty();
    }

}