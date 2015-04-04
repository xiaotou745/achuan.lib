using System.Collections.Generic;
using System.Text;
using AC.Extension;
using NUnit.Framework;

namespace AC.Core.Tests.Extension
{
    /// <summary>
    /// string 扩展
    /// </summary>
    [TestFixture]
    public class StringExtensionTest
    {
        #region Delete Last Char

        [Test]
        public void DeleteLastCharTest()
        {
            StringBuilder sb = new StringBuilder();
            for (var i = 1; i <= 10; i++)
            {
                sb.Append(i + ",");
            }

            string str = sb.ToString();
            string deleteLastChar = str.DeleteLastChar();
            Assert.AreEqual(str.Substring(0, str.Length - 1), deleteLastChar);
        }

        #endregion

        #region String Format

        [Test]
        public void FormatTest()
        {
            string format1 = "hello,{0}";
            string format2 = "hello,{0},{1}";
            string format3 = "hello,{0},{1},{2}";
            Assert.AreEqual(string.Format(format1, "achuan"), format1.format("achuan"));
            Assert.AreEqual(string.Format(format2, "a", "b"), format2.format("a", "b"));
            Assert.AreEqual(string.Format(format3, "a", "b", "c"), format3.format("a", "b", "c"));
        }

        #endregion

        #region toList
        [Test]
        public void ToListTest()
        {
            string str1 = string.Empty, str2 = string.Empty;
            for (var i = 0; i < 10; i++)
            {
                str1 += i + ",";
                str2 += i + ";";
            }

            List<string> list1 = str1.ToList(',');
            Assert.AreEqual(10, list1.Count);
            List<string> list2 = str2.ToList(';');
            Assert.AreEqual(10, list2.Count);

            List<string> list3 = str1.ToList();
            Assert.AreEqual(10, list3.Count);

            List<int> numberList = str1.ToNumberList();
            Assert.AreEqual(10, numberList.Count);
        }
        #endregion
    }
}