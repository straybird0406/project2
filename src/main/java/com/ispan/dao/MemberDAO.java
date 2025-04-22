package com.ispan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ispan.bean.MemberBean;

public class MemberDAO {
	
	private static final String SQL_getOne = "SELECT * FROM member WHERE member_id = ?";
	private static final String SQL_getAll = "SELECT * FROM member";
	
	private static final String SQL_getFuzzy = "SELECT * FROM member WHERE full_name LIKE ?";
	
	private static final String SQL_insert = "INSERT INTO member (full_name,username,password,email,total_miles,phone_number,registration_date,email_verified,phone_verified,membership_level) VALUES(?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_delete = "DELETE FROM member WHERE member_id = ?";
	private static final String SQL_update = "UPDATE member SET full_name = ?, username = ?, password = ?,email = ?,total_miles = ?,phone_number = ?,registration_date = ?,email_verified = ?,phone_verified = ?,membership_level = ?  WHERE member_id = ?";
	
	
	
Connection conn;	
	public MemberBean getOne(String member_id) {
		
		
		
		Context context;
		MemberBean member = new MemberBean();
		
		try {
			
			context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SQL_getOne);
			stmt.setString(1, member_id);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.next()) {
				member = new MemberBean();
				member.setMember_id(rs.getInt("Member_id"));
				member.setFull_name(rs.getString("Full_name"));
				member.setUsername(rs.getString("Username"));
				member.setPassword(rs.getString("Password"));
				member.setEmail(rs.getString("Email"));
				member.setTotal_miles(rs.getInt("total_miles"));
				member.setPhone_number(rs.getString("phone_number"));
				member.setRegistration_date(rs.getDate("Registration_date"));
				member.setEmail_verified(rs.getBoolean("Email_verified"));
				member.setPhone_verified(rs.getBoolean("Phone_verified"));
				member.setMembership_level(rs.getString("Membership_level"));
			}
			stmt.close();
			rs.close();
			conn.close();
			
