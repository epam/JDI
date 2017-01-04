using System;
using NUnit.Framework;
using Epam.JDI.Core.Settings;

namespace JDI_Matchers
{
    public class NUnitMatcher : BaseMatcher, IAssert
    {
        public Exception Exception(string message)
        {
            Assert.Fail(message);
            return new Exception(message);
        }

        protected override void ThrowFail(string message)
        {
            Assert.Fail(message);
        }
    }
}
