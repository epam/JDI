
#define USE_DUMMIES

using OpenQA.Selenium;

namespace Epam.JDI.Tests.Aliases
{

#if USE_DUMMIES
    public class Application : Epam.Tests.TutorialTests.Dummies.JDI.Application { }
#else
    public class Application : Application { }
#endif

#if USE_DUMMIES
    public abstract class BaseMatcher : Epam.Tests.TutorialTests.Dummies.JDI.BaseMatcher { }
#else
    public class BaseMatcher : BaseMatcher { }
#endif

#if USE_DUMMIES
    public class Check : Epam.Tests.TutorialTests.Dummies.JDI.Check
    {
        public Check(string checkMessage) : base(checkMessage) { }
    }
#else
    public class Check : Check { }
#endif

#if USE_DUMMIES
    public class Dropdown : Epam.Tests.TutorialTests.Dummies.JDI.Dropdown
    {
        public Dropdown(By by1, By by2) : base(by1, by2) { }
    }
#else
    public class Dropdown : Dropdown { }
#endif

#if USE_DUMMIES
    public class HeaderSolutionsMenu : Epam.Tests.TutorialTests.Dummies.JDI.HeaderSolutionsMenu { }
#else
    public class HeaderSolutionsMenu : HeaderSolutionsMenu { }
#endif

#if USE_DUMMIES
    internal interface IAsserter : Epam.Tests.TutorialTests.Dummies.JDI.IAsserter { }
#else
    internal interface IAsserter : IAsserter { }
#endif

#if USE_DUMMIES
    public interface IElement : Epam.Tests.TutorialTests.Dummies.JDI.IElement { }
#else
    public interface IElement : IElement { }
#endif

#if USE_DUMMIES
    public interface IPreconditions : Epam.Tests.TutorialTests.Dummies.JDI.IPreconditions { }
#else
    public interface IPreconditions : IPreconditions { }
#endif

#if USE_DUMMIES
    public interface ITable : Epam.Tests.TutorialTests.Dummies.JDI.ITable { }
#else
    public interface ITable : ITable { }
#endif

#if USE_DUMMIES
    internal class JDIData : Epam.Tests.TutorialTests.Dummies.JDI.JDIData { }
#else
    internal class JDIData : JDIData { }
#endif

#if USE_DUMMIES
    abstract class JDISettings : Epam.Tests.TutorialTests.Dummies.JDI.JDISettings { }
#else
    abstract class JDISettings : JDISettings { }
#endif

#if USE_DUMMIES
    public class LogerFactory : Epam.Tests.TutorialTests.Dummies.JDI.LogerFactory { }
#else
    public class LogerFactory : LogerFactory { }
#endif

#if USE_DUMMIES
    internal interface Logger : Epam.Tests.TutorialTests.Dummies.JDI.Logger { }
#else
    internal interface Logger : LogerFactory { }
#endif

#if USE_DUMMIES
    public class Menu<TEnum> : Epam.Tests.TutorialTests.Dummies.JDI.Menu<TEnum> { }
#else
    public class Menu : Menu { }
#endif

#if USE_DUMMIES
    public class PreconditionsState : Epam.Tests.TutorialTests.Dummies.JDI.PreconditionsState { }
#else
    public class PreconditionsState : PreconditionsState { }
#endif

#if USE_DUMMIES
    public class RFileInput : Epam.Tests.TutorialTests.Dummies.JDI.RFileInput { }
#else
    public class RFileInput : RFileInput { }
#endif

#if USE_DUMMIES
    public interface WebPreconditions : Epam.Tests.TutorialTests.Dummies.JDI.WebPreconditions { }
#else
    public interface WebPreconditions : WebPreconditions { }
#endif

}
