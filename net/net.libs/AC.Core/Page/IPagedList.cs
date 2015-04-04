using System.Collections.Generic;

namespace AC.Page
{
    /// <summary>
    /// 分页器接口
    /// </summary>
    /// <typeparam name="T">IPagedList<T></typeparam>
    public interface IPagedList<T>
    {
        ///<summary>
        /// 分页结果列表
        ///</summary>
        List<T> ContentList { get; }

        /// <summary>
        /// 结果总记录数
        /// </summary>
        int RecordCount { get; }

        /// <summary>
        /// 结果总页数
        /// </summary>
        int PageCount { get; }

        /// <summary>
        /// 页大小，每页20条等
        /// </summary>
        int PageSize { get; set; }

        /// <summary>
        /// 当前页码
        /// </summary>
        int PageIndex { get; set; }
    }

    /// <summary>
    /// 分页器类
    /// </summary>
    /// <typeparam name="T"></typeparam>
    public class PagedList<T> : IPagedList<T>
    {
        public PagedList()
        {
        }

        /// <summary>
        /// 分页结果类，用来封装分页结果
        /// </summary>
        /// <param name="contentList">分页对象结果</param>
        /// <param name="recordCount">总记录数</param>
        /// <param name="pageCount">分页数</param>
        public PagedList(List<T> contentList, int recordCount, int pageCount)
        {
            ContentList = contentList;
            RecordCount = recordCount;
            PageCount = pageCount;
        }

        /// <summary>
        /// 获取或设置分页结果列表
        /// </summary>
        public List<T> ContentList { get; set; }

        /// <summary>
        /// 获取或设置总记录数量
        /// </summary>
        public int RecordCount { get; set; }

        /// <summary>
        /// 获取或设置分页数量
        /// </summary>
        public int PageCount { get; set; }

        /// <summary>
        /// 获取或 设置每页记录数量
        /// </summary>
        public int PageSize { get; set; }

        /// <summary>
        /// 获取或设置当前页码
        /// </summary>
        public int PageIndex { get; set; }
    }
}