using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using log4net;

namespace Epam.JDI.Core.Logging
{
    public class LoggerNet : ILogger
    {
        private ILog _log;
        private readonly object _locker = new object();
        public ILog Log
        {
            get
            {
                if (_log != null)
                    return _log;

                lock (_locker)
                {
                    if (_log == null)
                        _log = LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType);
                }
                return _log;
            }
        }

        public void Trace(string message)
        {
            Log.Logger.Log(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType, log4net.Core.Level.Trace, message, null);
        }

        public void Debug(string message)
        {
            Log.Debug(message);
        }

        public void Info(string message)
        {
            Log.Info(message);
        }

        public void Error(string message)
        {
            Log.Error(message);
        }

        public void Step(string message)
        {
            new NotImplementedException();
        }

        public void TestDescription(string message)
        {
            new NotImplementedException();
        }

        public void TestSuit(string message)
        {
            new NotImplementedException();
        }

        public void ToLog(string message, LogLevels level)
        {
            switch (level)
            {
                case LogLevels.Info:
                    Info(message);
                    break;
                case LogLevels.Error:
                    Error(message);
                    break;

                case LogLevels.Trace:
                    Trace(message);
                    break;

                case LogLevels.Debug:
                    Debug(message);
                    break;
            }
        }
    }
}
