package com.it666.jdbc.test;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.it666.jdbc.dao.IStudent;
import com.it666.jdbc.domain.Student;
import com.it666.jdbc.impl.StudentDaoImpl;
import com.it666.jdbc.util.DbUtil;

public class StudentDaoTest {

	@Test
	public void deleteTest() {
		IStudent dao = new StudentDaoImpl();
		try {
			dao.delete(1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void updateTest() {
		Student student = new Student();
		student.setName("honghong11");
		student.setId(1);
		IStudent dao = new StudentDaoImpl();
		try {
			dao.update(student);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void saveTest() {
		Student student = new Student();
		student.setName("honghong4");
		student.setAge(21);
		student.setUpdate_time(DbUtil.getTime());
		IStudent dao = new StudentDaoImpl();
		try {
			dao.save(student);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void queryTest() {
		IStudent dao = new StudentDaoImpl();
		List<Student> list = null;
		try {
			list = dao.query("honghong%");
			for (Student su : list) {
				System.out.println(su);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void implTest() {
		IStudent studentDaoImpl = new StudentDaoImpl();

	}

	public static void main(String[] args) {
		new DbUtil();
	}
}
