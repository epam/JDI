using System.Collections.Generic;
using NUnit.Framework;
using static Epam.JDI.Core.Settings.JDISettings;
using static JDI_UIWebTests.UIObjects.TestSite;

namespace JDI_UIWebTests.Tests.Complex
{
    // TODO: Selector tests are not compleated
    [Ignore("Exception in CascadeInit due to oddNumberSelectors")]
    public class SelectorTests
    {
        private static readonly IList<string> OddOptions = new List<string> { "1", "3", "5", "7" };
        //private ISelector<Odds> OddNumbersSelector => MetalsColorsPage.SummaryBlock.OddNumbersSelector;

        [SetUp]
        public void SetUp()
        {
            Logger.Info("Navigating to Metals and Colors page.");
            MetalsColorsPage.Open();
            MetalsColorsPage.CheckTitle();
            MetalsColorsPage.IsOpened();
            Logger.Info("Setup method finished");
            Logger.Info("Start test: " + TestContext.CurrentContext.Test.Name);
        }

        [Test]
        public void SelectStringTest()
        {
            //OddNumbersSelector.Select("7");
            //CheckAction("Summary (Odd): value changed to 7");
        }
    }
}
