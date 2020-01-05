package com.it666.jdbc.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.it666.jdbc.dao.IDaoInterface;
import com.it666.jdbc.dao.IRsHandle;
import com.it666.jdbc.domain.User;
import com.it666.jdbc.util.DbUtil;

public class UserDaoImpl implements IDaoInterface<User> {

	@Override
	public void save(User su) throws IOException {
		// TODO Auto-generated method stub
		if (su == null) {
			System.out.println("object is null, can not insert into table");
			return;
		}
		String sql = "insert into user (name, pwd) values(?,?)";
		int rs = DbUtil.executeUpdate(sql, su.getName(), su.getPwd());
		System.out.println("execute result: " + rs);
	}

	@Override
	public void update(User su) throws IOException {
		// TODO Auto-generated method stub
		if (su == null) {
			System.out.println("object is null, can not update table");
			return;
		}
		String sql = "update user set name=? where id=?";		
		int rs = DbUtil.executeUpdate(sql, su.getName(),su.getId());
		System.out.println("execute result: " + rs);
	}

	@Override
	public void delete(int id) throws IOException {
		// TODO Auto-generated method stub
		String sql = "delete from user where id=?";
		int rs = DbUtil.executeUpdate(sql, id);
		System.out.println("execute result: " + rs);		
	}

	@Override
	public List<User> query(String name) throws IOException {
		// TODO Auto-generated method stub
		String sql = "select * from user where name like ?";
		List<User> list = DbUtil.executeQuery(sql, new UsrRsHandle(), name);
		return list;
	}

}

class UsrRsHandle implements IRsHandle<User>{

	@Override
	public List<User> rsHandle(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		List<User> list = new ArrayList<>();
		if (result.next()) {
			User su = new User();
			su.setId(result.getInt("id"));
			su.setName(result.getString("name"));
			su.setPwd(result.getString("pwd"));
			list.add(su);
		}
		return list;
	}
}
