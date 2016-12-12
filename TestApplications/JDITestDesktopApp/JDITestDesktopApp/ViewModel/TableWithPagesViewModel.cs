using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using System.Windows.Input;
using System.Windows.Controls;
using System.Collections.ObjectModel;
using System.ComponentModel;

namespace JDITestDesktopApp.ViewModel
{
    class TableWithPagesViewModel : ObservableObject, IPanel
    {
        private ObservableCollection<RecordInfo> _infos;

        private void FillPersonsInfo()
        {
            _infos = new ObservableCollection<RecordInfo>();

            _infos.Add(new RecordInfo { Type = "Drivers", Now = "Selenium, Custom", Plans = "JavaScript, Appium, WinAPI, Sikuli" });
            _infos.Add(new RecordInfo { Type = "Test Runner", Now = "TestNG, JUnit, Custom", Plans = "MSTest, NUnit, Epam" });
            _infos.Add(new RecordInfo { Type = "Asserter", Now = "TestNG, JUnit, Custom", Plans = "MSTest, NUnit, Epam" });
            _infos.Add(new RecordInfo { Type = "Logger", Now = "Log4J, TestNG log, Custom", Plans = "Epam, XML/Json logging, Hyper logging" });
            _infos.Add(new RecordInfo { Type = "Reportet", Now = "Jenkins, Allure, Custom", Plans = "Epam Report portal, Serenity, TeamCity, Hudson" });
            _infos.Add(new RecordInfo { Type = "BDD/DSL", Now = "Custom", Plans = "Cucumber, Jbehave, Thucydicles, Specflow" });
        }

        public ObservableCollection<RecordInfo> RecordInfos
        {
            get { return _infos; }
            set
            {
                _infos = value;
                RaisePropertyChangedEvent("RecordInfos");
            }
        }

        public TableWithPagesViewModel()
        {
            ResetValues();
        }

        public void ResetValues()
        {
            FillPersonsInfo();
            RaisePropertyChangedEvent("RecordInfos");
        }
    }
}
