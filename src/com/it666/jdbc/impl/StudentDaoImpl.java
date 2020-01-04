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
		Connection conn = DbUtil.getConnection();
		System.out.println(conn);
		if (conn == null) {
			System.out.println("get connection failed");
			throw new IOException("get connection failed");
		}
		PreparedStatement prepareStatement = null;
		String sql = "insert into student (name, age, update_time) values(?,?,?)";
		try {
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, su.getName());
			prepareStatement.setInt(2, su.getAge());
			prepareStatement.setString(3, su.getUpdate_time());
			int executeUpdate = prepareStatement.executeUpdate();
			System.out.println("execute result: " + executeUpdate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(conn, prepareStatement, null);
		}
	}

	@Override
	public void update(Student su) throws IOException {
		// TODO Auto-generated method stub
		if (su == null) {
			System.out.println("object is null, can not update table");
			return;
		}
		Connection conn = DbUtil.getConnection();
		System.out.println(conn);
		if (conn == null) {
			System.out.println("get connection failed");
			throw new IOException("get connection failed");
		}
		PreparedStatement prepareStatement = null;
		String sql = "update student set name=? where id=?";
		try {
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, su.getName());
			prepareStatement.setInt(2, su.getId());
			int executeUpdate = prepareStatement.executeUpdate();
			System.out.println("execute result: " + executeUpdate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(conn, prepareStatement, null);
		}
	}

	@Override
	public void delete(int id) throws IOException {
		// TODO Auto-generated method stub
		Connection conn = DbUtil.getConnection();
		System.out.println(conn);
		if (conn == null) {
			System.out.println("get connection failed");
			throw new IOException("get connection failed");
		}
		PreparedStatement prepareStatement = null;
		String sql = "delete from student where id=?";
		try {
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setInt(1, id);
			int executeUpdate = prepareStatement.executeUpdate();
			System.out.println("execute result: " + executeUpdate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(conn, prepareStatement, null);
		}
	}

	@Override
	public List<Student> query(String name) throws IOException {
		// TODO Auto-generated method stub
		Connection conn = DbUtil.getConnection();
		ArrayList<Student> list = new ArrayList<Student>();
		System.out.println(conn);
		if (conn == null) {
			System.out.println("get connection failed");
			throw new IOException("get connection failed");
		}
		PreparedStatement prepareStatement = null;
		String sql = "select * from student where name like ?";
		try {
			prepareStatement = conn.prepareStatement(sql);
			prepareStatement.setString(1, name);
			ResultSet result = prepareStatement.executeQuery();
			while (result.next()) {
				Student su = new Student();
				su.setId(result.getInt(1));
				su.setName(result.getString(2));
				su.setAge(result.getInt(3));
				su.setUpdate_time(result.getString(4));
				list.add(su);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(conn, prepareStatement, null);
		}
		return list;
	}

}
