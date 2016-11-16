using JDI_Web.Selenium.Elements.Complex.Table.Interfaces;
using NUnit.Framework;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;

namespace JDI_UIWebTests.Tests.Complex.Table.Base
{
    public class SupportTableTestBase // TODO: fix TestBase (consider InitTests from Java)
    {
        protected ITable Table => SupportPage.SupportTable;

        [SetUp]
        public void Setup()
        {
            // TODO: Preconditions
            Logger.Info("Navigating to Support page");
            SupportPage.Open();
            SupportPage.CheckTitle();
            SupportPage.IsOpened();
            Logger.Info("Setup method finished");
            Logger.Info($"Start test: {TestContext.CurrentContext.Test.Name}");
            // End preconditions

            Table.Clear();
        }
    }
}
