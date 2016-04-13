namespace Epam.JDI.Core.Settings
{
    public class HighlightSettings
    {
        private string _bgColor;
        public string BgColor => _bgColor;
        private string _frameColor;
        public string FrameColor => _frameColor;
        private int _timeoutInSec;
        public int TimeoutInSec => _timeoutInSec;
        
        public HighlightSettings(string bgColor = "yellow", string frameColor = "red", int timeoutInSec = 2)
        {
            _bgColor = bgColor;
            _frameColor = frameColor;
            _timeoutInSec = timeoutInSec;
        }
        public HighlightSettings SetBgColor(string bgColor)
        {
            _bgColor = bgColor;
            return this;
        }
        public HighlightSettings SetFrameColor(string frameColor)
        {
            _frameColor = frameColor;
            return this;
        }
        public HighlightSettings SetTimeoutInSec(int timeoutInSec)
        {
            _timeoutInSec = timeoutInSec;
            return this;
        }
    }
}
