package com.done;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.Connection;

import java.sql.Statement;

public class JdbcUtil {
	private static String ClassName;
	private static String user;
	private static String password;
	private static String url;
	static {
		try {
			InputStream in=JdbcUtil.class.getClassLoader().getResourceAsStream("/dbinfo.properties");
			Properties pp=new Properties();
			pp.load(in);
			ClassName=pp.getProperty("ClassName");
			url=pp.getProperty("url");
			password=pp.getProperty("password");
			user=pp.getProperty("user");
			Class.forName(ClassName);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection()throws Exception {
		return (Connection) DriverManager.getConnection(url, user, password);
	}
	public static void release(ResultSet rs,Statement stmt,Connection conn) {
		if(rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs=null;
		}
		if(stmt!=null) {
			try {
				stmt.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			stmt=null;
		}
		if(conn!=null) {
			try {
				conn.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			conn=null;
		}
		
	}
	
}
