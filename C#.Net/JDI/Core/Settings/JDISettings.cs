using System;
using Epam.JDI.Core.Interfaces.Settings;
using Epam.JDI.Core.Logging;
using JDI_Core.Interfaces.Settings;
using static Epam.JDI.Core.Properties.Settings;

namespace Epam.JDI.Core.Settings
{
    public class JDISettings
    {
        public static ILogger Logger;
        public static IAssert Asserter;
        public static TimeoutSettings Timeouts = new TimeoutSettings();
        public static bool IsDemoMode;
        public static HighlightSettings HighlightSettings = new HighlightSettings();
        public static bool ShortLogMessagesFormat = true;
        public static string JDISettingsPath = "test.properties";
        public static bool ExceptionThrown;
        public static IDriver<object> DriverFactory = new DefaultDriver();
        public static bool UseCache = false;
        
        public static void ToLog(string message, LogLevels level)
        {
            switch (level)
            {
                case LogLevels.Debug:
                    Logger.Debug(message);
                    return;
                case LogLevels.Error:
                    Logger.Error(message);
                    return;
            }
            Logger.Info(message);
        }

        public static string UseDriver(string driverName)
        {
            return DriverFactory.RegisterDriver(driverName);
        }

        public static void InitFromProperties()
        {
            FillFromSettings(p => DriverFactory.RegisterDriver(p), "driver");
            FillFromSettings(p => DriverFactory.SetRunType(p), "run.type");
            FillFromSettings(p => Timeouts.WaitElementSec = int.Parse(p), "timeout.wait.element");
            FillFromSettings(p => Timeouts.WaitPageLoadSec = int.Parse(p), "timeout.wait.pageLoad");
        }

        protected static void FillFromSettings(Action<string> action, string name)
        {
            ExceptionUtils.AvoidExceptions(() => action.Invoke(Default[name].ToString()));
        }
        
        public static void InitFromProperties(string propertyPath)
        {
            JDISettingsPath = propertyPath;
            InitFromProperties();
        }

        public static void NewTest()
        {
            ExceptionThrown = false;
        }

        public static Exception Exception(string msg)
        {
            ExceptionThrown = true;
             return Asserter.Exception(msg);
        }
    }
}
