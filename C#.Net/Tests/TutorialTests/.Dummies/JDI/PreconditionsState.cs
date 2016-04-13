using System;
using System.Reflection;
using System.Threading;

namespace Epam.Tests.TutorialTests.Dummies.JDI
{
    public class PreconditionsState
    {
        public static bool alwaysMoveToCondition;

        public static void isInState(IPreconditions condition, MethodBase method)
        {
            try
            {
                System.Diagnostics.Debug.WriteLine(string.Format("=== Start precondition. Thread id : %s", Thread.CurrentThread.ManagedThreadId));
                JDISettings.logger.info("Move to condition: " + condition);
                if (method != null) JDIData.testName = method.Name;
                if (!alwaysMoveToCondition && condition.checkAction())
                    return;
                condition.moveToAction();
                System.Diagnostics.Debug.WriteLine(string.Format("=== Move to done precondition. Thread id : %s", Thread.CurrentThread.ManagedThreadId));
                JDISettings.asserter.isTrue(condition.checkAction);
                JDISettings.logger.info(condition + " condition achieved");
            }
            catch (Exception ex)
            {
                throw JDISettings.asserter.exception(string.Format("Can't reach state: %s. Reason: %s", condition, ex.Message));
            }
        }

    }
}
