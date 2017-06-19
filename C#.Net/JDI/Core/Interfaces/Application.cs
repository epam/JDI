using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using RestSharp;

using Epam.JDI.Core.Settings;
using JDI_Core.Preconditions;

namespace JDI_Core.Interfaces
{
    public class Application
    {
        private string driverName;

        public void SetDriverName(string driverName)
        {
            this.driverName = driverName;
        }

        public void IsInState(IPreconditions precondition)
        {
            JDISettings.DriverFactory.CurrentDriverName = driverName;
            PreconditionsState.IsInState(precondition);
        }
        public void IsInState(IPreconditions precondition, NUnit.Framework.DescriptionAttribute method)
        {
            JDISettings.DriverFactory.CurrentDriverName = driverName;
            PreconditionsState.IsInState(precondition, method);
        }
    }
}
