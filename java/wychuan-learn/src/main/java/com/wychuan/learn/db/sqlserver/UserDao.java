package com.wychuan.learn.db.sqlserver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.wychuan.learn.User;

public class UserDao extends DaoBase {

	public User getById(int id) throws Exception {
		Connection connection = getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement("select * from [User] u where u.Id=?");
		prepareStatement.setInt(1, 5);
		ResultSet resultSet = prepareStatement.executeQuery();
		User user = new User();
		if (resultSet.next()) {
			user.setId(resultSet.getInt("Id"));
			user.setLoginName(resultSet.getString("LoginName"));
			user.setLoginPwd(resultSet.getString("LoginPwd"));
			user.setRegisterTime(resultSet.getTimestamp("RegisterTime"));
		}
		return user;
	}
}
