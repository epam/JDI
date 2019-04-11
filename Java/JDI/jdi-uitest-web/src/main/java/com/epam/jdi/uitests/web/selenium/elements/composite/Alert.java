package com.epam.jdi.uitests.web.selenium.elements.composite;

/**
 * Created by Roman_Iovlev on 3/24/2017.
 */
public class Alert extends Popup {

    private org.openqa.selenium.Alert alert;
    private org.openqa.selenium.Alert getAlert() {
        if (alert == null)
            alert = getDriver().switchTo().alert();
        return alert;
    }

    @Override
    protected void okAction() {
        getAlert().accept();
    }

    @Override
    protected void cancelAction() {
        getAlert().dismiss();
    }
    @Override
    protected void closeAction() {
        getAlert().dismiss();
    }
    @Override
    protected String getTextAction() {
        return getAlert().getText();
    }

    /**
     * Click on Button marked with annotation @CancelButton or named "cancelButton"
     */
    public void sendKeys(String text) {
        invoker.doJAction("Send keys in popup", () -> sendKeysAction(text));
    }
    /**
     * Click on Button marked with annotation @CancelButton or named "cancelButton"
     */
    public void inputAndAccept(String text) {
        invoker.doJAction("Send keys in popup", () -> {
            sendKeysAction(text);
            okAction();
        } );
    }
    protected void sendKeysAction(String text) {
        getAlert().sendKeys(text);
    }

}
