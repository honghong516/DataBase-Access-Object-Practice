package com.it666.jdbc.dao;

import java.io.IOException;
import java.util.List;

import com.it666.jdbc.domain.Student;

public interface IStudent {
	// 保存数据到数据库
	void save(Student su) throws IOException;

	// 更新数据
	void update(Student su) throws IOException;

	// delete
	void delete(int id) throws IOException;

	// query
	List<Student> query(String name) throws IOException;
}
