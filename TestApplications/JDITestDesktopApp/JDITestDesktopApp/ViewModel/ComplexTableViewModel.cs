using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.Linq;
using System.Text;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows;
using System.Windows.Documents;
using System.Windows.Input;

namespace JDITestDesktopApp.ViewModel
{
    class ComplexTableViewModel : ObservableObject, IPanel
    {
        private ObservableCollection<DataGridColumn> _columnCollection;
        private ObservableCollection<ComplexTableRecordInfo> _infos;
        private DataGridTextColumn _zeroColumn;
        private DataGridTemplateColumn _firstColumn, _secondColumn, _thirdColumn;
        private const string _firstColumnName = "Column 1";
        private const string _secondColumnName = "Column 2";
        private const string _thirdColumnName = "Column 3";
        private ObservableCollection<string> _columnsList = new ObservableCollection<string> { _firstColumnName, _secondColumnName, _thirdColumnName };
        private ObservableCollection<string> _selectedColumns;

        private void FillPersonsInfo()
        {
            _infos = new ObservableCollection<ComplexTableRecordInfo>();

            _infos.Add(new ComplexTableRecordInfo { ZeroColumn = "Microsoft Technologies", FirstColumn = ".NET Technologies", SecondColumn = "Internet Technologies", ThirdColumn = "Programming Languages"});
            _infos.Add(new ComplexTableRecordInfo { ZeroColumn = "Mobile", FirstColumn = "HTML5/CSS3", SecondColumn = "JQuery Mobile", ThirdColumn = "JQuery Mobile" });
            _infos.Add(new ComplexTableRecordInfo { ZeroColumn = "UXD", FirstColumn = "(X)CSS Development", SecondColumn = "Grunt (The JavaScript Task Runner)", ThirdColumn = "Less CSS" });
            _infos.Add(new ComplexTableRecordInfo { ZeroColumn = "Version Control Systems", FirstColumn = "CVS", SecondColumn = "TortoiseSVN", ThirdColumn = "Git" });
            _infos.Add(new ComplexTableRecordInfo { ZeroColumn = "JavaScript Components and Frameworks", FirstColumn = "Backbone marionette js", SecondColumn = "Bootstrap", ThirdColumn = "RequireJS"});
            _infos.Add(new ComplexTableRecordInfo { ZeroColumn = "Software Construction", FirstColumn = "Internet Technologies", SecondColumn = "JavaScript Components and Frameworks", ThirdColumn = "Backend" });
            _infos.Add(new ComplexTableRecordInfo { ZeroColumn = "Life Sciences", FirstColumn = "Biology", SecondColumn = "Chemistry", ThirdColumn = "Medicine" });
            _infos.Add(new ComplexTableRecordInfo { ZeroColumn = "Content management", FirstColumn = "Drupal", SecondColumn = "Adobe Day CRX", ThirdColumn = "Sharepoint" });
        }

        public ObservableCollection<string> ColumnsListProperty
        {
            get { return _columnsList; }
            set
            {
                _columnsList = value;
                RaisePropertyChangedEvent("ColumnsListProperty");
            }
        }

        public ObservableCollection<string> SelectedColumns
        {
            get { return _selectedColumns; }
            set
            {
                _selectedColumns = value;
                
                RaisePropertyChangedEvent("SelectedColumns");
            }
        }

        public ObservableCollection<ComplexTableRecordInfo> PersonsInfo
        {
            get { return _infos; }
            set
            {
                _infos = value;
                RaisePropertyChangedEvent("PersonsInfo");
            }
        }

        public ObservableCollection<DataGridColumn> ColumnCollection
        {
            get { return _columnCollection; }
            set
            {
                _columnCollection = value;
                RaisePropertyChangedEvent("ColumnCollection");
            }
        }

        public ICommand SelectedItemsChanged {
            get 
            {
                return new DelegateCommand(() => {
                    RaiseAddToLog("Columns: value changed to " + String.Join(", ", _selectedColumns));
                });
            }
        }

        public ICommand ApplyCommand
        {
            get
            {
                return new DelegateCommand(() =>
                {
                    _columnCollection = new ObservableCollection<DataGridColumn>();
                    _columnCollection.Add(_zeroColumn);
                    if (_selectedColumns.Contains(_firstColumnName))
                        _columnCollection.Add(_firstColumn);
                    if (_selectedColumns.Contains(_secondColumnName))
                        _columnCollection.Add(_secondColumn);
                    if (_selectedColumns.Contains(_thirdColumnName))
                        _columnCollection.Add(_thirdColumn);
                    RaisePropertyChangedEvent("ColumnCollection");
                    RaiseAddToLog("Apply button clicked");
                });
            }
        }

