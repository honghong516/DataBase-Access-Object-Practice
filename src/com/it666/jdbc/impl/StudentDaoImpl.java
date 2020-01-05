package com.it666.jdbc.impl;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.it666.jdbc.dao.IDaoInterface;
import com.it666.jdbc.dao.IRsHandle;
import com.it666.jdbc.domain.Student;
import com.it666.jdbc.util.DbUtil;

public class StudentDaoImpl implements IDaoInterface<Student> {
	// load params from conf

	@Override
	public void save(Student su) throws IOException {
		// TODO Auto-generated method stub
		if (su == null) {
			System.out.println("object is null, can not insert into table");
			return;
		}
		String sql = "insert into student (name, age, update_time) values(?,?,?)";
		int rs = DbUtil.executeUpdate(sql, su.getName(), su.getAge(), su.getUpdate_time());
		System.out.println("execute result: " + rs);
	}

	@Override
	public void update(Student su) throws IOException {
		// TODO Auto-generated method stub
		if (su == null) {
			System.out.println("object is null, can not update table");
			return;
		}
		String sql = "update student set name=? where id=?";		
		int rs = DbUtil.executeUpdate(sql, su.getName(), su.getId());
		System.out.println("execute result: " + rs);
	}

	@Override
	public void delete(int id) throws IOException {
		// TODO Auto-generated method stub
		String sql = "delete from student where id=?";
		int rs = DbUtil.executeUpdate(sql, id);
		System.out.println("execute result: " + rs);
	}

	@Override
	public List<Student> query(String name) throws IOException {
		// TODO Auto-generated method stub
		String sql = "select * from student where name like ?";
		List<Student> list = DbUtil.executeQuery(sql, new StuRsHandle(), name);
		return list;
	}

}

class StuRsHandle implements IRsHandle<Student>{

	@Override
	public List<Student> rsHandle(ResultSet result) throws SQLException {
		// TODO Auto-generated method stub
		List<Student> list = new ArrayList<>();
		while (result.next()) {
			Student su = new Student();
			su.setId(result.getInt(1));
			su.setName(result.getString(2));
			su.setAge(result.getInt(3));
			su.setUpdate_time(result.getString(4));
			list.add(su);
		}
		return list;
	}
	
}
