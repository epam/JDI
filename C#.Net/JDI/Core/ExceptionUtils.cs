using System;
using Epam.JDI.Core.Settings;

namespace Epam.JDI.Core
{
    public static class ExceptionUtils
    {
        public static void ActionWithException(Action action, Func<string, string> exception)
        {
            try
            {
                action.Invoke();
            }
            catch (Exception ex)
            {
                throw JDISettings.Exception(exception.Invoke(ex.Message));
            }
        }
        public static T ActionWithException<T>(Func<T> func, Func<string, string> exception)
        {
            try
            {
                return func.Invoke();
            }
            catch (Exception ex)
            {
                throw JDISettings.Exception(exception.Invoke(ex.Message));
            }
        }

        public static T AvoidExceptions<T>(this Func<T> waitFunc)
        {
            try { return waitFunc(); }
            catch { return default(T); }
        }

        public static void AvoidExceptions(this Action action)
        {
            try { action(); }
            catch { /* ignored */ }
        }
    }
}