        public ICommand ReestablishCommand
        {
            get
            {
                return new DelegateCommand(() =>
                {
                    _columnCollection = new ObservableCollection<DataGridColumn>();
                    _columnCollection.Add(_zeroColumn);
                    _columnCollection.Add(_firstColumn);
                    _columnCollection.Add(_secondColumn);
                    _columnCollection.Add(_thirdColumn);
                    RaisePropertyChangedEvent("ColumnCollection");
                    _selectedColumns = new ObservableCollection<string>();
                    _selectedColumns.Add(_firstColumnName);
                    _selectedColumns.Add(_secondColumnName);
                    _selectedColumns.Add(_thirdColumnName);
                    RaisePropertyChangedEvent("SelectedColumns");
                    RaiseAddToLog("Reestablish button clicked");
                });
            }
        }

        private void UnderlindedTextClicked(object sender, RoutedEventArgs e)
        {
            RaiseAddToLog(":See More link clicked");
        }

        private void CheckboxCheckedEvent(object sender, RoutedEventArgs e)
        {
            RaiseAddToLog("Select: condition changed to true");
        }

        private void CheckboxUncheckedEvent(object sender, RoutedEventArgs e)
        {
            RaiseAddToLog("Select: condition changed to false");
        }

        public ComplexTableViewModel()
        {
            ResetValues();
        }

        private DataGridTemplateColumn CreateColumn(string bindingRow, string header)
        {
            DataGridTemplateColumn column = new DataGridTemplateColumn();
            column.Header = header;
            DataTemplate dataTemplate = new DataTemplate();
            column.CellTemplate = dataTemplate;

            dataTemplate.VisualTree = new FrameworkElementFactory(typeof(StackPanel));

            FrameworkElementFactory checkBox = new FrameworkElementFactory(typeof(CheckBox));
            checkBox.SetValue(CheckBox.ContentProperty, "Select");
            checkBox.AddHandler(CheckBox.CheckedEvent, new RoutedEventHandler(CheckboxCheckedEvent));
            checkBox.AddHandler(CheckBox.UncheckedEvent, new RoutedEventHandler(CheckboxUncheckedEvent));            
            dataTemplate.VisualTree.AppendChild(checkBox);            

            FrameworkElementFactory label = new FrameworkElementFactory(typeof(Label));
            label.SetBinding(Label.ContentProperty, new Binding(bindingRow));
            dataTemplate.VisualTree.AppendChild(label);

            FrameworkElementFactory textBlock = new FrameworkElementFactory(typeof(TextBlock));            
            textBlock.SetValue(TextBlock.TextProperty, "See more");
            TextDecorationCollection textDecorations = new TextDecorationCollection();
            textDecorations.Add(TextDecorations.Underline);
            textBlock.SetValue(TextBlock.TextDecorationsProperty, textDecorations);
            textBlock.AddHandler(TextBlock.MouseUpEvent, new MouseButtonEventHandler(UnderlindedTextClicked));
            dataTemplate.VisualTree.AppendChild(textBlock);            

            return column;
        }

        public void ResetValues() {
            FillPersonsInfo();
            PersonsInfo = _infos;
            RaisePropertyChangedEvent("PersonsInfo");

            ColumnCollection = new ObservableCollection<DataGridColumn>();

            _zeroColumn = new DataGridTextColumn();

            _zeroColumn.Binding = new Binding("ZeroColumn");
            ColumnCollection.Add(_zeroColumn);

            _firstColumn = CreateColumn("FirstColumn", "Column 1");
            ColumnCollection.Add(_firstColumn);

            _secondColumn = CreateColumn("SecondColumn", "Column 2");
            ColumnCollection.Add(_secondColumn);

            _thirdColumn = CreateColumn("ThirdColumn", "Column 3");

            _selectedColumns = new ObservableCollection<string>();
            _selectedColumns.Add(_firstColumnName);
            _selectedColumns.Add(_secondColumnName);
            RaisePropertyChangedEvent("SelectedColumns");
        }
    }

    class ComplexTableRecordInfo : ObservableObject
    {
        string _zeroColumn, _firstColumn, _secondColumn, _thirdColumn;

        public string ZeroColumn
        {
            get { return _zeroColumn; }
            set
            {
                _zeroColumn = value;
                RaisePropertyChangedEvent("ZeroColumn");
            }
        }

        public string FirstColumn
        {
            get { return _firstColumn; }
            set
            {
                _firstColumn = value;
                RaisePropertyChangedEvent("FirstColumn");
            }
        }

        public string SecondColumn
        {
            get { return _secondColumn; }
            set
            {
                _secondColumn = value;
                RaisePropertyChangedEvent("SecondColumn");
            }
        }

        public string ThirdColumn
        {
            get { return _thirdColumn; }
            set
            {
                _thirdColumn = value;
                RaisePropertyChangedEvent("ThirdColumn");
            }
        }
    }
}
