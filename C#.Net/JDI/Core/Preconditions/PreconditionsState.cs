using System;
using RestSharp;

using Epam.JDI.Core.Logging;
using Epam.JDI.Core.Settings;

namespace JDI_Core.Preconditions
{
    public class PreconditionsState
    {
        public static bool alwaysMoveToCondition;
        private PreconditionsState() { }

        public static void IsInState(IPreconditions condition, NUnit.Framework.DescriptionAttribute method)
        {
            try
            {
                new Log4Net().Info("Move to condition: " + condition);
                if (method != null) JDIData.testName = method.GetType().FullName;
                if (!alwaysMoveToCondition && condition.CheckAction())
                    return;
                condition.MoveToAction();
                NUnit.Framework.Assert.IsTrue(condition.CheckAction());
                new Log4Net().Info(condition + " condition achieved");
            }
            catch (Exception ex)
            {
                throw new ArgumentException(String.Format("Can't reach state: %s. Reason: %s", condition, ex.Message));
            }
        }

        public static void IsInState(IPreconditions condition)
        {
            IsInState(condition, null);
        }

        public static void MoveToState(IPreconditions condition, NUnit.Framework.DescriptionAttribute method)
        {
            try
            {
                bool temp = alwaysMoveToCondition;
                alwaysMoveToCondition = true;
                IsInState(condition, method);
                alwaysMoveToCondition = temp;
            }
            catch (Exception ex)
            {
                throw new ArgumentException(String.Format("Can't reach state: %s. Reason: %s", condition, ex.Message));
            }
        }

        public static void MoveToState(IPreconditions condition)
        {
            MoveToState(condition, null);
        }
    }
}
