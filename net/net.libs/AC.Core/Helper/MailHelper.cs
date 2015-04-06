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
    /// �ʼ�������
    /// </summary>
    public class MailHelper
    {
        /// <summary>
        /// �ʼ����ͷ�����
        /// </summary>
        public const string SMTP_SERVER = "smtp.exmail.qq.com";

        public const string DEFAULT_SEND_USER = "system@beimai.com";
        public const string DEFAULT_SEND_USER_PASSWD = "bm2014";
        public const string DEFAULT_SEND_FROM = "system@beimai.com";
        public const bool DEFAULT_ENABLE_SSL = true;

        protected static readonly ILog logger = LogManager.GetLogger(typeof (MailHelper));

        #region IMail Members

        /// <summary>
        /// �����ʼ�
        /// </summary>
        /// <param name="mailServer">��������ַ</param>
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
        /// �����ʼ�
        /// </summary>
        /// <param name="mailServer">��������ַ</param>
        /// <param name="port">�˿�</param>
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
        /// �����ʼ�
        /// </summary>
        /// <param name="mailServer">�ʼ���������ַ</param>
        /// <param name="mailUser">�������ʺ�</param>
        /// <param name="mailPwd">����������</param>
        /// <param name="mailAddress">�ռ���Email��ַ(����ԷֺŸ���)</param>
        /// <param name="mailFrom">������Email��ַ</param>
        /// <param name="subject">����</param>
        /// <param name="body">����</param>
        /// <param name="isHtml">�����Ƿ�HTML��ʽ</param>
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
        /// �����ʼ�
        /// </summary>
        /// <param name="mailServer">�ʼ���������ַ</param>
        /// <param name="mailUser">�������ʺ�</param>
        /// <param name="mailPwd">����������</param>
        /// <param name="mailAddress">�ռ���Email��ַ(����ԷֺŸ���)</param>
        /// <param name="mailFrom">������Email��ַ</param>
        /// <param name="subject">����</param>
        /// <param name="body">����</param>
        public static void SendMail(string mailServer, string mailUser, string mailPwd, string mailAddress, string mailFrom,
                                    string subject, string body)
        {
            SendMail(mailServer, mailUser, mailPwd, mailAddress, mailFrom, subject, body, false);
        }

        /// <summary>
        /// �����ʼ�
        /// </summary>
        /// <param name="mailServer">�ʼ���������ַ</param>
        /// <param name="mailUser">�������ʺ�</param>
        /// <param name="mailPwd">����������</param>
        /// <param name="mailAddress">�ռ���Email��ַ(����ԷֺŸ���)</param>
        /// <param name="mailFrom">������Email��ַ</param>
        /// <param name="cc">����</param>
        /// <param name="bcc">����</param>
        /// <param name="subject">����</param>
        /// <param name="body">����</param>
        public static void SendMail(string mailServer, string mailUser, string mailPwd, string mailAddress, string cc, string bcc,
                                    string mailFrom,
                                    string subject, string body)
        {
            SendMail(mailServer, mailUser, mailPwd, mailAddress, cc, bcc, mailFrom, subject, body, false);
        }

        /// <summary>
        /// �����ʼ�
        /// </summary>
        /// <param name="mailServer">�ʼ���������ַ</param>
        /// <param name="port">�˿�</param>
        /// <param name="mailUser">�������ʺ�</param>
        /// <param name="mailPwd">����������</param>
        /// <param name="mailAddress">�ռ���Email��ַ(����ԷֺŸ���)</param>
        /// <param name="mailFrom">������Email��ַ</param>
        /// <param name="subject">����</param>
        /// <param name="body">����</param>
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
        /// ���ʹ�����������ʼ�
        /// ���ʹ������ʼ��ʼ�
        /// </summary>
        /// <param name="mailPwd"></param>
        /// <param name="mailAddress"></param>
        /// <param name="mailFrom"></param>
        /// <param name="mailSubject"></param>
        /// <param name="mailBody"></param>
        /// <param name="filePathes">������ַ�б�</param>
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
        /// ���ʹ�����������ʼ�
        /// ���ʹ������ʼ��ʼ�
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

            //mail.AlternateViews.Add(AlternateView.CreateAlternateViewFromString("ͼƬ", null, "text/plain"));

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
        /// ����ϵͳ�ʼ�
        /// </summary>
        /// <param name="mailSubject">�ʼ�����</param>
        /// <param name="mailContent">�ʼ�����</param>
        /// <param name="mailAddress">�ʼ���ַ(��;�ָ�)</param>
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
        /// �����ʼ�
        /// </summary>
        /// <param name="mailServer">�ʼ���������ַ</param>
        /// <param name="mailUser">�������ʺ�</param>
        /// <param name="mailPwd">����������</param>
        /// <param name="mailAddress">�ռ���Email��ַ(����ԷֺŸ���)</param>
        /// <param name="mailCc">����</param>
        /// <param name="mailBcc">����</param>
        /// <param name="mailFrom">������Email��ַ</param>
        /// <param name="subject">����</param>
        /// <param name="body">����</param>
        /// <param name="isHtml">�����Ƿ�HTML��ʽ</param>
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