using System;

namespace AC.Helper
{
    /// <summary>
    /// 常用数据类型转换类
    /// </summary>
    public static class ConvertHelper
    {
        /// <summary>
        /// 将字符串转换到一个指定的类型
        /// 目前支持Int32,Int64,Bool,Short
        /// </summary>
        /// <param name="s"></param>
        /// <param name="objType"></param>
        /// <returns></returns>
        public static object ToType(string s, System.Type objType)
        {
            if (objType == typeof (Int32))
                return Int32.Parse(s);
            if (objType == typeof (Int64))
                return Int64.Parse(s);
            if (objType == typeof (bool))
                return bool.Parse(s);
            if (objType == typeof (short))
                return short.Parse(s);
            return s;
        }

        /// <summary>
        /// 转换 Int32，如果格式正确，就返回parse的结果，不能，就返回缺省值
        /// </summary>
        /// <param name="obj"></param>
        /// <param name="defaultValue"></param>
        /// <returns></returns>
        public static Int32 ZParseInt32(object obj, Int32 defaultValue)
        {
            if (obj == null)
                return defaultValue;

            string s = Convert.ToString(obj);

            Int32 x = 0;
            if (Int32.TryParse(s, out x))
                return x;

            return defaultValue;
        }

        /// <summary>
        /// 转换 UInt32，如果格式正确，就返回parse的结果，不能，就返回缺省值
        /// </summary>
        /// <param name="obj"></param>
        /// <param name="defaultValue"></param>
        /// <returns></returns>
        public static UInt32 ZParseUInt32(object obj, UInt32 defaultValue)
        {
            if (obj == null)
                return defaultValue;

            string s = Convert.ToString(obj);
            UInt32 x = 0;
            if (UInt32.TryParse(s, out x))
                return x;

            return defaultValue;
        }


        /// <summary>
        /// 转换UInt64，如果能，就返回parse的结果，不能，就返回缺省值
        /// </summary>
        /// <param name="obj"></param>
        /// <param name="defaultValue"></param>
        /// <returns></returns>
        public static Int64 ZParseInt64(object obj, Int64 defaultValue)
        {
            if (obj == null)
                return defaultValue;

            string s = Convert.ToString(obj);

            Int64 x = 0;
            if (Int64.TryParse(s, out x))
                return x;

            return defaultValue;
        }

        /// <summary>
        /// 如果能，就返回parse的结果，不能，就返回缺省值
        /// </summary>
        /// <param name="obj"></param>
        /// <param name="defaultValue"></param>
        /// <returns></returns>
        public static UInt64 ZParseUInt64(string obj, UInt64 defaultValue)
        {
            if (obj == null)
                return defaultValue;

            string s = Convert.ToString(obj);

            UInt64 x = 0;
            if (UInt64.TryParse(s, out x))
                return x;

            return defaultValue;
        }

        /// <summary>
        /// 如果能就返回Parse的值，不能就返回缺省值
        /// </summary>
        /// <param name="obj"></param>
        /// <param name="defaultValue"></param>
        /// <returns></returns>
        public static DateTime ZParseDateTime(string obj, DateTime defaultValue)
        {
            if (obj == null)
                return defaultValue;

            string s = Convert.ToString(obj);

            DateTime x = DateTime.MinValue;
            if (DateTime.TryParse(s, out x))
                return x;

            return defaultValue;
        }


        /// <summary>
        /// True或者TRUE会被认为是true，其他的都是false 
        /// </summary>
        /// <param name="obj"></param>
        /// <param name="defaultValue"></param>
        public static bool ZParseBool(object obj, bool defaultValue)
        {
            if (obj == null)
                return defaultValue;

            string s = Convert.ToString(obj);

            bool x = true;
            if (bool.TryParse(s, out x))
                return x;

            return defaultValue;
        }


        /// <summary>
        /// True或者TRUE会被认为是true，其他的都是false 
        /// </summary>
        /// <param name="s"></param>
        /// <param name="obj"></param>
        /// <param name="defaultValue"></param>
        public static double ZParseDouble(object obj, double defaultValue)
        {
            if (obj == null)
                return defaultValue;

            string s = Convert.ToString(obj);
            double x = 0.0;
            if (double.TryParse(s, out x))
                return x;

            return defaultValue;
        }


        /// <summary>
        /// 根据数据位数来获得一个上限值
        /// </summary>
        /// <param name="number"></param>
        /// <returns></returns>
        public static int GetNumberDigit(double number)
        {
            double num = number;
            int digit = 0;
            while (number >= 1)
            {
                digit++;
                number /= 10;
            }
            double pownumber = Math.Pow(10, digit - 1);
            double firstnum = Math.Round((num/pownumber), 1);
            //当小于1的时候
            if (pownumber < 1)
            {
                return 1;
            }
            return ZParseInt32((firstnum + 0.1)*pownumber, 0);
        }
    }
}