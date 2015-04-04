using System.Web.Script.Serialization;

namespace AC.Json
{
    /// <summary>
    /// Json帮助类，基于<see cref="System.Web.Script.Serialization.JavaScriptSerializer"/>实现
    /// </summary>
    public class JsonSerializer
    {
        /// <summary>
        /// 将对象转换为Json字符串
        /// </summary>
        /// <param name="obj">要序列化的对象。</param>
        /// <returns>序列化的 JSON 字符串。</returns>
        /// <exception cref="System.InvalidOperationException">
        /// 所生成的 JSON 字符串超出了 System.Web.Script.Serialization.JavaScriptSerializer.MaxJsonLength
        /// 的值。- 或 -obj 包含循环引用。循环引用出现在子对象引用父对象，而父对象又引用子对象时。
        /// </exception>
        /// <exception cref="System.ArgumentException">
        /// 超出了由 System.Web.Script.Serialization.JavaScriptSerializer.RecursionLimit定义的递归限制。
        /// </exception>
        public static string Serialize(object obj)
        {
            JavaScriptSerializer serializer = new JavaScriptSerializer();
            return serializer.Serialize(obj);
        }

        /// <summary>
        /// 将指定的 JSON 字符串转换为 T 类型的对象。
        /// </summary>
        /// <typeparam name="T">所生成对象的类型。</typeparam>
        /// <param name="input">要进行反序列化的 JSON 字符串。</param>
        /// <returns>反序列化的对象。</returns>
        /// <exception cref="System.ArgumentNullException">
        /// input 为 null。
        /// </exception>
        /// <exception cref="System.ArgumentException">
        ///  input 长度超出了 System.Web.Script.Serialization.JavaScriptSerializer.MaxJsonLength
        ///  的值。- 或 -超出了由 System.Web.Script.Serialization.JavaScriptSerializer.RecursionLimit
        ///  定义的递归限制。- 或 -input 包含意外的字符序列。- 或 -input 是字典类型但遇到一个非字符串键值。- 或 -input 包括不可用于
        ///  T 类型的成员定义。
        /// </exception>
        /// <exception cref="System.InvalidOperationException">
        /// input 包含指示自定义类型的“__type”属性，但是与序列化程序关联的类型解析程序找不到相应的托管类型。- 或 -input 包含指示自定义类型的“__type”属性，但是反序列化相应
        /// JSON 字符串的结果无法分配给所需的目标类型。- 或 -input 包含指示 System.Object 或不可实例化的类型（例如，抽象类型或接口）的“__type”属性。-
        /// 或 -尝试将 JSON 数组转换为类似数组的托管类型，该类型不可用作 JSON 反序列化目标。- 或 -不能将 input 转换为 T。
        /// </exception>
        public static T Deserialize<T>(string input) where T : class
        {
            JavaScriptSerializer serializer = new JavaScriptSerializer();

            return serializer.Deserialize<T>(input);
        }
    }
}