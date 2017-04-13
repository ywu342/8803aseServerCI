package testServer;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;

import org.junit.Test;
import com.google.gson.*;


public class TestServer {
	
	HttpURLConnectionTest http = new HttpURLConnectionTest();
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	
	
	

/*	@Test
	public void testRegisterResponseCodeValidUser() throws Exception{
		String randomEmail = timeStamp+"@gatech.edu";
		String randomPass = "true";
		String randomUser = "{\"name\": \"yaling\",\"email\": \""+randomEmail+"\",\"password\": \""+randomPass+"\"}";
		String output = http.sendPost("http://35.187.194.28:8080/server/users/register", randomUser);
		JsonObject jsonObject = (new JsonParser()).parse(output).getAsJsonObject();
		//System.out.println("valid email : " + output);
		assertEquals(jsonObject.get("code").toString(), "\"201\"");
	}

	
	
	@Test
	public void testRegisterTokenValidUser() throws Exception{
		String randomEmailtmp = timeStamp+"@gmail.com";
		String randomUsertmp = "{\"name\": \"yaling\",\"email\": \""+randomEmailtmp+"\",\"password\": \"true\"}";
		String output = http.sendPost("http://35.187.194.28:8080/server/users/register", randomUsertmp);
		System.out.println("initial register:" + output);
		String toEncode = randomEmailtmp+"true";
		byte[] encodedBytes = Base64.getEncoder().encode(toEncode.getBytes());
		JsonObject jsonObject = (new JsonParser()).parse(output).getAsJsonObject();		
		//System.out.println("ok ok " + jsonObject.get("token") + " second:" + new String(encodedBytes) );
		assertEquals(jsonObject.get("token").toString(), "\""+ new String(encodedBytes) + "\"");	
	
	}
	
	@Test
	public void testLoginResponseCodeValidUser() throws Exception{
		//Register the user
		String randomEmail = timeStamp+"@gatech.edu";
		String randomPass = "true";
		String randomUser = "{\"name\": \"yaling\",\"email\": \""+randomEmail+"\",\"password\": \""+randomPass+"\"}";
		http.sendPost("http://35.187.194.28:8080/server/users/register", randomUser);
		
		
		//login the same user
		String urlParams = "{\"email\": \""+randomEmail+"\",\"password\": \""+randomPass+"\"}";
		String output = http.sendPost("http://35.187.194.28:8080/server/users/login", urlParams);
		JsonObject jsonObject = (new JsonParser()).parse(output).getAsJsonObject();

		assertEquals(jsonObject.get("code").toString(), "\"200\"");		
	}

	
	@Test
	public void testLoginTokenValidUser() throws Exception{
		//Register the user
		String randomEmail = timeStamp+"@gatech.edu";
		String randomPass = "true";
		String randomUser = "{\"name\": \"yaling\",\"email\": \""+randomEmail+"\",\"password\": \""+randomPass+"\"}";
		http.sendPost("http://35.187.194.28:8080/server/users/register", randomUser);
		
		//Login the same user
		String urlParams = "{\"name\": \"yaling\",\"email\": \""+randomEmail+"\",\"password\": \""+randomPass+"\"}";
		String output = http.sendPost("http://35.187.194.28:8080/server/users/login", urlParams);
		JsonObject jsonObject = (new JsonParser()).parse(output).getAsJsonObject();

		String toEncode = randomEmail+randomPass;
		byte[] encodedBytes = Base64.getEncoder().encode(toEncode.getBytes());
		
		assertEquals(jsonObject.get("token").toString(), "\""+ new String(encodedBytes) + "\"");		
	}*/
	
	
	@Test
	public void testRegisterDuplicateEmail() throws Exception{

		
		
		//String randomUser = "{\"name\": \"yaling\",\"email\": \""+	+"\",\"password\": \""+randomPass+"\"}";
		String randomUser = "{\"name\": \"Ricket34\",\"email\": \"anuanu79wwee3493@gmail.com\",\"password\": \""+"trueue"+"\"}";
		String output = http.sendPost("http://35.187.194.28:8080/server/users/register", randomUser);
		System.out.println("dupemail : " + output);
		//JsonObject jsonObject = (new JsonParser()).parse(output).getAsJsonObject();

		
		//assertEquals(jsonObject.get("code").toString(), "\"201\"");

	}
	
	@Test
	public void testLoginTUserTPass() throws Exception{
		fail("Not yet implemented");
	}
	
	@Test
	public void testLoginTUserFPass() throws Exception{
		fail("Not yet implemented");
	}

	@Test
	public void testLoginFUserFPass() throws Exception{
		fail("Not yet implemented");
	}

	@Test
	public void testLoginFUserTPass() throws Exception{
		fail("Not yet implemented");
	}
	
	@Test
	public void testRegisterMissUsername() throws Exception{
		fail("Not yet implemented");
	}
	
	@Test
	public void testRegisterMissPassword() throws Exception{
		fail("Not yet implemented");
	}
	
	@Test
	public void testRegisterMissEmail() throws Exception{
		fail("Not yet implemented");
	}
	
	@Test
	public void testLoginMissUsername() throws Exception{
		fail("Not yet implemented");
	}
	
	@Test
	public void testLoginMissPassword() throws Exception{
		fail("Not yet implemented");
	}
	
	@Test
	public void testRegisterInvalidEmail() throws Exception{
		fail("Not yet implemented");
	}
	
	@Test
	public void testLoginInvalidEmail() throws Exception{
		fail("Not yet implemented");
	}
}
