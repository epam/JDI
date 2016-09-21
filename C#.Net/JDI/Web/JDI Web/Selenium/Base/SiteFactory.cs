using System;
using System.Collections.Generic;
using System.Linq;
using JDI_Web.Selenium.Elements.Composite;

namespace JDI_Web.Selenium.Base
{
    public class SiteFactory<TScope, TSite> 
        where TSite : WebSite 
        where TScope : class 
    {
        public static Dictionary<TScope, List<SiteInfo<TSite>>> Sites 
            = new Dictionary<TScope, List<SiteInfo<TSite>>>();

        public static SiteInfo<TSite> Site(TScope scope)
        {
            TSite newSite;
            SiteInfo<TSite> siteInfo;
            if (Sites.ContainsKey(scope))
            {
                var sites = Sites[scope];
                newSite = sites.Any(site => !site.IsUsed)
                    ? sites.First(site => !site.IsUsed).Site
                    : Activator.CreateInstance<TSite>().InitScope<TSite>(scope);
                siteInfo = new SiteInfo<TSite>(newSite);
                sites.Add(siteInfo);
                return siteInfo;
            }
            newSite = Activator.CreateInstance<TSite>().InitScope<TSite>(scope);
            siteInfo = new SiteInfo<TSite>(newSite);
            Sites.Add(scope, new List<SiteInfo<TSite>> { siteInfo });
            return siteInfo;
        }
    }
}
