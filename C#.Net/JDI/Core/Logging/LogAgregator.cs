
using System;
using System.Linq;
using JDI_Commons;

namespace Epam.JDI.Core.Logging
{
    public class LogAgregator : ILogger
    {
        private readonly ILogger[] _loggers;

        public LogAgregator(params ILogger[] loggers)
        {
            _loggers = loggers;
        }

        public void Exception(Exception ex)
        {
            _loggers.ToList().ForEach(l => l.Exception(ex));
        }

        public void Trace(string message)
        {
            foreach (var logger in _loggers)
                logger.Trace(message);
        }

        public void Debug(string message)
        {
            foreach (var logger in _loggers)
                logger.Debug(message);
        }

        public void Info(string message)
        {
            foreach (var logger in _loggers)
                logger.Info(message);
        }

        public void Error(string message)
        {
            foreach (var logger in _loggers)
                logger.Error(message);
        }

        public void Step(string message)
        {
            foreach (var logger in _loggers)
                logger.Step(message);
        }

        public void TestDescription(string message)
        {
            foreach (var logger in _loggers)
                logger.TestDescription(message);
        }

        public void TestSuit(string message)
        {
            foreach (var logger in _loggers)
                logger.TestSuit(message);
        }
    }
}
