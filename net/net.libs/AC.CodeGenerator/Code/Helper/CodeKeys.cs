using System.Collections.Generic;

namespace AC.Code.Helper
{
    /// <summary>
    /// �����У��������ݿ�������������ֶ�
    /// </summary>
    public class CodeKey
    {
        /// <summary>
        /// �ֶ���
        /// </summary>
        public string KeyName { set; get; }

        /// <summary>
        /// �ֶ�����
        /// </summary>
        public string KeyType { set; get; }

        /// <summary>
        /// �Ƿ�������
        /// </summary>
        public bool IsPK { set; get; }

        /// <summary>
        /// �Ƿ��Ǳ�ʶ��
        /// </summary>
        public bool IsIdentity { set; get; }
    }

    /// <summary>
    /// �����ֶκ������ֶβ�����
    /// </summary>
    public class CodeKeyMaker
    {
        /// <summary>
        /// �õ��������б�(���磺����Exists  Delete  GetModel �Ĳ�������)
        /// </summary>
        /// <param name="keys"></param>
        /// <returns></returns>
        public static string GetParameter(List<CodeKey> keys)
        {
            var strclass = new StringPlus();
            foreach (CodeKey key in keys)
            {
                strclass.Append(CodeCommon.DbTypeToCS(key.KeyType) + " " + key.KeyName + ",");
            }
            strclass.DelLastComma();
            return strclass.Value;
        }

        ///// <summary>
        ///// �õ�Where�������-SQL��ʽ (���磺����Exists  Delete  GetModel ��where)
        ///// </summary>
        ///// <param name="keys"></param>
        ///// <returns></returns>
        //public static string GetParameter(List<CodeKey> keys)
        //{
        //    StringPlus strclass = new StringPlus();
        //    foreach (CodeKey key in keys)
        //    {
        //        if (CodeCommon.IsAddMark(key.KeyType))
        //        {
        //            strclass.Append(key.KeyName + "='\"+" + key.KeyName + "+\"'\"");
        //        }
        //        else
        //        {
        //            strclass.Append(key.KeyName + "=\"+" + key.KeyName );
        //        }

        //    }            
        //    return strclass.Value;
        //}
        ///// <summary>
        ///// �õ�Where�������-SqlParameter��ʽ (���磺����Exists  Delete  GetModel ��where)
        ///// </summary>
        ///// <param name="keys"></param>
        ///// <returns></returns>
        //public static string GetParameter(List<CodeKey> keys)
        //{
        //    StringPlus strclass = new StringPlus();
        //    foreach (CodeKey key in keys)
        //    {
        //        if (CodeCommon.IsAddMark(key.KeyType))
        //        {
        //            strclass.Append(key.KeyName + "='\"+" + key.KeyName + "+\"'\"");
        //        }
        //        else
        //        {
        //            strclass.Append(key.KeyName + "=\"+" + key.KeyName);
        //        }

        //    }
        //    return strclass.Value;
        //}
    }
}