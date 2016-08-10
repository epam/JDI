using System;
using System.Diagnostics;
using PostSharp.Aspects;

namespace JDI_Tests.TestBase
{
    [Serializable]
    public class PostSharpInterceptor : OnMethodBoundaryAspect
    {
        public override void OnEntry(MethodExecutionArgs args)
        {
            Trace.WriteLine("POSTSHARP Start");
        }
        
        public override void OnExit(MethodExecutionArgs args)
        {
            Trace.WriteLine("POSTSHARP Finish");
        }

    }
}
