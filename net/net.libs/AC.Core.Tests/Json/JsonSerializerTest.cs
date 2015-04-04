using AC.Core.Tests.Model;
using AC.Json;
using Common.Logging;
using NUnit.Framework;

namespace AC.Core.Tests.Json
{
    [TestFixture]
    public class JsonSerializerTest
    {
        private readonly ILog logger = LogManager.GetLogger<JsonSerializerTest>();

        private User getUserInfo()
        {
            User user = new User
                {
                    UserId = 1,
                    UserName = "test",
                    Age = 18,
                };
            return user;
        }

        [Test]
        public void SerializeTest()
        {
            User user = getUserInfo();
            string userJson = JsonSerializer.Serialize(user);
            logger.Info(userJson);
        }

        [Test]
        public void DeserializeTest()
        {
            string jsonUserInfo = JsonSerializer.Serialize(getUserInfo());
            var userInfo = JsonSerializer.Deserialize<User>(jsonUserInfo);
            Assert.AreEqual(18, userInfo.Age);
            Assert.AreEqual("test", userInfo.UserName);
        }
    }
}