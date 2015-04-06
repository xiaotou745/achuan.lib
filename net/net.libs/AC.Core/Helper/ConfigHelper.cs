using System;
using System.Collections.Generic;
using System.Configuration;
using System.Globalization;
using System.Linq;
using AC.Util;
using Common.Logging;

namespace AC.Helper
{
    /// <summary>
    /// 配置文件 AppSettings 获取类
    /// </summary>
    public static class ConfigHelper
    {
        /// <summary>
        /// 日志对象
        /// </summary>
        private static readonly ILog Logger = LogManager.GetCurrentClassLogger();

        /// <summary>
        /// 获取应用程序配置节中的字符串
        /// </summary>
        /// <param name="key"></param>
        /// <param name="defaultValue"></param>
        /// <returns></returns>
        public static string GetConfigString(string key, string defaultValue)
        {
            AssertUtils.StringNotNullOrEmpty(key, "key");

            if (Contains(ConfigurationManager.AppSettings.AllKeys, key))
            {
                return ConfigurationManager.AppSettings[key] ?? defaultValue;
            }
            return defaultValue;
        }

        /// <summary>
        /// 获取应用程序配置节中的整数
        /// </summary>
        /// <param name="key"></param>
        /// <param name="defaultValue"></param>
        /// <returns></returns>
        public static int GetConfigInt(string key, int defaultValue)
        {
            var settingValue = GetConfigString(key, defaultValue.ToString(CultureInfo.InvariantCulture));
            return ParseHelper.ToInt(settingValue, defaultValue);
        }

        /// <summary>
        /// 获取应用程序配置节中的double值
        /// </summary>
        /// <param name="key"></param>
        /// <param name="defaultValue"></param>
        /// <returns></returns>
        public static double GetConfigDouble(string key, double defaultValue)
        {
            var settingValue = GetConfigString(key, defaultValue.ToString());
            return ParseHelper.ToDouble(settingValue, defaultValue);
        }

        public static DateTime GetConfigDateTime(string key, DateTime defaultValue)
        {
            var settingValue = GetConfigString(key, string.Empty);
            return ParseHelper.ToDatetime(settingValue, defaultValue);
        }

        public static decimal GetConfigDecimal(string key, decimal defaultValue)
        {
            var settingValue = GetConfigString(key, defaultValue.ToString());
            return ParseHelper.ToDecimal(settingValue, defaultValue);
        }

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
                        else if (typeof (T) == typeof (decimal))
                        {
                            result = decimal.Parse(obj.Trim()).ToString() as T;
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