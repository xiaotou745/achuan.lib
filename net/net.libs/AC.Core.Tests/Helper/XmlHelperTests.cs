using System.Collections.Generic;
using System.Net.Mime;
using AC.Helper;
using AC.Json;
using Common.Logging;
using NUnit.Framework;

namespace AC.Core.Tests.Helper
{
    [TestFixture]
    public class XmlHelperTests
    {
        private ILog logger = LogManager.GetCurrentClassLogger();
        private string categoryFilePath;

        [TestFixtureSetUp]
        public void FixtureSetUp()
        {
            categoryFilePath = System.Environment.CurrentDirectory + @"\categories.xml";
        }

        [Test]
        public void ToXmlTest()
        {
            var categories = Category.GetCategories();
            //logger.Info(System.Diagnostics.Process.GetCurrentProcess().MainModule.FileName);

            logger.Info(System.Environment.CurrentDirectory);
            XmlHelper.ToXml(categoryFilePath, categories);
        }

        [Test]
        public void ToObjectTest()
        {
            var categories = XmlHelper.ToObject<List<Category>>(categoryFilePath);

            logger.Info(JsonSerializer.Serialize(categories));
        }
    }

    public class Category
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public int ParentId { get; set; }
        private List<Category> childs = new List<Category>();

        public List<Category> Childs
        {
            get { return childs; }
            set { childs = value; }
        }

        public static IList<Category> GetCategories()
        {
            IList<Category> result = new List<Category>();

            Category categoryOfNet = new Category();
            categoryOfNet.Id = 1;
            categoryOfNet.Name = ".Net技术";
            categoryOfNet.ParentId = 0;

            Category categoryChild1 = new Category();
            categoryChild1.Id = 11;
            categoryChild1.Name = "NuGet";
            categoryChild1.ParentId = 1;

            categoryOfNet.Childs = new List<Category>() {categoryChild1};

            result.Add(categoryOfNet);

            Category categoryOfJava = new Category();
            categoryOfJava.Id = 2;
            categoryOfJava.Name = "Java技术";
            categoryOfJava.ParentId = 0;

            result.Add(categoryOfJava);

            return result;
        }
    }
}