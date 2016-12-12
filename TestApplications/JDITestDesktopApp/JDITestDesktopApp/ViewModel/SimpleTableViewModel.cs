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
    class SimpleTableViewModel : ObservableObject, IPanel
    {
        private ObservableCollection<RecordInfo> _infos;
        private DataGridCellInfo _cellInfo;
        private String _logStr;
        private bool _isCellChanged;

        public SimpleTableViewModel()
        {
            ResetValues();
        }

        private void FillPersonsInfo()
        {
            _infos = new ObservableCollection<RecordInfo>();

            _infos.Add(new RecordInfo { Type = "Drivers", Now = "Selenium, Custom", Plans = "JavaScript, Appium, WinAPI, Sikuli" });
            _infos.Add(new RecordInfo { Type = "Test Runner", Now = "TestNG, JUnit, Custom", Plans = "MSTest, NUnit, Epam" });
            _infos.Add(new RecordInfo { Type = "Asserter", Now = "TestNG, JUnit, Custom", Plans = "MSTest, NUnit, Epam" });
            _infos.Add(new RecordInfo { Type = "Logger", Now = "Log4J, TestNG log, Custom", Plans = "Epam, XML/Json logging, Hyper logging" });
            _infos.Add(new RecordInfo { Type = "Reporter", Now = "Jenkins, Allure, Custom", Plans = "Epam Report portal, Serenity, TeamCity, Hudson" });
            _infos.Add(new RecordInfo { Type = "BDD/DSL", Now = "Custom", Plans = "Cucumber, Jbehave, Thucydicles, Specflow" });
        }

        public DataGridCellInfo CellInfo
        {
            get { return _cellInfo; }
            set
            {
                _cellInfo = value;
                if (_cellInfo.IsValid) {
                    var cellContent = _cellInfo.Column.GetCellContent(_cellInfo.Item);
                    if (cellContent != null) {
                        DataGridCell dataGridCell = (DataGridCell)cellContent.Parent;
                        _logStr = ":value=" + ((TextBlock)dataGridCell.Content).Text + "; cell has been selected";
                        _isCellChanged = true;
                    }                                            
                }
                    
                RaisePropertyChangedEvent("CellInfo");
            }
        }

        public ObservableCollection<RecordInfo> PersonsInfo
        {
            get { return _infos; }
            set
            {
                _infos = value;
                RaisePropertyChangedEvent("PersonsInfo");
            }
        }

        public ICommand MouseUpCommand
        {
            get
            {
                return new DelegateCommand(() =>
                {
                    if (_isCellChanged)
                    {
                        RaiseAddToLog(_logStr);
                        _isCellChanged = false;
                    }
                }
                );
            }
        }

        public void ResetValues() 
        {
            _isCellChanged = false;

            _infos = null;
            FillPersonsInfo();
            RaisePropertyChangedEvent("PersonsInfo");
        }
    }

    class RecordInfo : ObservableObject
    {
        string _type, _now, _plans;

        public string Type
        {
            get { return _type; }
            set
            {
                _type = value;
                RaisePropertyChangedEvent("Type");
            }
        }

        public string Now
        {
            get { return _now; }
            set
            {
                _now = value;
                RaisePropertyChangedEvent("Now");
            }
        }

        public string Plans
        {
            get { return _plans; }
            set
            {
                _plans = value;
                RaisePropertyChangedEvent("Plans");
            }
        }
    }
}
