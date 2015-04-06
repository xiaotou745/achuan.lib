using System;
using System.Security.Cryptography;
using System.Text;
using System.Web;

namespace AC.Helper
{
    public class StringHelper
    {
        public static string ConvertDateTimeStamp(System.DateTime time)
        {
            return time.Ticks.ToString();
        }

        /// <summary>
        /// 获得一个唯一字符，16位
        /// </summary>
        /// <returns></returns>
        public static string GuidString()
        {
            long i = 1;
            foreach (byte b in Guid.NewGuid().ToByteArray())
            {
                i *= (b + 1);
            }
            return string.Format("{0:x}", i - DateTime.Now.Ticks);
        }

        //随机生成8未字符不包括 0-o,2-z,l-1
        public static string GetRandomString(int codeLen)
        {
            string codeSerial = "3,4,5,6,7,8,9,a,b,c,d,e,f,g,h,i,j,k,m,n,p,q,r,s,t,u,v,w,x,y";
            string[] arr = codeSerial.Split(',');
            string code = "";
            int randValue = -1;
            Random rand = new Random(unchecked((int) DateTime.Now.Ticks));
            for (int i = 0; i < codeLen; i++)
            {
                randValue = rand.Next(0, arr.Length - 1);
                code += arr[randValue];
            }
            return code;
        }

        /// <summary>
        /// 截断字符串，保留指定字节数
        /// </summary>
        /// <param name="failReason"></param>
        /// <returns></returns>
        public static string TruncateString(string input, uint keepByteCount)
        {
            while (Encoding.GetEncoding("gb2312").GetByteCount(input) > keepByteCount)
            {
                input = input.Remove(input.Length - 1);
            }
            return input;
        }

        /// <summary>
        /// 允许0开头，如果allow_zero是true
        /// </summary>
        /// <param name="random_length"></param>
        /// <param name="alow_zero"></param>
        /// <returns></returns>
        public static string GetRandomNum(int random_length, bool allow_zero)
        {
            int i, p;
            string r_string;

            if (random_length <= 0 || random_length > 20)
                return null;

            Random rd = StringHelper.SYS_RD;

            //再用一个随机因子，时间。。。
            long tick = DateTime.Now.Ticks;
            Random rd2 = new Random((int) (tick & 0xffffffffL) | (int) (tick >> 32));

            if (!allow_zero)
                while ((p = GetNextRandDigit(rd, rd2)) == 0)
                {
                }
            else
                p = GetNextRandDigit(rd, rd2);

            r_string = p.ToString();

            for (i = 0; i < random_length - 1; i++)
            {
                p = GetNextRandDigit(rd, rd2);
                r_string += p.ToString();
            }

            return r_string;
        }

        /// <summary>
        /// 为更强的随机数准备的
        /// </summary>
        /// <param name="rd1"></param>
        /// <param name="rd2"></param>
        /// <returns></returns>
        private static int GetNextRandDigit(Random rd1, Random rd2)
        {
            return (rd1.Next(10) + rd2.Next(10))%10;
        }

        public static Random SYS_RD
        {
            get
            {
                return new Random(GetRandomSeed());

                //if (_SYS_RD == null) //全局共享rd会造成死锁问题
                //    _SYS_RD = new Random(GetRandomSeed());

                //return _SYS_RD;
            }
        }


