using System;
using System.Web;

namespace AC.Helper
{
    public class CookieHelper
    {
        #region Private Methods

        /// <summary>
        /// 获取客户端发送的Cookie的集合
        /// </summary>
        private static HttpCookieCollection RequestCookies
        {
            get { return HttpContext.Current.Request.Cookies; }
        }

        /// <summary>
        /// 获取响应的Cookie的集合
        /// </summary>
        private static HttpCookieCollection ResponseCookies
        {
            get { return HttpContext.Current.Response.Cookies; }
        }

        #endregion

        private const string DOMAIN = "wychuan.com";

        #region Set Cookie

        public static void Set(string cookieName, object value)
        {
            Set(cookieName, value, null);
        }

        /// <summary>
        /// 设置超时时间
        /// </summary>
        /// <param name="cookieName"></param>
        /// <param name="expires"></param>
        public static void SetExpires(string cookieName, TimeSpan expires)
        {
            HttpCookie cookie = ResponseCookies[cookieName];
            if (cookie == null)
            {
                return;
            }
            cookie.Expires = DateTime.Now.Add(expires);
            ResponseCookies.Add(cookie);
        }

        public static void Set(string cookieName, object value, TimeSpan? expires)
        {
            if (!expires.HasValue)
            {
                expires = TimeSpan.FromDays(7);
            }

            string cookieValue = Json.JsonSerializer.Serialize(value);
            cookieValue = Security.DES.Encrypt(cookieValue);

            HttpCookie cookie = new HttpCookie(cookieName);
            if (HttpContext.Current.Request.Url.Host.ToLower().Contains(DOMAIN))
            {
                cookie.Domain = "." + DOMAIN;
            }
            cookie.Value = cookieValue;
            cookie.Expires = DateTime.Now.Add(expires.Value);
            ResponseCookies.Add(cookie);
        }

        #endregion

        #region Get

        public static T Get<T>(string cookieName) where T : class
        {
            T t = null;

            HttpCookie cookie = RequestCookies[cookieName];
            if (cookie != null)
            {
                string cookieValue = cookie.Value;
                t = Json.JsonSerializer.Deserialize<T>(Security.DES.Decrypt(cookieValue));
            }

            return t;
        }

        #endregion

        #region Remove

        public static void Remove(string cookieName)
        {
            HttpCookie cookie = new HttpCookie(cookieName);
            if (HttpContext.Current.Request.Url.Host.ToLower().IndexOf(DOMAIN) > -1)
            {
                cookie.Domain = "." + DOMAIN;
            }
            cookie.Expires = DateTime.Now.AddDays(-1);
            cookie.Value = "";
            HttpContext.Current.Response.Cookies.Add(cookie);
        }

        #endregion
    }
}