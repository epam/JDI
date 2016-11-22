using System;
using System.Collections.Generic;

namespace JDI_Matchers.NUnit
{
    public class Assert
    {
        private static ScreenshotState screen = ScreenshotState.OnFail;
        private static BaseMatcher matcher = new Check().SetScreenshot(screen);

        public static void Contains(string actual, string expected)
        {
            matcher.Contains(actual, expected);
        }

        public static void Matches(string actual, string regEx)
        {
            matcher.Matches(actual, regEx);
        }

        public static void IsTrue(bool condition)
        {
            matcher.IsTrue(condition);
        }

        public static void IsFalse(bool condition)
        {
            matcher.IsFalse(condition);
        }

        public static BaseMatcher IgnoreCase()
        {
            return matcher.IgnoreCase();
        }
        
        public static BaseMatcher.ListChecker<T> Each<T>(IEnumerable<T> collection)
        {
            // TODO: Need to use interface instead of ListChecker<T> ???
            return matcher.EachElementOf(collection);
        }

        public static void CollectionEquals<T>(IEnumerable<T> actual, IEnumerable<T> expected)
        {
            matcher.CollectionEquals(actual, expected);
        }

        public static void IsSortedByAsc(IEnumerable<int> collection)
        {
            matcher.IsSortedByAsc(collection);
        }

        public static void IsSortedByDesc(IEnumerable<int> collection)
        {
            matcher.IsSortedByDesc(collection);
        }

        public static void AreEquals<T>(Func<T> actualFunc, T expected)
        {
            matcher.AreEquals(actualFunc, expected);
        }

        public static void AreEquals<T>(T actual, T expected)
        {
            matcher.AreEquals(actual, expected);
        }

        public static void Contains(Func<string> actualFunc, string expected)
        {
            matcher.Contains(actualFunc, expected);
        }

        public static BaseMatcher WaitTimeout(long timeout)
        {
            return matcher.SetTimeout(timeout);
        }

        public static void ThrowException(Action throwException, string exceptionMessage)
        {
            matcher.ThrowException(throwException, null, exceptionMessage);
        }

        public static void ThrowException(Action throwException, Type exceptionType, string exceptionMessage = null)
        {
            matcher.ThrowException(throwException, exceptionType, exceptionMessage);
        }

        public static void HasNoException(Action action)
        {
            matcher.HasNoException(action);
        }
    }
}
