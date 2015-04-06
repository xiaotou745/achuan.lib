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
        /// 刷新配置文件计时器
        /// </summary>
        //internal static TimerConfigRefesh TimerConfigRefeshS;
        /// <summary>
        /// 异常邮件通知日志对象
        /// </summary>
        private readonly ILog exceptionLogger = LogManager.GetLogger("ErrorLogger");

        /// <summary>
        /// 日志对象
        /// </summary>
        private readonly ILog logger = LogManager.GetLogger("Logger");

        /// <summary>
        /// 服务执行入口
        /// </summary>
        /// <param name="args"></param>
        protected override void OnStart(string[] args)
        {
            base.OnStart(args);

            logger.InfoFormat("----------windows服务:{0} .服务名称：{1} Start Run.", GetType(), ServiceName);

            try
            {
                ServiceFilePathManager.InstallPathInit(ServiceName);
                //ConfigService.Read();
                //初始化服务安装路径
                //Startup.Init<ProductSolrDTO>(ConfigService.SolrConnectionUrlOfProduct);
                //Startup.Init<BasicSolrDTO>(ConfigService.SolrConnectionUrlOfBasic);

                //服务启动邮件通知
                ServiceStartMailNotice();

                //TimerConfigRefeshS = new TimerConfigRefesh();
                //TimerConfigRefeshS.StartService();

                //开始正式运行
                OnStarted();
            }
            catch (Exception ex)
            {
                logger.Error(ex.Message);
                exceptionLogger.Fatal(ex.Message);
            }
        }

        /// <summary>
        /// 服务启动邮件通知
        /// </summary>
        private void ServiceStartMailNotice()
        {
            string mailContext = string.Format("服务：{0} 已启动<br/>服务IP:{1}<br/>服务名称：{2}", GetType(),
                                               MailHelper.GetLocalIP(), ServiceName);

            string mailSubject = "服务启动IP：" + MailHelper.GetLocalIP() + " solr更新索引服务启动通知";

#if DEBUG
            mailSubject += "DEBUG";
            MailHelper.SendMail(mailSubject, mailContext, string.Empty);
#else
            mailSubject += "Release";
            MailUtils.SendMail(mailSubject, mailContext, ConfigService.MailAddress);
#endif
        }

        /// <summary>
        /// 服务停止入口
        /// </summary>
        protected override void OnStop()
        {
            try
            {
                base.OnStop();

                //服务停止邮件通知
                ServiceStopMailNotice();
                //留出发邮件时间
                Thread.Sleep(1000);

                //注销配置文件刷新计时器
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
        /// 服务停止邮件通知
        /// </summary>
        private void ServiceStopMailNotice()
        {
            string mailContext = string.Format("服务：{0} 已停止<br/>服务IP:{1}<br/>服务名称：{2}", GetType(),
                                               MailHelper.GetLocalIP(), ServiceName);

            string mailSubject = "服务停止IP：" + MailHelper.GetLocalIP() + " solr更新索引服务停止通知";

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
        /// 开始正式运行
        /// </summary>
        protected virtual void OnStarted()
        {
        }

        /// <summary>
        /// 开始停止运行
        /// </summary>
        protected virtual void OnStoped()
        {
        }

        #endregion
    }
}