package com.wychuan.learn.db.sqlserver;

import org.junit.Test;

import static org.junit.Assert.*;

import com.wychuan.learn.User;


public class UserDaoTest {
	@Test
	public void GetByIdTest(){
		UserDao userDao = new UserDao();
		try {
			User user = userDao.getById(5);
			assertNotNull(user);
			
			assertEquals("xiaotou745", user.getLoginName());
			System.out.println(user.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
