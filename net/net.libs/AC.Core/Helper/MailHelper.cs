#region

using System;
using System.Collections.Generic;
using System.IO;
using System.Net;
using System.Net.Mail;
using System.Net.Mime;
using System.Reflection;
using System.Text;
using Common.Logging;

#endregion

namespace AC.Helper
{
    /// <summary>
    /// 邮件发送类
    /// </summary>
    public class MailHelper
    {
        /// <summary>
        /// 邮件发送服务器
        /// </summary>
        public const string SMTP_SERVER = "smtp.exmail.qq.com";

        public const string DEFAULT_SEND_USER = "system@beimai.com";
        public const string DEFAULT_SEND_USER_PASSWD = "bm2014";
        public const string DEFAULT_SEND_FROM = "system@beimai.com";
        public const bool DEFAULT_ENABLE_SSL = true;

        protected static readonly ILog logger = LogManager.GetLogger(typeof (MailHelper));

        #region IMail Members

        /// <summary>
        /// 发送邮件
        /// </summary>
        /// <param name="mailServer">服务器地址</param>
        /// <param name="credential">NetworkCredential</param>
        /// <param name="mail">MailMessage</param>
        /// <param name="enableSsl"></param>
        public static void SendMail(string mailServer, NetworkCredential credential, MailMessage mail, bool enableSsl = false)
        {
            var sc = new SmtpClient(mailServer)
                {
                    Credentials = credential,
                    DeliveryMethod = SmtpDeliveryMethod.Network
                };
            if (enableSsl)
            {
                sc.EnableSsl = true;
            }
            sc.SendAsync(mail, null);
        }

        /// <summary>
        /// 发送邮件
        /// </summary>
        /// <param name="mailServer">服务器地址</param>
        /// <param name="port">端口</param>
        /// <param name="credential">NetworkCredential</param>
        /// <param name="mail">MailMessage</param>
        public static void SendMail(string mailServer, int port, NetworkCredential credential, MailMessage mail, bool enableSsl = false)
        {
            var sc = new SmtpClient(mailServer, port)
                {
                    Credentials = credential,
                    DeliveryMethod = SmtpDeliveryMethod.Network
                };
            if (enableSsl)
            {
                sc.EnableSsl = true;
            }
            sc.SendAsync(mail, null);
        }
        public static void SendMail(string subject, string body, string mailAddress)
        {
            var mail = new MailMessage();
            var fromAddress = new MailAddress(DEFAULT_SEND_FROM);
            mail.From = fromAddress;
            mail.Subject = subject;
            mail.Body = body;
            string[] arrAddress = mailAddress.Split(';');
            foreach (string address in arrAddress)
            {
                if (!string.IsNullOrEmpty(address))
                {
                    mail.To.Add(address);
                }
            }
            mail.IsBodyHtml = true;
            mail.SubjectEncoding = Encoding.GetEncoding("gb2312");
            mail.BodyEncoding = Encoding.GetEncoding("gb2312");

            var credential = new NetworkCredential(DEFAULT_SEND_USER, DEFAULT_SEND_USER_PASSWD);
            SendMail(SMTP_SERVER, credential, mail, DEFAULT_ENABLE_SSL);
        }
        /// <summary>
        /// 发送邮件
        /// </summary>
        /// <param name="mailServer">邮件服务器地址</param>
        /// <param name="mailUser">发件人帐号</param>
        /// <param name="mailPwd">发件人密码</param>
        /// <param name="mailAddress">收件人Email地址(多个以分号隔开)</param>
        /// <param name="mailFrom">发件人Email地址</param>
        /// <param name="subject">标题</param>
        /// <param name="body">正文</param>
        /// <param name="isHtml">正文是否HTML格式</param>
        public static void SendMail(string mailServer, string mailUser, string mailPwd, string mailAddress, string mailFrom,
                                    string subject, string body, bool isHtml)
        {
            var mail = new MailMessage();
            var fromAddress = new MailAddress(mailFrom);
            mail.From = fromAddress;
            mail.Subject = subject;
            mail.Body = body;
            string[] arrAddress = mailAddress.Split(';');
            foreach (string address in arrAddress)
            {
                if (!string.IsNullOrEmpty(address))
                {
                    mail.To.Add(address);
                }
            }
            mail.IsBodyHtml = isHtml;
            mail.SubjectEncoding = Encoding.GetEncoding("gb2312");
            mail.BodyEncoding = Encoding.GetEncoding("gb2312");

            var credential = new NetworkCredential(mailUser, mailPwd);
            SendMail(mailServer, credential, mail, DEFAULT_ENABLE_SSL);
        }

