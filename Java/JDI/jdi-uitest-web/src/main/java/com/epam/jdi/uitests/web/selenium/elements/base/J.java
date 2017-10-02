package com.epam.jdi.uitests.web.selenium.elements.base;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.BySelectorCollection;
import com.codeborne.selenide.impl.DownloadFileWithHttpRequest;
import com.codeborne.selenide.impl.WebElementsCollection;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.support.ui.Select;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

import static com.epam.commons.LinqUtils.first;

/**
 * Created by Sergey_Mishanin on 12/14/16.
 */
public class J extends Element implements SelenideElement {
    public J() {super();}

    public J(By locator){
        super(locator);
    }

    public J(WebElement webElement){
        super(webElement);
    }

    public void followLink() {
        clickCenter();
    }

    public J setValue(String text) {
        WebElement element = getWebElement();
        if ("select".equalsIgnoreCase(element.getTagName())) {
            selectOptionByValue(text);
        }
        else if ("input".equalsIgnoreCase(element.getTagName()) && "radio".equals(element.getAttribute("type"))) {
            selectRadio(text);
        }
        else if (text == null || text.isEmpty()) {
            element.clear();
        }
        else {
            element.clear();
            element.sendKeys(text);
        }
        return this;
    }

    public J val(String text) {
        return setValue(text);
    }

    public J append(String text) {
        invoker.doJAction("Append text to Element", () -> {
            Actions builder = new Actions(getDriver());
            builder.sendKeys(getWebElement(), text).perform();
        });
        return this;
    }
    
    public J doubleClick() {
        doubleClicks();
        return this;
    }

