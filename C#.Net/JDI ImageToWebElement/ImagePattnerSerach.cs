using OpenQA.Selenium;
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace JDI_ImageToWebElement
{
    /// <summary>
    /// 
    /// </summary>
    public enum Command
    {
        FIND_ALL
    }

    /// <summary>
    /// Класс предоставляющий методы для поиска элементов по его изображению
    /// 1. Для использования, необходимо установить JVM
    /// 2. Прописать в системных переменных JAVA_HOME
    /// 3. Установить nuget пакет SikuliIntegrator
    /// </summary>
    public class ImagePattnerSerach
    {
        static IWebDriver webDriver;
        /// <summary>
        /// "Пробрасывает" ошибку
        /// </summary>
        /// <param name="output"></param>
        /// <param name="error"></param>
        private static void ConsumeResult(string output, string error)
        {
            if (!string.IsNullOrEmpty(error))
            {
                throw new Exception(error);
            }
            if (output.Contains("###FAILURE"))
            {
                throw new Exception(output);
            }
        }

        /// <summary>
        /// Используя модуль JSikuliModule.jar из nuget пакета SikuliIntegrator
        /// получает координаты центра элемента(ов), относительно экрана, найденных по заданному паттрену
        /// </summary>
        /// <param name="mainPattern">Файл с паттреном</param>
        /// <param name="similarity">Точность поиска</param>
        /// <param name="timeout">Таймаут для поиска</param>
        /// <returns>Список координат</returns>
        private static List<Point> Execute(string mainPattern, float similarity, int timeout)
        {
            Command command = Command.FIND_ALL;
            List<Point> points;
            object[] directoryName;
            ProcessStartInfo psi = null;
            string output = "";
            string error = "";

            directoryName = new object[] { "-jar \"", Path.GetDirectoryName(typeof(ImagePattnerSerach).Assembly.Location)
                , "\\JSikuliModule.jar\" \"",mainPattern, "\" \"", command.ToString(), "\" ", similarity, " ", timeout };

            psi = new ProcessStartInfo("java.exe", string.Concat(directoryName));
            psi.WindowStyle = ProcessWindowStyle.Hidden;
            psi.RedirectStandardOutput = true;
            psi.RedirectStandardError = true;
            psi.UseShellExecute = false;
            psi.CreateNoWindow = true;
            Process process = Process.Start(psi);
            process.WaitForExit();

            StreamReader srProcessOutput = process.StandardOutput;
            try { output = srProcessOutput.ReadToEnd(); }
            finally
            {
                if (srProcessOutput != null)
                    ((IDisposable)srProcessOutput).Dispose();
            }

            StreamReader srProcessError = process.StandardError;
            try { error = srProcessError.ReadToEnd(); }
            finally
            {
                if (srProcessError != null)
                    ((IDisposable)srProcessError).Dispose();
            }

            ConsumeResult(output, error);
            points = PrepareCoordinates(output);

            return points;
        }

        /// <summary>
        /// Парсит координаты из ответа Sikuli
        /// </summary>
        /// <param name="rawCoordinates">Строка ответа</param>
        /// <returns>Список координат</returns>
        private static List<Point> PrepareCoordinates(string rawCoordinates)
        {
            List<Point> points = new List<Point>();
            string[] strArrays = Regex.Split(rawCoordinates, Environment.NewLine);
            for (int i = 0; i < (int)strArrays.Length; i++)
            {
                string point = strArrays[i];
                if ((string.IsNullOrEmpty(point) ? false : !string.IsNullOrWhiteSpace(point)))
                {
                    string rawCoord = point.Substring(rawCoordinates.IndexOf("###") + "###".Length);
                    rawCoord = rawCoord.Trim().Replace("(", "").Replace(")", "");
                    if (!Regex.Match(rawCoord, "[0-9];[0-9]", RegexOptions.IgnoreCase).Success)
                    {
                        //goto Label0;
                        continue;
                    }
                    string[] coordinates = rawCoord.Split(new char[] { ';' });
                    if ((int)coordinates.Length != 2)
                    {
                        throw new Exception(string.Concat("Controls coordinates can not be determined: ", rawCoordinates));
                    }
                    points.Add(new Point(int.Parse(coordinates[0]), int.Parse(coordinates[1])));
                }
                //Label0:;

            }
            return points;
        }

        /// <summary>
        /// Получает локаторы элемента по его изображению
        /// </summary>
        /// <param name="driver">WebDriver окна</param>
        /// <param name="patternFile">Путь к файлу с изображением контрола</param>
        /// <param name="similarity">Точность сранвения</param>
        /// <param name="timeout">Таймаут для поиска в мс</param>
        /// <returns></returns>
        public static List<ImageElementLocators> GetElementLocatorsByElementImage(IWebDriver driver, string patternFile, float similarity = 0.85f, int timeout = 10000)
        {
            List<ImageElementLocators> ielList = new List<ImageElementLocators>();
            webDriver = driver;
            List<Point> points = Execute(patternFile, similarity, timeout);
            Size imgSize = GetImageSise(patternFile);
            int WindowOffsetfromScreen = GetScreeWindowOffset();

            foreach (Point point in points)
            {
                Point actualPointToFind = new Point(
                    point.X - imgSize.Width / 2 + 4, point.Y - imgSize.Height / 2 - WindowOffsetfromScreen + 4);
                IWebElement element = GetElementFromPointJS(actualPointToFind);

                var attrs = GetElementAttributesJS(element);
                var xPath = GetElementXPathJS(element);
                var css = GetElementCssJS(element);

                ielList.Add(new ImageElementLocators
                {
                    CssLocator = css,
                    WebElement = element,
                    PattnerCoordinates = point,
                    XPathLocator = xPath,
                    ElementAttributesLocators = attrs
                });
            }

            return ielList;
        }

        /// <summary>
        /// Получает сдвиг окна браузера относительно экрана
        /// </summary>
        /// <returns>Величина сдвига</returns>
        private static int GetScreeWindowOffset()
        {
            return int.Parse(((IJavaScriptExecutor)webDriver).ExecuteScript(
                           "return window.outerHeight - window.innerHeight").ToString());
        }

        /// <summary>
        /// Получает размер изображения
        /// </summary>
        /// <param name="fileName">Путь к файлу</param>
        /// <returns>Размер изображения</returns>
        private static Size GetImageSise(string fileName)
        {
            return Image.FromFile(fileName).Size;
        }

        /// <summary>
        /// Получает WebElement по заданным координатам
        /// </summary>
        /// <param name="point">Кординаты элемента</param>
        /// <returns>WebElement</returns>
        private static IWebElement GetElementFromPointJS(Point point)
        {
            return (IWebElement)((IJavaScriptExecutor)webDriver).ExecuteScript(
                    "return document.elementFromPoint(arguments[0], arguments[1])",
                    point.X, point.Y);
        }

        /// <summary>
        /// Получает атрибуты элемента с помощью JS
        /// </summary>
        /// <param name="webElement">Webelement</param>
        /// <returns>Словарь атрибутов</returns>
        private static Dictionary<string, string> GetElementAttributesJS(IWebElement webElement)
        {
            Dictionary<string, string> result = new Dictionary<string, string>();
            var attributes = ((IJavaScriptExecutor)webDriver).ExecuteScript(
                @"var items = {}; 
                for (index = 0; index < arguments[0].attributes.length; ++index) 
                { 
                    items[arguments[0].attributes[index].name] = arguments[0].attributes[index].value 
                }; 
                return items;", webElement);
            if (attributes != null)
            {
                foreach (var item in (attributes as Dictionary<string, object>))
                {
                    result.Add(item.Key, item.Value.ToString());
                }
            }

            return result;
        }

        /// <summary>
        /// Формирует xPath к элементу с помощью JS
        /// </summary>
        /// <param name="element">WebElement</param>
        /// <returns>Строка с хPath при успешном формировании, иначе ""</returns>
        private static string GetElementXPathJS(IWebElement element)
        {
            string result = "";
            var jsResult = ((IJavaScriptExecutor)webDriver).ExecuteScript(@"
                return getElementXPath(arguments[0]);
                function getElementXPath(element)
                {

                    if (element && element.id)
                        return //*[@id='' + element.id + '\'];
                    else
                        return getElementTreeXPath(element);
                };

                function getElementTreeXPath(element)
                {
                    var paths = [];  // Use nodeName (instead of localName) 
                    // so namespace prefix is included (if any).
                    for (; element && element.nodeType == Node.ELEMENT_NODE; 
                        element = element.parentNode)
                    {
                        var index = 0;
                        var hasFollowingSiblings = false;
                        for (var sibling = element.previousSibling; sibling; 
                            sibling = sibling.previousSibling)
                        {
                            // Ignore document type declaration.
                            if (sibling.nodeType == Node.DOCUMENT_TYPE_NODE)
                                continue;

                            if (sibling.nodeName == element.nodeName)
                                ++index;
                        }

                        for (var sibling = element.nextSibling; 
                            sibling && !hasFollowingSiblings;
                                sibling = sibling.nextSibling)
                        {
                            if (sibling.nodeName == element.nodeName)
                                hasFollowingSiblings = true;
                        }

                        var tagName = (element.prefix ? element.prefix + ':' : '') 
                            + element.localName;
                        var pathIndex = (index || hasFollowingSiblings ? '['
                            + (index + 1) + ']' : '');
                        paths.splice(0, 0, tagName + pathIndex);
                }

            return paths.length ? '/' + paths.join('/') : null;
            };"
            , element);
            if (jsResult == null)
            {
                var jsResult2 = ((IJavaScriptExecutor)webDriver).ExecuteScript(@"
                    var xpath = '';
                    var element = arguments[0];
                    for ( ; element && element.nodeType == 1; element = element.parentNode )
                    {
                        var id = $(element.parentNode).children(element.tagName).index(element) + 1;
                        id > 1 ? (id = '[' + id + ']') : (id = '');
                        xpath = '/' + element.tagName.toLowerCase() + id + xpath;
                    }
                    return xpath;", element);

                if (jsResult2 != null)
                    result = jsResult2.ToString();
            }
            else
                result = jsResult.ToString();

            return result;
        }

        /// <summary>
        /// Формирует css path к элементу с помощью JS
        /// </summary>
        /// <param name="element">WebElement</param>
        /// <returns>Строка с css path при успешном формировании, иначе ""</returns>
        private static string GetElementCssJS(IWebElement element)
        {
            return ((IJavaScriptExecutor)webDriver).ExecuteScript(@"
                return getPath(arguments[0]);
                function previousElementSibling (element) 
                {
                    if (element.previousElementSibling !== 'undefined') 
                    {
                        return element.previousElementSibling;
                    } 
                    else 
                    {
                        // Loop through ignoring anything not an element
                        while (element = element.previousSibling) 
                        {
                            if (element.nodeType === 1) 
                            {
                                return element;
                            }
                        }
                    }
                }
                
                function getPath (element) 
                {
                    // False on non-elements
                    if (!(element instanceof HTMLElement)) { return false; }
                    var path = [];
                    while (element.nodeType === Node.ELEMENT_NODE) 
                    {
                        var selector = element.nodeName;
                        if (element.id) { selector += ('#' + element.id); }
                        else 
                        {
                            // Walk backwards until there is no previous sibling
                            var sibling = element;
                            // Will hold nodeName to join for adjacent selection
                            var siblingSelectors = [];
                            while (sibling !== null && sibling.nodeType === Node.ELEMENT_NODE) 
                            {
                                siblingSelectors.unshift(sibling.nodeName);
                                sibling = previousElementSibling(sibling);
                            }
                            // :first-child does not apply to HTML
                            if (siblingSelectors[0] !== 'HTML') 
                            {
                                siblingSelectors[0] = siblingSelectors[0] + ':first-child';
                            }
                            selector = siblingSelectors.join(' + ');
                    }
                    path.unshift(selector);
                    element = element.parentNode;
                }
                return path.join(' > ');}"
            , element).ToString();
        }
    }

    /// <summary>
    /// Класс локаторов элемента по его изображению
    /// </summary>
    public class ImageElementLocators
    {
        /// <summary>
        /// Координаты центра элемента, найденного по паттрену, относительно экрана
        /// </summary>
        public Point PattnerCoordinates { get; set; }
        /// <summary>
        /// xPath локатор элемента
        /// </summary>
        public string XPathLocator { get; set; }

        /// <summary>
        /// CSS локатор элемента
        /// </summary>
        public string CssLocator { get; set; }

        /// <summary>
        /// Локаторы по атрибтам элемента
        /// </summary>
        public Dictionary<string, string> ElementAttributesLocators { get; set; }

        /// <summary>
        /// WebElement
        /// </summary>
        public IWebElement WebElement { get; set; }
    }
}
