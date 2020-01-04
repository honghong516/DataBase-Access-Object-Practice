package com.it666.jdbc.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class DbUtil {
	/*
	 *  driver=com.mysql.jdbc.Driver
		db_connection=jdbc:mysql://127.0.0.1:3306/test
		user=honghong1
		pwd=182002
	 */
	public static String driver=null;
	public static String db_conn = null;
	public static String user = null;
	public static String pwd = null;
	public static String getTime() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String nowTime = simpleDateFormat.format(new Date());
		return nowTime;
	}
	static{
		try {
			System.out.println("begin to load info");
			load();
			System.out.println("load info end");
			Class.forName(DbUtil.driver);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(DbUtil.driver + " found failed");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void load() throws IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream("source/db.properties"));
		DbUtil.driver = properties.getProperty("driver", null);
		DbUtil.db_conn = properties.getProperty("db_conn", null);
		DbUtil.user = properties.getProperty("user", null);
		DbUtil.pwd = properties.getProperty("pwd", null);
	}
	public static Connection getConnection() {
		Connection conn = null;
		try {
			System.out.println(DbUtil.db_conn + " " + DbUtil.user + " " + DbUtil.pwd);
			conn = DriverManager.getConnection(DbUtil.db_conn, DbUtil.user, DbUtil.pwd);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	public static void close(Connection conn, Statement st, ResultSet set) {
		if(set!=null) {
			try {
				set.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(st!=null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