			return member;
			
			
		}catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	        e.printStackTrace();
	    }
		return member;
		
		
	}
	public List<MemberBean> getFuzzy(String fuzzyName){
		Context context;
		MemberBean member = null;
		List<MemberBean> members = new ArrayList<>();
		
		
		try {
			context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SQL_getFuzzy);
			stmt.setString(1, "%" + fuzzyName + "%");
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				member = new MemberBean();
				member.setMember_id(rs.getInt("Member_id"));
				member.setFull_name(rs.getString("Full_name"));
				member.setUsername(rs.getString("Username"));
				member.setPassword(rs.getString("Password"));
				member.setEmail(rs.getString("Email"));
				member.setTotal_miles(rs.getInt("total_miles"));
				member.setPhone_number(rs.getString("phone_number"));
				member.setRegistration_date(rs.getDate("Registration_date"));
				member.setEmail_verified(rs.getBoolean("Email_verified"));
				member.setPhone_verified(rs.getBoolean("Phone_verified"));
				member.setMembership_level(rs.getString("Membership_level"));
				members.add(member);
			}
			stmt.close();
			rs.close();
			conn.close();
			return members;
			
			
		} catch (NamingException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return members;
		
		
	}
	
	
	
	
	
	
	public List<MemberBean> getAll() {
		
		
		Context context;
		MemberBean member = null;
		List<MemberBean> members = new ArrayList<>();
		
		
		try {
		context = new InitialContext();
		DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
		conn = ds.getConnection();	
		PreparedStatement stmt = conn.prepareStatement(SQL_getAll);
		ResultSet rs = stmt.executeQuery();
				
		while(rs.next()) {
			member = new MemberBean();
			member.setMember_id(rs.getInt("Member_id"));
			member.setFull_name(rs.getString("Full_name"));
			member.setUsername(rs.getString("Username"));
			member.setPassword(rs.getString("Password"));
			member.setEmail(rs.getString("Email"));
			member.setTotal_miles(rs.getInt("total_miles"));
			member.setPhone_number(rs.getString("phone_number"));
			member.setRegistration_date(rs.getDate("Registration_date"));
			member.setEmail_verified(rs.getBoolean("Email_verified"));
			member.setPhone_verified(rs.getBoolean("Phone_verified"));
			member.setMembership_level(rs.getString("Membership_level"));
			members.add(member);
		}
		stmt.close();
		rs.close();
		conn.close();
		return members;
		
		}catch (SQLException e) {
	        System.out.println("SQL Error: " + e.getMessage());
	        e.printStackTrace();
	    } catch (Exception e) {
	        System.out.println("Error: " + e.getMessage());
	        e.printStackTrace();
	    }
		return members;
		
	}
	
	
	public MemberBean insert(String full_name,String username,String password,String email,int total_miles,String phone_number,Date registration_date,Boolean email_verified,Boolean phone_verified,String membership_level) {
		
		MemberBean member = new MemberBean();
		
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
			stmt.setInt(5, total_miles);
			stmt.setString(6, phone_number);
			stmt.setDate(7, registration_date);
			stmt.setBoolean(8, email_verified);
			stmt.setBoolean(9, phone_verified);
			stmt.setString(10, membership_level);
			
			stmt.execute();
			
//			member.setMember_id(member_id);
			member.setFull_name(full_name);
			member.setUsername(username);
			member.setPassword(password);
			member.setEmail(email);
			member.setTotal_miles(total_miles);
			member.setPhone_number(phone_number);
			member.setRegistration_date(registration_date);
			member.setEmail_verified(email_verified);
			member.setPhone_verified(phone_verified);
			member.setMembership_level(membership_level);
			
			stmt.close();
			conn.close();
			
			return member;
			
		} catch (NamingException | SQLException e) {
			e.printStackTrace();
		}
		return member;
		
		
		
	}
	
	
	
	public MemberBean delete(int member_id) {
	    MemberBean member = null;

	    Context context;
	    try {
	        context = new InitialContext();
	        DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/servdb");
	        conn = ds.getConnection();
	        PreparedStatement stmt = conn.prepareStatement(SQL_delete);
	        stmt.setInt(1, member_id);
	        
	        int affectedRows = stmt.executeUpdate();
	        System.out.println("Affected rows: " + affectedRows);  // 用來檢查 affectedRows

	        if (affectedRows == 0) {
	            // 如果沒有刪除任何資料
	            return null;  // 沒有刪除，返回 null
	        }

	        member = new MemberBean();
	        member.setMember_id(member_id);  // 設置刪除的 member_id
	        
	        stmt.close();
	        conn.close();
	        return member;  // 返回刪除的會員
	    } catch (NamingException | SQLException e) {
	        e.printStackTrace();
	    }

	    return member;  // 如果發生錯誤，返回 null
	}
	
	public MemberBean update(int member_id,String full_name,String username,String password,String email,int total_miles,String phone_number,Date registration_date,Boolean email_verified,Boolean phone_verified,String membership_level) {
		
		Context context;
		MemberBean member = new MemberBean();
		
		
		try {
			context = new InitialContext();
			DataSource ds = (DataSource)context.lookup("java:/comp/env/jdbc/servdb");
			conn = ds.getConnection();
			PreparedStatement stmt = conn.prepareStatement(SQL_update);
			
			stmt.setString(1, full_name);
			stmt.setString(2, username);
			stmt.setString(3, password);
			stmt.setString(4, email);
			stmt.setInt(5, total_miles);
			stmt.setString(6, phone_number);
			stmt.setDate(7, registration_date);
			stmt.setBoolean(8, email_verified);
			stmt.setBoolean(9, phone_verified);
			stmt.setString(10, membership_level);
			stmt.setInt(11, member_id);
			stmt.execute();
			
			

			member.setMember_id(member_id);
			member.setFull_name(full_name);
			member.setUsername(username);
			member.setPassword(password);
			member.setEmail(email);
			member.setTotal_miles(total_miles);
			member.setPhone_number(phone_number);
			member.setRegistration_date(registration_date);
			member.setEmail_verified(email_verified);
			member.setPhone_verified(phone_verified);
			member.setMembership_level(membership_level);
			
			
			stmt.close();
			conn.close();
			
			return member;
			
			
			
			
		} catch (NamingException | SQLException e) {

			e.printStackTrace();
		}
		return member;
		

		
		
		
		
		
	}
	

	
	

}
