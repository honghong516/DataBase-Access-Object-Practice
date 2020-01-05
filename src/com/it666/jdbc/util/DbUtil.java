package com.it666.jdbc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.it666.jdbc.dao.IRsHandle;

public class DbUtil {
	/*
	 * driver=com.mysql.jdbc.Driver db_connection=jdbc:mysql://127.0.0.1:3306/test
	 * user=honghong1 pwd=182002
	 */

	public static DataSource ds = null;
	public static String getTime() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String nowTime = simpleDateFormat.format(new Date());
		return nowTime;
	}

	public static void load() throws IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream("source/db.properties"));
	}

	public static Connection getConnection() {
		Connection conn = null;
		/*HikariConfig config = new HikariConfig("source/db.properties");
		System.out.println(config);//一直沒有調試通過
		HikariDataSource ds = new HikariDataSource(config);*/
		Properties properties=new Properties();
		try {
			properties.load(new FileInputStream("source/db.Properties"));
			ds = DruidDataSourceFactory.createDataSource(properties);
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static int executeUpdate(String sql, Object...params){
		Connection conn = DbUtil.getConnection();
		System.out.println(conn);
		if (conn == null) {
			System.out.println("get connection failed");
			return 0;
		}
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++) {
				prepareStatement.setObject(i+1, params[i]);
			}
			int executeUpdate = prepareStatement.executeUpdate();
			return executeUpdate;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			DbUtil.close(conn, prepareStatement, null);
		}
	}
	
	public static <T>List<T> executeQuery(String sql,IRsHandle<T> rh, Object...params){
		List<T> list = null;
		ResultSet result = null;
		Connection conn = DbUtil.getConnection();
		if (conn == null) {
			System.out.println("get connection failed");
			return list;
		}
		PreparedStatement prepareStatement = null;
		try {
			prepareStatement = conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++) {
				prepareStatement.setObject(i+1, params[i]);
			}
			result = prepareStatement.executeQuery();
			list = rh.rsHandle(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DbUtil.close(conn, prepareStatement, result);
		}
		return list;
	}
	
	public static void close(Connection conn, Statement st, ResultSet set) {
		if (set != null) {
			try {
				set.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
