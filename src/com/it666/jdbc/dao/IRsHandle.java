package com.it666.jdbc.dao;

import java.sql.ResultSet;
import java.util.List;

public interface IRsHandle<T> {
	public List<T> rsHandle(ResultSet result) throws Exception;
}
