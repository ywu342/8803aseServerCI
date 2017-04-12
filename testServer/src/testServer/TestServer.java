package testServer;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class TestServer {
	
	HttpURLConnectionTest http = new HttpURLConnectionTest();
	String randomEmail;
	String randomUser;

	@Test
	public void testRegisterValidUser() throws Exception{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		randomEmail = timeStamp+"@gatech.edu";
		randomUser = "{\"name\": \"yaling\",\"email\": \""+randomEmail+"\",\"password\": \"true\"}";
		String output = http.sendPost("http://35.187.194.28:8080/server/users/register", randomUser);
		Gson gson = new Gson();
		String userstring = gson.toJson(user);  
		JsonObject jsonObject = (new JsonParser()).parse(output).getAsJsonObject();
		fail("Not yet implemented");
	}
	
	@Test
	public void testRegisterValidToken() throws Exception{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String randomEmailtmp = timeStamp+"gmail.com";
		String randomUsertmp = "{\"name\": \"yaling\",\"email\": \""+randomEmailtmp+"\",\"password\": \"true\"}";
		http.sendPost("http://35.187.194.28:8080/server/users/register", randomUsertmp);
		String toEncode = randomEmailtmp+"true";
		byte[] encodedBytes = Base64.getEncoder().encode(toEncode.getBytes());
		
		fail("Not yet implemented");
	}
	
	@Test
	public void testLoginValidToken() throws Exception{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String randomEmail = timeStamp+"@gatech.edu";
		String urlParams = "{\"name\": \"yaling\",\"email\": \""+randomEmail+"\",\"password\": \"true\"}";
		http.sendPost("http://35.187.194.28:8080/server/users/register", urlParams);
		fail("Not yet implemented");
	}
	
	@Test
	public void testRegisterDupEmail() throws Exception{
		fail("Not yet implemented");
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
