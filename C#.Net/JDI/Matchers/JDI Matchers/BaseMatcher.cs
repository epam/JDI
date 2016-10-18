using System;
using System.Collections.Generic;
using System.Linq;
using Epam.JDI.Core.Logging;
using JDI_Commons;
using RestSharp.Extensions;

namespace JDI_Matchers
{
    public abstract class BaseMatcher
    {
        private static ILogger _logger;
        private static long _waitTimeout = 10;

        private string _chekMessage;
        private ScreenshotState _screenshot = ScreenshotState.Off;
        private bool _ignoreCase;

        protected abstract void ThrowFail(string message);

        protected BaseMatcher(string checkMessage) : this() // TODO: Fix it! (setting logger)
        {
            _chekMessage = getCheckMessage(checkMessage);
        }

        protected BaseMatcher()
        {
            // TODO: Fix it!
            _logger = new NUnitLogger();
        }

        private string getCheckMessage(string checkMessage)
        {
            if (string.IsNullOrEmpty(checkMessage))
            {
                return string.Empty;
            }
            var firstWord = checkMessage.Split(' ')[0];
            if (firstWord.Contains("check", StringComparison.OrdinalIgnoreCase) ||
                firstWord.Contains("verify", StringComparison.OrdinalIgnoreCase))
            {
                return checkMessage;
            }
            return "Check that " + checkMessage;
        }

        public BaseMatcher SetScreenshot(ScreenshotState screenshot)
        {
            _screenshot = screenshot;
            return this;
        }

        public void Contains(string actual, string expected)
        {
            Contains(actual, expected, false);
        }

        public void Contains(string actual, string expected, bool logOnlyFail)
        {
            Contains(actual, expected, logOnlyFail, null);
        }

        public void Contains(string actual, string expected, bool logOnlyFail, string failMessage)
        {
            bool result = _ignoreCase
                ? actual.Contains(expected, StringComparison.OrdinalIgnoreCase)
                : actual.Contains(expected);
            AssertAction($"Check that '{actual}' contains '{expected}'", result, logOnlyFail);
        }

        private void AssertAction(string message, bool result, bool logOnlyFail = false, string failMessage = null)
        {
            if (!logOnlyFail)
            {
                _logger.Info(GetBeforeMessage(message));
            }
            // TODO: Take screenshot
            //TakeScreenshot();
            if (!result)
            {
                //TakeScreenshot();
                AssertException(failMessage ?? message + " failed");
            }
        }

        private string GetBeforeMessage(string message)
        {
            return !string.IsNullOrEmpty(_chekMessage) ? _chekMessage : message;
        }

        private void AssertAction(string message, Func<bool> resultFunc)
        {
            _logger.Info(message);
            // TODO: Take screenshot
            //TakeScreenshot();
            bool result = new Timer(_waitTimeout * 1000).GetResultByCondition(resultFunc, r => r);
            if (!result)
            {
                //TakeScreenshot();
                AssertException(message + " failed");
            }
        }

        private void AssertException(string failMessage, params object[] args)
        {
            string failMsg = args.Length > 0 ? string.Format(failMessage, args) : failMessage;
            _logger.Error(failMsg);
            ThrowFail(failMsg);
        }

        public void Matches(string actual, string regEx, bool logOnlyFail = false, string failMessage = null)
        {
            bool result = _ignoreCase
                ? actual.ToLower().Matches(regEx.ToLower())
                : actual.Matches(regEx);
            AssertAction($"Check that '{actual}' matches to regular expression '{regEx}", result, logOnlyFail, failMessage);
        }

        public void Matches(Func<string> actualFunc, string regEx)
        {
            Func<bool> resultFunc;
            if (_ignoreCase)
            {
                resultFunc = () => actualFunc().ToLower().Matches(regEx.ToLower());
            }
            else
            {
                resultFunc = () => actualFunc().Matches(regEx);
            }
            AssertAction($"Check that action result matches to regular expression '{regEx}'", resultFunc);
        }

        public void IsTrue(bool condition)
        {
            AssertAction($"Check that condition '{condition}' is True", condition);
        }

        public void IsFalse(bool condition)
        {
            AssertAction($"Check that condition '{condition}' is False", !condition);
        }

        public BaseMatcher IgnoreCase()
        {
            _ignoreCase = true;
            return this;
        }

        public void AreEquals<T>(T actual, T expected, bool logOnlyFail = false)
        {
            bool result;
            if (typeof (T) == typeof (string))
            {
                result = _ignoreCase
                    ? actual.ToString().Equals(expected.ToString(), StringComparison.OrdinalIgnoreCase)
                    : actual.ToString().Equals(expected.ToString());
            }
            else
            {
                result = actual.Equals(expected);
            }
            AssertAction($"Check that '{actual}' equals to '{expected}'", result, logOnlyFail);
        }

