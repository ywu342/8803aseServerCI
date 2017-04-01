package com.gatech.userreg;

public class UserError {
	private int code;
	private String descrip;
	private User smallUser;
	public void setCode(int code) {
		this.code = code;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	public void setData(User usr) {
		smallUser = usr;
	}
	public String getDescrip() {
		return descrip;
	}
}
