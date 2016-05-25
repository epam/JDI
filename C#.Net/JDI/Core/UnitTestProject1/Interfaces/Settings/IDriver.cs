using JDI_Core.Interfaces.Base;
using JDI_Core.Settings;

namespace JDI_Core.Interfaces.Settings
{
    public interface IDriver<out T>
    {
        string RegisterDriver(string driverName);

        void SetRunType(string runType);

        T GetDriver();

        bool HasDrivers();

        bool HasRunDrivers();
        
        T GetDriver(string name);

        void Highlight(IElement element);

        void Highlight(IElement element, HighlightSettings highlightSettings);

        string CurrentDriverName { get; set; }
        string DriverPath { get; set; }
    }
}
