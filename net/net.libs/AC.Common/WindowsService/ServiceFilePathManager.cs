using System;
using Common.Logging;

namespace AC.WindowsService
{
    /// <summary>
    /// 初始化windows服务的路径信息
    /// </summary>
	public static class ServiceFilePathManager
	{
		private const string SERVICE_REGISTRY_KEY_PREX = "SYSTEM\\CurrentControlSet\\Services\\";

		private static readonly ILog Logger = LogManager.GetCurrentClassLogger();

		/// <summary>
		/// 当前服务安装路径
		/// </summary>
		public static string ServiceInstallPath { get; set; }

		/// <summary>
		/// 获取当前服务安装路径
		/// </summary>
		/// <param name="ServiceName"></param>
		/// <returns></returns>
		public static void InstallPathInit(string ServiceName)
		{
#if DEBUG
            string path = AppDomain.CurrentDomain.BaseDirectory;
		    ServiceInstallPath = path;
            Logger.InfoFormat("path:{0} serviceinstallpath:{1}", path, ServiceInstallPath);
#else
            string key = SERVICE_REGISTRY_KEY_PREX + ServiceName;
            RegistryKey openSubKey = Registry.LocalMachine.OpenSubKey(key);
            if (openSubKey == null)
            {
                throw new Exception(ServiceName + "没有找到安装路径");
            }
            string path = openSubKey.GetValue("ImagePath").ToString();
            ServiceInstallPath = path.Substring(1, path.LastIndexOf("\\") - 1);
#endif
            Logger.Info("----------windows服务："+ServiceName + "安装路径获取完成。 ServiceInstallPath:" + ServiceInstallPath);
		}
	}
}