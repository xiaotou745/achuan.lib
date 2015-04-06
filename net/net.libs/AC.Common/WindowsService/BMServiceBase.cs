#region

using System;
using System.ServiceProcess;
using System.Threading;
using AC.Helper;
using Common.Logging;

#endregion

namespace AC.WindowsService
{
    public class BMServiceBase : ServiceBase
    {
        /// <summary>
        /// ˢ�������ļ���ʱ��
        /// </summary>
        //internal static TimerConfigRefesh TimerConfigRefeshS;
        /// <summary>
        /// �쳣�ʼ�֪ͨ��־����
        /// </summary>
        private readonly ILog exceptionLogger = LogManager.GetLogger("ErrorLogger");

        /// <summary>
        /// ��־����
        /// </summary>
        private readonly ILog logger = LogManager.GetLogger("Logger");

        /// <summary>
        /// ����ִ�����
        /// </summary>
        /// <param name="args"></param>
        protected override void OnStart(string[] args)
        {
            base.OnStart(args);

            logger.InfoFormat("----------windows����:{0} .�������ƣ�{1} Start Run.", GetType(), ServiceName);

            try
            {
                ServiceFilePathManager.InstallPathInit(ServiceName);
                //ConfigService.Read();
                //��ʼ������װ·��
                //Startup.Init<ProductSolrDTO>(ConfigService.SolrConnectionUrlOfProduct);
                //Startup.Init<BasicSolrDTO>(ConfigService.SolrConnectionUrlOfBasic);

                //���������ʼ�֪ͨ
                ServiceStartMailNotice();

                //TimerConfigRefeshS = new TimerConfigRefesh();
                //TimerConfigRefeshS.StartService();

                //��ʼ��ʽ����
                OnStarted();
            }
            catch (Exception ex)
            {
                logger.Error(ex.Message);
                exceptionLogger.Fatal(ex.Message);
            }
        }

        /// <summary>
        /// ���������ʼ�֪ͨ
        /// </summary>
        private void ServiceStartMailNotice()
        {
            string mailContext = string.Format("����{0} ������<br/>����IP:{1}<br/>�������ƣ�{2}", GetType(),
                                               MailHelper.GetLocalIP(), ServiceName);

            string mailSubject = "��������IP��" + MailHelper.GetLocalIP() + " solr����������������֪ͨ";

#if DEBUG
            mailSubject += "DEBUG";
            MailHelper.SendMail(mailSubject, mailContext, string.Empty);
#else
            mailSubject += "Release";
            MailUtils.SendMail(mailSubject, mailContext, ConfigService.MailAddress);
#endif
        }

        /// <summary>
        /// ����ֹͣ���
        /// </summary>
        protected override void OnStop()
        {
            try
            {
                base.OnStop();

                //����ֹͣ�ʼ�֪ͨ
                ServiceStopMailNotice();
                //�������ʼ�ʱ��
                Thread.Sleep(1000);

                //ע�������ļ�ˢ�¼�ʱ��
                //if (TimerConfigRefeshS != null)
                //{
                //    TimerConfigRefeshS.Enabled = false;
                //    TimerConfigRefeshS.Close();
                //    TimerConfigRefeshS.Dispose();
                //}

                OnStoped();
            }
            catch (Exception ex)
            {
                logger.Error(ex.Message, ex);
            }
        }

        /// <summary>
        /// ����ֹͣ�ʼ�֪ͨ
        /// </summary>
        private void ServiceStopMailNotice()
        {
            string mailContext = string.Format("����{0} ��ֹͣ<br/>����IP:{1}<br/>�������ƣ�{2}", GetType(),
                                               MailHelper.GetLocalIP(), ServiceName);

            string mailSubject = "����ֹͣIP��" + MailHelper.GetLocalIP() + " solr������������ֹ֪ͣͨ";

#if DEBUG
            mailSubject += "DEBUG";
            MailHelper.SendMail(mailSubject, mailContext, string.Empty);

#else
            mailSubject += "Release";
            MailHelper.SendMail(mailSubject, mailContext, ConfigService.MailAddress);
#endif
        }

        #region virtual Function

        /// <summary>
        /// ��ʼ��ʽ����
        /// </summary>
        protected virtual void OnStarted()
        {
        }

        /// <summary>
        /// ��ʼֹͣ����
        /// </summary>
        protected virtual void OnStoped()
        {
        }

        #endregion
    }
}