package com.epam.jdi.site.epam.CustomElements;

import com.epam.jdi.uitests.core.interfaces.common.ILink;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.common.Link;

/**
 * Created by Roman_Iovlev on 11/22/2016.
 */
public class JobRecord extends Element {
    public Link name;
    public IText description;
    public IText location;
    public ILink apply;
}
