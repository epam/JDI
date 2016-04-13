using System;
using System.Diagnostics;
using System.Linq;
using System.Management;

namespace Epam.JDI.Web.Selenium.DriverFactory
{
    public static class WebDriverUtils
    {
        public static void KillAllRunWebDrivers()
        {
            foreach (var proc in Process.GetProcessesByName("chromedriver"))
                KillProcessTree(proc.Id);
            foreach (var proc in from proc in Process.GetProcessesByName("firefox")
                                 let cmd = GetProcessCommandLine(proc.Id)
                                 where cmd.EndsWith("-foreground")
                                 select proc)
                proc.Kill();

            foreach (var proc in Process.GetProcessesByName("IEDriverServer"))
                KillProcessTree(proc.Id);

        }

        private static string GetProcessCommandLine(int pid)
        {
            using (var searcher = new ManagementObjectSearcher("Select * From Win32_Process Where ProcessID=" + pid))
            {
                using (var moc = searcher.Get())
                {
                    foreach (var mo in moc)
                        return mo["CommandLine"].ToString();
                }
            }
            throw new ArgumentException("pid");
        }

        private static void KillProcessTree(int pid)
        {
            using (var searcher = new ManagementObjectSearcher("Select * From Win32_Process Where ParentProcessID=" + pid))
            {
                using (var moc = searcher.Get())
                {
                    foreach (var mo in moc)

                        KillProcessTree(Convert.ToInt32(mo["ProcessID"]));
                    try
                    {
                        var proc = Process.GetProcessById(pid);
                        proc.Kill();
                    }
                    catch (ArgumentException)
                    {
                        // Process already exited
                    }
                }
            }
        }
    }
}
