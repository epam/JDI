

using Microsoft.VisualStudio.TestTools.UnitTesting;

namespace Epam.Tests.TutorialTests.Dummies.JDI
{
    public class Check : BaseMatcher
    {
        public Check() : base()
        {
        }

        public Check(string checkMessage) : base (checkMessage)
        {
        }

        protected override void throwFail(string msg)
        {
            Assert.Fail(msg);
        }
    }
}
