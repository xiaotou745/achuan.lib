using System;
using System.Collections.Generic;
using System.Text;

namespace AC.Extension
{
    /// <summary>
    /// List扩展类
    /// </summary>
    public static class ListExtensions
    {
        /// <summary>
        /// 把一个List<typeparamref name="T"/>使用指定字符拼接成一个字符串
        /// </summary>
        /// <typeparam name="T">类型</typeparam>
        /// <param name="list"></param>
        /// <param name="c">拼接字符</param>
        /// <returns></returns>
        public static string SplitString<T>(this List<T> list, char c)
        {
            var result = new StringBuilder();

            foreach (var str in list)
            {
                result.Append(str.ToString() + c);
            }

            if (list.Count > 0)
                return result.ToString().TrimEnd(c);

            return result.ToString();
        }

        /// <summary>
        /// 把一个string List转换为 int List
        /// </summary>
        /// <param name="stringList">string list</param>
        /// <returns>返回int list结果</returns>
        public static List<int> ToNumberList(this List<string> stringList)
        {
            var result = new List<int>();
            foreach (var item in stringList)
            {
                result.Add(Convert.ToInt32(item));
            }
            return result;
        }
    }
}