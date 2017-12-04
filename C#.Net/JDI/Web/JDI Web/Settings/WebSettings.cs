using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Core.Interfaces.Common;
using Epam.JDI.Core.Interfaces.Complex;
using Epam.JDI.Core.Interfaces.Settings;
using Epam.JDI.Core.Logging;
using Epam.JDI.Core.Settings;
using JDI_Matchers;
using JDI_Web.Selenium.Base;
using JDI_Web.Selenium.DriverFactory;
using JDI_Web.Selenium.Elements.Base;
using JDI_Web.Selenium.Elements.Common;
using JDI_Web.Selenium.Elements.Complex;
using JDI_Web.Selenium.Elements.Complex.Table;
using JDI_Web.Selenium.Elements.Complex.Table.Interfaces;
using OpenQA.Selenium;
using static System.Int32;
using static JDI_Web.Selenium.DriverFactory.WebDriverFactory;
using Image = JDI_Web.Selenium.Elements.Common.Image;

// ReSharper disable InconsistentNaming

namespace JDI_Web.Settings
{
    public class WebSettings : JDISettings
    {
        public static bool GetLatestDriver = true;
        public static string Domain;
        public static bool HasDomain => Domain != null && Domain.Contains("://");
        public static IWebDriver WebDriver => WebDriverFactory.GetDriver();
        private static WebDriverFactory _webDriverFactory;

        public static WebDriverFactory WebDriverFactory => _webDriverFactory ?? (_webDriverFactory = new WebDriverFactory());

        public static string UseDriver(DriverTypes driverName = DriverTypes.Firefox)
        {
            return WebDriverFactory.RegisterDriver(driverName);
        }

        public static string UseDriver(Func<IWebDriver> driver)
        {
            return WebDriverFactory.RegisterDriver(driver);
        }

        public static IJavaScriptExecutor JSExecutor => DriverFactory.GetDriver() as IJavaScriptExecutor;

        public static void Init(ILogger logger = null, IAssert assert = null,
            TimeoutSettings timeouts = null, IDriver<IWebDriver> driverFactory = null)
        {
            DriverFactory = driverFactory ?? new WebDriverFactory();
            Asserter = assert ?? new WebAssert();
            Timeouts = timeouts ?? new WebTimeoutSettings();
            Logger = logger ?? new LogAgregator(new NUnitLogger(), new Log4Net());
            MapInterfaceToElement.Init(DefaultInterfacesMap);
        }

        public static void InitNUnitDefault()
        {
            InitFromProperties();
        }

        public static void InitMsTestDefault()
        {
            InitFromProperties(new Log4Net());
        }

        public static void InitFromProperties(ILogger logger = null, IAssert assert = null,
            TimeoutSettings timeouts = null, IDriver<IWebDriver> driverFactory = null)
        {
            Init(logger, assert, timeouts, driverFactory);
            JDISettings.InitFromProperties();
            FillFromSettings(p => Domain = p, "Domain");
            FillFromSettings(p => DriverFactory.DriverPath = p, "DriversFolder");
            FillFromSettings(p => GetLatestDriver = p.ToLower().Equals("true") || p.ToLower().Equals("1"), "GetLatest");
            // FillFromSettings(p => DriverFactory.DriverVersion = p, "DriversVersion");
            // fillAction(p->getDriverFactory().getLatestDriver =
            //        p.toLowerCase().equals("true") || p.toLowerCase().equals("1"), "driver.getLatest");
            // fillAction(p->asserter.doScreenshot(p), "screenshot.strategy");
            FillFromSettings(p =>
            {
                p = p.ToLower();
                if (p.Equals("soft"))
                    p = "any,multiple";
                if (p.Equals("strict"))
                    p = "visible,single";
                if (p.Split(',').Length != 2) return;
                var parameters = p.Split(',').ToList();
                if (parameters.Contains("visible") || parameters.Contains("displayed"))
                    WebDriverFactory.ElementSearchCriteria = el => el.Displayed;
                if (parameters.Contains("any") || parameters.Contains("all"))
                    WebDriverFactory.ElementSearchCriteria = el => el != null;
                if (parameters.Contains("single") || parameters.Contains("displayed"))
                    OnlyOneElementAllowedInSearch = true;
                if (parameters.Contains("multiple") || parameters.Contains("displayed"))
                    OnlyOneElementAllowedInSearch = false;
            }, "SearchElementStrategy");

            FillFromSettings(p =>
            {
                string[] split = null;
                if (p.Split(',').Length == 2)
                    split = p.Split(',');
                if (p.ToLower().Split('x').Length == 2)
                    split = p.ToLower().Split('x');
                if (split != null)
                    BrowserSize = new Size(Parse(split[0]), Parse(split[1]));
            }, "BrowserSize");
        }

        private static readonly Dictionary<Type, Type> DefaultInterfacesMap = new Dictionary<Type, Type>
        {
            {typeof(IElement), typeof(WebElement)},
            {typeof(IButton), typeof(Button)},
            {typeof(IClickable), typeof(Clickable)},
            {typeof(IComboBox), typeof(ComboBox)},
            {typeof(ISelector), typeof(Selector)},
            {typeof(IText), typeof(Text)},
            {typeof(IImage), typeof(Image)},
            {typeof(ITextArea), typeof(TextArea)},
            {typeof(ITextField), typeof(TextField)},
            {typeof(ILabel), typeof(Label)},
            {typeof(IDropDown), typeof(Dropdown)},
            {typeof(IDropList), typeof(DropList)},
            {typeof(ITable), typeof(Table)},
            {typeof(ICheckBox), typeof(CheckBox)},
            {typeof(IRadioButtons), typeof(RadioButtons)},
            {typeof(ICheckList), typeof(CheckList)},
            {typeof(ITextList), typeof(TextList)},
            {typeof(ITabs), typeof(Tabs)},
            {typeof(IMenu), typeof(Menu)},
            {typeof(IFileInput), typeof(FileInput)},
            {typeof(IDatePicker), typeof(DatePicker)},
            {typeof(ILink), typeof(Link)}
        };
    }
}
