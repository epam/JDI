package com.epam.page.object.generator.rule;

import com.epam.page.object.generator.model.ElementAttribute;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractSearchRule implements ISearchRule {
    protected String tag;
    protected boolean searchText;
    protected List<String> classes;
    protected List<ElementAttribute> attributes;

    public AbstractSearchRule() {
    }

    public AbstractSearchRule(String tag, boolean searchText, List<String> classes, List<ElementAttribute> attributes) {
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

        for (String currentURL: urls) {
            searchResults.addAll(extractElementsFromWebSite(currentURL));
        }

        return searchResults;
    };

    abstract public Elements extractElementsFromWebSite(String url);

    protected Elements searchElementsByTag(Document document) {
        Elements searchResults = new Elements();

        if (tag != null) {
            searchResults = document.select(tag);
        } else {
            searchResults = document.getAllElements();
        }

        return searchResults;
    }

    protected Elements searchElementsByClasses(Document document) {
        Elements searchResults = new Elements();

        if (!classesAreEmpty()) {
            searchResults = document.select(prepareCSSQuerySelector());
        } else {
            searchResults = document.getAllElements();
        }

        return searchResults;
    }

    protected Elements searchElementsByAttributes(Document document) {
        Elements searchResults = new Elements();

        if (attributes != null && !attributes.isEmpty()) {
            searchResults = new Elements(document.getAllElements().stream().filter(element -> elementAttributesMatch(element)).collect(Collectors.toList()));
        } else {
            searchResults = document.getAllElements();
        }

        return searchResults;
    }

    protected boolean elementAttributesMatch(Element element) {
        return attributes.stream().noneMatch(elementAttribute -> element.attr(elementAttribute.getAttributeName()) == null
                || !element.attr(elementAttribute.getAttributeName()).equals(elementAttribute.getAttributeValue()));
    }

    protected String prepareCSSQuerySelector() {
        StringBuilder selector = new StringBuilder();

        classes.forEach(clazz -> selector.append(".").append(clazz));

        return selector.toString();
    }

    protected Document getURLConnection(String url) {
        Document document = null;

        try {
            document = Jsoup.connect(url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return document;
    }

    protected boolean classesAreEmpty() {
        return classes == null || classes.isEmpty();
    }

    protected boolean attributesAreEmpty() {
        return attributes == null || attributes.isEmpty();
    }
}
