using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace JDI_Web.Selenium.DriverManager
{
    public static class WebDriverManagerHelper
    {
        /// <summary>
        /// Checks driver version by parsing *.exe file of driver
        /// </summary>
        /// <param name="path">Path to driver binary</param>
        /// <param name="version">Version to check</param>
        /// <returns>True - if versions are equals, else false</returns>
        public static bool CheckDriverVersionFromExe(string path, string version)
        {
            bool result = false;

            if (File.Exists(path))
            {
                string exeContent = File.ReadAllText(path);
                if (exeContent.Contains(version))
                    result = true;
            }
            return result;
        }

        /// <summary>
        /// Checks driver version by parsing binary attributes
        /// </summary>
        /// <param name="path">Path to driver binar</param>
        /// <param name="version">Version to check</param>
        /// <returns>True - if versions are equals, else false</returns>
        public static bool CheckDriverVerionFormExeAttributes(string path, string version)
        {
            bool result = false;
            if (File.Exists(path))
            {
                var versionInfo = FileVersionInfo.GetVersionInfo(path);
                if (versionInfo.ProductVersion.Contains(version))
                    result = true;
            }
            return result;
        }
    }
}
