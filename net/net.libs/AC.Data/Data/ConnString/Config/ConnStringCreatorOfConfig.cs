using System;
using System.Collections.Generic;
using System.Configuration;
using System.Text;
using AC.Dao;
using Common.Logging;

namespace AC.Data.ConnString.Config
{
	/// <summary>
	/// ��������Ӧ�ó��������ļ��е�ConnectionString���ý��ж�����ַ���
	/// </summary>
	public class ConnStringCreatorOfConfig : IConnectionStringCreator
	{
		#region Logger Definition

		private readonly ILog logger = DataLogManager.GetConnectionStringLog();

		#endregion

		#region Private Constructor.

		private ConnStringCreatorOfConfig()
		{
		}

		#endregion

		#region IConnectionStringCreator Members

		/// <summary>
		/// ����Ӧ�ó��������ļ��е�ConnectionString���ý��ж�����ַ���
		/// </summary>
		/// <returns>����ConnectionString���ý��ж���������ַ����б�.</returns>
		public IList<IConnectionString> CreateConnStrings()
		{
			try
			{
				IList<IConnectionString> result = new List<IConnectionString>();

				foreach (ConnectionStringSettings connectionString in ConfigurationManager.ConnectionStrings)
				{
					var connString = new ConnStringOfConfig
					{
						Name = connectionString.Name,
						ConnectionString = connectionString.ConnectionString,
						ProviderName = connectionString.ProviderName
					};
					result.Add(connString);
				}

				if (logger.IsInfoEnabled)
				{
					StringBuilder logText = new StringBuilder();
					logText.AppendLine(string.Format("�����ļ���[connectionStrings]���ý��й����ַ���������{0}.", result.Count));
					foreach (IConnectionString connectionString in result)
					{
						logText.AppendLine(string.Format("ConfigConnString:{0}", connectionString));
					}
					logger.Info(logText.ToString());
				}
				return result;
			}
			catch(Exception exception)
			{
				string errorMsg = string.Format("[ConnStringCreatorOfConfig.CreateConnStrings] Error:{0}", exception.Message);
				logger.Error(errorMsg);
				throw new ConnStringCreateException(errorMsg, exception);
			}
		}

		#endregion

		/// <summary>
		/// Create a instance of class <see cref="ConnStringCreatorOfConfig"/>
		/// </summary>
		/// <returns></returns>
		public static ConnStringCreatorOfConfig Create()
		{
			return new ConnStringCreatorOfConfig();
		}

		/// <summary>
		/// ToString Override.
		/// </summary>
		/// <returns></returns>
		public override string ToString()
		{
			return typeof (ConnStringCreatorOfConfig).ToString();
		}
	}
}