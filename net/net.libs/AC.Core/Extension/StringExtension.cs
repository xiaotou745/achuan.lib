using System;
using System.Collections.Generic;
using System.Linq;

namespace AC.Extension
{
    /// <summary>
    /// string 扩展类
    /// </summary>
    public static class StringExtension
    {
        /// <summary>
        /// 删除字符串最后一个字符
        /// </summary>
        /// <param name="source">源字符串</param>
        /// <returns>返回删除最后一个字符后的结果字符串</returns>
        public static string DeleteLastChar(this string source)
        {
            return source.Substring(0, source.Length - 1);
        }

        #region Format
        
        public static string format(this string source, object arg0)
        {
            return format(source, new[] { arg0 });
        }

        public static string format(this string source, object arg0, object arg1)
        {
            return format(source, new[] { arg0, arg1 });
        }

        public static string format(this string source, object arg0, object arg1, object arg2)
        {
            return format(source, new[] { arg0, arg1, arg2 });
        }

        public static string format(this string source, params object[] args)
        {
            return string.Format(source, args);
        }

        #endregion

        #region ToList

        /// <summary>
        /// 默认分隔字符串 , 
        /// </summary>
        /// <param name="text"></param>
        /// <returns></returns>
        public static List<string> ToList(this string text)
        {
            return ToList(text, ',');
        }

        /// <summary>
        /// 默认分隔字符为,的字符串，转换为int集合
        /// </summary>
        /// <param name="text"></param>
        /// <returns></returns>
        public static List<int> ToNumberList(this string text)
        {
            return ToList(text, ',').ToNumberList();
        }

        /// <summary>
        /// 转换一个字符串为指定字符分隔的List数组
        /// </summary>
        /// <param name="text"></param>
        /// <param name="c"></param>
        /// <returns></returns>
        public static List<string> ToList(this string text, char c)
        {
            if (!string.IsNullOrEmpty(text))
                return text.Split(new[] {c}, StringSplitOptions.RemoveEmptyEntries).ToList();
            else
                return new List<string>();
        }

        #endregion
    }
}