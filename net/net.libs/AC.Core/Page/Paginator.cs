namespace AC.Page
{
    /// <summary>
    /// ��ҳ������
    /// </summary>
    public class Paginator
    {
        ///<summary>
        ///��ҳ�ߴ�
        ///</summary>
        public int PageSize { get; set; }

        ///<summary>
        ///��ǰҳ��
        ///</summary>
        public int PageIndex { get; set; }

        /// <summary>
        /// ��ҳ��
        /// </summary>
        public int PageCount { get; set; }
    }
}