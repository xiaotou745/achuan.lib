package com.wychuan.code.common;

import org.junit.Test;
import static org.junit.Assert.*;

public class CodeCommonTest {
	@Test
	public void setFirstCharLowerTest() {
		String result = CodeCommon.setFirstCharLower("WangYuChuan");
		assertEquals("wangYuChuan", result);
	}
}
