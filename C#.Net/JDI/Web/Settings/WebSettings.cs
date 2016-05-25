using System;
using System.Collections.Generic;
using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Core.Interfaces.Common;
using Epam.JDI.Core.Interfaces.Complex;
using Epam.JDI.Core.Logging;
using Epam.JDI.Core.Settings;
using Epam.JDI.Matchers;
using Epam.JDI.Web.Selenium.DriverFactory;
using Epam.JDI.Web.Selenium.Elements.Base;
using Epam.JDI.Web.Selenium.Elements.Common;
using Epam.JDI.Web.Selenium.Elements.Complex;
using Epam.JDI.Web.Selenium.Elements.Complex.table;
using Epam.JDI.Web.Selenium.Elements.Complex.table.interfaces;
using OpenQA.Selenium;

namespace Epam.JDI.Web.Settings
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
            Logger = new Log4Net();
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
