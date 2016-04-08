using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using Epam.JDI.Core.Logging;
using Epam.JDI.Core.Reporting;
using Epam.JDI.Core.Settings;
using PostSharp.Aspects;
using Timer = Epam.JDI.Commons.Timer;

namespace Epam.JDI.Core.Attributes
{
    [Serializable]
    [Scenario(AttributeExclude = true)]
    public class Scenario : OnMethodBoundaryAspect
    {
        private static readonly ILogger Logger = new LoggerNet();

        private MethodBase _method;
        private object _instance;

        [NonSerialized]
        private Stopwatch _stopwatch;

        public LogLevels LogLevel { get; set; }

        public override void OnEntry(MethodExecutionArgs args)
        {
            Logger.ToLog($"Start {args.Method.Name}", LogLevel);

            _stopwatch = Stopwatch.StartNew();

            if (args.Arguments != null)
            {
                foreach (var arg in args.Arguments)
                    Logger.ToLog($"Method arguments: {arg}", LogLevel);
            }

            if (args.Instance != null)
                _instance = args.Instance;

            if (args.Method != null)
                _method = args.Method;
        }

        public override void OnException(MethodExecutionArgs args)
        {
            if (args.Exception != null)
                Logger.Error(args.Exception.ToString());

            var executedTime = _stopwatch.ElapsedMilliseconds * 0.001;

            if (executedTime < JDISettings.Timeouts.CurrentTimeoutSec)
            {
                if (_instance is WebBaseElement)
                {
                    try
                    {
                        _stopwatch.Start();
                        _method.Invoke(_instance, args.Arguments.ToArray());
                    }
                    catch (Exception ex)
                    {
                        Logger.Error(ex.ToString());
                    }
                }
            }
        }

        public override void OnExit(MethodExecutionArgs args)
        {
            _stopwatch.Stop();

            var executedTime = $"{args.Method.Name} Executed in {_stopwatch.ElapsedMilliseconds / 1000} seconds";

            PerformanceStatistic.AddStatistic(_stopwatch.ElapsedMilliseconds);

            Logger.ToLog(executedTime, LogLevel);

            if (args.ReturnValue != null)
                Logger.ToLog($"Return value = {args.ReturnValue}", LogLevel);

            if (args.ReturnValue != null)
                Logger.ToLog("result  " + args.ReturnValue, LogLevel);

            Logger.ToLog($"End {args.Method.Name}", LogLevel);
        }

        public override void OnSuccess(MethodExecutionArgs args)
        {
            _stopwatch.Stop();
            Logger.ToLog(args.Method.Name + " done", LogLevel);
        }
    }
}
