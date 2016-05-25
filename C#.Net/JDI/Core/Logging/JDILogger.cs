using System;
using System.Collections.Concurrent;
using System.IO;
using System.Linq;
using static System.String;

namespace Epam.JDI.Core.Logging
{
    public class JDILogger : ILogger
    {
        public Func<string> LogFileFormat = () => "{0}_" + DateTime.Now.ToString("yyyy-MM-dd_HH-mm-ss") + ".log";
        private static readonly ConcurrentDictionary<string, object> LogFileSyncRoots = new ConcurrentDictionary<string, object>();
        private static readonly string LogRecordTemplate = Environment.NewLine + "[{0}] {1}: {2}" + Environment.NewLine;
        public Func<string> LogDirectoryRoot = () => "/../.Logs/";
        public bool CreateFoldersForLogTypes = true;

        private static string GetLogRecord(string typeName, string msg)
        {
            return Format(LogRecordTemplate, typeName, DateTime.Now.ToString("yyyy-MM-dd HH:mm:ss.ffff"), msg);
        }

        public JDILogger()
        {
            var logRoot = GetValidUrl(ExceptionUtils.AvoidExceptions(() => Properties.Settings.Default["log.path"].ToString()));
            if (!IsNullOrEmpty(logRoot))
                LogDirectoryRoot = () => logRoot;
        }

        public JDILogger(string path)
        {
            LogDirectoryRoot = () => path;
        }

        public static string GetValidUrl(string logPath)
        {
            if (IsNullOrEmpty(logPath))
                return "";
            var result = logPath.Replace("/", "\\");
            if (result[1] != ':' && result.Substring(0, 3) != "..\\")
                result = (result[0] == '\\')
                    ? ".." + result
                    : "..\\" + result;
            return (result.Last() == '\\')
                ? result
                : result + "\\";
        }

        private void InLog(string fileName, string typeName, string msg)
        {
            System.Diagnostics.Debug.WriteLine("TEST: " + msg);
            var logDirectory = GetValidUrl(LogDirectoryRoot()) + (CreateFoldersForLogTypes ? fileName + "s\\" : "");
            CreateDirectory(logDirectory);
            var logFileName = logDirectory + Format(LogFileFormat(), fileName);

            var logFileSyncRoot = LogFileSyncRoots.GetOrAdd(logFileName, s => s);
            lock (logFileSyncRoot)
            {
                File.AppendAllText(logFileName, GetLogRecord(typeName, msg));
            }
        }

        public static void CreateDirectory(string directoryName)
        {
            if (!File.Exists(directoryName))
                Directory.CreateDirectory(directoryName);
        }

        public void Trace(string msg)
        {
            InLog("Event", "Trace", msg);
        }
        public void Debug(string msg)
        {
            InLog("Event", "Debug", msg);
        }
        public void Info(string msg)
        {
            InLog("Event", "Info", msg);
        }

        public void Error(string msg)
        {
            InLog("Error", "Error", msg);
            InLog("Event", "Error", msg);
        }
        public void Step(string msg)
        {
            InLog("Event", "Step", msg);
        }
        public void TestDescription(string msg)
        {
            InLog("Event", "Test", msg);
        }
        public void TestSuit(string msg)
        {
            InLog("Event", "Suit", msg);
        }
    }
}

