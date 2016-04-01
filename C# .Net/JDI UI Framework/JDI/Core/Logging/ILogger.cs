using System;

namespace Epam.JDI.Core.Logging
{
    public interface ILogger
    {
        void Trace(String message);
        void Debug(String message);
        void Info(String message);
        void Error(String message);
        void Step(String message);
        void TestDescription(String message);
        void TestSuit(String message);
    }
}
