package com.it666.jdbc.test;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.it666.jdbc.dao.IDaoInterface;
import com.it666.jdbc.domain.User;
import com.it666.jdbc.impl.UserDaoImpl;

public class UserDaoTest {
	@Test
	public void deleteTest() {
		IDaoInterface<User> dao = new UserDaoImpl();
		try {
			dao.delete(2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void updateTest() {
		User user = new User();
		user.setName("honghong1");
		user.setId(1);
		IDaoInterface<User> dao = new UserDaoImpl();
		try {
			dao.update(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void saveTest() {
		User user = new User();
		user.setName("honghong2");
		user.setPwd("182002");
		IDaoInterface<User> dao = new UserDaoImpl();
		try {
			dao.save(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void queryTest() {
		IDaoInterface<User> dao = new UserDaoImpl();
		List<User> list = null;
		try {
			list = dao.query("honghong%");
			for (User su : list) {
				System.out.println(su);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
