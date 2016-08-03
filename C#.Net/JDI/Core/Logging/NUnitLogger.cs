using System;
using static NUnit.Framework.TestContext;

namespace Epam.JDI.Core.Logging
{
    public class NUnitLogger : ILogger
    {
        private readonly object _locker = new object();

        public void Trace(string message)
        {
            WriteLine($"[Trace] {message}");
            System.Diagnostics.Debug.WriteLine("");
        }

        public void Debug(string message)
        {
            WriteLine($"[Debug] {message}");
        }

        public void Info(string message)
        {
            WriteLine($"[Info] {message}");
        }

        public void Error(string message)
        {
            WriteLine($"[Error] {message}");
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
