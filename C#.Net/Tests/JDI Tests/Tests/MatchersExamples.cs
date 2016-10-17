using System;
using System.Collections.Generic;
using NUnit.Framework;
using Assert = JDI_Matchers.NUnit.Assert;

namespace JDI_Tests.Tests
{
    [TestFixture]
    public class MatchersExamples
    {
        [Test]
        public void CommonAssertsExample()
        {
            Assert.Contains("Test Text", "Text");
            Assert.Matches("1352-423-85746", "\\d{4}-\\d{3}-\\d{5}");
            Assert.IsTrue(1 == 1);
            Assert.IsFalse("test".Contains("a"));
            Assert.IgnoreCase().AreEquals("Test String", "test STRING");
        }

        [Test]
        public void ListAssertsExample()
        {
            string[] searchResults =
            {
                "IPhone 4", "IPhone 5S", "IPhone 6"
            };
            Assert.Each(searchResults).Contains("IPhone");
            Assert.Each(searchResults).Matches("IPhone \\d.*");
            Assert.CollectionEquals(searchResults, new[] {"IPhone 4", "IPhone 6", "IPhone 5S"});
            Assert.CollectionEquals(searchResults, new List<string> {"IPhone 4", "IPhone 6", "IPhone 5S"});
            // TODO: Add case insensetive version of them
            // TODO: Add failMessage parameter for each method
            Assert.Each(new[] {"IPhone 4", "IPhone 6", "IPhone 5S"}).AreDifferent();

            List<string> sameList = new List<string> {"test", "test", "tesT"};
            Assert.IgnoreCase().Each(sameList).AreSame();

            List<int> sortedListAsc = new List<int> {1, 2, 3};
            Assert.IsSortedByAsc(sortedListAsc);

            int[] sortedArrayDesc = {8, 7, 6};
            Assert.IsSortedByDesc(sortedArrayDesc);
        }

        [Test]
        public void WaitAssertExample()
        {
            string[] searchResults = {"IPhone 4", "IPhone 5S", "IPhone 6", "IPhone 7"};
            int i = 0;
            Func<string> getNextFunc = () =>
            {
                if (i == searchResults.Length) i = 0;
                return searchResults[i++];
            };

            Assert.AreEquals(() => getNextFunc(), "IPhone 7");
            Assert.Contains(() => getNextFunc(), "IPhone 6");
            Assert.WaitTimeout(5).IgnoreCase().Matches(() => getNextFunc(), ".*s");
        }

        [Test]
        public void ExceptionAssertExample()
        {
            Assert.ThrowException(() => { throw new Exception("My exception"); }, "My exception");
            Assert.ThrowException(() => { throw new ArgumentException("Test exception"); }, typeof (ArgumentException), "Test exception");
            Assert.HasNoException(() =>
            {
                int i = 10;
                i++;
            });
        }
    }
}
