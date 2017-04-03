package com.gatech.userreg;

public class UserError {
	private int code;
	private String descrip;
	private User smallUser;
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public User getSmallUser() {
		return smallUser;
	}
	public void setSmallUser(User smallUser) {
		this.smallUser = smallUser;
	}
	public int getCode() {
		return code;
	}
	
	public void setCode(int code) {
		this.code = code;
	}
	
}
