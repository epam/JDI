package com.epam.page.object.generator.rule;

import com.epam.page.object.generator.model.ElementAttribute;
import org.jsoup.select.Elements;

import java.util.List;

public interface ISearchRule {

    String getTag();

    void setTag(String tag);

    boolean getSearchText();

    void setSearchText(boolean searchText);

    List<String> getClasses();

    void setClasses(List<String> classes);

    List<ElementAttribute> getAttributes();

    void setAttributes(List<ElementAttribute> attributes);

    Elements extractElementsFromWebSite(List<String> urls);

    Elements extractElementsFromWebSite(String url);

}