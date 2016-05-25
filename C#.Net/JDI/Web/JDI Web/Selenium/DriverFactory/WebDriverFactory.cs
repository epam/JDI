using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading;
using JDI_Core.Interfaces.Base;
using JDI_Core.Interfaces.Settings;
using JDI_Core.Settings;
using JDI_Web.Selenium.Elements.Base;
using OpenQA.Selenium;
using OpenQA.Selenium.Chrome;
using OpenQA.Selenium.Firefox;
using OpenQA.Selenium.IE;
using OpenQA.Selenium.Remote;
using static JDI_Core.Settings.JDISettings;
using static JDI_Web.Properties.Settings;

namespace JDI_Web.Selenium.DriverFactory
{
    public enum RunTypes { Local, Remote }
    public enum DriverTypes { Chrome, Firefox, IE }
    public class WebDriverFactory : IDriver<IWebDriver>
    {
        private Dictionary<string, Func<IWebDriver>> Drivers { get; } = new Dictionary<string, Func<IWebDriver>>();
        private Dictionary<string, IWebDriver> RunDrivers { get; } = new Dictionary<string, IWebDriver>();
        public string CurrentDriverName { get; set; }
        public string DriverPath { get; set; } = "C:/Selenium";
        public RunTypes RunType { get; set; } = RunTypes.Local;
        public HighlightSettings HighlightSettings = new HighlightSettings();
        public Func<IWebElement, bool> ElementSearchCriteria = el => el.Displayed;

        private readonly Dictionary<DriverTypes, string> _driverNamesDictionary = new Dictionary<DriverTypes, string>
        {
            {DriverTypes.Chrome, "chrome"},
            {DriverTypes.Firefox, "firefox"},
            {DriverTypes.IE, "internet explorer"}
        };
        private readonly Dictionary<DriverTypes, Func<string, IWebDriver>> _driversDictionary = new Dictionary<DriverTypes, Func<string, IWebDriver>>
        {
            {DriverTypes.Chrome, path => string.IsNullOrEmpty(path) ? new ChromeDriver() : new ChromeDriver(path)},
            {DriverTypes.Firefox, path => new FirefoxDriver()},
            {DriverTypes.IE, path => string.IsNullOrEmpty(path) ? new InternetExplorerDriver() : new InternetExplorerDriver(path)}
        };

        //TODO 
        private string RegisterLocalDriver(DriverTypes driverType)
        {
            return RegisterDriver(_driverNamesDictionary[driverType], 
                () => WebDriverSettings(_driversDictionary[driverType](DriverPath)));
        }

        public string RegisterDriver(string driverName, Func<IWebDriver> driver)
        {
            try
            {
                Drivers.Add(driverName, driver);
                CurrentDriverName = driverName;
            }
            catch
            {
                throw new Exception($"Can't register WebDriver {driverName} . Driver with same name already registered");
            }
            return driverName;
        }

        public string RegisterDriver(Func<IWebDriver> driver)
        {
            return RegisterDriver("Driver" + (Drivers.Count + 1), driver);
        }

        public IWebDriver GetDriver()
        {
            try
            {
                if (!string.IsNullOrEmpty(CurrentDriverName))
                    return GetDriver(CurrentDriverName);
                RegisterDriver(DriverTypes.Chrome);
                return GetDriver(DriverTypes.Chrome);
            }
            catch
            {
                throw new Exception(); // TODO
            }
        }

        public IWebDriver GetDriver(DriverTypes driverType)
        {
            return GetDriver(_driverNamesDictionary[driverType]);
        }
        public Func<IWebDriver, IWebDriver> WebDriverSettings = driver => {
            driver.Manage().Window.Maximize();
            driver.Manage().Timeouts().ImplicitlyWait(TimeSpan.FromSeconds(Timeouts.WaitElementSec));
            return driver;
        };

        public IWebDriver GetDriver(string driverName)
        {
            if (!Drivers.ContainsKey(driverName))
                throw new Exception($"Can't find driver with name {driverName}");
            try
            {
                if (RunDrivers.ContainsKey(driverName))
                    return RunDrivers[driverName];
                var resultDriver = Drivers[driverName]();
                RunDrivers.Add(driverName, resultDriver);
                if (resultDriver == null)
                    throw new Exception($"Can't get Webdriver {driverName}. This Driver name not registered");
                return resultDriver;
            }
            catch
            {
                throw new Exception("Can't get driver.");
            }
        }
        
        public string RegisterDriver(string driverName)
        {
            try
            {
                var driverType = _driverNamesDictionary.FirstOrDefault(x => x.Value == driverName).Key;
                return RegisterLocalDriver(driverType);
            }
            catch {
                throw new Exception(); // TODO
            }
        }

        public string RegisterDriver(DriverTypes driverType)
        {
            switch (RunType)
            {
                case RunTypes.Local:
                    return RegisterLocalDriver(driverType);
                case RunTypes.Remote:
                    return RegisterRemoteDriver(driverType);
            }
            throw new Exception(); // TODO
        }

        private string RegisterRemoteDriver(DriverTypes driverType)
        {
            var capabilities = new DesiredCapabilities(new Dictionary<string, object>
            {
                {"browserName", _driverNamesDictionary[driverType]},
                {"version", string.Empty},
                {"javaScript", true}
            });

            return RegisterDriver("Remote_" + _driverNamesDictionary[driverType],
                () => new RemoteWebDriver(new Uri(Default.remote_url), capabilities));
        }

        public void SwitchToDriver(string driverName)
        {
            if (Drivers.ContainsKey(driverName))
                CurrentDriverName = driverName;
            else
                throw new Exception($"Can't switch to Webdriver {driverName}. This Driver name not registered");
        }

        public void ReopenDriver()
        {
            ReopenDriver(CurrentDriverName);
        }

        public void ReopenDriver(string driverName)
        {
            if (RunDrivers.ContainsKey(driverName))
            {
                RunDrivers[driverName].Close();
                RunDrivers.Remove(driverName);
            }
            if (Drivers.ContainsKey(driverName))
                GetDriver(); // TODO
        }

        public void Close()
        {
            foreach (var driver in RunDrivers)
                driver.Value.Quit();
            RunDrivers.Clear();
        }

        public void SetRunType(string runType)
        {
            switch (runType)
            {
                case "local" : RunType = RunTypes.Local; return;
                case "remote" : RunType = RunTypes.Remote; return;
            }
            RunType = RunTypes.Local;
        }

        public bool HasDrivers()
        {
            return Drivers.Any();
        }

        public bool HasRunDrivers()
        {
            return RunDrivers.Any();
        }
        
        public void Highlight(IElement element)
        {
            Highlight(element, HighlightSettings);
        }

        public void Highlight(IElement element, HighlightSettings highlightSettings)
        {
            if (highlightSettings == null)
                highlightSettings = new HighlightSettings();
            string orig = ((WebElement) element).GetWebElement().GetAttribute("style");
            element.SetAttribute("style",
                $"border: 3px solid {highlightSettings.FrameColor}; background-color: {highlightSettings.BgColor};");
            Thread.Sleep(highlightSettings.TimeoutInSec * 1000);
            element.SetAttribute("style", orig);
        }
        
    }
}