        private static int GetRandomSeed()
        {
            byte[] bytes = new byte[4];
            System.Security.Cryptography.RNGCryptoServiceProvider rng =
                new System.Security.Cryptography.RNGCryptoServiceProvider();
            rng.GetBytes(bytes);
            return BitConverter.ToInt32(bytes, 0);
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="source"></param>
        /// <param name="length"></param>
        /// <param name="extStr"></param>
        /// <returns></returns>
        public static string SubString(string source, int length, string extStr)
        {
            if (string.IsNullOrEmpty(source))
                return source;
            if (source.Length <= length)
                return source;

            return source.Substring(0, length) + extStr;
        }

        /// <summary>
        /// 
        /// </summary>
        /// <param name="codeLen"></param>
        /// <returns></returns>
        public static string CreateVerifyCode(int codeLen)
        {
            //const string codeSerial = "1,2,3,4,5,6,7,8,9,0,A,B,";
            string codeSerial =
                "0,1,2,3,4,5,6,7,8,9,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z";


            string[] arr = codeSerial.Split(',');

            string code = "";

            int randValue = -1;

            Random rand = new Random(unchecked((int) DateTime.Now.Ticks));

            for (int i = 0; i < codeLen; i++)
            {
                randValue = rand.Next(0, arr.Length - 1);

                code += arr[randValue];
            }

            return code;
        }

        /// <summary>
        /// Gets the PY string.
        /// </summary>
        /// <param name="str">The STR.</param>
        /// <returns></returns>
        public static string GetPYString(string str)
        {
            string tempStr = "";
            foreach (char c in str)
            {
                if (c >= 33 && c <= 126)
                {
//字母和符号原样保留
                    tempStr += c.ToString();
                }
                else
                {
//累加拼音声母
                    tempStr += GetPYChar(c.ToString());
                }
            }
            return tempStr;
        }

        /// <summary>
        /// Gets the PY char.
        /// </summary>
        /// <param name="c">The c.</param>
        /// <returns></returns>
        private static string GetPYChar(string c)
        {
            byte[] array = new byte[2];
            array = System.Text.Encoding.Default.GetBytes(c);
            int i = (short) (array[0] - '\0')*256 + ((short) (array[1] - '\0'));

            if (i < 0xB0A1) return "*";
            if (i < 0xB0C5) return "a";
            if (i < 0xB2C1) return "b";
            if (i < 0xB4EE) return "c";
            if (i < 0xB6EA) return "d";
            if (i < 0xB7A2) return "e";
            if (i < 0xB8C1) return "f";
            if (i < 0xB9FE) return "g";
            if (i < 0xBBF7) return "h";
            if (i < 0xBFA6) return "g";
            if (i < 0xC0AC) return "k";
            if (i < 0xC2E8) return "l";
            if (i < 0xC4C3) return "m";
            if (i < 0xC5B6) return "n";
            if (i < 0xC5BE) return "o";
            if (i < 0xC6DA) return "p";
            if (i < 0xC8BB) return "q";
            if (i < 0xC8F6) return "r";
            if (i < 0xCBFA) return "s";
            if (i < 0xCDDA) return "t";
            if (i < 0xCEF4) return "w";
            if (i < 0xD1B9) return "x";
            if (i < 0xD4D1) return "y";
            if (i < 0xD7FA) return "z";

            return "*";
        }


        /// <summary>
        /// Creates the number.
        /// </summary>
        /// <param name="codeLen">The code len.</param>
        /// <returns></returns>
        public static string CreateNumber(int codeLen)
        {
            const string codeSerial = "1,2,3,4,5,6,7,8,9,0";
            string[] arr = codeSerial.Split(',');

            return CreateRandomStr(codeLen, arr);
        }

        /// <summary>
        /// Creates the password.
        /// </summary>
        /// <param name="codeLen">The code len.</param>
        /// <returns></returns>
        public static string CreatePassword(int codeLen)
        {
            const string codeSerial = "1,2,3,4,5,6,7,8,9,0,a,b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r,s,t,u,v,w,x,y,z";
            string[] arr = codeSerial.Split(',');

            return CreateRandomStr(codeLen, arr);
        }


        /// <summary>
        /// Creates the random STR.
        /// </summary>
        /// <param name="codeLen">The code len.</param>
        /// <param name="baseSerial">The base serial.</param>
        /// <returns></returns>
        public static string CreateRandomStr(int codeLen, string[] baseSerial)
        {
            string code = "";

            int randValue = -1;

            Random rand = new Random(unchecked((int) DateTime.Now.Ticks));

            for (int i = 0; i < codeLen; i++)
            {
                randValue = rand.Next(0, baseSerial.Length - 1);

                code += baseSerial[randValue];
            }

            return code;
        }

        /// <summary>
        /// Gets the friendly date.
        /// </summary>
        /// <param name="dtTime">The dt time.</param>
        /// <returns></returns>
        public static string GetFriendlyTime(DateTime dtTime)
        {
            DateTime dtNow = DateTime.Now;
            TimeSpan tsDiff = dtNow - dtTime;
            string lastDateStr = "";
            if (tsDiff.TotalMinutes <= 1)
            {
                lastDateStr = "<span class='s time'>" + (int) tsDiff.TotalSeconds + "秒前</span>";
            }
            else if (tsDiff.TotalHours <= 1)
            {
                lastDateStr = "<span class='m time'>" + (int) tsDiff.TotalMinutes + "分前</span>";
            }
            else if (tsDiff.TotalHours <= 8)
            {
                lastDateStr = "<span class='h time'>" + (int) tsDiff.TotalHours + "小时前</span>";
            }
            else if (tsDiff.Days == 0 && dtNow.Day == dtTime.Day)
            {
                lastDateStr = "<span class='td time'>今天 " + dtTime.ToString("HH:mm") + "</span>";
            }
            else if (tsDiff.Days <= 1 && dtNow.Day == dtTime.Day + 1)
            {
                lastDateStr = "<span class='yt time'>昨天 " + dtTime.ToString("HH:mm") + "</span>";
            }
            else
            {
                lastDateStr = "<span class='t time'>" + dtTime.ToString("yy-MM-dd hh:mm") + "</span>";
            }
            return lastDateStr; // +dtTime + ":::" + tsDiff.Days;
        }


        public static string GetHostIp()
        {
            return System.Web.HttpContext.Current.Request.ServerVariables.Get("Local_Addr");
        }

        public static string GetHostDomain()
        {
            return System.Web.HttpContext.Current.Request.Url.Host;
        }

        public static string GetClientIP()
        {
            string proxed_ip = (HttpContext.Current.Request.ServerVariables["HTTP_X_FORWARDED_FOR"]);
            if (proxed_ip == null || proxed_ip.Trim() == "")
                return HttpContext.Current.Request.UserHostAddress;

            int last_comma = proxed_ip.LastIndexOf(',');
            if (last_comma == -1)
                return proxed_ip;

            return proxed_ip.Substring(last_comma + 1, (proxed_ip.Length - last_comma - 1)).Trim();
        }

        /**/

        /// <summary>
        /// MD5 16位加密
        /// </summary>
        /// <param name="ConvertString"></param>
        /// <returns></returns>
        public static string GetMd5Str16(string ConvertString)
        {
            MD5CryptoServiceProvider md5 = new MD5CryptoServiceProvider();
            string t2 = BitConverter.ToString(md5.ComputeHash(Encoding.Default.GetBytes(ConvertString)), 4, 8);
            t2 = t2.Replace("-", "");
            return t2;
        }

        /**/

        /// <summary>
        /// MD5　32位加密
        /// </summary>
        /// <param name="str"></param>
        /// <returns></returns>
        public static string MD5KeyEncry(string key)
        {
            return System.Web.Security.FormsAuthentication.HashPasswordForStoringInConfigFile(key, "MD5");
        }

        /// <summary>
        /// 检查字符串是否为空，不为空则将szNot附到o.String()最后再返回，空则什么也不做即返回空
        /// </summary>
        /// <param name="o">转换对象</param>
        /// <param name="szNot">不为空时附加的字符串</param>
        /// <returns></returns>
        public static string NotNullOrEmptyThenAppend(object o, string szNot)
        {
            string szReturn = string.Empty;
            string szValue = o.ToString();
            if (!string.IsNullOrEmpty(szValue))
            {
                szReturn += szValue + szNot;
            }
            return szReturn;
        }

        /// <summary>
        /// 检查字符串是否为空，不为空则将szNot附到最后，空则返回szYes
        /// </summary>
        /// <param name="o">转换对象</param>
        /// <param name="szNot">不为空时附加的字符串</param>
        /// <param name="szYes">为空时返回该值</param>
        /// <returns></returns>
        public static string NotNullOrEmptyThenAppend(object o, string szNot, string szYes)
        {
            string szReturn = string.Empty;
            string szValue = o.ToString();
            if (!string.IsNullOrEmpty(szValue))
            {
                szReturn += szValue + szNot;
            }
            else
            {
                szReturn = szYes;
            }
            return szReturn;
        }

        /// <summary>  
        /// 对字符串进行裁剪  
        /// </summary>  
        public static string Trim(string stringTrim, int maxLength)
        {
            return Trim(stringTrim, maxLength, "...");
        }

        /// <summary>  
        /// 对字符串进行裁剪(区分单字节及双字节字符)  
        /// </summary>  
        /// <param name="rawString">需要裁剪的字符串</param>  
        /// <param name="maxLength">裁剪的长度，按双字节计数</param>  
        /// <param name="appendString">如果进行了裁剪需要附加的字符</param>  
        public static string Trim(string rawString, int maxLength, string appendString)
        {
            if (string.IsNullOrEmpty(rawString) || rawString.Length <= maxLength)
            {
                return rawString;
            }
            else
            {
                int rawStringLength = Encoding.UTF8.GetBytes(rawString).Length;
                if (rawStringLength <= maxLength*2)
                    return rawString;
            }

            int appendStringLength = Encoding.UTF8.GetBytes(appendString).Length;
            StringBuilder checkedStringBuilder = new StringBuilder();
            int appendedLenth = 0;
            for (int i = 0; i < rawString.Length; i++)
            {
                char _char = rawString[i];
                checkedStringBuilder.Append(_char);

                appendedLenth += Encoding.Default.GetBytes(new[] {_char}).Length;

                if (appendedLenth >= maxLength*2 - appendStringLength)
                    break;
            }

            return checkedStringBuilder + appendString;
        }
    }
}