    public J pressEnter() {
        invoker.doJAction("Press Enter on Element", () -> {
            Actions builder = new Actions(getDriver());
            builder.sendKeys(getWebElement(), Keys.ENTER).perform();
        });
        return this;
    }

    
    public J pressTab() {
        invoker.doJAction("Press Tab on Element", () -> {
            Actions builder = new Actions(getDriver());
            builder.sendKeys(getWebElement(), Keys.TAB).perform();
        });
        return this;
    }

    
    public SelenideElement pressEscape() {
        invoker.doJAction("Press Escape on Element", () -> {
            Actions builder = new Actions(getDriver());
            builder.sendKeys(getWebElement(), Keys.ESCAPE).perform();
        });
        return this;
    }

    
    public String getText() {
        return getWebElement().getText().trim();
    }

    
    public List<WebElement> findElements(By by) {
        return getList(by);
    }

    
    public WebElement findElement(By by) {
        return get(by);
    }

    
    public Point getLocation() {
        return getWebElement().getLocation();
    }

    
    public Dimension getSize() {
        return getWebElement().getSize();
    }

    
    public Rectangle getRect() {
        return getWebElement().getRect();
    }

    
    public String getCssValue(String propertyName) {
        return getWebElement().getCssValue(propertyName);
    }

    
    public String text() {
        return getText();
    }

    
    public String innerText() {
        String innerText;
        innerText = attr("textContent");
        if (StringUtils.isBlank(innerText)){
            innerText = attr("innerText");
        }
        return innerText;
    }

    
    public String innerHtml() {
        return attr("innerHTML");
    }

    
    public String attr(String attributeName) {
        return getAttribute(attributeName);
    }

    
    public String name() {
        return attr("name");
    }

    
    public String val() {
        return getValue();
    }

    
    public String getValue() {
        return attr("value");
    }

    
    public J selectRadio(String value) {
        invoker.doJAction("Select Radio Button with [" + value + "] value", () -> {
            WebElement radio = get(By.xpath(".//input[@type='radio'][@value=" + value + "]"));
            if (radio.getAttribute("readonly") != null)
                throw new InvalidElementStateException("Cannot select readonly radio button");
            Actions builder = new Actions(getDriver());
            builder.click(radio).perform();
        });
        return this;
    }

    
    public String data(String dataAttributeName) {
        return attr("data-" + dataAttributeName);
    }

    
    public boolean exists() {
        try{
            getWebElement().getTagName();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    
    public boolean is(Condition condition) {
        return Selenide.$(getWebElement()).is(condition);
    }

    
    public boolean has(Condition condition) {
        return Selenide.$(getWebElement()).has(condition);
    }

    
    public J setSelected(boolean selected) {
        invoker.doJAction("Set selected state of Element to [" + selected + "]", () -> {
            WebElement checkbox = getWebElement();
            if (checkbox.isSelected() ^ selected) {
                if (checkbox.getAttribute("readonly") != null)
                    throw new InvalidElementStateException("Cannot change value of readonly element");
                click();
            }
        });
        return this;
    }

    
    public J should(Condition... conditions) {
        Selenide.$(getWebElement()).should(conditions);
        return this;
    }

    
    public J shouldHave(Condition... conditions) {
        Selenide.$(getWebElement()).shouldHave(conditions);
        return this;
    }

    
    public J shouldBe(Condition... conditions) {
        Selenide.$(getWebElement()).shouldBe(conditions);
        return this;
    }

    
    public J shouldNot(Condition... conditions) {
        Selenide.$(getWebElement()).shouldNot(conditions);
        return this;
    }

    
    public J shouldNotHave(Condition... conditions) {
        Selenide.$(getWebElement()).shouldNotHave(conditions);
        return this;
    }

    
    public J shouldNotBe(Condition... conditions) {
        Selenide.$(getWebElement()).shouldNotBe(conditions);
        return this;
    }

    
    public J waitUntil(Condition condition, long timeoutMilliseconds) {
        Selenide.$(getWebElement()).waitUntil(condition, timeoutMilliseconds);
        return this;
    }

    
    public J waitUntil(Condition condition, long timeoutMilliseconds, long pollingIntervalMilliseconds) {
        Selenide.$(getWebElement()).waitUntil(condition, timeoutMilliseconds, pollingIntervalMilliseconds);
        return this;
    }

    
    public J waitWhile(Condition condition, long timeoutMilliseconds) {
        Selenide.$(getWebElement()).waitWhile(condition, timeoutMilliseconds);
        return this;
    }

    
    public J waitWhile(Condition condition, long timeoutMilliseconds, long pollingIntervalMilliseconds) {
        Selenide.$(getWebElement()).waitWhile(condition, timeoutMilliseconds, pollingIntervalMilliseconds);
        return this;
    }

    
    public J parent() {
        WebElement parent = getWebElement().findElement(By.xpath("./.."));
        return new J(parent);
    }

    
    public J closest(String tagOrClass) {
        String xpath = tagOrClass.startsWith(".") ?
                String.format("ancestor::*[contains(concat(' ', normalize-space(@class), ' '), ' %s ')][1]", tagOrClass.substring(1)) :
                String.format("ancestor::%s[1]", tagOrClass);

        return find(By.xpath(xpath));
    }

    
    public J find(String cssSelector) {
        return new J(get(By.cssSelector(cssSelector)));
    }

    
    public J find(String cssSelector, int index) {
        return new J(getList(By.cssSelector(cssSelector)).get(index));
    }

    
    public J find(By by) {
        return new J(get(by));
    }

    
    public J find(By by, int index) {
        return new J(getList(by).get(index));
    }

    
    public J $(String cssSelector) {
        return find(cssSelector);
    }

    
    public J $(String cssSelector, int index) {
        return find(cssSelector, index);
    }

    
    public J $(By by) {
        return find(by);
    }

    
    public J $(By by, int index) {
        return find(by, index);
    }

    public SelenideElement $x(String s) {
        return find(By.xpath(s));
    }

    public SelenideElement $x(String s, int i) {
        return findAll(By.xpath(s)).get(i);
    }


    public ElementsCollection findAll(String cssSelector) {
        return new ElementsCollection(new BySelectorCollection(By.cssSelector(cssSelector)));
    }

    
    public ElementsCollection findAll(By by) {
        return new ElementsCollection(new BySelectorCollection(by));
    }

    
    public ElementsCollection $$(String cssSelector) {
        return findAll(cssSelector);
    }

    
    public ElementsCollection $$(By by) {
        return findAll(by);
    }

    public ElementsCollection $$x(String s) {
        return findAll(By.xpath(s));
    }


    public File uploadFromClasspath(String... fileName) {
        File[] files = new File[fileName.length];
        for (int i = 0; i < fileName.length; i++) {
            files[i] = findFileInClasspath(fileName[i]);
        }

        return uploadFile(files);
    }

    protected File findFileInClasspath(String name){
        URL resource = Thread.currentThread().getContextClassLoader().getResource(name);
        if (resource == null) {
            throw new IllegalArgumentException("File not found in classpath: " + name);
        }
        try {
            return new File(resource.toURI());
        }
        catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    
    public File uploadFile(File... files)  {
        if (files.length == 0) {
            throw new IllegalArgumentException("No files to upload");
        }

        WebElement inputField = getWebElement();
        File uploadedFile = uploadFile(inputField, files[0]);

        if (files.length > 1) {
            J form = closest("form");
            for (int i = 1; i < files.length; i++) {
                uploadFile(cloneInputField(form, inputField), files[i]);
            }
        }

        return uploadedFile;
    }

    protected File uploadFile(WebElement inputField, File file) {
        if (!"input".equalsIgnoreCase(inputField.getTagName())) {
            throw new IllegalArgumentException("Cannot upload file because this field is not an INPUT");
        }

        if (!file.exists()) {
            throw new IllegalArgumentException("File not found: " + file.getAbsolutePath());
        }

        try {
            String canonicalPath = file.getCanonicalPath();
            inputField.sendKeys(canonicalPath);
            return new File(canonicalPath);
        } catch (IOException ex){
            throw new RuntimeException("Couldn't upload the [" + file.getName() +  "]", ex);
        }
    }

    protected WebElement cloneInputField(J form, WebElement inputField) {
        return (WebElement)jsExecutor().executeScript(
                "var fileInput = document.createElement('input');" +
                        "fileInput.setAttribute('type', arguments[1].getAttribute('type'));" +
                        "fileInput.setAttribute('name', arguments[1].getAttribute('name'));" +
                        "fileInput.style.width = '1px';" +
                        "fileInput.style.height = '1px';" +
                        "arguments[0].appendChild(fileInput);" +
                        "return fileInput;",
                form, inputField);
    }

    
    public void selectOption(int... index) {
        Select selectField = new Select(getWebElement());
        for (int value: index) {
            selectField.selectByIndex(value);
        }
    }

    
    public void selectOption(String... text) {
        Select selectField = new Select(getWebElement());
        for (String value: text) {
            selectField.selectByVisibleText(value);
        }
    }

    public void selectOptionContainingText(String s) {
        first(getSelectedOptions(), o -> o.text().contains(s)).click();
    }


    public void selectOptionByValue(String... values) {
        Select selectField = new Select(getWebElement());
        for (String value: values) {
            selectField.selectByValue(value);
        }
    }

    
    public J getSelectedOption() throws NoSuchElementException {
        Select selectField = new Select(getWebElement());
        WebElement selectedOption = selectField.getFirstSelectedOption();
        return new J(selectedOption);
    }

    
    public ElementsCollection getSelectedOptions() {
        Select selectField = new Select(getWebElement());
        return new ElementsCollection(new WebElementsCollection() {

            
            public List<WebElement> getActualElements() {
                return selectField.getAllSelectedOptions();
            }

            
            public String description() {
                return null;
            }
        });
    }

    
    public String getSelectedValue() {
        J option = getSelectedOption();
        return option == null ? null : option.attr("value");
    }

    
    public String getSelectedText() {
        J option = getSelectedOption();
        return option == null ? null : option.text();
    }

    
    public SelenideElement scrollTo() {
        Point location = getWebElement().getLocation();
        jsExecutor().executeScript("window.scrollTo(" + location.getX() + ", " + location.getY() + ')');
        return this;
    }

    
    public File download() throws FileNotFoundException {
        try {
            return new DownloadFileWithHttpRequest().download(getWebElement());
        } catch (IOException e) {
            throw new RuntimeException("Couldn't download file from the link: " + toString(), e);
        }
    }

    public String getSearchCriteria() {
        return "NOT IMPLEMENTED";
    }


    public WebElement toWebElement() {
        return getWebElement();
    }

    
    public WebElement getWrappedElement() {
        return getWebElement();
    }

    
    public void click() {
        clickCenter();
    }

    public void click(int i, int i1) {

    }


    public void submit() {
        getWebElement().submit();
    }

    
    public void sendKeys(CharSequence... keysToSend) {
        getWebElement().sendKeys(keysToSend);
    }

    
    public void clear() {
        getWebElement().clear();
    }

    
    public String getTagName() {
        return getWebElement().getTagName();
    }

    
    public boolean isSelected() {
        return getWebElement().isSelected();
    }

    
    public boolean isEnabled() {
        return getWebElement().isEnabled();
    }

    
    public J contextClick() {
        rightClick();
        return this;
    }

    
    public J hover() {
        mouseOver();
        return this;
    }

    
    public J dragAndDropTo(String targetCssSelector) {
        dragAndDrop(new J(By.cssSelector(targetCssSelector)));
        return this;
    }

    
    public J dragAndDropTo(WebElement webElement) {
        dragAndDrop(new J(webElement));
        return this;
    }

    
    public boolean isImage() {
        WebElement img = getWebElement();
        if (!"img".equalsIgnoreCase(img.getTagName())) {
            throw new IllegalArgumentException("Method isImage() is only applicable for img elements");
        }
        return Boolean.parseBoolean(
                jsExecutor().executeScript("return arguments[0].complete && " +
                "typeof arguments[0].naturalWidth != 'undefined' && " +
                "arguments[0].naturalWidth > 0", img).toString());
    }

    
    public File screenshot() {
        return getScreenshotAs(OutputType.FILE);
    }

    
    public BufferedImage screenshotAsImage() {
        try {
            return ImageIO.read(screenshot());
        } catch (IOException e) {
            return null;
        }
    }

    
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(target);
    }

    
    public WebElement findElementByClassName(String using) {
        return get(By.className(using));
    }

    
    public List<WebElement> findElementsByClassName(String using) {
        return getList(By.className(using));
    }

    
    public WebElement findElementByCssSelector(String using) {
        return get(By.cssSelector(using));
    }

    
    public List<WebElement> findElementsByCssSelector(String using) {
        return getList(By.cssSelector(using));
    }

    
    public WebElement findElementById(String using) {
        return get(By.id(using));
    }

    
    public List<WebElement> findElementsById(String using) {
        return getList(By.id(using));
    }

    
    public WebElement findElementByLinkText(String using) {
        return get(By.linkText(using));
    }

    
    public List<WebElement> findElementsByLinkText(String using) {
        return getList(By.linkText(using));
    }

    
    public WebElement findElementByPartialLinkText(String using) {
        return get(By.partialLinkText(using));
    }

    
    public List<WebElement> findElementsByPartialLinkText(String using) {
        return getList(By.partialLinkText(using));
    }

    
    public WebElement findElementByName(String using) {
        return get(By.name(using));
    }

    
    public List<WebElement> findElementsByName(String using) {
        return getList(By.name(using));
    }

    
    public WebElement findElementByTagName(String using) {
        return get(By.tagName(using));
    }

    
    public List<WebElement> findElementsByTagName(String using) {
        return getList(By.tagName(using));
    }

    
    public WebElement findElementByXPath(String using) {
        return get(By.xpath(using));
    }

    
    public List<WebElement> findElementsByXPath(String using) {
        return getList(By.xpath(using));
    }

    
    public Coordinates getCoordinates() {
        return ((Locatable)getWebElement()).getCoordinates();
    }

    
    public WebDriver getWrappedDriver() {
        return getDriver();
    }
}
