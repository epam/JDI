using System;
using System.Collections.Generic;
using NUnit.Framework;

namespace Epam.Tests
{
    /// <summary>
    /// Summary description for UnitTest1
    /// </summary>
    [TestFixture]
    public class UnitTest1
    {
        public UnitTest1()
        {
            //
            // TODO: Add constructor logic here
            //
        }
        
        private TestContext testContextInstance;
        public TestContext TestContext
        {
            get
            {
                return testContextInstance;
            }
            set
            {
                testContextInstance = value;
            }
        }

        #region Additional test attributes
        //
        // You can use the following additional attributes as you write your tests:
        //
        // Use ClassInitialize to run code before running the first test in the class
        // [ClassInitialize()]
        // public static void MyClassInitialize(TestContext testContext) { }
        //
        // Use ClassCleanup to run code after all tests in a class have run
        // [ClassCleanup()]
        // public static void MyClassCleanup() { }
        //
        // Use TestInitialize to run code before running each test 
        // [TestInitialize()]
        // public void MyTestInitialize() { }
        //
        // Use TestCleanup to run code after each test has run
        // [TestCleanup()]
        // public void MyTestCleanup() { }
        //
        #endregion

        [Test]
        public void TestMethod1()
        {
            var l = new List<int> {1, 2 ,3};
            var a = l[-1];
            var b = l[0];
            example1(null);
            example1("5");
            example2(null);
            example2(5);
            example2(new List<String> { "test", "data", "example" });
            var list = new List<String> {"test", "data", "example"};
            String third = list[3];
        }



        private Boolean example1(String value)
        {
            String value2 = value != null ? value : "5";
            String value3 = value ?? "5";
            return value2 == value3;
        }
        private Boolean example2(Object value)
        {
            var value2 = value != null ? value.ToString() : null;
            var value3 = value?.ToString();
            return value2 == value3;
        }


    }
}
