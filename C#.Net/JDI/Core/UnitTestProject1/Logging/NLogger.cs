using NLog;

namespace JDI_Core.Logging
{
    public class NLogger : ILogger
    {
        private NLog.ILogger _log;
        private readonly object _locker = new object();
        public NLog.ILogger Log
        {
            get
            {
                if (_log != null)
                    return _log;

                lock (_locker)
                {
                    if (_log == null)
                        _log = LogManager.GetCurrentClassLogger();
                }
                return _log;
            }
        }

        public void Trace(string message)
        {
            Log.Trace(message);
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
            throw new System.NotImplementedException();
        }

        public void TestDescription(string message)
        {
            throw new System.NotImplementedException();
        }

        public void TestSuit(string message)
        {
            throw new System.NotImplementedException();
        }
    }
}
