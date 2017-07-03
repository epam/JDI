using System;
using Epam.JDI.Core.Interfaces.Settings;
using Epam.JDI.Core.Logging;
using JDI_Core.Interfaces.Settings;
using static System.Int32;
using static Epam.JDI.Core.Properties.Settings;

// ReSharper disable InconsistentNaming

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
        public static IDriver<IDisposable> DriverFactory;
        public static bool UseCache;

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
            FillFromSettings(p => DriverFactory.RegisterDriver(p), "Driver");
            FillFromSettings(p => DriverFactory.SetRunType(p), "RunType");
            FillFromSettings(p => Timeouts.WaitElementSec = Parse(p), "TimeoutWaitElement");
            FillFromSettings(p => ShortLogMessagesFormat = p.ToLower().Equals("short"), "LogMessageFormat");
            FillFromSettings(p =>
                UseCache = p.ToLower().Equals("true") || p.ToLower().Equals("1"), "Cache");
            FillFromSettings(p =>
                UseCache = p.ToLower().Equals("true") || p.ToLower().Equals("1"), "DemoMode");
            FillFromSettings(p => HighlightSettings.SetTimeoutInSec(Parse(p)), "DemoDelay");
        }

        protected static void FillFromSettings(Action<string> action, string name)
        {
            //var b = System.Configuration.ConfigurationManager.AppSettings["DriversFolder"];
            //var a = Properties.Settings.Default["DriversFolder"];
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

        public static Exception Exception(string msg, Exception ex)
        {
            ExceptionThrown = true;
            return Asserter.Exception(msg, ex);
        }

        public static Exception Exception(string msg)
        {
            ExceptionThrown = true;
            return Asserter.Exception(msg);
        }
    }
}
