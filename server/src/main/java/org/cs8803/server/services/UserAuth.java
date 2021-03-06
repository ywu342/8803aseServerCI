package org.cs8803.server.services;

import java.util.Base64;
import java.util.HashMap;

import org.cs8803.server.models.User;

public class UserAuth {
	
	private HashMap<String, String> activeUsers = new HashMap();
	
	//Get the user with a particular user id (restricted access)
	//Create a new user
	 //Get a list of play lists associated with the user
	//Get a list of play lists associated with the owner
	//Update user
	private String encodeString(User user) {
		String toEncode = user.getEmail()+user.getPassword();
		byte[] encodedBytes = Base64.getEncoder().encode(toEncode.getBytes());
		return new String(encodedBytes);
	}
	
	public String storeTokens(User user) {
		String token = encodeString(user);
		activeUsers.put(user.getEmail(), token);
		return token;
	}
	
	public boolean verifyToken(String email, String token){
		if(!activeUsers.containsKey(email)) return false;
		String correctToken = activeUsers.get(email);
		return correctToken.equals(token);
	}
	
//	public String decodeString(String toDecode) {
//		byte[] encodedBytes = toDecode.getBytes();
//		byte[] decodedBytes = Base64.getDecoder().decode(encodedBytes);
//		System.out.println("decodedBytes " + new String(decodedBytes));
//		return new String(decodedBytes);
//	}	
}
