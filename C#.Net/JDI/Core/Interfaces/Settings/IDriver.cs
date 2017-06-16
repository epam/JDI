using Epam.JDI.Core.Interfaces.Base;
using Epam.JDI.Core.Settings;

namespace Epam.JDI.Core.Interfaces.Settings
{
    public interface IDriver<out T>
    {
        string RegisterDriver(string driverName);

        void SetRunType(string runType);

        T GetDriver();

        bool HasDrivers();

        bool HasRunDrivers();

        string CurrentDriverName();
        void SetCurrentDriver(string driverName);

        T GetDriver(string name);

        void Highlight(IElement element);

        void Highlight(IElement element, HighlightSettings highlightSettings);

        string GetDriverPath();

        void SetDriverPath(string driverPath);
    }
}
