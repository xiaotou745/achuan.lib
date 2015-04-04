using System.Collections.Generic;

namespace AC.Page
{
    /// <summary>
    /// ��ҳ���ӿ�
    /// </summary>
    /// <typeparam name="T">IPagedList<T></typeparam>
    public interface IPagedList<T>
    {
        ///<summary>
        /// ��ҳ����б�
        ///</summary>
        List<T> ContentList { get; }

        /// <summary>
        /// ����ܼ�¼��
        /// </summary>
        int RecordCount { get; }

        /// <summary>
        /// �����ҳ��
        /// </summary>
        int PageCount { get; }

        /// <summary>
        /// ҳ��С��ÿҳ20����
        /// </summary>
        int PageSize { get; set; }

        /// <summary>
        /// ��ǰҳ��
        /// </summary>
        int PageIndex { get; set; }
    }

    /// <summary>
    /// ��ҳ����
    /// </summary>
    /// <typeparam name="T"></typeparam>
    public class PagedList<T> : IPagedList<T>
    {
        public PagedList()
        {
        }

        /// <summary>
        /// ��ҳ����࣬������װ��ҳ���
        /// </summary>
        /// <param name="contentList">��ҳ������</param>
        /// <param name="recordCount">�ܼ�¼��</param>
        /// <param name="pageCount">��ҳ��</param>
        public PagedList(List<T> contentList, int recordCount, int pageCount)
        {
            ContentList = contentList;
            RecordCount = recordCount;
            PageCount = pageCount;
        }

        /// <summary>
        /// ��ȡ�����÷�ҳ����б�
        /// </summary>
        public List<T> ContentList { get; set; }

        /// <summary>
        /// ��ȡ�������ܼ�¼����
        /// </summary>
        public int RecordCount { get; set; }

        /// <summary>
        /// ��ȡ�����÷�ҳ����
        /// </summary>
        public int PageCount { get; set; }

        /// <summary>
        /// ��ȡ�� ����ÿҳ��¼����
        /// </summary>
        public int PageSize { get; set; }

        /// <summary>
        /// ��ȡ�����õ�ǰҳ��
        /// </summary>
        public int PageIndex { get; set; }
    }
}