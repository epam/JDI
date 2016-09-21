using JDI_Web.Selenium.Elements.Composite;

namespace JDI_Web.Selenium.Base
{
    public class SiteInfo<TSite> where TSite : WebSite
    {
        public bool IsUsed;
        public TSite Site;

        public SiteInfo(TSite site)
        {
            Site = site;
            IsUsed = true;
        }
    }
}
