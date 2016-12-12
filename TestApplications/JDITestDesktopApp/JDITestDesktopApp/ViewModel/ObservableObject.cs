using System.ComponentModel;
using System;

namespace JDITestDesktopApp.ViewModel
{
    class ObservableObject : INotifyPropertyChanged
    {
        public event PropertyChangedEventHandler PropertyChanged;

        protected void RaisePropertyChangedEvent(string propertyName)
        {
            var handler = PropertyChanged;
            if (handler != null)
                handler(this, new PropertyChangedEventArgs(propertyName));
        }

        public event Action<string> StateChanged;
        public event Action<string> ResultUpdated;

        protected void RaiseAddToLog(string text)
        {
            var handler = StateChanged;
            if (handler != null)
                handler(text);
        }

        protected void RaiseChangeResult(string text)
        {
            var handler = ResultUpdated;
            if (handler != null)
                handler(text);
        }
    }
}
