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

/**
 * Created by Sergey_Mishanin on 12/14/16.
 */
public class SupremeElement extends Element<SupremeElement> implements SelenideElement {

    public SupremeElement(By locator){
        super(locator);
    }

    public SupremeElement(WebElement webElement){
        super(webElement);
    }

    @Override
    public void followLink() {
        clickCenter();
    }

    @Override
    public SupremeElement setValue(String text) {
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

    @Override
    public SupremeElement val(String text) {
        return setValue(text);
    }

    @Override
    public SupremeElement append(String text) {
        invoker.doJAction("Append text to Element", () -> {
            Actions builder = new Actions(getDriver());
            builder.sendKeys(getWebElement(), text).perform();
        });
        return this;
    }

    @Override
    public SupremeElement pressEnter() {
        invoker.doJAction("Press Enter on Element", () -> {
            Actions builder = new Actions(getDriver());
            builder.sendKeys(getWebElement(), Keys.ENTER).perform();
        });
        return this;
    }

    @Override
    public SupremeElement pressTab() {
        invoker.doJAction("Press Tab on Element", () -> {
            Actions builder = new Actions(getDriver());
            builder.sendKeys(getWebElement(), Keys.TAB).perform();
        });
        return this;
    }

    @Override
    public SelenideElement pressEscape() {
        invoker.doJAction("Press Escape on Element", () -> {
            Actions builder = new Actions(getDriver());
            builder.sendKeys(getWebElement(), Keys.ESCAPE).perform();
        });
        return this;
    }

    @Override
    public String getText() {
        return getWebElement().getText().trim();
    }

    @Override
    public List<WebElement> findElements(By by) {
        return getList(by);
    }

    @Override
    public WebElement findElement(By by) {
        return get(by);
    }

    @Override
    public Point getLocation() {
        return getWebElement().getLocation();
    }

    @Override
    public Dimension getSize() {
        return getWebElement().getSize();
    }

    @Override
    public Rectangle getRect() {
        return getWebElement().getRect();
    }

    @Override
    public String getCssValue(String propertyName) {
        return getWebElement().getCssValue(propertyName);
    }

    @Override
    public String text() {
        return getText();
    }

    @Override
    public String innerText() {
        String innerText;
        innerText = attr("textContent");
        if (StringUtils.isBlank(innerText)){
            innerText = attr("innerText");
        }
        return innerText;
    }

    @Override
    public String innerHtml() {
        return attr("innerHTML");
    }

    @Override
    public String attr(String attributeName) {
        return getAttribute(attributeName);
    }

    @Override
    public String name() {
        return attr("name");
    }

    @Override
    public String val() {
        return getValue();
    }

    @Override
    public String getValue() {
        return attr("value");
    }

    @Override
    public SupremeElement selectRadio(String value) {
        invoker.doJAction("Select Radio Button with [" + value + "] value", () -> {
            WebElement radio = get(By.xpath(".//input[@type='radio'][@value=" + value + "]"));
            if (radio.getAttribute("readonly") != null)
                throw new InvalidElementStateException("Cannot select readonly radio button");
            Actions builder = new Actions(getDriver());
            builder.click(radio).perform();
        });
        return this;
    }

    @Override
    public String data(String dataAttributeName) {
        return attr("data-" + dataAttributeName);
    }

    @Override
    public boolean exists() {
        try{
            getWebElement().getTagName();
            return true;
        } catch (Exception ex){
            return false;
        }
    }

    @Override
    public boolean is(Condition condition) {
        return Selenide.$(getWebElement()).is(condition);
    }

    @Override
    public boolean has(Condition condition) {
        return Selenide.$(getWebElement()).has(condition);
    }

    @Override
    public SupremeElement setSelected(boolean selected) {
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

    @Override
    public SupremeElement should(Condition... conditions) {
        Selenide.$(getWebElement()).should(conditions);
        return this;
    }

    @Override
    public SupremeElement shouldHave(Condition... conditions) {
        Selenide.$(getWebElement()).shouldHave(conditions);
        return this;
    }

    @Override
    public SupremeElement shouldBe(Condition... conditions) {
        Selenide.$(getWebElement()).shouldBe(conditions);
        return this;
    }

    @Override
    public SupremeElement shouldNot(Condition... conditions) {
        Selenide.$(getWebElement()).shouldNot(conditions);
        return this;
    }

    @Override
    public SupremeElement shouldNotHave(Condition... conditions) {
        Selenide.$(getWebElement()).shouldNotHave(conditions);
        return this;
    }

    @Override
    public SupremeElement shouldNotBe(Condition... conditions) {
        Selenide.$(getWebElement()).shouldNotBe(conditions);
        return this;
    }

    @Override
    public SupremeElement waitUntil(Condition condition, long timeoutMilliseconds) {
        Selenide.$(getWebElement()).waitUntil(condition, timeoutMilliseconds);
        return this;
    }

    @Override
    public SupremeElement waitUntil(Condition condition, long timeoutMilliseconds, long pollingIntervalMilliseconds) {
        Selenide.$(getWebElement()).waitUntil(condition, timeoutMilliseconds, pollingIntervalMilliseconds);
        return this;
    }

    @Override
    public SupremeElement waitWhile(Condition condition, long timeoutMilliseconds) {
        Selenide.$(getWebElement()).waitWhile(condition, timeoutMilliseconds);
        return this;
    }

    @Override
    public SupremeElement waitWhile(Condition condition, long timeoutMilliseconds, long pollingIntervalMilliseconds) {
        Selenide.$(getWebElement()).waitWhile(condition, timeoutMilliseconds, pollingIntervalMilliseconds);
        return this;
    }

    @Override
    public SupremeElement parent() {
        WebElement parent = getWebElement().findElement(By.xpath("./.."));
        return new SupremeElement(parent);
    }

    @Override
    public SupremeElement closest(String tagOrClass) {
        String xpath = tagOrClass.startsWith(".") ?
                String.format("ancestor::*[contains(concat(' ', normalize-space(@class), ' '), ' %s ')][1]", tagOrClass.substring(1)) :
                String.format("ancestor::%s[1]", tagOrClass);

        return find(By.xpath(xpath));
    }

    @Override
    public SupremeElement find(String cssSelector) {
        return new SupremeElement(get(By.cssSelector(cssSelector)));
    }

    @Override
    public SupremeElement find(String cssSelector, int index) {
        return new SupremeElement(getList(By.cssSelector(cssSelector)).get(index));
    }

    @Override
    public SupremeElement find(By by) {
        return new SupremeElement(get(by));
    }

    @Override
    public SupremeElement find(By by, int index) {
        return new SupremeElement(getList(by).get(index));
    }

    @Override
    public SupremeElement $(String cssSelector) {
        return find(cssSelector);
    }

    @Override
    public SupremeElement $(String cssSelector, int index) {
        return find(cssSelector, index);
    }

    @Override
    public SupremeElement $(By by) {
        return find(by);
    }

    @Override
    public SupremeElement $(By by, int index) {
        return find(by, index);
    }

    @Override
    public ElementsCollection findAll(String cssSelector) {
        return new ElementsCollection(new BySelectorCollection(By.cssSelector(cssSelector)));
    }

    @Override
    public ElementsCollection findAll(By by) {
        return new ElementsCollection(new BySelectorCollection(by));
    }

    @Override
    public ElementsCollection $$(String cssSelector) {
        return findAll(cssSelector);
    }

    @Override
    public ElementsCollection $$(By by) {
        return findAll(by);
    }

    @Override
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

    @Override
    public File uploadFile(File... files)  {
        if (files.length == 0) {
            throw new IllegalArgumentException("No files to upload");
        }

        WebElement inputField = getWebElement();
        File uploadedFile = uploadFile(inputField, files[0]);

        if (files.length > 1) {
            SupremeElement form = closest("form");
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

    protected WebElement cloneInputField(SupremeElement form, WebElement inputField) {
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

    @Override
    public void selectOption(int... index) {
        Select selectField = new Select(getWebElement());
        for (int value: index) {
            selectField.selectByIndex(value);
        }
    }

    @Override
    public void selectOption(String... text) {
        Select selectField = new Select(getWebElement());
        for (String value: text) {
            selectField.selectByVisibleText(value);
        }
    }

    @Override
    public void selectOptionByValue(String... values) {
        Select selectField = new Select(getWebElement());
        for (String value: values) {
            selectField.selectByValue(value);
        }
    }

    @Override
    public SupremeElement getSelectedOption() throws NoSuchElementException {
        Select selectField = new Select(getWebElement());
        WebElement selectedOption = selectField.getFirstSelectedOption();
        return new SupremeElement(selectedOption);
    }

    @Override
    public ElementsCollection getSelectedOptions() {
        Select selectField = new Select(getWebElement());
        return new ElementsCollection(new WebElementsCollection() {

            @Override
            public List<WebElement> getActualElements() {
                return selectField.getAllSelectedOptions();
            }

            @Override
            public String description() {
                return null;
            }
        });
    }

    @Override
    public String getSelectedValue() {
        SupremeElement option = getSelectedOption();
        return option == null ? null : option.attr("value");
    }

    @Override
    public String getSelectedText() {
        SupremeElement option = getSelectedOption();
        return option == null ? null : option.text();
    }

    @Override
    public SelenideElement scrollTo() {
        Point location = getWebElement().getLocation();
        jsExecutor().executeScript("window.scrollTo(" + location.getX() + ", " + location.getY() + ')');
        return this;
    }

    @Override
    public File download() throws FileNotFoundException {
        try {
            return new DownloadFileWithHttpRequest().download(getWebElement());
        } catch (IOException e) {
            throw new RuntimeException("Couldn't download file from the link: " + toString(), e);
        }
    }

    @Override
    public WebElement toWebElement() {
        return getWebElement();
    }

    @Override
    public WebElement getWrappedElement() {
        return getWebElement();
    }

    @Override
    public void click() {
        clickCenter();
    }

    @Override
    public void submit() {
        getWebElement().submit();
    }

    @Override
    public void sendKeys(CharSequence... keysToSend) {
        getWebElement().sendKeys(keysToSend);
    }

    @Override
    public void clear() {
        getWebElement().clear();
    }

    @Override
    public String getTagName() {
        return getWebElement().getTagName();
    }

    @Override
    public boolean isSelected() {
        return getWebElement().isSelected();
    }

    @Override
    public boolean isEnabled() {
        return getWebElement().isEnabled();
    }

    @Override
    public SupremeElement contextClick() {
        rightClick();
        return this;
    }

    @Override
    public SupremeElement hover() {
        mouseOver();
        return this;
    }

    @Override
    public SupremeElement dragAndDropTo(String targetCssSelector) {
        dragAndDrop(new SupremeElement(By.cssSelector(targetCssSelector)));
        return this;
    }

    @Override
    public SupremeElement dragAndDropTo(WebElement webElement) {
        dragAndDrop(new SupremeElement(webElement));
        return this;
    }

    @Override
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

    @Override
    public File screenshot() {
        return getScreenshotAs(OutputType.FILE);
    }

    @Override
    public BufferedImage screenshotAsImage() {
        try {
            return ImageIO.read(screenshot());
        } catch (IOException e) {
            return null;
        }
    }

    @Override
    public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
        return ((TakesScreenshot) getDriver()).getScreenshotAs(target);
    }

    @Override
    public WebElement findElementByClassName(String using) {
        return get(By.className(using));
    }

    @Override
    public List<WebElement> findElementsByClassName(String using) {
        return getList(By.className(using));
    }

    @Override
    public WebElement findElementByCssSelector(String using) {
        return get(By.cssSelector(using));
    }

    @Override
    public List<WebElement> findElementsByCssSelector(String using) {
        return getList(By.cssSelector(using));
    }

    @Override
    public WebElement findElementById(String using) {
        return get(By.id(using));
    }

    @Override
    public List<WebElement> findElementsById(String using) {
        return getList(By.id(using));
    }

    @Override
    public WebElement findElementByLinkText(String using) {
        return get(By.linkText(using));
    }

    @Override
    public List<WebElement> findElementsByLinkText(String using) {
        return getList(By.linkText(using));
    }

    @Override
    public WebElement findElementByPartialLinkText(String using) {
        return get(By.partialLinkText(using));
    }

    @Override
    public List<WebElement> findElementsByPartialLinkText(String using) {
        return getList(By.partialLinkText(using));
    }

    @Override
    public WebElement findElementByName(String using) {
        return get(By.name(using));
    }

    @Override
    public List<WebElement> findElementsByName(String using) {
        return getList(By.name(using));
    }

    @Override
    public WebElement findElementByTagName(String using) {
        return get(By.tagName(using));
    }

    @Override
    public List<WebElement> findElementsByTagName(String using) {
        return getList(By.tagName(using));
    }

    @Override
    public WebElement findElementByXPath(String using) {
        return get(By.xpath(using));
    }

    @Override
    public List<WebElement> findElementsByXPath(String using) {
        return getList(By.xpath(using));
    }

    @Override
    public Coordinates getCoordinates() {
        return ((Locatable)getWebElement()).getCoordinates();
    }

    @Override
    public WebDriver getWrappedDriver() {
        return getDriver();
    }
}
