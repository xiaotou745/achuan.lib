using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Globalization;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Xml.Serialization;
using Common.Logging;
using Spring.Context;
using Spring.Context.Support;
using Spring.Objects.Factory.Config;
using Spring.Objects.Factory.Support;

namespace AC.SpringUtils
{
	public class SpringContextHandler : ContextHandler
	{
        protected static readonly ILog Logger = LogManager.GetLogger("ConnectionStringLog");

        protected override IApplicationContext InstantiateContext(IApplicationContext parentContext, object configContext,
		                                                          string contextName, Type contextType, bool caseSensitive,
		                                                          string[] resources)
		{
            var stopwatch = new Stopwatch();
            stopwatch.Start();

			IApplicationContext context = base.InstantiateContext(parentContext, configContext, contextName, contextType,
			                                                      caseSensitive, resources);
			var applicationContext = context as AbstractApplicationContext;

            //CheckName(applicationContext);
			if (applicationContext != null)
			{
				//string path = System.AppDomain.CurrentDomain.BaseDirectory;
				string utilPath = GetType().Assembly.CodeBase;
                string[] assemblyProperties = utilPath.Split(new[] { @"/" }, StringSplitOptions.RemoveEmptyEntries);
			    string assemblyName = assemblyProperties.Length > 0 ? assemblyProperties[assemblyProperties.Length-1].ToLower() : "dz.spring.dll";

			    string path = utilPath.ToLower().Replace(assemblyName,string.Empty).Replace("file:///", "");
			    Logger.InfoFormat("spring context handler assembly file path:{0}", path);

                if (Directory.Exists(path))
                {
                    string[] springAssemblyNames = GetSpringAssemblyNames(path);
                    
                    foreach (string file in Directory.GetFiles(path, "*.dll", SearchOption.TopDirectoryOnly))
                    {
                        string[] strings = file.Split(new[] {@"/"}, StringSplitOptions.RemoveEmptyEntries);
                        string dllName = strings.Length > 0 ? strings[strings.Length - 1] : string.Empty;
                        //如果有设置配置文件，则不在配置文件中的DLL跳过
                        if (springAssemblyNames.Length > 0 && !string.IsNullOrEmpty(dllName) && !springAssemblyNames.ToList().Contains(dllName))
                        {
                            continue;
                        }
                        Logger.InfoFormat("spring context handler init assembly:{0}", dllName);
						try
						{
							HandlAssembly(applicationContext, file);
						}
						catch (Exception ex)
						{
						    Logger.Error("spring handler error:" + file, ex);
						}
					}
				}
			}
            stopwatch.Stop();
            Logger.InfoFormat("spring初始化使用时间是：{0} 秒", stopwatch.ElapsedMilliseconds/1000.0);
			return context;
		}

		private void HandlAssembly(AbstractApplicationContext wmsContext, string domaindllFileNm)
		{
			if (File.Exists(domaindllFileNm))
			{
				Assembly domainDll = Assembly.LoadFrom(domaindllFileNm);
				//Assembly domainDll = Assembly.LoadFile(domaindllFileNm);
				//Assembly domainDll = Assembly.Load(domaindllFileNm);
				AddObjFromAssembly(domainDll, wmsContext);
			}
		}

		private void AddObjFromAssembly(Assembly pAssembly, AbstractApplicationContext pContext)
		{
			try
			{
				if (pAssembly == null)
					return;

				Type[] allTypes = pAssembly.GetTypes();
				foreach (Type type in allTypes)
				{
					foreach (Attribute attr in Attribute.GetCustomAttributes(type, false))
					{
						if (attr.GetType() != typeof (SpringAttribute))
							continue;
						var attrItem = attr as SpringAttribute;
						if (attrItem == null)
							continue;
						string contextId = attrItem.Id;
						if (string.IsNullOrEmpty(contextId)) //如果没有设置ObjId，则使用下面规则生成一个Id
						{
						    Type[] intfaces = type.GetInterfaces();
						    if (intfaces.Length < 1) //如果不存在接口，则直接取其类名，第一个字母小写
						    {
						        contextId = type.Name[0].ToString(CultureInfo.InvariantCulture).ToLower() + type.Name.Substring(1);
						    }
						    else //如果存在接口，则去掉接口的I字符，第二个字符小写
						    {
                                contextId = intfaces[0].Name;
                                contextId = contextId[1].ToString(CultureInfo.InvariantCulture).ToLower() + contextId.Substring(2, contextId.Length - 2);
						    }
						}
						if (!pContext.ContainsObjectDefinition(contextId))
						{
							var typeDef = new RootObjectDefinition(type);
							var arguments = new ConstructorArgumentValues();
							foreach (string conParas in  attrItem.ConstructorArgs.Split(';'))
							{
								string[] conPara = conParas.Split(':');
								if (conPara.Length != 2)
									continue;
								arguments.AddNamedArgumentValue(conPara[0], new RuntimeObjectReference(conPara[1]));
							}
							typeDef.ConstructorArgumentValues = arguments;

							typeDef.IsSingleton = attrItem.IsSingleton;

							typeDef.IsLazyInit = (attrItem.IsLazyInit && attrItem.IsSingleton);

							pContext.RegisterObjectDefinition(contextId, typeDef);
						}
						break;
					}
				}
			}
			catch (Exception ex)
			{
                Logger.Error(ex);
			}
		}

		private IList<string> CheckName(AbstractApplicationContext pContext)
		{
			var errList = new List<string>();
			string[] names = pContext.ObjectFactory.GetObjectDefinitionNames();
			foreach (string name in names)
			{
				try
				{
					IObjectDefinition def = pContext.ObjectFactory.GetObjectDefinition(name);
					Type[] inter = def.ObjectType.GetInterfaces();
					if (inter.Length != 1)
						errList.Add(name);
					string errorName = inter[0].Name;
					errorName = errorName[1].ToString().ToLower() + errorName.Substring(2, errorName.Length - 2);
					if (errorName != name)
						errList.Add(name);
				}
				catch (Exception)
				{
					errList.Add(name);
				}
			}
			return errList;
		}

        private string[] GetSpringAssemblyNames(string path)
        {
            string xmlFilePath = Path.Combine(path, "SpringDlls.xml");
            if (IsWeb)
            {
                xmlFilePath = System.Web.HttpContext.Current.Server.MapPath("~/SpringDlls.xml");
            }
            
            Logger.InfoFormat("spring xml settings file path:{0}",xmlFilePath);
            if (string.IsNullOrEmpty(xmlFilePath))
            {
                return new string[]{};
            }
            if (!File.Exists(xmlFilePath))
            {
                return new string[]{};
            }

            using (FileStream fileStream = new FileStream(xmlFilePath, FileMode.Open, FileAccess.Read))
            {
                var sr = new StreamReader(fileStream);
                string xmlString = sr.ReadToEnd();
                var sz = new XmlSerializer(typeof(List<SpringSetting>));
                sr.Close();
                var springSettings = sz.Deserialize(new StringReader(xmlString)) as List<SpringSetting>;
                string[] strings = springSettings.Select(s => s.AssemblyName).ToArray();
                if (strings != null && strings.Length > 0)
                {
                    return strings;
                }
                return new string[] {};
            }
        } 
	    private bool IsWeb
	    {
	        get
	        {
	            return System.Web.HttpContext.Current != null;
	        }
	    }
	}
}