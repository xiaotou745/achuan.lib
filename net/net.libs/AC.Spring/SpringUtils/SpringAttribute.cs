using System;

namespace AC.SpringUtils
{
    public class SpringAttribute : Attribute
    {
        public SpringAttribute()
        {
            Id = "";
            IsSingleton = true;
            IsLazyInit = true;
            ConstructorArgs = "";
        }

        /// <summary>
        /// ID
        /// </summary>
        public string Id { get; set; }

        /// <summary>
        /// 是否单例
        /// </summary>
        public bool IsSingleton { get; set; }

        /// <summary>
        /// 是否懒加载
        /// </summary>
        public bool IsLazyInit { get; set; }

        /// <summary>
        /// 实例化时构造参数。
        /// </summary>
        public string ConstructorArgs { get; set; }
    }

    public class SpringSetting
    {
        public string AssemblyName { get; set; }
    }
}