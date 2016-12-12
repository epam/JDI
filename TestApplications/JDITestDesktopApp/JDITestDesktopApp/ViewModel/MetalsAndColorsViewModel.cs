using System;
using System.Windows.Input;
using System.Collections.Generic;
using System.Collections.ObjectModel;

namespace JDITestDesktopApp.ViewModel
{
    class MetalsAndColorsViewModel : ObservableObject, IPanel
    {
        private int _firstArgument, _secondArgument;
        private string _summaryText, _elementsText, _colorStr, _colorText, _metalStr, _customMetalStr, _vegetablesText, _metalsText;
        private bool _isWaterElement, _isEarthElement, _isWindElement, _isFireElement;
        private bool _isOne, _isTwo, _isThree, _isFour, _isFive, _isSix, _isSeven, _isEight;
        private bool _isLogged; 
        private List<string> _colorList = new List<string> { "Colors", "Red", "Green", "Blue", "Yellow" };
        private List<string> _metalsList = new List<string> { "Col", "Gold", "Silver", "Bronze", "Selen" };
        private ObservableCollection<string> _vegetablesList = new ObservableCollection<string> { "Cucumber", "Tomato", "Salad", "Onion" };
        private ObservableCollection<string> _selectedVegetables;


        public bool IsOneProperty 
        {
            get { return _isOne; }
            set 
            {                
                _isOne = value;
                if (_isOne) _firstArgument = 1;
                RaisePropertyChangedEvent("IsOneProperty");
            }
        }

        public bool IsThreeProperty
        {
            get { return _isThree; }
            set
            {
                _isThree = value;
                if (_isThree) _firstArgument = 3;
                RaisePropertyChangedEvent("IsThreeProperty");
            }
        }

        public bool IsFiveProperty
        {
            get { return _isFive; }
            set
            {
                _isFive = value;
                if (_isFive) _firstArgument = 5;
                RaisePropertyChangedEvent("IsFiveProperty");
            }
        }

        public bool IsSevenProperty
        {
            get { return _isSeven; }
            set
            {
                _isSeven = value;
                if (_isSeven) _firstArgument = 7;
                RaisePropertyChangedEvent("IsSevenProperty");
            }
        }

        public bool IsTwoProperty
        {
            get { return _isTwo; }
            set
            {
                _isTwo = value;
                if (_isTwo) _secondArgument = 2;
                RaisePropertyChangedEvent("IsTwoProperty");
            }
        }

        public bool IsFourProperty
        {
            get { return _isFour; }
            set
            {
                _isFour = value;
                if (_isFour) _secondArgument = 4;
                RaisePropertyChangedEvent("IsFourProperty");
            }
        }

        public bool IsSixProperty
        {
            get { return _isSix; }
            set
            {
                _isSix = value;
                if (_isSix) _secondArgument = 6;
                RaisePropertyChangedEvent("IsSixProperty");
            }
        }

        public bool IsEightProperty
        {
            get { return _isEight; }
            set
            {
                _isEight = value;
                if (_isEight) _secondArgument = 8;
                RaisePropertyChangedEvent("IsEightProperty");
            }
        }

        public bool IsWater
        {
            get { return _isWaterElement; }
            set
            {
                _isWaterElement = value;
                RaisePropertyChangedEvent("IsWater");
            }
        }

        public bool IsEarth
        {
            get { return _isEarthElement; }
            set
            {
                _isEarthElement = value;
                RaisePropertyChangedEvent("IsEarth");
            }
        }

        public bool IsWind
        {
            get { return _isWindElement; }
            set
            {
                _isWindElement = value;
                RaisePropertyChangedEvent("IsWind");
            }
        }

        public bool IsFire
        {
            get { return _isFireElement; }
            set
            {
                _isFireElement = value;
                RaisePropertyChangedEvent("IsFire");
            }
        }

        public string ColorProperty {
            get { return _colorStr; }
            set {
                _colorStr = value;
                RaiseAddToLog("Colors: value changed to " + _colorStr);
                RaisePropertyChangedEvent("ColorProperty");
            }
        }

        public List<string> ColorsListProperty {
            get { return _colorList; }
            set 
            { 
                _colorList = value;
                RaisePropertyChangedEvent("ColorsListProperty");
            }
        }

        public string MetalProperty 
        {
            get { return _metalStr; }
            set 
            {
                _metalStr = value;
                RaisePropertyChangedEvent("MetalProperty");
            }
        }

        public string CustomMetalProperty 
        {
            get { return _customMetalStr; }
            set
            {
                _customMetalStr = value;
                if (_metalStr == null)
                    _isLogged = false;
                RaisePropertyChangedEvent("CustomMetalProperty");
            }
        }

        public List<string> MetalsListProperty
        {
            get { return _metalsList; }
            set 
            { 
                _metalsList = value;
                RaisePropertyChangedEvent("MetalsListProperty");
            }
        }

        public ObservableCollection<string> VegetablesListProperty
        {
            get { return _vegetablesList; }
            set {
                _vegetablesList = value;
                RaisePropertyChangedEvent("VegetablesListProperty");
            }
        }

