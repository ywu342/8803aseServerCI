package org.cs8803.server.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	
	private String name, id, password, email;
	
	public User() {}
	
	public User(String id, String password, String name, String email){
		this.id=id;
		this.email=email;
		this.name=name;
		this.password=password;		
	}
	
    public String getName() {
        return name;
	}
	public void setName(String name) {
	        this.name = name;
	}
    public String getId() {
        return id;
	}
	public void setId(String id) {
	        this.id = id;
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
}