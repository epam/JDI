package com.epam.jdi.cucumber.stepdefs.vtb;

import com.epam.jdi.uitests.core.interfaces.base.IClickable;
import com.epam.jdi.uitests.core.interfaces.common.IText;
import com.epam.web.matcher.junit.Assert;
import cucumber.api.java.ru.Тогда;

import java.util.List;

import static com.epam.jdi.cucumber.Utils.getElementByName;
import static java.lang.Integer.parseInt;

public class ButtonStepDef {

    /**
     * Нажатие на кнопку
     * @param blockName  название блока
     * @param buttonName название кнопки
     */
    @ExampleUseStep("в блоке \"@Element\" нажимаем на кнопку \"@Element\"")
    @Тогда("^(?:в блоке \"([^\"]*)\" )*нажимаем на кнопку \"([^\"]*)\"$")
    public void clickButton(String blockName, String buttonName) {
        IClickable cl = getElementByName(blockName, buttonName);
        cl.click();
    }

    /**
     * @param blockName - имя блока
     * @param nameField - название кнопоки
     * @param index     - индекс элемента в списке
     */
    @ExampleUseStep("нажимает в блоке \"@Block\" по индексу \"@text\" на кнопку \"@Element\"")
    @Тогда("^нажимает в блоке \"([^\"]*)\" по индексу \"([^\"]*)\" на элемент \"([^\"]*)\"")
    public void clickButtonInBlockByIndex(String blockName, String index, String nameField) {
        List<IClickable> cl = getElementByName(blockName, nameField);
        cl.get(parseInt(index)).click();
    }

    /**
     * @param elementName - название элемента
     * @param index       - индекс элемента в списке
     */
    @Тогда("^нажимает на элемент \"([^\"]*)\" по индексу \"([^\"]*)\"")
    public void clickButtonByIndex(String elementName, String index) {
        List<IClickable> cl = getElementByName(elementName);
        cl.get(parseInt(index)).click();
    }

    /**
     * Проверка содержания текста кнопки
     * @param nameBlock      имя блока
     * @param nameButton     название кнопки
     * @param checkOperation не|
     * @param typeCompare    текст|строку|подстроку|текст из тестовых данных|подстроку из тестовых данных
     * @param compareText    сравниваемый текст
     */
    @ExampleUseStep("в блоке \"@Block\" кнопка \"@Element\" не содержит @typeCompare \"@Text\"")
    @Тогда("^(?:в блоке \"([^\"]*)\" )*кнопка \"([^\"]*)\" (?:(не) )*содержит (текст|строку|подстроку|текст из тестовых данных|подстроку из тестовых данных) \"([^\"]*)\"$")
    public void hasButtonText(String nameBlock, String nameButton, String checkOperation, String typeCompare, String compareText) {
        IText cl = getElementByName(nameBlock, nameButton);
        Assert.assertTrue(checkOperation == null ^ comparator(typeCompare, compareText, cl.getText()));
    }
    private boolean comparator(String typeCompare, String expText, String actText) { return true; }

}