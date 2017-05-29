# JDI UI Test Automation Framework

| | C#.Net | Java |
|---|---|---|
|CI|[![Build status](https://ci.appveyor.com/api/projects/status/98p7dbaiggwp7ilh?svg=true)](https://ci.appveyor.com/project/elv1s42/jdi-m0fd6)|[![Build Status](https://travis-ci.org/epam/JDI.svg?branch=master)](https://travis-ci.org/epam/JDI)|
|Package|[![NuGet Version](https://img.shields.io/nuget/v/JDI.UICore.svg)](https://www.nuget.org/packages/JDI.UICore)|[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.epam.jdi/jdi-commons/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.epam.jdi/jdi-uitest-web)|

[![License](https://img.shields.io/badge/license-GPLv3-blue.svg)](http://www.gnu.org/licenses/gpl-3.0.html)
[![stackoverflow](https://img.shields.io/badge/stackoverflow-jdiframework-orange.svg?style=flat)](http://stackoverflow.com/questions/tagged/jdiframework)

Copyright (c) 2017, EPAM Systems

License: GPL v3. [GPL License](http://www.gnu.org/licenses)

## Introduction

JDI – is the test Framework for UI test automation. It extends the Page Objects design pattern and introduces many additional elements along with implementation of its common usages.

The framework bases on the following concept: “Easy things should be easy, and hard things should be possible” Larry Wall (c).

Thus, all elements of the framework and all capabilities it provides have default realizations that would be used in most cases. 

However, if your application performs some actions in a different way, you can override this behavior on any level - just for this element, for all elements with the same type, or even customize the scenario of actions for all elements.

Similarly, you can use any external tools and frameworks for the relevant functionality - different loggers, reporting tools, drivers test runners, and asserters.

We strive to make the test process easier and full of joy.

Enjoy to us! :)

## Simple detailed first example
Lets implement simple login scenario with JDI on our test site (https://jdi-framework.github.io/tests/)
1) Open Login page
2) Login as user
3) Check that homePage opened

### 1. Create Java test project and add jdi dependency
If you familiar with Java or already have test project then just add com.epam.jdi:jdi-uitest-web dependency in your project
```xml
<dependency>
    <groupId>com.epam.jdi</groupId>
    <artifactId>jdi-uitest-web</artifactId>
    <version>1.0.67</version>
</dependency>
```
### OR 
Download simple already predefined test project using this [link](https://github.com/epam/JDI-Examples/archive/master.zip)

Unpack zip and run project (open pom.xml) with IntelliJIdea or Eclipse

Note: Rebuild your project if necessary (In IntelliJIdea do Build > Rebuild Project)

### 2. Setup your UI Objects (PageObjects) for your project
Note: if you download example project via link this Pages already exist in \src\main\java\org\mytests\uiobjects\example

[View](http://pix.my/o/3lHw5f?1495800530)

Pages:
```Java
@JSite(domain = "https://jdi-framework.github.io/tests/")
public class JDIExampleSite extends WebSite {
    @JPage(url = "/")
    public static LoginPage loginPage;
    @JPage(url = "/index.htm", title = "Index Page")
    public static HomePage homePage;

    public static LoginForm loginForm;
}
```
Then setup Pages content
```Java
public class HomePage extends WebPage {
}
public class User {
    public String name = "epam";
    public String password = "1234";
}
public class LoginPage extends WebPage {
    @FindBy(css = ".profile-photo")
    public Label profilePhoto;

    public void login() {
        loginPage.profilePhoto.click();
        loginForm.loginAs(new User());
    }
}
public class LoginForm extends Form<User> {
    @FindBy(id="Login")
    public TextField name;
    @FindBy(id="Password")
    public TextField password;

    @FindBy(css="[type=submit]")
    public Button enter;
}
```
### 3. (Optional) Add test properties 
See in \src\test\test.properties.For example:
```Java
driver=chrome
timeout.wait.element=10
driver.getLatest=true
```
### 4. Init Test Site (like PageFactory) via just one line
Note: if you download example project via link this Test already exist in \src\test\java\org\mytests\tests\example\SimpleTestsInit.java
```Java
    @BeforeSuite(alwaysRun = true)
    public static void setUp() throws Exception {
        WebSite.init(JDIExampleSite.class);
        logger.info("Run Tests");
    }
```
### 5. Write a simple test (SimpleExampleTest.java)
Note: if you download example project via link this Test already exist in \src\test\java\org\mytests\tests\example\SimpleExampleTest.java
```Java
    @Test
    public void loginExample() {
        loginPage.open();
        loginPage.login();
        homePage.checkOpened();
    }    
```
### 6. Run Test. 
Right click on test and choose Run

[View](http://pix.my/o/GN0ykf?1495801680)
### 7. Observe results in console log or in Allure report (target > site > index.html)
Open Maven Window (View > Tool Windows > Maven Projects)

And run allure:report (jdi.examples > Plugins > allure > allure:report)

[View](http://pix.my/o/qyx2Pj?1495800923)

## More test examples
### 1) Filling large form in one row example with DataProvider using Business entity Attendee
```Java
    @Test(dataProvider = "attendees", dataProviderClass = AttendeesProvider.class)
    public void fillFormExample(Attendee attendee) {
        sendCVPage.open();
        cvForm.submit(attendee);
        // Check
        Assert.contains(() -> jobDescriptionPage.captcha.getAttribute("class"), "form-field-error");
    }
```
1. Open SendCV page
2. Submit cv form with your attendee data 
3. Check that Form not sent because captcha field has an error

### 2) Work with Table (jobList) example
```Java
    @Test
    public void getTableInfoExample() {
        jobsPage.open();
        Assert.isFalse(jobsList::isEmpty);
        Assert.areEquals(jobsList.columns().count(), 4);
        Assert.areEquals(jobsList.rows().count(), 2);
        Assert.areEquals(jobsList.getValue(),
            "||X||JOB_NAME|JOB_CATEGORY|JOB_LOCATION|APPLY||\n" +
            "||1||QA Specialist|Software Test Engineering|St-Petersburg, Russia|Apply||\n" +
            "||2||Senior QA Automation Engineer|Software Test Engineering|St-Petersburg, Russia|Apply||");
    }
```
1. Open Jobs page
2. Verify data in jobsList table
* Table has records
* Columns amount equal 4
* Rows amount equal 2
* Table structure is correct

### 3) Simple example of complex Search in table 
```Java
@Test
    public void searchInTableExample() {
        jobsList.row(withValue("Senior QA Automation Engineer"), inColumn("JOB_NAME"))
            .get("APPLY").select();
        // Check
        jobDescriptionPage.checkOpened();
    }
```
0. Wait while table have some Rows
1. Get first row where value in column "JOB_NAME" equals to "Senior QA Automation Engineer"
2. For this row select cell in Column APPLY

### 4) Simple example of complex Search with multiple criteria in table 
```Java    
    @Test
    public void searchByMultiCriteriaInTableExample() {
        MapArray<String, ICell> firstRow = jobsList.rows(
                "JOB_NAME=Senior QA Automation Engineer",
                "JOB_CATEGORY=Software Test Engineering")
                .first().value;

        Assert.areEquals(firstRow.get("JOB_LOCATION").getText(), "St-Petersburg, Russia");
    }
```
1. Get first row where 
    value in column "JOB_NAME" equals to "Senior QA Automation Engineer" AND 
    value in column "JOB_CATEGORY" equals to "Software Test Engineering" 
2. For this row select cell in Column APPLY

### 5) Some our matchers examples
```Java    
    @Test
    public void matcherExamples() {
        Assert.contains("Test Text", "Text");
        Assert.matches("1352-423-85746", "\\d{4}-\\d{3}-\\d{5}");
    }
```
### 6) Assert that actual result *becomes* equal expected result during specified timeout
Just add '() -> ' to your Assert and wait some result from service or page loading (example fails if you remove '() ->' operator)
```Java   
    private int i = 0;
    private String[] searchResults = new String[] { "Iphone 4", "Iphone 5S", "Iphone 6" };
    private String getNext() {
        if (i == 3) i = 0;
        return searchResults[i++];
    }
    @Test
    public void waitAssertsExample() {
        Assert.areEquals(() -> getNext(), "Iphone 6");
        Assert.contains(() -> getNext(), "Iphone 5");
        Assert.matches(() -> getNext(), ".*S");
    }
```
### 7) Match lists and arrays
```Java    
    @Test
    public void listAssertsExample() {
        Assert.assertEach(searchResults).contains("Iphone");
        Assert.assertEach(searchResults).matches("Iphone \\d.*");
        Assert.arrayEquals(searchResults,
                new String[] { "Iphone 4", "Iphone 5S", "Iphone 6" });
        Assert.listEquals(asList(searchResults),
                asList("Iphone 4", "Iphone 5S", "Iphone 6"));
        Assert.assertEach(searchResults).areDifferent();
        Assert.assertEach(sameList).areSame();
        Assert.isSortedByAsc(sortedListAsc);
        Assert.isSortedByDesc(sortedArrayDesc);
    }

```
### 8) Or even verify exceptions
```Java  
    @Test
    public void exceptionAssertsExample() {
        Assert.throwException(this::throwException, "Test Exception");
        Assert.throwException(this::throwException, RuntimeException.class, "Test Exception");
        Assert.hasNoExceptions(this::getNext);
    }
```

See more Eamples [here](https://github.com/epam/JDI/tree/master/Java/Tests/jdi-uitest-tutorialtests/src/test/java/com/epam/jdi/uitests/testing)

### UI Objects
```Java
@JSite(domain = "https://www.epam.com")
public class EpamSite extends WebSite {

    @JPage(url = "/", title = "EPAM | Software Product Development Services")
    public static HomePage homePage;
    @JPage(url = "/careers", title = "Careers")
    public static CareerPage careerPage;
    ...
    
    @FindBy(css = ".tile-menu>li>a")              // Menu with limited list of options described by enum Header menu
    public static Menu<HeaderMenu> headerMenu; 
    @FindBy(css = ".tile-menu>li>a")              // List of elements accessible only by index
    public static List<Label> listMenu;
    @FindBy(css = ".tile-menu>li>a")              // List of elements with ability to access by name
    public static Elements<Label> listMenu;
}
```
### Page
```Java
public class CareerPage extends WebPage {
    @FindBy(className = "job-search-input")       // Simple Text field
    public ITextField keywords;
    
    public IDropDown<JobCategories> category = new Dropdown<>(  // Complex Dropdown with two locators
        By.className("multi-select-filter"), 
        By.className("blue-checkbox-label"));
    @FindBy(className = "career-location-box")    // Simple Dropdown
    public IDropDown<Locations> city;

    @FindBy(className = "job-search-button")      // Simple Button
    public IButton selectButton;

}
```
### Form
```Java
public class AddCVForm extends Form<Attendee> {  
    @FindBy(css = "[placeholder='First Name']") 
    private ITextField name;
    @FindBy(css = "[placeholder='Last Name']")  
    private ITextField lastName;
    @FindBy(css = "[placeholder='Email']")      
    private ITextField email;
    private IDropDown country = new Dropdown<>(
        By.cssSelector(".country-wrapper .arrow"), 
        By.xpath("//*[contains(@id,'select-box-applicantCountry')]//li"));
    private IDropDown city = new Dropdown<>(
        By.cssSelector(".city-wrapper .arrow"), 
        By.xpath("//*[contains(@id,'select-box-applicantCity')]//li"));
    @FindBy(css = ".file-upload")               
    private RFileInput cv;
    @FindBy(css = ".comment-input")             
    private ITextArea comment;

    @FindBy(xpath = "//*[.='Submit']")          
    private IButton submit;
    @FindBy(xpath = "//*[.='Cancel']")          
    private IButton cancel;

}
```

## How to add
### Java:
#### Web:
#### Maven
```xml
<dependency>
    <groupId>com.epam.jdi</groupId>
    <artifactId>jdi-uitest-web</artifactId>
    <version>1.0.67</version>
</dependency>
```
#### Gradle
```xml
dependencies {
  testCompile 'com.epam.jdi:jdi-uitest-web:1.0.39'
}
```
#### IVY
```xml
<ivy-module>
  <dependencies>
    <dependency org="com.epam.jdi" name="jdi-uitest-web" rev="1.0.39"/>
  </dependencies>
</ivy-module>
```
#### Mobile(Maven):
```xml
<dependency>
    <groupId>com.epam.jdi</groupId>
    <artifactId>jdi-uitest-mobile</artifactId>
    <version>1.0.67</version>
</dependency>
```
#### Desktop(Maven):
```xml
<dependency>
    <groupId>com.epam.jdi</groupId>
    <artifactId>jdi-uitest-gui</artifactId>
    <version>1.0.67</version>
</dependency>
```
*NOTE:* You need to setup Java version 8 or higher (see instruction on [Maven](https://maven.apache.org/plugins/maven-compiler-plugin/examples/set-compiler-source-and-target.html) site or example [here](https://github.com/epam/JDI/blob/master/Java/Tests/jdi-uitest-tutorialtests/pom.xml))

## JDI settings
```Java
driver=chrome
timeout.wait.element=10
domain=https://www.epam.com/
driver.getLatest=true
search.element.strategy=strict | soft
browser.size=1800X1000
demo.mode=false | true
multithread=true
run.type=local | remote
screenshot.strategy=on fail | on | off
```
See [more settings examples](https://github.com/epam/JDI/blob/master/Java/Tests/jdi-uitest-tutorialtests/src/test/resources/test.properties)

### .Net 
Add Nuget package "JDI.UIWeb" to your solution

## Examples
[Java tests](https://github.com/epam/JDI/tree/master/Java/Tests)

[C# tests](https://github.com/epam/JDI/tree/master/C%23.Net/Tests)

## Methods Documentation
[Java](https://github.com/epam/JDI/blob/master/JDI_UI_TEST_Framework.docx )

## Links

Site: http://jdi.epam.com/

VK: https://vk.com/jdi_framework

You can ask your questions on StackOverflow with [![htmlelements](https://img.shields.io/badge/stackoverflow-jdiframework-orange.svg?style=flat)](http://stackoverflow.com/questions/tagged/jdiframework) tag.

## Contacts:

Mail: roman_iovlev@epam.com

Skype: roman.iovlev

