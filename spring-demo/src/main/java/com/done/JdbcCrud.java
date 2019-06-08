package com.done;

import java.io.IOException;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;



public class JdbcCrud {
	public static void add() {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
	try {
		conn=JdbcUtil.getConnection();
		stmt=(Statement) conn.createStatement();
		int i=stmt.executeUpdate("insert into Employee(id,name,grade) values('4','nihao','90')");
		if(1!=i)  throw  new  Exception();
		
	} catch (Exception e) {
		throw new RuntimeException(e);
	}finally {
		JdbcUtil.release(rs, stmt, conn);
	}
	}
	public void update() {
		
	}
	public void queryone() {
		
	}
	public void queryAll() {
		
	}
	public void delete() {
		
	}
	public static void main(String[] args) throws IOException {
		add();
		
		
	}
	
}
