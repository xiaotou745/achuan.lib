﻿#region

using Common.Logging;
using System;
using System.Net;
using System.Net.Mail;
using System.Text;

#endregion

namespace AC.Log
{
    public class Log4NetSmtpAppender : log4net.Appender.SmtpAppender
    {
        private static readonly ILog Logger = LogManager.GetLogger<Log4NetSmtpAppender>();

        #region Private Instance Fields
        private static string localIp = string.Empty;
        private readonly Encoding m_bodyEncoding = Encoding.GetEncoding("gb2312");
        private readonly Encoding m_subjectEncoding = Encoding.GetEncoding("gb2312");

        private string m_bcc;
        private string m_cc;
        private bool m_enableSsl;
        private MailPriority m_mailPriority = MailPriority.Normal;
        private string m_replyTo;
        private bool m_enableIp;
        #endregion

        #region Public Instance Fields
        public bool EnableIp
        {
            get { return m_enableIp; }
            set { m_enableIp = value; }
        }
        /// <summary>
        /// Enable or disable use of SSL when sending e-mail message
        /// </summary>
        /// <remarks>
        /// This is available on MS .NET 2.0 runtime and higher
        /// </remarks>
        public new bool EnableSsl
        {
            get { return m_enableSsl; }
            set { m_enableSsl = value; }
        }

        /// <summary>
        /// Gets or sets a comma- or semicolon-delimited list of recipient e-mail addresses 
        /// that will be carbon copied (use semicolon on .NET 1.1 and comma for later versions).
        /// </summary>
        /// <value>
        /// <para>
        /// For .NET 1.1 (System.Web.Mail): A semicolon-delimited list of e-mail addresses.
        /// </para>
        /// <para>
        /// For .NET 2.0 (System.Net.Mail): A comma-delimited list of e-mail addresses.
        /// </para>
        /// </value>
        /// <remarks>
        /// <para>
        /// For .NET 1.1 (System.Web.Mail): A semicolon-delimited list of e-mail addresses.
        /// </para>
        /// <para>
        /// For .NET 2.0 (System.Net.Mail): A comma-delimited list of e-mail addresses.
        /// </para>
        /// </remarks>
        public new string Cc
        {
            get { return m_cc; }
            set { m_cc = value; }
        }

        /// <summary>
        /// Gets or sets the reply-to e-mail address.
        /// </summary>
        /// <remarks>
        /// This is available on MS .NET 2.0 runtime and higher
        /// </remarks>
        public new string ReplyTo
        {
            get { return m_replyTo; }
            set { m_replyTo = value; }
        }

        /// <summary>
        /// Gets or sets a semicolon-delimited list of recipient e-mail addresses
        /// that will be blind carbon copied.
        /// </summary>
        /// <value>
        /// A semicolon-delimited list of e-mail addresses.
        /// </value>
        /// <remarks>
        /// <para>
        /// A semicolon-delimited list of recipient e-mail addresses.
        /// </para>
        /// </remarks>
        public new string Bcc
        {
            get { return m_bcc; }
            set { m_bcc = value; }
        }

        /// <summary>
        /// Gets or sets the priority of the e-mail message
        /// </summary>
        /// <value>
        /// One of the <see cref="MailPriority"/> values.
        /// </value>
        /// <remarks>
        /// <para>
        /// Sets the priority of the e-mails generated by this
        /// appender. The default priority is <see cref="MailPriority.Normal"/>.
        /// </para>
        /// <para>
        /// If you are using this appender to report errors then
        /// you may want to set the priority to <see cref="MailPriority.High"/>.
        /// </para>
        /// </remarks>
        public new MailPriority Priority
        {
            get { return m_mailPriority; }
            set { m_mailPriority = value; }
        }
        #endregion

        private static string GetLocalIP()
        {
            if (string.IsNullOrEmpty(localIp))
            {
                try
                {
                    string hostNm = Dns.GetHostName();
                    IPAddress[] ips = Dns.GetHostEntry("").AddressList;
                    foreach (IPAddress ipAddress in ips)
                    {
                        if (!ipAddress.IsIPv6LinkLocal && !ipAddress.IsIPv6Multicast && !ipAddress.IsIPv6SiteLocal && !ipAddress.IsIPv6Teredo)
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
            }
            return localIp;
        }

        protected override void SendEmail(string messageBody)
        {
            
            // .NET 2.0 has a new API for SMTP email System.Net.Mail
            // This API supports credentials and multiple hosts correctly.
            // The old API is deprecated.

            // Create and configure the smtp client
            var smtpClient = new SmtpClient();
            if (!String.IsNullOrEmpty(SmtpHost))
            {
                smtpClient.Host = SmtpHost;
            }
            if (Port>0)
            {
                smtpClient.Port = Port;
            }
            smtpClient.DeliveryMethod = SmtpDeliveryMethod.Network;
            smtpClient.EnableSsl = m_enableSsl;
            //smtpClient.UseDefaultCredentials = false;
            if (Authentication == SmtpAuthentication.Basic)
            {
                // Perform basic authentication
                smtpClient.Credentials = new System.Net.NetworkCredential(Username, Password);
            }
            else if (Authentication == SmtpAuthentication.Ntlm)
            {
                // Perform integrated authentication (NTLM)
                smtpClient.Credentials = System.Net.CredentialCache.DefaultNetworkCredentials;
            }

            using (var mailMessage = new MailMessage())
            {
                mailMessage.Body = messageBody;
                mailMessage.BodyEncoding = m_bodyEncoding;
                mailMessage.From = new MailAddress(From);
                mailMessage.To.Add(To);
                //if (!String.IsNullOrEmpty(m_cc))
                //{
                //    mailMessage.CC.Add(m_cc);
                //}
                //if (!String.IsNullOrEmpty(m_bcc))
                //{
                //    mailMessage.Bcc.Add(m_bcc);
                //}
                //if (!String.IsNullOrEmpty(m_replyTo))
                //{
                //    mailMessage.ReplyToList.Add(new MailAddress(m_replyTo));
                //}
                mailMessage.Subject = this.EnableIp ? GetLocalIP() + Subject : Subject;
                mailMessage.SubjectEncoding = m_subjectEncoding;
                mailMessage.IsBodyHtml = true;
                //mailMessage.Priority = m_mailPriority;
                Logger.InfoFormat("from:{0} to:{1} port:{2} ssl:{3} cc:{4} bcc:{5} replyto:{6} subject:{7} enableip:{8} pwd:{9}",
                                         From, To, Port, EnableSsl, Cc, Bcc, ReplyTo, Subject, EnableIp, Password);
                // TODO: Consider using SendAsync to send the message without blocking. This would be a change in
                // behaviour compared to .NET 1.x. We would need a SendCompletedCallback to log errors.
                try
                {
                    smtpClient.Send(mailMessage);
                }
                catch (Exception ex)
                {
                    Logger.Info(ex);
                }
            }
        }
    }
}