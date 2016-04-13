using System;

namespace Epam.Tests.TutorialTests.Dummies.JDI
{
    internal interface IAsserter
    {
        void isTrue(Func<bool> actual);
        SystemException exception(string message, params object[] args);
    }
}