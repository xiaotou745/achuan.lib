package com.wychuan.apache.common.beanutils;

import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Assert;
import org.junit.Test;

public class BeanUtilsTests {
	private Person getPerson() {
		Person person1 = new Person();
		person1.setAge(22);
		person1.setEmail("yuchuan.wang@gmail.com");
		person1.setName("yuchuan.wang");
		return person1;
	}

	private Map getMap() {
		Map result = new HashMap();

		result.put("name", "yuchuan.wang");
		result.put("age", 22);
		result.put("email", "yuchuan.wang@gmail.com");

		return result;
	}

	/**
	 * clone一个对象
	 */
	@Test
	public void cloneTest() {
		Person person1 = getPerson();

		try {
			Person p2 = (Person) BeanUtils.cloneBean(person1);

			Assert.assertEquals(person1.getAge(), p2.getAge());
			Assert.assertEquals(person1.getEmail(), p2.getEmail());
			Assert.assertEquals(person1.getName(), p2.getName());
		} catch (IllegalAccessException | InstantiationException | InvocationTargetException
				| NoSuchMethodException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将一个map对象转化成一个bean 使用map填充一个对象。
	 */
	@Test
	public void populateTest() {
		Person person1 = getPerson();

		Person p2 = new Person();
		try {
			BeanUtils.populate(p2, getMap());
			Assert.assertEquals(person1.getAge(), p2.getAge());
			Assert.assertEquals(person1.getEmail(), p2.getEmail());
			Assert.assertEquals(person1.getName(), p2.getName());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将一个Bean转化为一个Map对象
	 */
	@Test
	public void describeTest() {
		Person p1 = getPerson();

		try {
			Map<String, String> map = BeanUtils.describe(p1);
			Map map2 = getMap();

			Assert.assertNotNull(map);
			Assert.assertNotEquals(0, map.size());
			for (Object key : map2.keySet()) {
				assertTrue(map.containsKey(key));
				assertEquals(map.get(key).toString(), map2.get(key).toString());
			}

		} catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
			e.printStackTrace();
		}
	}
}
