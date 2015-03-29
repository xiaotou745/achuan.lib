using System.Collections.Generic;
using System.Data;
using AC.Code.Helper;

namespace AC.Code.DbObjects
{
    /// <summary>
    /// ��ȡ���ݿ���Ϣ��Ľӿڶ��塣
    /// </summary>
    public interface IDbObject
    {
        /// <summary>
        ///  ���ݿ�����
        /// </summary>
        DbType DbType { get; }

        /// <summary>
        /// ���ݿ�����
        /// </summary>
        string DbName { get; set; }

        /// <summary>
        /// ����
        /// </summary>
        string TableName { get; set; }

        /// <summary>
        /// ��������
        /// </summary>
        string ServerName { get; set; }

        /// <summary>
        /// ���ݿ������ַ���
        /// </summary>
        string DbConnectStr { set; get; }

        #region db����

        int ExecuteSql(string dbName, string sqlString);
        DataSet Query(string dbName, string sqlString);

        #endregion

        void ReSetConnString(bool sspi, string ip, string user, string pwd);
        #region �õ����ݿ�������б� GetDBList()

        /// <summary>
        /// �õ����ݿ�������б�
        /// </summary>
        /// <returns></returns>
        List<string> GetDBList();

        #endregion

        #region  �õ����ݿ�������û����� GetTables(string DbName)

        /// <summary>
        /// �õ����ݿ�����б���
        /// </summary>
        /// <param name="dbName"></param>
        /// <returns></returns>
        List<string> GetTables(string dbName);

        DataTable GetVIEWs(string dbName);

        /// <summary>
        /// �õ����ݿ�����б����ͼ��
        /// </summary>
        /// <param name="dbName">���ݿ�</param>
        /// <returns></returns>
        DataTable GetTabViews(string dbName);

        DataTable GetProcs(string dbName);

        #endregion

        #region  �õ����ݿ�����б����ϸ��Ϣ GetTablesInfo(string DbName)

        /// <summary>
        /// �õ����ݿ�����б����ϸ��Ϣ
        /// </summary>
        /// <param name="DbName">���ݿ�</param>
        /// <returns></returns>
        List<TableInfo> GetTablesInfo(string DbName);

        List<TableInfo> GetVIEWsInfo(string dbName);

        /// <summary>
        /// �õ����ݿ�����б����ͼ����ϸ��Ϣ
        /// </summary>
        /// <param name="dbName">���ݿ�</param>
        /// <returns></returns>
        List<TableInfo> GetTabViewsInfo(string dbName);

        List<TableInfo> GetProcInfo(string dbName);

        #endregion

        #region �õ����������

        /// <summary>
        /// �õ���ͼ��洢���̵Ķ������
        /// </summary>  
        string GetObjectInfo(string dbName, string objName);

        #endregion

        #region �õ�(����)���ݿ��������������� GetColumnList(string DbName,string TableName)

        /// <summary>
        /// �õ����ݿ���������������
        /// </summary>
        /// <param name="dbName"></param>
        /// <param name="tableName"></param>
        /// <returns></returns>
        List<ColumnInfo> GetColumnList(string dbName, string tableName);

        #endregion

        #region �õ����ݿ������е���ϸ��Ϣ GetColumnInfoList(string DbName,string TableName)

        /// <summary>
        /// �õ����ݿ������е���ϸ��Ϣ
        /// </summary>
        /// <param name="dbName"></param>
        /// <param name="tableName"></param>
        /// <returns></returns>
        List<ColumnInfo> GetColumnInfoList(string dbName, string tableName);

        #endregion

        #region �õ����ݿ��������� GetKeyName(string DbName,string TableName)

        /// <summary>
        /// �õ����ݿ���������
        /// </summary>
        /// <param name="dbName"></param>
        /// <param name="tableName"></param>
        /// <returns></returns>
        DataTable GetKeyName(string dbName, string tableName);

        #endregion

        #region �õ����ݱ�������� GetTabData(string DbName,string TableName)

        /// <summary>
        /// �õ����ݱ��������
        /// </summary>
        /// <param name="dbName"></param>
        /// <param name="tableName"></param>
        /// <returns></returns>
        DataTable GetTabData(string dbName, string tableName);

        #endregion

        #region ���ݿ����Բ���

        /// <summary>
        /// �޸����ݿ�����
        /// </summary>
        /// <param name="oldName"></param>
        /// <param name="newName"></param>
        /// <returns></returns>
        bool RenameTable(string dbName, string oldName, string newName);

        /// <summary>
        /// ɾ����
        /// </summary>	
        bool DeleteTable(string dbName, string tableName);

        string GetVersion();

        #endregion
    }
}