        /// <summary>
        /// 发送邮件
        /// </summary>
        /// <param name="mailServer">邮件服务器地址</param>
        /// <param name="mailUser">发件人帐号</param>
        /// <param name="mailPwd">发件人密码</param>
        /// <param name="mailAddress">收件人Email地址(多个以分号隔开)</param>
        /// <param name="mailFrom">发件人Email地址</param>
        /// <param name="subject">标题</param>
        /// <param name="body">正文</param>
        public static void SendMail(string mailServer, string mailUser, string mailPwd, string mailAddress, string mailFrom,
                                    string subject, string body)
        {
            SendMail(mailServer, mailUser, mailPwd, mailAddress, mailFrom, subject, body, false);
        }

        /// <summary>
        /// 发送邮件
        /// </summary>
        /// <param name="mailServer">邮件服务器地址</param>
        /// <param name="mailUser">发件人帐号</param>
        /// <param name="mailPwd">发件人密码</param>
        /// <param name="mailAddress">收件人Email地址(多个以分号隔开)</param>
        /// <param name="mailFrom">发件人Email地址</param>
        /// <param name="cc">抄送</param>
        /// <param name="bcc">密送</param>
        /// <param name="subject">标题</param>
        /// <param name="body">正文</param>
        public static void SendMail(string mailServer, string mailUser, string mailPwd, string mailAddress, string cc, string bcc,
                                    string mailFrom,
                                    string subject, string body)
        {
            SendMail(mailServer, mailUser, mailPwd, mailAddress, cc, bcc, mailFrom, subject, body, false);
        }

        /// <summary>
        /// 发送邮件
        /// </summary>
        /// <param name="mailServer">邮件服务器地址</param>
        /// <param name="port">端口</param>
        /// <param name="mailUser">发件人帐号</param>
        /// <param name="mailPwd">发件人密码</param>
        /// <param name="mailAddress">收件人Email地址(多个以分号隔开)</param>
        /// <param name="mailFrom">发件人Email地址</param>
        /// <param name="subject">标题</param>
        /// <param name="body">正文</param>
        /// <param name="isHtml"></param>
        public static void SendMail(string mailServer, int port, string mailUser, string mailPwd, string mailAddress, string mailFrom,
                                    string subject, string body, bool isHtml)
        {
            var mail = new MailMessage();
            var fromAddress = new MailAddress(mailFrom);
            mail.From = fromAddress;
            mail.Subject = subject;
            mail.Body = body;
            string[] arrAddress = mailAddress.Split(';');
            foreach (string address in arrAddress)
            {
                mail.To.Add(address);
            }
            mail.IsBodyHtml = isHtml;
            mail.SubjectEncoding = Encoding.GetEncoding("gb2312");
            mail.BodyEncoding = Encoding.GetEncoding("gb2312");
            var credential = new NetworkCredential(mailUser, mailPwd);
            SendMail(mailServer, port, credential, mail, DEFAULT_ENABLE_SSL);
        }


