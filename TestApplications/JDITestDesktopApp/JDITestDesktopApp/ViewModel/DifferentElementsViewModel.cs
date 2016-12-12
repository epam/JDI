using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Windows.Input;

namespace JDITestDesktopApp.ViewModel
{
    class DifferentElementsViewModel : ObservableObject, IPanel
    {
        private List<string> _colorList = new List<string> { "Red", "Green", "Blue", "Yellow" };
        private bool _isWaterElement, _isEarthElement, _isWindElement, _isFireElement, _isGold, _isSilver, _isBronze, _isSelen;
        private string _colorStr;


        public bool IsWater
        {
            get { return _isWaterElement; }
            set
            {
                _isWaterElement = value;
                RaiseAddToLog("Water: condition changed to " + _isWaterElement);
                RaisePropertyChangedEvent("IsWater");
            }
        }

        public bool IsEarth
        {
            get { return _isEarthElement; }
            set
            {
                _isEarthElement = value;
                RaiseAddToLog("Earth: condition changed to " + _isEarthElement);
                RaisePropertyChangedEvent("IsEarth");
            }
        }

        public bool IsWind
        {
            get { return _isWindElement; }
            set
            {
                _isWindElement = value;
                RaiseAddToLog("Wind: condition changed to " + _isWindElement);
                RaisePropertyChangedEvent("IsWind");
            }
        }

        public bool IsFire
        {
            get { return _isFireElement; }
            set
            {
                _isFireElement = value;
                RaiseAddToLog("Fire: condition changed to " + _isFireElement);
                RaisePropertyChangedEvent("IsFire");
            }
        }

        public bool IsGoldProperty
        {
            get { return _isGold; }
            set
            {
                _isGold = value;
                if (_isGold)
                    RaiseAddToLog("metal: value changed to Gold");
                RaisePropertyChangedEvent("IsGoldProperty");
            }
        }

        public bool IsSilverProperty
        {
            get { return _isSilver; }
            set
            {
                _isSilver = value;
                if (_isSilver)
                    RaiseAddToLog("metal: value changed to Silver");
                RaisePropertyChangedEvent("IsSilverProperty");
            }
        }

        public bool IsBronzeProperty
        {
            get { return _isBronze; }
            set
            {
                _isBronze = value;
                if (_isBronze)
                    RaiseAddToLog("metal: value changed to Bronze");
                RaisePropertyChangedEvent("IsBronzeProperty");
            }
        }

        public bool IsSelenProperty
        {
            get { return _isSelen; }
            set
            {
                _isSelen = value;
                if (_isSelen)
                    RaiseAddToLog("metal: value changed to Selen");
                RaisePropertyChangedEvent("IsSelenProperty");
            }
        }

        public string ColorProperty
        {
            get { return _colorStr; }
            set
            {
                _colorStr = value;
                RaiseAddToLog("Colors: value changed to " + _colorStr);
                RaisePropertyChangedEvent("ColorProperty");
            }
        }

        public List<string> ColorsListProperty
        {
            get { return _colorList; }
            set
            {
                _colorList = value;
                RaisePropertyChangedEvent("ColorsListProperty");
            }
        }

        public ICommand DefaultButtonCommand 
        {
            get { return new DelegateCommand(() => RaiseAddToLog("Default Button:button clicked")); }
        }

        public ICommand ButtonCommand
        {
            get { return new DelegateCommand(() => RaiseAddToLog("button:button clicked")); }
        }

        public DifferentElementsViewModel()
        {
            ResetValues();
        }

        public void ResetValues()
        {
            _colorStr = "Red";
            RaisePropertyChangedEvent("ColorProperty");
            _isWaterElement = false;
            RaisePropertyChangedEvent("IsWater");
            _isEarthElement = false;
            RaisePropertyChangedEvent("IsEarth");
            _isWindElement = false;
            RaisePropertyChangedEvent("IsWind");
            _isFireElement = false;
            RaisePropertyChangedEvent("IsFire");
            _isGold = false;
            RaisePropertyChangedEvent("IsGoldProperty");
            _isSilver = false;
            RaisePropertyChangedEvent("IsSilverProperty");
            _isBronze = false;
            RaisePropertyChangedEvent("IsBronzeProperty");
            _isSelen = false;
            RaisePropertyChangedEvent("IsSelenProperty");
        }
    }
}
