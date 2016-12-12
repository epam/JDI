using System;
using System.Windows;
using System.Windows.Controls;

using System.Windows.Input;

namespace JDITestDesktopApp.ViewModel
{
    class MainWindowViewModel : ObservableObject, IPanel
    {
        private string _logText, _resultText;        
        private ContactFormViewModel _contactFormViewModel;
        private DatesViewModel _datesViewModel;
        private SimpleTableViewModel _simpleTableViewModel;
        private TableWithPagesViewModel _tableWithPagesViewModel;
        private DifferentElementsViewModel _differentElementsViewModel;
        private MetalsAndColorsViewModel _metalsAndColorsViewModel;
        private ComplexTableViewModel _complexTableViewModel;
        
        private IPanel _panel;

        public MainWindowViewModel()
        {
            ContactFormViewModelProperty = new ContactFormViewModel();
            ContactFormViewModelProperty.StateChanged += LogAdded;
            ContactFormViewModelProperty.ResultUpdated += ResultChanged;

            DatesViewModelProperty = new DatesViewModel();
            DatesViewModelProperty.StateChanged += LogAdded;
            DatesViewModelProperty.ResultUpdated += ResultChanged;

            ComplexTableViewModelProperty = new ComplexTableViewModel();
            ComplexTableViewModelProperty.StateChanged += LogAdded;
            ComplexTableViewModelProperty.ResultUpdated += ResultChanged;

            SimpleTableViewModelProperty = new SimpleTableViewModel();
            SimpleTableViewModelProperty.StateChanged += LogAdded;
            SimpleTableViewModelProperty.ResultUpdated += ResultChanged;

            TableWithPagesViewModelProperty = new TableWithPagesViewModel();
            TableWithPagesViewModelProperty.StateChanged += LogAdded;
            TableWithPagesViewModelProperty.ResultUpdated += ResultChanged;

            DifferentElementsViewModelProperty = new DifferentElementsViewModel();
            DifferentElementsViewModelProperty.StateChanged += LogAdded;
            DifferentElementsViewModelProperty.ResultUpdated += ResultChanged;

            MetalsAndColorsViewModelProperty = new MetalsAndColorsViewModel();
            MetalsAndColorsViewModelProperty.StateChanged += LogAdded;
            MetalsAndColorsViewModelProperty.ResultUpdated += ResultChanged;

            _panel = this;
        }

        public ContactFormViewModel ContactFormViewModelProperty
        {
            get { return _contactFormViewModel; }
            set
            {
                _contactFormViewModel = value;
                RaisePropertyChangedEvent("ContactFormViewModelProperty");
            }
        }

        public DatesViewModel DatesViewModelProperty
        {
            get { return _datesViewModel; }
            set
            {
                _datesViewModel = value;
                RaisePropertyChangedEvent("DatesViewModelProperty");
            }
        }

        public ComplexTableViewModel ComplexTableViewModelProperty
        {
            get { return _complexTableViewModel; }
            set 
            {
                _complexTableViewModel = value;
                RaisePropertyChangedEvent("ComplexTableViewModelProperty");
            }
        }

        public SimpleTableViewModel SimpleTableViewModelProperty
        {
            get { return _simpleTableViewModel; }
            set
            {
                _simpleTableViewModel = value;
                RaisePropertyChangedEvent("SimpleTableViewModelProperty");
            }
        }

        public TableWithPagesViewModel TableWithPagesViewModelProperty
        {
            get { return _tableWithPagesViewModel; }
            set 
            {
                _tableWithPagesViewModel = value;
                RaisePropertyChangedEvent("TableWithPagesViewModelProperty");
            }
        }

        public DifferentElementsViewModel DifferentElementsViewModelProperty
        {
            get { return _differentElementsViewModel; }
            set
            {
                _differentElementsViewModel = value;
                RaisePropertyChangedEvent("DifferentElementsViewModelProperty");
            }
        }

        public MetalsAndColorsViewModel MetalsAndColorsViewModelProperty
        {
            get { return _metalsAndColorsViewModel; }
            set
            {
                _metalsAndColorsViewModel = value;
                RaisePropertyChangedEvent("MetalsAndColorsViewModelProperty");
            }
        }

        public string LogText
        {
            get { return _logText; }
            set
            {
                _logText = value;
                RaisePropertyChangedEvent("LogText");
            }
        }

        public string ResultText
        {
            get { return _resultText; }
            set
            {
                _resultText = value;
                RaisePropertyChangedEvent("ResultText");
            }
        }

        public TabItem SelectedTabItem
        {
            set {
                _panel.ResetValues();
                LogText = "";
                ResultText = "";

                TabItem tabItem = value;
                _panel = (IPanel)((FrameworkElement)tabItem.Content).DataContext;
                
                RaisePropertyChangedEvent("SelectedTabItem");
            }
        }

        public ICommand TabPressedButtonCommand
        {
            get { return new DelegateCommand(
                () => {
//                    _panel.ResetValues();
                }); 
            }
        }        

        private void LogAdded(string text)
        {
            LogText = DateTime.Now.ToString("H:mm:ss ") + text + Environment.NewLine + LogText;
        }

        private void ResultChanged(string text)
        {            
            ResultText = text;
        }

        public void ResetValues() { 
        }
    }
}
