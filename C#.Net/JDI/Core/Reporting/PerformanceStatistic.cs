using System.Collections.Generic;
using System.Linq;
using Epam.JDI.Commons;

namespace Epam.JDI.Core.Reporting
{
    public static class PerformanceStatistic
    {
        private static readonly Dictionary<string, List<double>> Statistic = new Dictionary<string, List<double>>();
        
        public static void AddStatistic(double time)
        {
            AddStatistic("JDI_ACTION", time);
        }

        public static void AddStatistic(string actionType, double time)
        {
            if (Statistic.ContainsKey(actionType))
                Statistic[actionType].Add(time);
        }

        public static string PrintStatistic()
        {
            return "Average Actions Time: " + Statistic.ToDictionary(el => el.Key, el => Average(el.Value).ToString("F")).Print();
        }
        public static double Average(List<double> collection)
        {
            if (collection == null || collection.Count == 0)
                return 0;
            if (collection.Count == 1)
                return collection[0];
            var average = collection[0];
            for (var i = 1; i < collection.Count; i++)
                average = i * (average + collection[i] / i) / (i + 1);
            return average;
        }
    }
}
