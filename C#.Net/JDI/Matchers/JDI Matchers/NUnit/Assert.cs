using System;
using System.Collections.Generic;

namespace JDI_Matchers.NUnit
{
    public class Assert
    {
        private static ScreenshotState _screen = ScreenshotState.OnFail;
        private static BaseMatcher _matcher = new Check().SetScreenshot(_screen);

        public static void Contains(string actual, string expected)
        {
            _matcher.Contains(actual, expected);
        }

        public static void Matches(string actual, string regEx)
        {
            _matcher.Matches(actual, regEx);
        }

        public static void IsTrue(bool condition)
        {
            _matcher.IsTrue(condition);
        }

        public static void IsFalse(bool condition)
        {
            _matcher.IsFalse(condition);
        }

        public static BaseMatcher IgnoreCase()
        {
            return _matcher.IgnoreCase();
        }
        
        public static BaseMatcher.ListChecker<T> Each<T>(IEnumerable<T> collection)
        {
            // TODO: Need to use interface instead of ListChecker<T> ???
            return _matcher.EachElementOf(collection);
        }

        public static void CollectionEquals<T>(IEnumerable<T> actual, IEnumerable<T> expected)
        {
            _matcher.CollectionEquals(actual, expected);
        }

        public static void IsSortedByAsc(IEnumerable<int> collection)
        {
            _matcher.IsSortedByAsc(collection);
        }

        public static void IsSortedByDesc(IEnumerable<int> collection)
        {
            _matcher.IsSortedByDesc(collection);
        }

        public static void AreEquals<T>(Func<T> actualFunc, T expected)
        {
            _matcher.AreEquals(actualFunc, expected);
        }

        public static void AreEquals<T>(T actual, T expected)
        {
            _matcher.AreEquals(actual, expected);
        }

        public static void Contains(Func<string> actualFunc, string expected)
        {
            _matcher.Contains(actualFunc, expected);
        }

        public static BaseMatcher WaitTimeout(long timeout)
        {
            return _matcher.SetTimeout(timeout);
        }

        public static void ThrowException(Action throwException, string exceptionMessage)
        {
            _matcher.ThrowException(throwException, null, exceptionMessage);
        }

        public static void ThrowException(Action throwException, Type exceptionType, string exceptionMessage = null)
        {
            _matcher.ThrowException(throwException, exceptionType, exceptionMessage);
        }

        public static void HasNoException(Action action)
        {
            _matcher.HasNoException(action);
        }
    }
}
