package com.it666.jdbc.dao;

import java.io.IOException;
import java.util.List;


public interface IDaoInterface<T> {
	// �������ݵ����ݿ�
	void save(T su) throws IOException;

	// ��������
	void update(T su) throws IOException;

	// delete
	void delete(int id) throws IOException;

	// query
	List<T> query(String name) throws IOException;
}