        public void AreEquals<T>(Func<T> actualFunc, T expected)
        {
            Func<bool> resultFunc;
            if (typeof (T) == typeof (string))
            {
                if (_ignoreCase)
                {
                    resultFunc = () => actualFunc().ToString().Equals(expected.ToString(), StringComparison.OrdinalIgnoreCase);
                }
                else
                {
                    resultFunc = () => actualFunc().ToString().Equals(expected.ToString());
                }
            }
            else
            {
                resultFunc = () => actualFunc().Equals(expected);
            }
            AssertAction($"Check that action result equals to '{expected}'", resultFunc);
        }

        public void Contains(Func<string> actualFunc, string expected)
        {
            Func<bool> resultFunc;
            if (_ignoreCase)
            {
                resultFunc =
                    () => actualFunc().Contains(expected, StringComparison.OrdinalIgnoreCase);
            }
            else
            {
                resultFunc = () => actualFunc().Contains(expected);
            }
            AssertAction($"Check that action result contains '{expected}'", resultFunc);
        }

        public ListChecker<T> EachElementOf<T>(IEnumerable<T> collection)
        {
            return new ListChecker<T>(this, collection);
        }

        public void CollectionEquals<T>(IEnumerable<T> actual, IEnumerable<T> expected)
        {
            var first = actual as T[] ?? actual.ToArray();
            var second = expected as T[] ?? expected.ToArray();
            bool result = first.OrderBy(i => i).SequenceEqual(second.OrderBy(i => i));
            AssertAction($"Check that collection '{string.Join(", ", first)}' equals to '{string.Join(", ", second)}'", result);
        }
        public ListChecker<T> Each<T>(IEnumerable<T> collection)
        {
            return EachElementOf(collection);
        }

        public void IsSortedByAsc(IEnumerable<int> collection)
        {
            var listChecker = new ListChecker<int>(this, collection);
            listChecker.IsSortedByAsc();
        }

        public void IsSortedByDesc(IEnumerable<int> collection)
        {
            var listChecker = new ListChecker<int>(this, collection);
            listChecker.IsSortedByDesc();
        }

        public BaseMatcher SetTimeout(long timeout)
        {
            _waitTimeout = timeout;
            return this;
        }

        public void ThrowException(Action throwException, Type exceptionType, string exceptionMessage)
        {
            try
            {
                throwException();
            }
            catch (Exception e)
            {
                if (exceptionType != null)
                {
                    AreEquals(e.GetType(), exceptionType);
                }
                if (exceptionMessage != null)
                {
                    AreEquals(e.Message, exceptionMessage);
                }
                return;
            }
            throw new Exception($"Action 'action name' throws no exceptions. Expected ''");
        }

        public void HasNoException(Action action)
        {
            try
            {
                action();
            }
            catch (Exception e)
            {
                AssertException($"Action throws exception: {e.GetType()} - {e.Message}");
            }
        }

        public class ListChecker<T>
        {
            private IEnumerable<T> _list;
            private BaseMatcher _matcher;

            internal ListChecker(BaseMatcher matcher, IEnumerable<T> list)
            {
                _matcher = matcher;
                if (list == null || !list.Any())
                {
                    _matcher.ThrowFail("The list is empty");
                }
                _list = list;
            }

            public void Contains(string expected)
            {
                _logger.Info($"Check that each item of list '{string.Join(", ", _list)}' contains '{expected}'");
                _list.ForEach(i => _matcher.Contains(i.ToString(), expected, true));
            }

            public void Matches(string regEx)
            {
                _logger.Info($"Check that each item of list '{string.Join(", ", _list)}' matches to regEx '{regEx}'");
                _list.ForEach(i => _matcher.Matches(i.ToString(), regEx, true));
            }

            public void AreDifferent()
            {
                bool result = _list.Distinct().Count() == _list.Count();
                _matcher.AssertAction($"Check that all items of list '{string.Join(", ", _list)}' are different", result);
            }

            public void AreSame()
            {
                _logger.Info($"Check that all items of list '{string.Join(", ", _list)}' are the same");
                var first = _list.First();
                _list.Skip(1).ForEach(e => _matcher.AreEquals(e, first, true));
            }

            public void IsSortedByDesc()
            {
                var descSortedList = _list.OrderByDescending(e => e);
                bool result = descSortedList.SequenceEqual(_list);
                _matcher.AssertAction($"Check that collection '{string.Join(", ", _list)}' ordered by descending", result);
            }

            public void IsSortedByAsc()
            {
                var ascSortedList = _list.OrderBy(e => e);
                bool result = ascSortedList.SequenceEqual(_list);
                _matcher.AssertAction($"Check that collection '{string.Join(", ", _list)}' ordered by ascending", result);
            }
        }
    }
}
