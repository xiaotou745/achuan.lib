namespace AC.Page
{
    /// <summary>
    /// 分页数据类
    /// </summary>
    public class Paginator
    {
        ///<summary>
        ///分页尺寸
        ///</summary>
        public int PageSize { get; set; }

        ///<summary>
        ///当前页码
        ///</summary>
        public int PageIndex { get; set; }

        /// <summary>
        /// 总页数
        /// </summary>
        public int PageCount { get; set; }
    }
}