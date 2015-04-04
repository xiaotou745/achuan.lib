using System.Collections.Generic;
using AC.Extension;
using NUnit.Framework;

namespace AC.Core.Tests.Extension
{
    [TestFixture]
    public class ListExtensionsTest
    {
        [Test]
        public void ToNumberListTest()
        {
            List<string> lstString = new List<string>();
            string s = string.Empty;
            for (var i = 0; i < 10; i++)
            {
                lstString.Add(i.ToString());
                s += i + ",";
            }

            List<int> numberList = lstString.ToNumberList();
            Assert.AreEqual(10, numberList.Count);

            string splitString = lstString.SplitString(',');
            Assert.AreEqual(s.DeleteLastChar(), splitString);
        }
    }
}