using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Epam.JDI.Core.Base
{
    public class Application
    {
        public static Type CurrentSite;

        private string _driverName;
        public string DriverName {
            set { _driverName = value; }
        }

    }
}
