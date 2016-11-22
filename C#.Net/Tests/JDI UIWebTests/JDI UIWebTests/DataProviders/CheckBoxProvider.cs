using System.Collections;
using System.Collections.Generic;
using NUnit.Framework;

namespace JDI_UIWebTests.DataProviders
{
    class CheckBoxProvider
    {
        public static IEnumerable InputData
        {
            get
            {
                yield return new TestCaseData("true", true);
                yield return new TestCaseData("1", true);
                yield return new TestCaseData("false", false);
                yield return new TestCaseData("0", false);
            }
        }

        public static IEnumerable<string> InputValue
        {
            get
            {
                yield return "true ";
                yield return "1 ";
                yield return " false";
                yield return "0 ";
                yield return " ";
                yield return "123";
                yield return " 1";
                yield return " 0";
                yield return "!@#$%^&*";
                yield return "qwdewf";
                yield return "1qwe";
                yield return "true123";
                yield return "123true";
                yield return "false123";
                yield return "123false";
                yield return "o";
                yield return "O";
                yield return "tr ue";
            }
        }
    }
}
