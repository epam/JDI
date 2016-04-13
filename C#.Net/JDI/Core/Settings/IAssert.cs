using System;

namespace Epam.JDI.Core.Settings
{
    public interface IAssert
    {
        Exception Exception(string message);
        void IsTrue(bool actual);

    }
}
