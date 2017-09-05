package com.epam.jdi.uitests.testing.unittests.enums;

import com.epam.commons.linqinterfaces.JAction;
import com.epam.jdi.uitests.core.preconditions.IPreconditions;
import com.epam.jdi.uitests.core.preconditions.PreconditionsState;
import com.epam.jdi.uitests.win.settings.WinSettings;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import static com.epam.jdi.uitests.testing.unittests.pageobjects.Desktop.mainWindow;


public enum Preconditions implements IPreconditions {
    METALS_AND_COLORS_PAGE(new CheckAction(() -> By.className("MetalsAndColorsView")),
        () -> {
            mainWindow.mainTabPane.supportButton.click();
            mainWindow.mainTabPane.metalsAndColorsTab.click();
        }),
    SUPPORT_PAGE(() -> true, //todo поправить
            () -> mainWindow.mainTabPane.supportButton.click()),
    CONTACT_PAGE_FILLED(new CheckAction(() -> By.className("ContactFormView")), () -> {
                mainWindow.mainTabPane.supportButton.click();
                mainWindow.mainTabPane.contactFormTab.click();
                mainWindow.mainTabPane.contactFormTab.nestedContactFormView.nameTextBox.input("Name");
                mainWindow.mainTabPane.contactFormTab.nestedContactFormView.lastNameTextBox.input("Last Name");
                mainWindow.mainTabPane.contactFormTab.nestedContactFormView.descriptionTextBox.input("Description");
            }),
    CONTACT_PAGE(new CheckAction(() -> By.className("ContactFormView")), () -> {
                mainWindow.mainTabPane.supportButton.click();
                mainWindow.mainTabPane.contactFormTab.click();
            }),
    COMPLEX_TABLE_PAGE(() -> true, () -> {
        mainWindow.mainTabPane.supportButton.click();
        mainWindow.mainTabPane.complexTablePageTab.click();
    }),
    SIMPLE_TABLE_PAGE(() -> true, () -> {
        mainWindow.mainTabPane.supportButton.click();
        mainWindow.mainTabPane.simpleTableTab.click();
    });

    public BooleanSupplier checkAction;
    public JAction moveToAction;

    Preconditions(BooleanSupplier checkAction, JAction moveToAction) {
        this.checkAction = checkAction;
        this.moveToAction = moveToAction;
        PreconditionsState.alwaysMoveToCondition = true;
    }

    public Boolean checkAction() {
        return checkAction.getAsBoolean();
    }

    public void moveToAction() {
        moveToAction.invoke();
    }
}

class CheckAction implements BooleanSupplier {
    private Supplier<By> byOfExpectedElementSupplier;

    public CheckAction(Supplier<By> byOfExpectedElementSupplier) {
        this.byOfExpectedElementSupplier = byOfExpectedElementSupplier;
    }

    @Override
    public boolean getAsBoolean() {
        WebDriver driver = (WebDriver) WinSettings.driverFactory.getDriver();

        return driver.findElements(byOfExpectedElementSupplier.get()).size() != 0;
    }
}
