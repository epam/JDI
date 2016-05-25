using System;
using System.Collections.Generic;
using JDI_Core.Interfaces.Base;
using JDI_Core.Interfaces.Common;
using JDI_Core.Interfaces.Complex;
using JDI_Core.Logging;
using JDI_Core.Settings;
using JDI_Matchers;
using JDI_Web.Selenium.DriverFactory;
using JDI_Web.Selenium.Elements.Base;
using JDI_Web.Selenium.Elements.Common;
using JDI_Web.Selenium.Elements.Complex;
using JDI_Web.Selenium.Elements.Complex.table;
using JDI_Web.Selenium.Elements.Complex.table.interfaces;
using OpenQA.Selenium;

namespace JDI_Web.Settings
{
    public class WebSettings : JDISettings
    {
        public static string Domain;
        public static bool HasDomain => Domain != null && Domain.Contains("://");

        public static IWebDriver WebDriver => WebDriverFactory.GetDriver();
        public static WebDriverFactory WebDriverFactory => (WebDriverFactory) DriverFactory;

        public static string UseDriver(DriverTypes driverName)
        {
            return WebDriverFactory.RegisterDriver(driverName);
        }

        public static string UseDriver(Func<IWebDriver> driver)
        {
            return WebDriverFactory.RegisterDriver(driver);
        }

        public static IJavaScriptExecutor JSExecutor => DriverFactory.GetDriver() as IJavaScriptExecutor;


        public static void Init()
        {
            DriverFactory = new WebDriverFactory();
            Asserter = new NUnitMatcher();
            Timeouts = new WebTimeoutSettings();
            Logger = new JDILogger();
            MapInterfaceToElement.Init(DefaultInterfacesMap);
        }

        public new static void InitFromProperties()
        {
            Init();
            JDISettings.InitFromProperties();
            FillFromSettings(p => Domain = p, "domain");
            FillFromSettings(p => DriverFactory.DriverPath = p, "drivers.folder");
            // var isMultithread = Default["multithread"].ToString();
            // TODO
            /*Logger = isMultithread.Equals("true") || isMultithread.Equals("1")
                ? new TestNGLogger("JDI Logger", s -> String.format("[ThreadId: {0}] {0}", Thread.currentThread().getId(), s))
                : new TestNGLogger("JDI Logger");*/
        }

        private static readonly Dictionary<Type, Type> DefaultInterfacesMap = new Dictionary<Type, Type> {
            { typeof(IElement), typeof(WebElement)},
            { typeof(IButton), typeof(Button)},
            { typeof(IClickable), typeof(Clickable)},
            { typeof(IComboBox), typeof(ComboBox)},
            { typeof(ISelector), typeof(Selector)},
            { typeof(IText), typeof(Textbox)},
            { typeof(IImage), typeof(Image)},
            { typeof(ITextArea), typeof(TextArea)},
            { typeof(ITextField), typeof(TextField)},
            { typeof(ILabel), typeof(Label)},
            { typeof(IDropDown), typeof(Dropdown)},
            { typeof(IDropList), typeof(DropList)},
            { typeof(ITable), typeof(Table)},
            { typeof(ICheckBox), typeof(CheckBox)},
            { typeof(IRadioButtons), typeof(RadioButtons)},
            { typeof(ICheckList), typeof(CheckList)},
            { typeof(ITextList), typeof(TextList)},
            { typeof(ITabs), typeof(Tabs)},
            { typeof(IMenu), typeof(Menu)},
            { typeof(IFileInput), typeof(FileInput)},
            { typeof(IDatePicker), typeof(DatePicker)},
        };
    }
}
