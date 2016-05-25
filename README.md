#JDI UI Test Automation Framework

||C#.Net|Java|
|---|---|---|
|CI|[![Windows Build status](https://ci.appveyor.com/api/projects/status/0tqpq0g45urdhg2m/branch/master?svg=true)](https://ci.appveyor.com/project/elv1s42/jdi-yqifx/branch/master)|[![Build Status](https://travis-ci.org/epam/JDI.svg?branch=master)](https://travis-ci.org/epam/JDI)|
|Package|[![NuGet Version and Downloads count](https://buildstats.info/nuget/JDI.UITestFramework)](https://www.nuget.org/packages/JDI.UITestFramework)|[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.epam.jdi/jdi-commons/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.epam.jdi/jdi-commons)|

[![License](https://img.shields.io/badge/license-GPLv3-blue.svg)](http://www.gnu.org/licenses/gpl-3.0.html)
[![stackoverflow](https://img.shields.io/badge/stackoverflow-jdiframework-orange.svg?style=flat)](http://stackoverflow.com/questions/tagged/jdiframework)

Copyright (c) 2016, EPAM Systems

License: GPL v3. [GPL License](http://www.gnu.org/licenses)

##Try
First step: just download this [simplest Java example](https://github.com/epam/JDI-Examples/archive/master.zip) and run test

No special actions required (Just put `chromedriver.exe` into `src\main\resources` [see example](http://pix.my/pGNrjbmD)) 

##Introduction

JDI – is the test Framework for UI test automation. It extends the Page Objects design pattern and introduces many additional elements along with implementation of its common usages.

The framework bases on the following concept: “Easy things should be easy, and hard things should be possible” Larry Wall (c).

Thus, all elements of the framework and all capabilities it provides have default realizations that would be used in most cases. 

However, if your application performs some actions in a different way, you can override this behavior on any level - just for this element, for all elements with the same type, or even customize the scenario of actions for all elements.

Similarly, you can use any external tools and frameworks for the relevant functionality - different loggers, reporting tools, drivers test runners, and asserters.

We strive to make the test process easier and full of joy.

Enjoy to us! :)


## How to use
### Site
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
###Java (Maven):
####Web:
```xml
<dependency>
    <groupId>com.epam.jdi</groupId>
    <artifactId>jdi-uitest-web</artifactId>
    <version>1.0.5</version>
</dependency>
```
####Mobile:
```xml
<dependency>
    <groupId>com.epam.jdi</groupId>
    <artifactId>jdi-uitest-mobile</artifactId>
    <version>1.0.5</version>
</dependency>
```
####Desktop:
```xml
<dependency>
    <groupId>com.epam.jdi</groupId>
    <artifactId>jdi-uitest-gui</artifactId>
    <version>1.0.5</version>
</dependency>
```
*NOTE:* You need to setup Java version 8 or higher (see instruction on [Maven](https://maven.apache.org/plugins/maven-compiler-plugin/examples/set-compiler-source-and-target.html) site or example [here](https://github.com/epam/JDI/blob/master/Java/Tests/jdi-uitest-tutorialtests/pom.xml))

###.Net 
Add Nuget package "JDI.UITestFramework"

##Examples
[Java tests](https://github.com/epam/JDI/tree/master/Java/Tests)

[C# tests](https://github.com/epam/JDI/tree/master/C%23.Net/Tests)

##Methods Documentation
[Java](https://github.com/epam/JDI/blob/master/JDI_UI_TEST_Framework.docx )

##Links

Site: http://jdi.epam.com/
VK: https://vk.com/jdi_framework

You can ask your questions on StackOverflow with [![htmlelements](https://img.shields.io/badge/stackoverflow-jdiframework-orange.svg?style=flat)](http://stackoverflow.com/questions/tagged/jdiframework) tag.
##Contacts:

Mail: roman_iovlev@epam.com

Skype: roman.iovlev