        public ObservableCollection<string> SelectedVegetables
        {
            get { return _selectedVegetables; }
            set
            {
                _selectedVegetables = value;
                RaisePropertyChangedEvent("SelectedVegetables");
            }
        }
        
        public ICommand CalculateCommand
        {
            get { return new DelegateCommand(OnCalculateButton); }
        }

        public ICommand SubmitCommand
        {
            get { return new DelegateCommand(OnSubmitButton); }
        }

        public ICommand OddChangedCommand
        {
            get { return new DelegateCommand(() => AddSummaryToLog("Odd", _firstArgument)); }
        }

        public ICommand EvenChangedCommand
        {
            get { return new DelegateCommand(() => AddSummaryToLog("Even", _secondArgument)); }
        }

        public ICommand WaterChangedCommand
        {
            get { return new DelegateCommand(() => CheckBoxChanged("Water", _isWaterElement)); }
        }

        public ICommand EarthChangedCommand
        {
            get { return new DelegateCommand(() => CheckBoxChanged("Earth", _isEarthElement)); }
        }

        public ICommand WindChangedCommand
        {
            get { return new DelegateCommand(() => CheckBoxChanged("Wind", _isWindElement)); }
        }

        public ICommand FireChangedCommand
        {
            get { return new DelegateCommand(() => CheckBoxChanged("Fire", _isFireElement)); }
        }

        public ICommand MetalChangedCommand 
        {
            get
            {
                return new DelegateCommand(() =>
                {
                    if (_metalStr == null) 
                        return;
                    if (!_isLogged)
                        RaiseAddToLog("Metals: value changed to " + _customMetalStr);
                    _isLogged = true;
                    RaiseAddToLog("Metals: value changed to " + _metalStr);
                });
            }
        }

        public ICommand FocusLostCommand {
            get 
            { 
                return new DelegateCommand(() => 
                { 
                    if (!_isLogged)
                        RaiseAddToLog("Metals: value changed to " + _customMetalStr);
                    _isLogged = true;
                });
            }
        }


        public MetalsAndColorsViewModel()
        {            
            ResetValues();
        }

        public void MyFunc() {
            if (_metalStr == null) 
                RaiseAddToLog("Metals: value changed to " + _customMetalStr);
        }

        private void OddChanged(int num) {
            _firstArgument = num;
            AddSummaryToLog("Odd", num);
        }

        private void EvenChanged(int num)
        {
            _secondArgument = num;
            AddSummaryToLog("Even", num);
        }

        private void AddSummaryToLog(string str, int num) 
        {
            RaiseAddToLog("Summary (" + str + "): value changed to " + num);
        }

        private void CheckBoxChanged(String str, bool isSelected)
        {
            string isSelectedStr = isSelected ? "true" : "false";
            RaiseAddToLog(str + ": condition changed to " + isSelectedStr);
        }

        private void CreateSummaryString() 
        {
            _summaryText = "Summary: " + (_firstArgument + _secondArgument);
        }

        private void OnCalculateButton() 
        {
            CreateSummaryString();
            PrintResult();
        }

        private void OnSubmitButton() {
            CreateAllStrs();
            PrintResult();
        }

        private void CreateElementsString()
        {
            if (!_isWaterElement && !_isEarthElement && !_isWindElement && !_isFireElement) //only to copy logic of https://jdi-framework.github.io/tests/page2.htm
                return;

            Boolean notTheFirstOne = false;
            _elementsText = "Elements: ";
            if (_isWaterElement)
            {
                notTheFirstOne = true;
                _elementsText += "Water";
            }
            if (_isEarthElement)
            {
                if (notTheFirstOne)
                    _elementsText += ", ";
                else
                    notTheFirstOne = true;
                _elementsText += "Earth";
            }
            if (_isWindElement)
            {
                if (notTheFirstOne)
                    _elementsText += ", ";
                else
                    notTheFirstOne = true;
                _elementsText += "Wind";
            }
            if (_isFireElement)
            {
                if (notTheFirstOne)
                    _elementsText += ", ";
                _elementsText += "Fire";
            }
        }

        private void CreateAllStrs() 
        {
            CreateSummaryString();
            CreateElementsString();
            _colorText = "Color: " + _colorStr;
            _vegetablesText = _selectedVegetables.Count != 0 ? "Vegetables: " + String.Join(", ", _selectedVegetables) : null;
            _metalsText = "Metal: " + _customMetalStr;
        }

        private void PrintResult()
        {
            string text = _summaryText;
            if (_elementsText != null)
                text += "\n" + _elementsText;
            if (_colorText != null)
                text += "\n" + _colorText;
            if (_metalsText != null)
                text += "\n" + _metalsText;
            if (_vegetablesText != null)
                text += "\n" + _vegetablesText;

            RaiseChangeResult(text);
        }

        public void ResetValues()
        {
            IsOneProperty = true;
            IsTwoProperty = true;
            IsWater = false;
            IsEarth= false;
            IsWind = false;
            IsFire = false;

            _colorStr = "Colors";
            RaisePropertyChangedEvent("ColorProperty");

            CustomMetalProperty = "Col";

            SelectedVegetables = new ObservableCollection<string>();
            _isLogged = true;
        }        
    }
}
