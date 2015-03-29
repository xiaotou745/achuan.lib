namespace AC.Code.Helper
{
    /// <summary>
    /// �ֶ���Ϣ
    /// </summary>
    public class ColumnInfo
    {
        private string _deText = "";
        private string _defaultVal = "";
        private string _length = "";
        private string _preci = "";
        private string _scale = "";
        private string _typeName = "";

        /// <summary>
        /// ���
        /// </summary>
        public string Colorder { set; get; }

        /// <summary>
        /// �ֶ���
        /// </summary>
        public string ColumnName { set; get; }

        /// <summary>
        /// �ֶ�����
        /// </summary>
        public string TypeName
        {
            set { _typeName = value; }
            get { return _typeName; }
        }

        /// <summary>
        /// ����
        /// </summary>
        public string Length
        {
            set { _length = value; }
            get { return _length; }
        }

        /// <summary>
        /// ����
        /// </summary>
        public string Preci
        {
            set { _preci = value; }
            get { return _preci; }
        }

        /// <summary>
        /// С��λ��
        /// </summary>
        public string Scale
        {
            set { _scale = value; }
            get { return _scale; }
        }

        /// <summary>
        /// �Ƿ��Ǳ�ʶ��
        /// </summary>
        public bool IsIdentity { set; get; }

        /// <summary>
        /// �Ƿ�������
        /// </summary>
        public bool IsPK { set; get; }

        /// <summary>
        /// �Ƿ������
        /// </summary>
        public bool cisNull { set; get; }

        /// <summary>
        /// Ĭ��ֵ
        /// </summary>
        public string DefaultVal
        {
            set { _defaultVal = value; }
            get { return _defaultVal; }
        }

        /// <summary>
        /// ��ע
        /// </summary>
        public string DeText
        {
            set { _deText = value; }
            get { return _deText; }
        }
    }
}