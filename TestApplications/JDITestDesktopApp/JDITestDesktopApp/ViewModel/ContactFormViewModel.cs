using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Input;


namespace JDITestDesktopApp.ViewModel
{
    class ContactFormViewModel : ObservableObject, IPanel
    {
        private string _name, _lastName, _description, _summaryText, _nameText, _lastNameText, _descriptionText;
        private bool _nameChanged, _lastNameChanged, _descriptionChanged;
        private int _firstArgument, _secondArgument;
        private bool _isOne = true, _isTwo = true, _isThree, _isFour, _isFive, _isSix, _isSeven, _isEight;

        public string NameProperty {
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

        public bool IsOneProperty
        {
            get { return _isOne; }
            set
            {
                _isOne = value;
                if (_isOne) {
                    _firstArgument = 1;
                    AddSummaryToLog("Even", _firstArgument);
                } 
                RaisePropertyChangedEvent("IsOneProperty");
            }
        }

        public bool IsThreeProperty
        {
            get { return _isThree; }
            set
            {
                _isThree = value;
                if (_isThree) {
                    _firstArgument = 3;
                    AddSummaryToLog("Even", _firstArgument);
                } 
                RaisePropertyChangedEvent("IsThreeProperty");
            }
        }

        public bool IsFiveProperty
        {
            get { return _isFive; }
            set
            {
                _isFive = value;
                if (_isFive) {
                    _firstArgument = 5;
                    AddSummaryToLog("Even", _firstArgument);
                } 
                RaisePropertyChangedEvent("IsFiveProperty");
            }
        }

        public bool IsSevenProperty
        {
            get { return _isSeven; }
            set
            {
                _isSeven = value;
                if (_isSeven) {
                    _firstArgument = 7;
                    AddSummaryToLog("Even", _firstArgument);
                } 
                RaisePropertyChangedEvent("IsSevenProperty");                
            }
        }

        public bool IsTwoProperty
        {
            get { return _isTwo; }
            set
            {
                _isTwo = value;
                if (_isTwo) {
                    _secondArgument = 2;
                    AddSummaryToLog("Odd", _secondArgument);
                } 
                RaisePropertyChangedEvent("IsTwoProperty");
            }
        }

        public bool IsFourProperty
        {
            get { return _isFour; }
            set
            {
                _isFour = value;
                if (_isFour) {
                    _secondArgument = 4;
                    AddSummaryToLog("Odd", _secondArgument);
                } 
                RaisePropertyChangedEvent("IsFourProperty");
            }
        }

        public bool IsSixProperty
        {
            get { return _isSix; }
            set
            {
                _isSix = value;
                if (_isSix) {
                    _secondArgument = 6;
                    AddSummaryToLog("Odd", _secondArgument);
                } 
                RaisePropertyChangedEvent("IsSixProperty");                
            }
        }

        public bool IsEightProperty
        {
            get { return _isEight; }
            set
            {
                _isEight = value;
                if (_isEight) {
                    _secondArgument = 8;
                    AddSummaryToLog("Odd", _secondArgument);
                } 
                RaisePropertyChangedEvent("IsEightProperty");
            }
        }

        public ICommand NameFocusLostCommand
        {
            get
            {
                return new DelegateCommand(() =>
                {
                    if (_nameChanged) {
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

        public ICommand SubmitCommand
        {
            get { 
                return new DelegateCommand(() => {
                    CreateAllStrs();
                    PrintResult();
                }); }
        }

        public ICommand CalculateCommand
        {
            get { return new DelegateCommand(OnCalculateButton); }
        }

        public ContactFormViewModel()
        {
            ResetValues();
        }

        private void AddSummaryToLog(string str, int num)
        {
            RaiseAddToLog("Summary (" + str + "): value changed to " + num);
        }

        private void OnCalculateButton()
        {
            PrintResult();
        }

        private void CreateAllStrs()
        {
            _nameText = "\n" + "Name: " + _name;
            _lastNameText = "\n" + "Last Name: " + _lastName;
            _descriptionText = "\n" + "Description: " + _description;
        }

        private void PrintResult()
        {
            string result = "";
            result += "Summary: " + (_firstArgument + _secondArgument);
            if (!_name.Equals(""))
                result += _nameText;
            if (!_lastName.Equals(""))
                result += _lastNameText;
            if (!_description.Equals(""))
                result += _descriptionText;

            RaiseChangeResult(result);
        }

        public void ResetValues() 
        {
            NameProperty = "";
            _nameChanged = false;
            LastNameProperty = "";
            _lastNameChanged = false;
            DescriptionProperty = "";
            _descriptionChanged = false;

            IsOneProperty = true;
            IsTwoProperty = true;
        }
    }
}
