namespace Epam.JDI.Web.Utils
{
    public class ScreenshotAlert// : IAlert
    {
        // TODO Old code
        /*
        private readonly VISite _site;
        public string LogDirectory;
        private Func<string> _fileName;
        public Func<string> FileName
        {
            set { _fileName = value; }
            get { return () => TestContext.CurrentContext.Test.Name.CutTestData() + "_fail_" + VISite.RunId; }
        }

        public ImageFormat ImgFormat = ImageFormat.Jpeg;

        public ScreenshotAlert(VISite site)
        {
            _site = site;
        }

        public Exception ThrowError(string errorMsg)
        {
            VISite.Logger.Error(errorMsg);
            TakeScreenshot();
            Assert.Fail(errorMsg);
            return new Exception(errorMsg);
        }

        public void TakeScreenshot(string path = null, string outputFileName = null, ImageFormat imgFormat = null)
        {
            if (path == null)
            {
                var imgRoot = JDILogger.GetValidUrl(ConfigurationSettings.AppSettings["VIScreenshotsPath"]);
                path = (!string.IsNullOrEmpty(imgRoot))
                    ? imgRoot
                    : LogDirectory ?? "/../.Logs/.Screenshots";
            }
            if (string.IsNullOrEmpty(outputFileName))
                if (_fileName != null)
                    outputFileName = _fileName();
                else
                {
                    outputFileName = JDILogger.GetValidUrl(ConfigurationSettings.AppSettings["VIScreenshotsFileName"]);
                    if (string.IsNullOrEmpty(outputFileName))
                        outputFileName = FileName();
                }
            path = JDILogger.GetValidUrl(path);
            JDILogger.CreateDirectory(path);
            if (imgFormat == null)
                imgFormat = ImgFormat;
            var screenshotPath = path + outputFileName + "." + imgFormat;
            var screenshot = ((ITakesScreenshot)_site.WebDriver).GetScreenshot();
            screenshot.SaveAsFile(screenshotPath, ImgFormat);
            VISite.Logger.Info("Add Screenshot: " + screenshotPath);
        }
        */
    }
}