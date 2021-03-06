package com.audien.b2c.domain;

public class MemberVO {
	
	private int userpk;
	private String userid;
	private String userpw;
	private String username;
	private String email;
	public int getUserpk() {
		return userpk;
	}
	public void setUserpk(int userpk) {
		this.userpk = userpk;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUserpw() {
		return userpw;
	}
	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "MemberVO [userpk=" + userpk + ", userid=" + userid + ", userpw=" + userpw + ", username=" + username + ", email=" + email + "]";
	}
}
