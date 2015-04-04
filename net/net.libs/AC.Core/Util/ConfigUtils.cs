using System;
using System.Collections.Generic;
using System.Configuration;
using System.Globalization;
using System.Linq;
using Common.Logging;

namespace AC.Util
{
    /// <summary>
    /// 配置文件 AppSettings 获取类
    /// </summary>
    public class ConfigUtils
    {
        /// <summary>
        /// 日志对象
        /// </summary>
        private static readonly ILog Logger = LogManager.GetCurrentClassLogger();

        /// <summary>
        /// 获取appSetting 配置节指定key的value
        /// </summary>
        /// <typeparam name="T">数据类型</typeparam>
        /// <param name="key">key</param>
        /// <param name="defaule">默认值，如果读取不到key，则返回此默认值</param>
        /// <returns></returns>
        public static T GetConfigValue<T>(string key, T defaule) where T : class
        {
            AssertUtils.StringNotNullOrEmpty(key, "key");

            T result = defaule;
            try
            {
                //是否含有此key
                if (Contains(ConfigurationManager.AppSettings.AllKeys, key))
                {
                    string obj = ConfigurationManager.AppSettings[key];
                    if (obj != null && obj.Trim() != "")
                    {
                        if (typeof (T) == typeof (int))
                        {
                            result = int.Parse(obj.Trim()).ToString(CultureInfo.InvariantCulture) as T;
                        }
                        else if (typeof (T) == typeof (bool))
                        {
                            result = bool.Parse(obj.Trim()).ToString(CultureInfo.InvariantCulture) as T;
                        }
                        else if (typeof (T) == typeof (string))
                        {
                            result = obj.Trim() as T;
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                Logger.Info(key + "没有读取到", ex);
                result = defaule;
            }
            return result;
        }

        private static bool Contains(IEnumerable<string> appSettings, string key)
        {
            AssertUtils.ArgumentNotNull(appSettings, "appSettings");
            AssertUtils.StringNotNullOrEmpty(key, "key");

            return appSettings.Contains(key);
        }
    }
}