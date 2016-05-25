namespace Epam.JDI.Core.Settings
{
    public class TimeoutSettings
    {
        public int CurrentTimeoutSec;

        public int WaitElementSec = 20;
        public int WaitPageLoadSec = 20;
        public int RetryMSec = 100;

        public TimeoutSettings()
        {
            SetCurrentTimeoutSec(WaitPageLoadSec);
        }

        public void SetCurrentTimeoutSec(int timeoutSec)
        {
            CurrentTimeoutSec = timeoutSec;
        }

        public void DropTimeouts()
        {
            SetCurrentTimeoutSec(WaitPageLoadSec);
        }
    }
}
