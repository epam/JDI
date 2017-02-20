package org.mytests.entities;

import com.epam.jdi.uitests.web.selenium.elements.common.*;
import com.epam.jdi.uitests.web.selenium.elements.composite.Section;
import org.mytests.uiobjects.custom.Rating;

/**
 * Created by Roman_Iovlev on 2/17/2017.
 */
public class FilmsTableRow extends Section {
    public CheckBox checkbox;
    public TextField number;
    public Link title;
    public Text year;
    public Text type;
    public Rating yourRating;
    public Text rating;
    public Text votes;
    public Text created;

}
