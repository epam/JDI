using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Input;

namespace JDITestDesktopApp.ViewModel
{
    class DatesViewModel : ObservableObject, IPanel
    {
        private DateTime _periodDate = DateTime.Now, _time = DateTime.Now;
        private string _firstRangeFrom, _secondRangeFrom, _name, _lastName, _description;
        private bool _isFirstRangeFromChanged, _isSecondRangeFromChanged, _isHigherValueChanged, _isLowerValueChanged, 
            _nameChanged, _lastNameChanged, _descriptionChanged;
        private int _higherValue, _lowerValue;

        public string NameProperty
        {
            get { return _name; }
            set
            {
                _name = value;
                _nameChanged = true;
                RaisePropertyChangedEvent("NameProperty");
            }
        }

        public string LastNameProperty
        {
            get { return _lastName; }
            set
            {
                _lastName = value;
                _lastNameChanged = true;
                RaisePropertyChangedEvent("LastNameProperty");
            }
        }

        public string DescriptionProperty
        {
            get { return _description; }
            set
            {
                _description = value;
                _descriptionChanged = true;
                RaisePropertyChangedEvent("DescriptionProperty");
            }
        }

        public DateTime PeriodDate
        {
            get { return _periodDate; }
            set 
            { 
                _periodDate = value;
                RaiseAddToLog("Time: value changed to " + _periodDate);
                RaisePropertyChangedEvent("PeriodDate");
            }
        }

        public DateTime Time
        {
            get { return _time; }
            set
            {
                _time = value;
                RaiseAddToLog("Time: value changed to " + String.Format("{0: HH:mm tt}", _time));
                RaisePropertyChangedEvent("Time");
            }
        }

        public string FirstRangeFromProperty
        {
            get { return _firstRangeFrom; }
            set 
            {
                if (!_firstRangeFrom.Equals(value)) {
                    _firstRangeFrom = value;
                    _isFirstRangeFromChanged = true;
                    RaisePropertyChangedEvent("FirstPeriodFromProperty");
                }
            }
        }

        public string SecondRangeFromProperty
        {
            get { return _secondRangeFrom; }
            set
            {
                if (!_secondRangeFrom.Equals(value))                
                {
                    _secondRangeFrom = value;
                    _isSecondRangeFromChanged = true;
                    RaisePropertyChangedEvent("SecondRangeFromProperty");
                }
            }
        }

        public int HigherValueProperty
        {
            get { return _higherValue; }
            set 
            {
                _higherValue = value;
                _isHigherValueChanged = true;
                RaisePropertyChangedEvent("HigherValueProperty");
            }
        }

        public int LowerValueProperty
        {
            get { return _lowerValue; }
            set
            {
                _lowerValue = value;
                _isLowerValueChanged = true;
                RaisePropertyChangedEvent("LowerValueProperty");
            }
        }

        public ICommand NameFocusLostCommand
        {
            get
            {
                return new DelegateCommand(() =>
                {
                    if (_nameChanged)
                    {
                        RaiseAddToLog("Name: value changed to " + _name);
                        _nameChanged = false;
                    }
                });
            }
        }

        public ICommand LastNameFocusLostCommand
        {
            get
            {
                return new DelegateCommand(() =>
                {
                    if (_lastNameChanged)
                    {
                        RaiseAddToLog("LastName: value changed to " + _lastName);
                        _lastNameChanged = false;
                    }
                });
            }
        }

        public ICommand DescriptionFocusLostCommand
        {
            get
            {
                return new DelegateCommand(() =>
                {
                    if (_descriptionChanged)
                    {
                        RaiseAddToLog("Description: value changed to " + _description);
                        _descriptionChanged = false;
                    }
                });
            }
        }

        public ICommand FirstFromFocusLostCommand 
        {
            get
            {
                return new DelegateCommand(() => 
                {
                    if (_isFirstRangeFromChanged)
                    {
                        RaiseAddToLog("Range 1(From): value changed to " + _firstRangeFrom);
                        _isFirstRangeFromChanged = false;
                    }
                });
            }
        }

        public ICommand SecondFromFocusLostCommand
        {
            get
            {
                return new DelegateCommand(() =>
                {
                    if (_isSecondRangeFromChanged)
                    {
                        RaiseAddToLog("Range 1(To): value changed to " + _secondRangeFrom);
                        _isSecondRangeFromChanged = false;
                    }
                });
            }
        }

        public ICommand HigherValueChangedCommand
        {
            get
            {
                return new DelegateCommand(() =>
                {
                    if (_isHigherValueChanged)
                    {
                        RaiseAddToLog("Range 2(To):" + _higherValue + " link clicked");
                        _isHigherValueChanged = false;
                        return;
                    }
                    if (_isLowerValueChanged)
                    {
                        RaiseAddToLog("Range 2(From):" + _lowerValue + " link clicked");
                        _isLowerValueChanged = false;
                        return;
                    }
                });
            }
        }

        public ICommand SubmitCommand
        {
            get
            {
                return new DelegateCommand(() =>
                {
                    string result = "";
                    result += "Period: " + _periodDate;
                    result += "\nTime: " + String.Format("{0: HH:mm tt}", _time);
                    result += "\nRange 1: from " + _firstRangeFrom + " to " + _secondRangeFrom;
                    result += "\nRange 2: from " + _lowerValue + " to " + _higherValue;

                    if (!_name.Equals(""))
                        result += "\n" + "Name: " + _name;
                    if (!_lastName.Equals(""))
                        result += "\n" + "Last Name: " + _lastName;
                    if (!_description.Equals(""))
                        result += "\n" + "Description: " + _description;

                    RaiseChangeResult(result);
                });
            }
        }

        public DatesViewModel() 
        {
            ResetValues();
        }

        public void ResetValues() {
            NameProperty = "";
            _nameChanged = false;
            LastNameProperty = "";
            _lastNameChanged = false;
            DescriptionProperty = "";
            _descriptionChanged = false;

            PeriodDate = DateTime.Now;
            Time = DateTime.Now;
            _firstRangeFrom = "";
            _isFirstRangeFromChanged = false;
            _secondRangeFrom = "";
            _isSecondRangeFromChanged = false;
            LowerValueProperty = 20;
            HigherValueProperty = 100;
            _isHigherValueChanged = false;
            _isLowerValueChanged = false;
        }
    }
}