        /// <summary>
        /// 发送带多个附件的邮件
        /// 发送带附件邮件邮件
        /// </summary>
        /// <param name="mailPwd"></param>
        /// <param name="mailAddress"></param>
        /// <param name="mailFrom"></param>
        /// <param name="mailSubject"></param>
        /// <param name="mailBody"></param>
        /// <param name="filePathes">附件地址列表</param>
        /// <param name="mailServer"></param>
        /// <param name="mailUser"></param>
        public static void SendMailByAttachments(string mailServer, string mailUser, string mailPwd,
                                                 string mailAddress, string mailFrom, string mailSubject, string mailBody,
                                                 IList<string> filePathes, bool enableSsl = false)
        {
            var mail = new MailMessage();
            var fromAddress = new MailAddress(mailFrom);
            mail.From = fromAddress;
            string[] arrTo = mailAddress.Split(';');
            foreach (string t in arrTo)
            {
                mail.To.Add(new MailAddress(t));
            }
            mail.Subject = mailSubject;
            mail.Body = mailBody;
            mail.IsBodyHtml = true;

            foreach (string path in filePathes)
            {
                mail.Attachments.Add(new Attachment(path));
            }

            mail.SubjectEncoding = Encoding.GetEncoding("gb2312");
            mail.BodyEncoding = Encoding.GetEncoding("gb2312");
            var credential = new NetworkCredential(mailUser, mailPwd);
            SendMail(mailServer, credential, mail, enableSsl);
        }

        /// <summary>
        /// 发送带多个附件的邮件
        /// 发送带附件邮件邮件
        /// </summary>
        /// <param name="mailContent"></param>
        /// <param name="mailAddress"></param>
        /// <param name="mailSubject"></param>
        /// <param name="fileStreams"></param>
        public static void SendMailByAttachments(string mailSubject, string mailContent, string mailAddress, IList<Stream> fileStreams,
                                                 bool enableSsl = false)
        {
            var mail = new MailMessage();
            var fromAddress = new MailAddress(DEFAULT_SEND_FROM);
            mail.From = fromAddress;
            string[] arrTo = mailAddress.Split(';');
            foreach (string t in arrTo)
            {
                mail.To.Add(new MailAddress(t));
            }
            mail.Subject = mailSubject;
            mail.Body = mailContent;
            mail.IsBodyHtml = true;

            foreach (Stream mFile in fileStreams)
            {
                string fileName;
                PropertyInfo fileNmPro = mFile.GetType().GetProperty("FileNm");
                if (fileNmPro != null)
                {
                    fileName = fileNmPro.GetValue(mFile, null).ToString();
                }
                else
                {
                    fileName = "Attachment.xls";
                }
                var fileAttachment = new Attachment(mFile, fileName) {TransferEncoding = TransferEncoding.Base64};
                mail.Attachments.Add(fileAttachment);
            }

            mail.SubjectEncoding = Encoding.GetEncoding("gb2312");
            mail.BodyEncoding = Encoding.GetEncoding("gb2312");
            var credential = new NetworkCredential(DEFAULT_SEND_USER, DEFAULT_SEND_USER_PASSWD);
            var sc = new SmtpClient(SMTP_SERVER) {Credentials = credential, DeliveryMethod = SmtpDeliveryMethod.Network};
            if (enableSsl)
            {
                sc.EnableSsl = true;
            }
            sc.Send(mail);
        }


        public static void SendMailByPictures(string to, string mailSubject, string mailBody, List<string> filePathes, bool enableSSL = false)
        {
            var mail = new MailMessage();
            var fromAddress = new MailAddress(DEFAULT_SEND_FROM);
            mail.From = fromAddress;
            string[] arrTo = to.Split(';');
            foreach (string t in arrTo)
            {
                mail.To.Add(new MailAddress(t));
            }
            mail.Subject = mailSubject;

            //mail.AlternateViews.Add(AlternateView.CreateAlternateViewFromString("图片", null, "text/plain"));

            AlternateView htmlBody = AlternateView.CreateAlternateViewFromString(mailBody + "<img src=\"cid:report\">", null, "text/html");
            var lrImage = new LinkedResource(filePathes[0], "image/gif");
            lrImage.ContentId = "report";
            htmlBody.LinkedResources.Add(lrImage);
            mail.AlternateViews.Add(htmlBody);

            mail.Body = mailBody;
            mail.IsBodyHtml = true;

            mail.SubjectEncoding = Encoding.GetEncoding("gb2312");
            mail.BodyEncoding = Encoding.GetEncoding("gb2312");
            var credential = new NetworkCredential(DEFAULT_SEND_USER, DEFAULT_SEND_USER_PASSWD);
            var sc = new SmtpClient(SMTP_SERVER) {Credentials = credential, DeliveryMethod = SmtpDeliveryMethod.Network};
            if (enableSSL)
            {
                sc.EnableSsl = true;
            }
            sc.Send(mail);
        }

