namespace JDI_Core.Logging
{
    public interface ILogger
    {
        void Trace(string message);
        void Debug(string message);
        void Info(string message);
        void Error(string message);
        void Step(string message);
        void TestDescription(string message);
        void TestSuit(string message);
    }
}
