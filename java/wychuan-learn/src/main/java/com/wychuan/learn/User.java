package com.wychuan.learn;

import java.util.Date;

public class User {
	private int id;
	private String loginName;
	private String loginPwd;
	private Date registerTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	@Override
	public String toString() {
		return String.format("[id]:%s \r\n[loginname]:%s\r\n[password]:%s\r\n[time]:%s", getId(), getLoginName(),
				getLoginPwd(), getRegisterTime());
	}
}
