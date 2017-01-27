using System;

namespace Epam.JDI.Core.Logging
{
    public interface ILogger
    {
        void Exception(Exception ex);
        void Trace(string message);
        void Debug(string message);
        void Info(string message);
        void Error(string message);
        void Step(string message);
        void TestDescription(string message);
        void TestSuit(string message);
    }
}
