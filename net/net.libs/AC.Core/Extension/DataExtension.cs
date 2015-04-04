using System.Data;

namespace AC.Extension
{
    /// <summary>
    /// System.SqlClient.Data 常用扩展类
    /// </summary>
    public static class DataExtension
    {
        /// <summary>
        /// 判断DataSet有无数据
        /// </summary>
        /// <param name="dataSet">DataSet</param>
        /// <returns></returns>
        public static bool IsEmpty(this DataSet dataSet)
        {
            return (dataSet == null) || (dataSet.Tables.Count < 1) || dataSet.Tables[0].IsEmpty();
        }

        /// <summary>
        /// 判断DataTable有无数据
        /// </summary>
        /// <param name="dataTable">DataTable</param>
        /// <returns></returns>
        public static bool IsEmpty(this DataTable dataTable)
        {
            return (dataTable == null) || (dataTable.Rows.Count < 1);
        }

        /// <summary>
        /// 判断DataRow 是否含有某列名
        /// </summary>
        /// <param name="dataRow"></param>
        /// <param name="columnName"></param>
        /// <returns></returns>
        public static bool HasColumn(this DataRow dataRow, string columnName)
        {
            if (dataRow == null)
            {
                return false;
            }
            return dataRow.Table.Columns.Contains(columnName);
        }
    }
}