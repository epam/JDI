package org.mytests.uiobjects.custom;

import com.epam.jdi.uitests.web.selenium.elements.GetElementType;
import com.epam.jdi.uitests.web.selenium.elements.base.Element;
import com.epam.jdi.uitests.web.selenium.elements.complex.Dropdown;
import com.epam.jdi.uitests.web.selenium.elements.pageobjects.annotations.objects.JDropdown;
import org.mytests.enums.UserOptions;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

/**
 * Created by Roman_Iovlev on 2/16/2017.
 */
public class DropMenu extends Dropdown<UserOptions> {
    public DropMenu() {
        Element el = new Element(By.id("navUserMenu"));
        el.setParent(getParent());
        setParent(el);
        this.allLabels = new GetElementType(By.linkText("%s"), this);
        this.expander = new GetElementType(By.className("downArrow"), this);
    }

    @Override
    public void selectAction(String name) {
        expand(name);

        super.selectAction(name);
    }
}
