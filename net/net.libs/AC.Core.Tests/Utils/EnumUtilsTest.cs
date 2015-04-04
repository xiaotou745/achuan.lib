using System.Collections.Generic;
using AC.Json;
using AC.Util;
using Common.Logging;
using NUnit.Framework;

namespace AC.Core.Tests.Utils
{
    public enum OrderStatus
    {
        [System.ComponentModel.Description("已创建")]
        Create = 1,

        [System.ComponentModel.Description("已支付")]
        HasPay = 2,

        [System.ComponentModel.Description("已出库")]
        HasOutstore = 3,

        [System.ComponentModel.Description("已完成")]
        HasCompleted = 4,
    }

    [TestFixture]
    public class EnumUtilsTest
    {
        private readonly ILog logger = LogManager.GetCurrentClassLogger();

        [Test]
        public void GetEnumDescriptions()
        {
            IList<KeyValuePair<string, string>> keyValuePairs = EnumUtils.GetEnumDescriptions(typeof (OrderStatus));

            logger.Info(JsonSerializer.Serialize(keyValuePairs));
        }
    }
}