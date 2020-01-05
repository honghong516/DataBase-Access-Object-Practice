package com.it666.jdbc.dao;

import java.io.IOException;
import java.util.List;


public interface IDaoInterface<T> {
	// 保存数据到数据库
	void save(T su) throws IOException;

	// 更新数据
	void update(T su) throws IOException;

	// delete
	void delete(int id) throws IOException;

	// query
	List<T> query(String name) throws IOException;
}
