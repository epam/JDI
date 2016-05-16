using System;
using log4net;

namespace Epam.JDI.Core.Logging
{
    public class Log4Net : ILogger
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
            throw new NotImplementedException();
        }

        public void TestDescription(string message)
        {
            throw new NotImplementedException();
        }

        public void TestSuit(string message)
        {
            throw new NotImplementedException();
        }
    }
}
