using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Threading;
using Epam.JDI.Core;
using OpenQA.Selenium;

namespace Epam.JDI.Commons
{
    public class Timer
    {
        private const double DefaultTimeout = 10000;
        private const int DefaultRetryTimeout = 100;

        private readonly Stopwatch _watch;
        private readonly double _timeoutInMSec;
        private readonly int _retryTimeoutInMSec = DefaultRetryTimeout;

        public Timer()
        {
            _timeoutInMSec = DefaultTimeout;
            _watch = Stopwatch.StartNew();
        }

        public Timer(double timoutInMsec = DefaultTimeout, int retryTimeoutInMSec = DefaultRetryTimeout) : this()
        { 
            _timeoutInMSec = timoutInMsec;
            _retryTimeoutInMSec = retryTimeoutInMSec;
        }
        
        public TimeSpan TimePassed => _watch.Elapsed;
        public bool TimeoutPassed => _watch.Elapsed > TimeSpan.FromMilliseconds(_timeoutInMSec);

        public bool Wait(Func<bool> waitFunc)
        {
            while (!TimeoutPassed)
            {
                if (waitFunc.AvoidExceptions())
                    return true;
                Thread.Sleep(_retryTimeoutInMSec);
            }
            return false;
        }

        public T GetResult<T>(Func<T> func)
        {
            return GetResultByCondition(func, result => true);
        }
        public T GetResultByCondition<T>(Func<T> getFunc, Func<T, bool> conditionFunc)
        {
            Exception exception = null;
            do
            {
                try 
                {
                    var result = getFunc.Invoke();
                    if (result != null && conditionFunc.Invoke(result))
                        return result;
                } catch (Exception ex) { exception = ex; }
                Thread.Sleep(_retryTimeoutInMSec);
            } while (!TimeoutPassed);
                if (exception != null)
                    throw new Exception(exception.Message);
                return default(T);
        }
    }
}
