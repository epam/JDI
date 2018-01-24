using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Controls.Primitives;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;

namespace JDI_AUT
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void tabControl_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            if (e.Source is TabControl)
            {
                TabControl tc = (TabControl)e.Source;
                
                if (contactFormTab.IsSelected)
                {
                    nameCFTextBox.Clear();
                    lastNameCFTextBox.Clear();
                    descriptionCFTextBox.Clear();
                    oneCFRadioButton.IsChecked = true;
                    twoCFRadioButton.IsChecked = true;
                }
                if (datesTab.IsSelected)
                {
                    nameDTextBox.Clear();
                    lastNameDTextBox.Clear();
                    descriptionDTextBox.Clear();
                    rangeFromDTextBox.Clear();
                    rangeToDTextBox.Clear();
                    dateDDatePicker.Text = "";
                    timeDTextBox.Clear();
                    sliderDSlider.Value = 0;
                }
                if (differentTab.IsSelected)
                {
                    earthDiffCheckBox.IsChecked = false;
                    waterDiffCheckBox.IsChecked = false;
                    windDiffCheckBox.IsChecked = false;
                    fireDiffCheckBox.IsChecked = false;
                    goldDiffRadioButton.IsChecked = true;
                    colorsDiffComboBox.SelectedIndex = 0;
                }
                if (metalsTab.IsSelected)
                {
                    oneMRadioButton.IsChecked = true;
                    twoMRadioButton.IsChecked = true;
                    waterMCheckBox.IsChecked = false;
                    fireMCheckBox.IsChecked = false;
                    earthMCheckBox.IsChecked = false;
                    windMCheckBox.IsChecked = false;
                    cucumberMCheckBox.IsChecked = false;
                    onionMCheckBox.IsChecked = false;
                    tomatoMCheckBox.IsChecked = false;
                    saladMCheckBox.IsChecked = true;
                    colorsMComboBox.SelectedIndex = 0;
                    metalsMComboBox.SelectedIndex = 0;
                }

                tc.Focus();
                logTextBox.Clear();
                resultTextBox.Clear();
            }
        }
        
        private void commonTextBox_LostFocus(object sender, RoutedEventArgs e)
        {
            TextBox tb = (TextBox)sender;
            logTextBox.Text = DateTime.Now.ToString("H:mm:ss ") + tb.Tag.ToString() + ": value changed to " + tb.Text + Environment.NewLine + logTextBox.Text;
        }

        private void commonRadioButton_Checked(object sender, RoutedEventArgs e)
        {
            RadioButton rb = (RadioButton)sender;
            logTextBox.Text = DateTime.Now.ToString("H:mm:ss ") + rb.GroupName.ToString() + ": value changed to " + rb.Content.ToString() + Environment.NewLine + logTextBox.Text;
        }

        private void commonCheckBox_Click(object sender, RoutedEventArgs e)
        {
            CheckBox cb = (CheckBox)sender;
            logTextBox.Text = DateTime.Now.ToString("H:mm:ss ") + cb.Tag.ToString() + ": condition changed to " + cb.IsChecked.ToString() + Environment.NewLine + logTextBox.Text;
        }

        private void commonCalculateButton_Click(object sender, RoutedEventArgs e)
        {
            Button button = (Button)sender;
            Grid parent = (Grid)this.FindName(button.Tag.ToString());
            Grid oddGrid = (Grid)parent.Children.OfType<Grid>().ElementAt(0);
            Grid evenGrid = (Grid)parent.Children.OfType<Grid>().ElementAt(1);
            RadioButton even = (RadioButton)evenGrid.Children.OfType<RadioButton>().FirstOrDefault(r => r.IsChecked.Value);
            RadioButton odd = (RadioButton)oddGrid.Children.OfType<RadioButton>().FirstOrDefault(r => r.IsChecked.Value);
            int evenSummand = Int32.Parse(even.Content.ToString());
            int oddSummand = Int32.Parse(odd.Content.ToString());
            int sum = evenSummand + oddSummand;
            string resultString = resultTextBox.Text;
            string goodString = "Summary: " + sum;
            if (resultString !="")
            {
                int i = resultString.IndexOf(Environment.NewLine);
                string badString = resultString.Substring(0, i);
                resultTextBox.Text = resultString.Replace(badString, goodString);                
            } else
            {
                resultTextBox.Text = goodString + Environment.NewLine;
            }
        }

        private void MenuItem_Click(object sender, RoutedEventArgs e)
        {
            MenuItem mi = (MenuItem)sender;
            TabItem ti = (TabItem)this.FindName(mi.Tag.ToString());
            ti.IsSelected = true;
        }

        private void commonComboBox_SelectionChanged(object sender, SelectionChangedEventArgs e)
        {
            ComboBox cb = (ComboBox)sender;
            ComboBoxItem cbi = (ComboBoxItem)cb.SelectedItem;
            try
            {
                logTextBox.Text = DateTime.Now.ToString("H:mm:ss ") + cb.Tag.ToString() + ": value changed to " + cbi.Content.ToString() + Environment.NewLine + logTextBox.Text;
            }
            catch (Exception exception)
            {
            }
        }

        private void DataGrid_GotFocus(object sender, RoutedEventArgs e)
        {
            if (e.OriginalSource.GetType()==typeof(DataGridCell))
            {
                DataGridCell cell = (DataGridCell) e.OriginalSource;
                TextBlock tb = (TextBlock)cell.Content;
                logTextBox.Text = DateTime.Now.ToString("H:mm:ss :value=") + tb.Text + "; cell has been selected" + Environment.NewLine + logTextBox.Text;
            }
        }

        private void Slider_DragCompleted(object sender, DragCompletedEventArgs e)
        {
            Slider slider = (Slider) sender;
            logTextBox.Text = DateTime.Now.ToString("H:mm:ss ")+ "Range 2(From):" + (int) slider.Value + " link clicked" + Environment.NewLine + logTextBox.Text;
        }

        private void submitCFButton_Click(object sender, RoutedEventArgs e)
        {
            TextBox firstNameTB = (TextBox) this.FindName("nameCFTextBox");
            TextBox lastNameTB = (TextBox) this.FindName("lastNameCFTextBox");
            TextBox descriptionTB = (TextBox) this.FindName("descriptionCFTextBox");

            string resultString = resultTextBox.Text;
            
            Grid parent = (Grid)this.FindName("summaryCFGrid");
            Grid oddGrid = (Grid)parent.Children.OfType<Grid>().ElementAt(0);
            Grid evenGrid = (Grid)parent.Children.OfType<Grid>().ElementAt(1);
            RadioButton even = (RadioButton)evenGrid.Children.OfType<RadioButton>().FirstOrDefault(r => r.IsChecked.Value);
            RadioButton odd = (RadioButton)oddGrid.Children.OfType<RadioButton>().FirstOrDefault(r => r.IsChecked.Value);
            int evenSummand = Int32.Parse(even.Content.ToString());
            int oddSummand = Int32.Parse(odd.Content.ToString());
            int sum = evenSummand + oddSummand;

            string goodNameString = firstNameTB.Text;
            string goodLastNameString = lastNameTB.Text;
            string goodDescriptionString = descriptionTB.Text;
            string goodSumString = "Summary: " + sum;

            List<string> listFromBox = new List<string>(resultString.Split(new string[] {Environment.NewLine}, StringSplitOptions.RemoveEmptyEntries));


            string badSumString = listFromBox.FirstOrDefault(r => r.IndexOf("Summary") == 0);
            if (badSumString != null)
            {
                listFromBox[listFromBox.IndexOf(badSumString)] = goodSumString;
            }
            else
            {
                listFromBox.Add(goodSumString);
            }

            if (goodNameString != "")
            {
                string badNameString = listFromBox.FirstOrDefault(r => r.IndexOf("Name") == 0);
                if (badNameString != null)
                {
                    listFromBox[listFromBox.IndexOf(badNameString)] = "Name: " + goodNameString;
                }
                else
                {
                    listFromBox.Add("Name: "+ goodNameString);
                }
            }

            if (goodLastNameString != "")
            {
                string badLastNameString = listFromBox.FirstOrDefault(r => r.IndexOf("Last Name") == 0);
                if (badLastNameString != null)
                {
                    listFromBox[listFromBox.IndexOf(badLastNameString)] = "Last Name: " + goodLastNameString;
                }
                else
                {
                    listFromBox.Add("Last Name: " + goodLastNameString);
                }
            }

            if (goodDescriptionString!= "")
            {
                string badDescriptionString = listFromBox.FirstOrDefault(r => r.IndexOf("Description") == 0);
                if (badDescriptionString!= null)
                {
                    listFromBox[listFromBox.IndexOf(badDescriptionString)] = "Description: " + goodDescriptionString;
                }
                else
                {
                    listFromBox.Add("Description: " + goodDescriptionString);
                }
            }

            resultTextBox.Text = String.Join(Environment.NewLine, listFromBox);
            
        }
    }
}
