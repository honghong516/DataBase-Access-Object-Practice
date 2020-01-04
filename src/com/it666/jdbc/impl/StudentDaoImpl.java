package com.it666.jdbc.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.it666.jdbc.dao.IStudent;
import com.it666.jdbc.domain.Student;
import com.it666.jdbc.util.DbUtil;

public class StudentDaoImpl implements IStudent {
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
		List<Student> list = DbUtil.executeQuery(sql, name);
		return list;
	}

}
