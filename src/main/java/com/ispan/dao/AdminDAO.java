package com.ispan.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ispan.bean.AdminBean;

public class AdminDAO {
	
	
	private static final String SQL_getPassword = "SELECT password FROM Admin WHERE username = ?";
	private static final String SQL_insert = "INSERT INTO Admin (full_name,username,password,email,phone_number,registration_date) VALUES(?,?,?,?,?,?)";
	
	
	
	
	Connection conn;	
	public String getPassword(String username) {
		
		
		
		Context context;
		String password_search = null;
		
		try {
			
			context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SQL_getPassword);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
                password_search = rs.getString("password");
//                System.out.println("查詢到的密碼：" + password_search);  // 打印查询到的密码
            } else {
//                System.out.println("沒有找到該用戶的紀錄");
            }
			
			
			stmt.close();
			rs.close();
			conn.close();
			
			return password_search;
			
			
		}catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	        e.printStackTrace();
	    }
		return password_search;
		
		
	}
	
	
	
	
public AdminBean insert(String full_name,String username,String password,String email,String phone_number,Date registration_date) {
		
		AdminBean admin = new AdminBean();
		
		Context context;
		try {
			context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SQL_insert);
//			stmt.setInt(1, member_id);
			stmt.setString(1, full_name);
			stmt.setString(2, username);
			stmt.setString(3, password);
			stmt.setString(4, email);
			stmt.setString(5, phone_number);
			stmt.setDate(6, registration_date);
			
			stmt.execute();
			
//			member.setMember_id(member_id);
			admin.setFull_name(full_name);
			admin.setUsername(username);
			admin.setPassword(password);
			admin.setEmail(email);
			admin.setPhone_number(phone_number);
			admin.setRegistration_date(registration_date);
			
			stmt.close();
			conn.close();
			
			return admin;
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return admin;
		
		
		
	}
	
	
	
	
	
	
	
	
}
