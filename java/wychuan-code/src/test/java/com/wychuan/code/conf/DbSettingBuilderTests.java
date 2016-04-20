package com.wychuan.code.conf;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class DbSettingBuilderTests {
	@Test
	public void testdbserver(){
		List<DbSetting> dbServers = DbSettingBuilder.getDbSettings();
		assertNotNull(dbServers);
		System.out.println(dbServers.size());
	}
}
