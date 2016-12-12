using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Collections.ObjectModel;


namespace JDITestDesktopApp.ViewModel
{
    class TableWithPagesData : View.IPageControlContract
    {
        private ObservableCollection<RecordInfo> _infos;

        public TableWithPagesData()
        {
            _infos = new ObservableCollection<RecordInfo>();

            _infos.Add(new RecordInfo { Type = "Drivers", Now = "Selenium, Custom", Plans = "JavaScript, Appium, WinAPI, Sikuli" });
            _infos.Add(new RecordInfo { Type = "Test Runner", Now = "TestNG, JUnit, Custom", Plans = "MSTest, NUnit, Epam" });
            _infos.Add(new RecordInfo { Type = "Asserter", Now = "TestNG, JUnit, Custom", Plans = "MSTest, NUnit, Epam" });
            _infos.Add(new RecordInfo { Type = "Logger", Now = "Log4J, TestNG log, Custom", Plans = "Epam, XML/Json logging, Hyper logging" });
            _infos.Add(new RecordInfo { Type = "Reportet", Now = "Jenkins, Allure, Custom", Plans = "Epam Report portal, Serenity, TeamCity, Hudson" });
            _infos.Add(new RecordInfo { Type = "BDD/DSL", Now = "Custom", Plans = "Cucumber, Jbehave, Thucydicles, Specflow" });
        }

        public uint GetTotalCount()
        {
            return (uint)_infos.Count; ;
        }

        public ICollection<object> GetRecordsBy(uint StartingIndex, uint NumberOfRecords, object FilterTag)
        {
            if (StartingIndex >= _infos.Count)
            {
                return new List<object>();
            }

            List<RecordInfo> result = new List<RecordInfo>();

            for (int i = (int)StartingIndex; i < _infos.Count && i < StartingIndex + NumberOfRecords; i++)
            {
                result.Add(_infos[i]);
            }

            return result.ToList<object>();
        }
    }
}
