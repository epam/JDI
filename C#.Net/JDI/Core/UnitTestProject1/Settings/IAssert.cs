using System;

namespace JDI_Core.Settings
{
    public interface IAssert
    {
        Exception Exception(string message);
        void IsTrue(bool actual);

    }
}
