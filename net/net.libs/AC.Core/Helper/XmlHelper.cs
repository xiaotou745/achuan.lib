using System;
using System.IO;
using System.Xml.Serialization;

namespace AC.Helper
{
    /// <summary>
    /// Xml 帮助类，主要用于对象与xml文件之间的互相序列化
    /// </summary>
    public class XmlHelper
    {
        public static void ToXml(string fileName, object obj)
        {
            using (FileStream stream = new FileStream(fileName, FileMode.Create, FileAccess.ReadWrite))
            {
                //得到被序列化的类型
                Type type = obj.GetType();
                XmlSerializer sz = new XmlSerializer(type);
                //开始序列化
                sz.Serialize(stream, obj);
            }
        }

        /// <summary>
        /// 反序列化xml文件为.net对象
        /// </summary>
        /// <typeparam name="T">c#对象类型</typeparam>
        /// <param name="fileName">xml文件路径</param>
        /// <returns>返回xml文件反序列化之后的c#对象</returns>
        public static T Deserialize<T>(string fileName) where T : class
        {
            try
            {
                using (FileStream fileStream = new FileStream(fileName, FileMode.Open, FileAccess.Read,
                                                              FileShare.ReadWrite))
                {
                    XmlSerializer xmlSerializer = new XmlSerializer(typeof (T));
                    return xmlSerializer.Deserialize(fileStream) as T;
                }
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        /// <summary>
        /// 反序列化xml文件为.net对象
        /// </summary>
        /// <typeparam name="T">c#对象类型</typeparam>
        /// <param name="fileName">xml文件路径</param>
        /// <returns>返回xml文件反序列化之后的c#对象</returns>
        public static T ToObject<T>(string fileName) where T : class
        {
            using (FileStream fileStream = new FileStream(fileName, FileMode.OpenOrCreate, FileAccess.ReadWrite))
            {
                var sr = new System.IO.StreamReader(fileStream);
                string xmlString = sr.ReadToEnd();
                XmlSerializer sz = new XmlSerializer(typeof (T));
                sr.Close();
                return sz.Deserialize(new StringReader(xmlString)) as T;
            }
        }
    }
}