        /// <summary>
        /// 发送系统邮件
        /// </summary>
        /// <param name="mailSubject">邮件标题</param>
        /// <param name="mailContent">邮件内容</param>
        /// <param name="mailAddress">邮件地址(以;分隔)</param>
        public static bool SendSystemMail(string mailSubject, string mailContent, string mailAddress)
        {
            try
            {
                mailSubject = GetLocalIP() + "----" + mailSubject;
                SendMail(SMTP_SERVER, DEFAULT_SEND_USER, DEFAULT_SEND_USER_PASSWD, mailAddress,
                         DEFAULT_SEND_FROM, mailSubject, mailContent);
            }
            catch (Exception e)
            {
                logger.Error(m => m("SendSystemMail faild!", e));
                return false;
            }
            return true;
            //SendSystemErrorMail(mailSubject, mailContent, mailAddress);
        }

        #endregion

        /// <summary>
        /// 发送邮件
        /// </summary>
        /// <param name="mailServer">邮件服务器地址</param>
        /// <param name="mailUser">发件人帐号</param>
        /// <param name="mailPwd">发件人密码</param>
        /// <param name="mailAddress">收件人Email地址(多个以分号隔开)</param>
        /// <param name="mailCc">抄送</param>
        /// <param name="mailBcc">密送</param>
        /// <param name="mailFrom">发件人Email地址</param>
        /// <param name="subject">标题</param>
        /// <param name="body">正文</param>
        /// <param name="isHtml">正文是否HTML格式</param>
        public static void SendMail(string mailServer, string mailUser, string mailPwd, string mailAddress, string mailCc,
                                    string mailBcc, string mailFrom,
                                    string subject, string body, bool isHtml)
        {
            var mail = new MailMessage();
            var fromAddress = new MailAddress(mailFrom);
            mail.From = fromAddress;
            mail.Subject = subject;
            mail.Subject = subject;
            mail.Body = body;
            string[] arrAddress = mailAddress.Split(';');
            foreach (string address in arrAddress)
            {
                if (!string.IsNullOrEmpty(address))
                {
                    mail.To.Add(address);
                }
            }
            string[] arrCc = mailCc.Split(';');
            foreach (string cc in arrCc)
            {
                if (!string.IsNullOrEmpty(cc))
                {
                    mail.CC.Add(cc);
                }
            }
            string[] arrBcc = mailBcc.Split(';');
            foreach (string bcc in arrBcc)
            {
                if (!string.IsNullOrEmpty(bcc))
                {
                    mail.Bcc.Add(bcc);
                }
            }

            mail.IsBodyHtml = isHtml;
            mail.SubjectEncoding = Encoding.GetEncoding("gb2312");
            mail.BodyEncoding = Encoding.GetEncoding("gb2312");
            var credential = new NetworkCredential(mailUser, mailPwd);
            SendMail(mailServer, credential, mail, DEFAULT_ENABLE_SSL);
        }

        public static string GetLocalIP()
        {
            string localIp = string.Empty;
            try
            {
                string hostNm = Dns.GetHostName();
                IPAddress[] ips = Dns.GetHostEntry("").AddressList;
                foreach (IPAddress ipAddress in ips)
                {
                    if (!ipAddress.IsIPv6LinkLocal && !ipAddress.IsIPv6Multicast && !ipAddress.IsIPv6SiteLocal  && !ipAddress.IsIPv6Teredo)
                    {
                        localIp = ipAddress.ToString();
                        break;
                    }
                }
            }
            catch (Exception)
            {
                localIp = string.Empty;
            }
            return localIp;
        }
    }
}