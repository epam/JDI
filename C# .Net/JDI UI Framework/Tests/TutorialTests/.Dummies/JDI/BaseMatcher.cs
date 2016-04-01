
using System;

namespace Epam.Tests.TutorialTests.Dummies.JDI
{
    public abstract class BaseMatcher
    {
        private static Logger logger = LogerFactory.getLogger("JDI Logger");
        private string checkMessage;
        private static string FOUND = "FOUND";
        private bool isListCheck;
        private DoScreen doScreenshot = DoScreen.NO_SCREEN;
        private long waitTimeout;
        private bool ignoreCase;

        public BaseMatcher()
        {
        }

        public BaseMatcher(string checkMessage)
        {
            this.checkMessage = checkMessage;
        }

        private string getCheckMessage(string checkMessage)
        {
            if (checkMessage == null || checkMessage.Equals(""))
                return "";
            string firstWord = checkMessage.Split(' ')[0];
            return (!firstWord.Equals("check", StringComparison.InvariantCultureIgnoreCase)) || firstWord.Equals("verify", StringComparison.InvariantCultureIgnoreCase)
                    ? "Check " + checkMessage
                    : checkMessage;
        }

        public void isFalse(Func<bool> condition, string failMessage)
        {
            waitAction(string.Format("Check that condition '%s' is False", "result"), () => !condition.Invoke(), failMessage);
        }

        public void isFalse(Func<bool> condition)
        {
            isFalse(condition, null);
        }

        private void waitAction(string defaultMessage, Func<bool> result, string failMessage)
        {
            assertAction(defaultMessage, () => result.Invoke() ? FOUND : "Check failed", failMessage, true);
        }

        protected string doScreenshotGetMessage()
        {
            return "Screenshots switched off";
        }

        private void assertAction(string defaultMessage, Func<string> result, string failMessage, bool wait)
        {
            if (!isListCheck && defaultMessage != null)
                logger.info(getBeforeMessage(defaultMessage));
            if (!isListCheck && doScreenshot == DoScreen.DO_SCREEN_ALWAYS)
                logger.info(doScreenshotGetMessage());
            // TODO
            string resultMessage = "";
            //string resultMessage = (wait)
            //        ? new Timer(timeout()).getResultByCondition(result.Invoke(), r=>r != null && r.equals(FOUND))
            //        : result.Invoke();
            if (resultMessage == null)
            {
                assertException("Assert Failed by Timeout. Wait %s seconds", timeout() / 1000);
                return;
            }
            if (!resultMessage.Equals(FOUND))
            {
                if (doScreenshot == DoScreen.SCREEN_ON_FAIL)
                    logger.info(doScreenshotGetMessage());
                assertException(failMessage == null ? defaultMessage + " failed" : failMessage);
            }
        }

        protected abstract void throwFail(string msg);

        private void assertException(string failMessage, params object[] args)
        {
            string failMsg = args.Length > 0 ? string.Format(failMessage, args) : failMessage;
            if (doScreenshot == DoScreen.SCREEN_ON_FAIL)
                logger.info(doScreenshotGetMessage());
            logger.error(failMsg);
            //throwFail().Invoke(failMsg);
            throwFail(failMsg);
        }

        private string getBeforeMessage(string defaultMessage)
        {
            return (checkMessage != null && !checkMessage.Equals(""))
                    ? checkMessage
                    : defaultMessage;
        }

        private long timeout() { return waitTimeout; }

        public void contains(Func<string> actual, string expected)
        {
            contains(actual, expected, null);
        }


        public void contains(Func<string> actual, string expected, string failMessage)
        {
            //Func<bool> resultAction = (ignoreCase && Equals(expected.GetType(), typeof(string))
            //    ? () => toUtf8(actual.Invoke()).contains(expected)
            //    : () => toUtf8(actual.Invoke()).toLowerCase().c
            Func<bool> resultAction = () => actual.Invoke().Contains(expected);
            waitAction(string.Format("Check that '%s' contains '%s'", "result", expected), resultAction, failMessage);
        }


    }
}
