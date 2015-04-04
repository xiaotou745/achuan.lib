using System;
using System.Collections.Generic;
using System.Web.Script.Serialization;

namespace AC
{
    public class JsonHelper
    {
        public static String ToJson(object obj)
        {
            JavaScriptSerializer serializer = new JavaScriptSerializer();
            serializer.MaxJsonLength = int.MaxValue;
            return serializer.Serialize(obj);
        }

        public static T ToObject<T>(string json) where T : class
        {
            JavaScriptSerializer serializer = new JavaScriptSerializer();
            serializer.MaxJsonLength = int.MaxValue;

            return serializer.Deserialize<T>(json);
        }
        public static List<T> JSONStringToList<T>(string JsonStr)
        {
            JavaScriptSerializer Serializer = new JavaScriptSerializer();
            Serializer.MaxJsonLength = int.MaxValue;

            List<T> objs = Serializer.Deserialize<List<T>>(JsonStr);

            return objs;
        } 
    }
}
