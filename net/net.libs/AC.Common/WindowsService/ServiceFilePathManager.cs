using System;
using Common.Logging;

namespace AC.WindowsService
{
    /// <summary>
    /// ��ʼ��windows�����·����Ϣ
    /// </summary>
	public static class ServiceFilePathManager
	{
		private const string SERVICE_REGISTRY_KEY_PREX = "SYSTEM\\CurrentControlSet\\Services\\";

		private static readonly ILog Logger = LogManager.GetCurrentClassLogger();

		/// <summary>
		/// ��ǰ����װ·��
		/// </summary>
		public static string ServiceInstallPath { get; set; }

		/// <summary>
		/// ��ȡ��ǰ����װ·��
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
                throw new Exception(ServiceName + "û���ҵ���װ·��");
            }
            string path = openSubKey.GetValue("ImagePath").ToString();
            ServiceInstallPath = path.Substring(1, path.LastIndexOf("\\") - 1);
#endif
            Logger.Info("----------windows����"+ServiceName + "��װ·����ȡ��ɡ� ServiceInstallPath:" + ServiceInstallPath);
		}
	}
}