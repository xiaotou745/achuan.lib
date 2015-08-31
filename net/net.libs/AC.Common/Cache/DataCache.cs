using System;
using System.Web;

namespace AC.Cache
{
    /// <summary>
    /// 缓存相关的操作类
    /// Copyright (C) Publish
    /// </summary>
    public class DataCache
    {
        /// <summary>
        /// 获取当前应用程序指定CacheKey的Cache值
        /// </summary>
        /// <param name="CacheKey"></param>
        /// <returns></returns>
        public static object GetCache(string CacheKey)
        {
            System.Web.Caching.Cache objCache = HttpRuntime.Cache;
            return objCache[CacheKey];
        }

        /// <summary>
        /// 设置当前应用程序指定CacheKey的Cache值
        /// </summary>
        /// <param name="cacheKey"></param>
        /// <param name="objObject"></param>
        public static void SetCache(string cacheKey, object objObject)
        {
            System.Web.Caching.Cache objCache = HttpRuntime.Cache;
            objCache.Insert(cacheKey, objObject);
        }

        /// <summary>
        /// 添加绝对过期时间的缓存项
        /// </summary>
        /// <param name="cacheKey"></param>
        /// <param name="objValue"></param>
        /// <param name="absoluteExpiration"></param>
        public static void SetCacheOfAbsolute(string cacheKey, object objValue, DateTime absoluteExpiration)
        {
            System.Web.Caching.Cache objCache = HttpRuntime.Cache;
            objCache.Insert(cacheKey, objValue, null, absoluteExpiration, System.Web.Caching.Cache.NoSlidingExpiration);
        }

        /// <summary>
        /// 添加滑动过期策略的缓存项
        /// </summary>
        /// <param name="cacheKey"></param>
        /// <param name="objValue"></param>
        /// <param name="slidingExpiration"></param>
        public static void SetCacheSliding(string cacheKey, object objValue, TimeSpan slidingExpiration)
        {
            System.Web.Caching.Cache objCache = HttpRuntime.Cache;
            objCache.Insert(cacheKey, objValue, null, System.Web.Caching.Cache.NoAbsoluteExpiration, slidingExpiration);
        }

        /// <summary>
        /// 设置当前应用程序指定CacheKey的Cache值
        /// </summary>
        /// <param name="cacheKey"></param>
        /// <param name="objObject"></param>
        /// <param name="absoluteExpiration"></param>
        /// <param name="slidingExpiration"></param>
        public static void SetCache(string cacheKey, object objObject, DateTime absoluteExpiration,
                                    TimeSpan slidingExpiration)
        {
            System.Web.Caching.Cache objCache = HttpRuntime.Cache;
            objCache.Insert(cacheKey, objObject, null, absoluteExpiration, slidingExpiration);
        }
    }
}