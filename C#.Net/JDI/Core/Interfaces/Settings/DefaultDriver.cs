using System;
using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Core.Interfaces.Settings;
using Epam.JDI.Core.Settings;

namespace JDI_Core.Interfaces.Settings
{
    public class DefaultDriver : IDriver<object>
    {
        public string RegisterDriver(string driverName)
        {
            throw new NotImplementedException();
        }



        public void SetRunType(string runType)
        {
            throw new NotImplementedException();
        }

        public void SetDriverPath(string driverPath)
        {
            throw new NotImplementedException();
        }

        public void SetCurrentDriver(string driverName)
        {
            throw new NotImplementedException();
        }

        public string GetDriverPath()
        {
            throw new NotImplementedException();
        }

        public object GetDriver()
        {
            throw new NotImplementedException();
        }

        public bool HasDrivers()
        {
            throw new NotImplementedException();
        }

        public bool HasRunDrivers()
        {
            throw new NotImplementedException();
        }

        public object GetDriver(string name)
        {
            throw new NotImplementedException();
        }

        public void Highlight(IElement element)
        {
            throw new NotImplementedException();
        }

        public void Highlight(IElement element, HighlightSettings highlightSettings)
        {
            throw new NotImplementedException();
        }

        public string CurrentDriverName { get; set; } = "NO DRIVER";
        public string DriverPath { get; set; } = "DEFAULT DRIVER";
    }
}
