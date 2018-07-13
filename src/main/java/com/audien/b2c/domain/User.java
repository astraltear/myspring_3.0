package com.audien.b2c.domain;

public class User {

	private String userid;
	private String username;
	private String userpassword;
	private String userage;
	private String usersex;
	private String useraddr;
	private String usertelno;
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserpassword() {
		return userpassword;
	}
	public void setUserpassword(String userpassword) {
		this.userpassword = userpassword;
	}
	public String getUserage() {
		return userage;
	}
	public void setUserage(String userage) {
		this.userage = userage;
	}
	public String getUsersex() {
		return usersex;
	}
	public void setUsersex(String usersex) {
		this.usersex = usersex;
	}
	public String getUseraddr() {
		return useraddr;
	}
	public void setUseraddr(String useraddr) {
		this.useraddr = useraddr;
	}
	public String getUsertelno() {
		return usertelno;
	}
	public void setUsertelno(String usertelno) {
		this.usertelno = usertelno;
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", userpassword=" + userpassword + ", userage=" + userage + ", usersex="
				+ usersex + ", useraddr=" + useraddr + ", usertelno=" + usertelno + "]";
	}
}
