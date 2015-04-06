namespace AC.Web
{
    public class AjaxResult
    {
        private AjaxResult()
        {
        }

        private bool _iserror;
        private bool _islogoff;

        /// <summary>
        /// 是否产生错误
        /// </summary>
        public bool iserror
        {
            get { return _iserror; }
        }

        /// <summary>
        /// 是否产生错误
        /// </summary>
        public bool islogoff
        {
            get { return _islogoff; }
        }

        /// <summary>
        /// 错误信息，或者成功信息
        /// </summary>
        public string message { get; set; }

        /// <summary>
        /// 成功可能时返回的数据
        /// </summary>
        public object data { get; set; }

        #region Error

        public static AjaxResult Error()
        {
            return new AjaxResult
                {
                    _iserror = true
                };
        }

        public static AjaxResult Error(string message)
        {
            return new AjaxResult
                {
                    _iserror = true,
                    message = message
                };
        }

        public static AjaxResult Error(object data, string message)
        {
            return new AjaxResult
                {
                    _iserror = true,
                    data = data,
                    message = message
                };
        }

        public static AjaxResult LogOff(string message)
        {
            return new AjaxResult
                {
                    _islogoff = true,
                    message = message
                };
        }

        #endregion

        #region Success

        public static AjaxResult Success()
        {
            return new AjaxResult
                {
                    _iserror = false
                };
        }

        public static AjaxResult Success(string message)
        {
            return new AjaxResult
                {
                    _iserror = false,
                    message = message
                };
        }

        public static AjaxResult Success(object data)
        {
            return new AjaxResult
                {
                    _iserror = false,
                    data = data
                };
        }

        public static AjaxResult Success(object data, string message)
        {
            return new AjaxResult
                {
                    _iserror = false,
                    data = data,
                    message = message
                };
        }

        #endregion

        public override string ToString()
        {
            return Json.JsonSerializer.Serialize(this);
        }
    }
}