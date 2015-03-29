using System.Collections.Generic;

namespace AC.Data.ConnString
{
	/// <summary>
	/// ����ConnString�ӿ�
	/// </summary>
	public interface IConnectionStringCreator
	{
		/// <summary>
		/// �������е�ConnString.
		/// </summary>
		/// <returns></returns>
		IList<IConnectionString> CreateConnStrings();
	}
}