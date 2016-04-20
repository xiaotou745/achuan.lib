package com.wychuan.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.ContextLoader;

public class SpringBeanHelper {
	private static ApplicationContext applicationContext;
	static {
		try {
			applicationContext = ContextLoader.getCurrentWebApplicationContext();
			//web站点启动后，ctx_bean才会有值。如果是api调用，则会返回null
			if (applicationContext == null) {
				applicationContext = new ClassPathXmlApplicationContext("conf/core/dev-context.xml");
				if (applicationContext == null) {
					throw new RuntimeException("没有获取到springcontext");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object getBean(String beanName) {
		return applicationContext.getBean(beanName);
	}

	public static <T> T getBean(Class<T> type) {
		return applicationContext.getBean(type);
	}
}
