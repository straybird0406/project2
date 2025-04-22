package com.ispan.bean;

import java.util.Date;

public class MemberBean implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int member_id;
	private String full_name;
	private String username;
	private String password;
	private String email;
	private int total_miles;
	private String phone_number;
	private Date registration_date;
	private boolean email_verified;
	private boolean phone_verified;
	private String membership_level;
	
	public int getMember_id() {
		return member_id;
	}
	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getTotal_miles() {
		return total_miles;
	}
	public void setTotal_miles(int total_miles) {
		this.total_miles = total_miles;
	}
	public String getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(String phone_number) {
		this.phone_number = phone_number;
	}
	public Date getRegistration_date() {
		return registration_date;
	}
	public void setRegistration_date(Date registration_date) {
		this.registration_date = registration_date;
	}
	public boolean isEmail_verified() {
		return email_verified;
	}
	public void setEmail_verified(boolean email_verified) {
		this.email_verified = email_verified;
	}
	public boolean isPhone_verified() {
		return phone_verified;
	}
	public void setPhone_verified(boolean phone_verified) {
		this.phone_verified = phone_verified;
	}
	public String getMembership_level() {
		return membership_level;
	}
	public void setMembership_level(String membership_level) {
		this.membership_level = membership_level;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	



}

