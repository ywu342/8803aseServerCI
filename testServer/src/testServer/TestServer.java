package testServer;

import static org.junit.Assert.assertEquals;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import com.google.gson.*;
import org.junit.Test;

public class TestServer {
	
	HttpURLConnectionTest http = new HttpURLConnectionTest();

@Test
	public void testRegisterResponseCodeValidUser() throws Exception{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
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
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String randomEmailtmp = timeStamp+"@gmail.com";
		String randomUsertmp = "{\"name\": \"yaling\",\"email\": \""+randomEmailtmp+"\",\"password\": \"true\"}";
		String output = http.sendPost("http://35.187.194.28:8080/server/users/register", randomUsertmp);
		//System.out.println("initial register:" + output);
		String toEncode = randomEmailtmp+"true";
		byte[] encodedBytes = Base64.getEncoder().encode(toEncode.getBytes());
		JsonObject jsonObject = (new JsonParser()).parse(output).getAsJsonObject();		
		//System.out.println("ok ok " + jsonObject.get("token") + " second:" + new String(encodedBytes) );
		assertEquals(jsonObject.get("token").toString(), "\""+ new String(encodedBytes) + "\"");	
	
	}
	
	@Test
	public void testLoginResponseCodeValidUser() throws Exception{
		//Register the user
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
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
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
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
	}
	
	
	@Test
	public void testRegisterDuplicateEmail() throws Exception{	
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String randomEmailtmp = timeStamp+"@gmail.com";
		String randomUsertmp = "{\"name\": \"yaling\",\"email\": \""+randomEmailtmp+"\",\"password\": \"true\"}";
		String output = http.sendPost("http://35.187.194.28:8080/server/users/register", randomUsertmp);
		
		
		output = http.sendPost("http://35.187.194.28:8080/server/users/register", randomUsertmp);
		assertEquals(output, "Authentication Failed");

	}
	

	
	@Test
	public void testLoginTUserFPass() throws Exception{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String randomEmail = timeStamp+"@gatech.edu";
		String randomPass = "true";
		String randomUser = "{\"name\": \"yaling\",\"email\": \""+randomEmail+"\",\"password\": \""+randomPass+"\"}";
		http.sendPost("http://35.187.194.28:8080/server/users/register", randomUser);
	
		
		//login the same email but different password
		randomPass = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String urlParams = "{\"email\": \""+randomEmail+"\",\"password\": \""+randomPass+"\"}";
		String output = http.sendPost("http://35.187.194.28:8080/server/users/login", urlParams);
		assertEquals(output, "Authentication Failed");	
	}

	@Test
	public void testLoginFUserFPass() throws Exception{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String randomEmail = timeStamp+"@gatech.edu";
		String randomPass = "true";
		String randomUser = "{\"name\": \"yaling\",\"email\": \""+randomEmail+"\",\"password\": \""+randomPass+"\"}";
		http.sendPost("http://35.187.194.28:8080/server/users/register", randomUser);
	
		
		//login the different email but different password
		randomPass = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		randomEmail = timeStamp+"@gatech.com";
		String urlParams = "{\"email\": \""+randomEmail+"\",\"password\": \""+randomPass+"\"}";
		String output = http.sendPost("http://35.187.194.28:8080/server/users/login", urlParams);
		assertEquals(output, "Authentication Failed");	
	}

	@Test
	public void testLoginFUserTPass() throws Exception{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String randomEmail = timeStamp+"@gatech.edu";
		String randomPass = "true";
		String randomUser = "{\"name\": \"yaling\",\"email\": \""+randomEmail+"\",\"password\": \""+randomPass+"\"}";
		http.sendPost("http://35.187.194.28:8080/server/users/register", randomUser);
	
		
		//login the different email but same password
		randomEmail += "d";
		String urlParams = "{\"email\": \""+randomEmail+"\",\"password\": \""+randomPass+"\"}";
		String output = http.sendPost("http://35.187.194.28:8080/server/users/login", urlParams);
		assertEquals(output, "Authentication Failed");		
	}
	
	@Test
	public void testRegisterMissUsername() throws Exception{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String randomEmail = timeStamp+"@gatech.edu";
		String randomPass = "true";
		String randomUser = "{\"name\": \"\",\"email\": \""+randomEmail+"\",\"password\": \""+randomPass+"\"}";
		String output = http.sendPost("http://35.187.194.28:8080/server/users/register", randomUser);
		assertEquals(output, "Authentication Failed");
	}
	
	@Test
	public void testRegisterMissPassword() throws Exception{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String randomEmail = timeStamp+"@gatech.edu";
		String randomUser = "{\"name\": \"yaling\",\"email\": \""+randomEmail+"\",\"password\": \""+""+"\"}";
		String output = http.sendPost("http://35.187.194.28:8080/server/users/register", randomUser);	
		assertEquals(output, "Authentication Failed");
	}
	
	@Test
	public void testRegisterMissEmail() throws Exception{
		String randomPass = "true";
		String randomUser = "{\"name\": \"yaling\",\"password\": \""+randomPass+"\",\"email\": \""+""+"\"}";
		String output = http.sendPost("http://35.187.194.28:8080/server/users/register", randomUser);
		assertEquals(output, "Authentication Failed");
	}
	
	@Test
	public void testLoginMissEmail() throws Exception{
		String randomPass = "true";
		String randomUser = "{\"email\": \""+""+"\",\"password\": \""+randomPass+"\"}";
		String output = http.sendPost("http://35.187.194.28:8080/server/users/login", randomUser);
		assertEquals(output, "Authentication Failed");
	}
	
	@Test
	public void testLoginMissPassword() throws Exception{
		// register a new user
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String randomEmail = timeStamp+"@gatech.edu";
		String randomPass = "true";
		String randomUser = "{\"name\": \"yaling\",\"email\": \""+randomEmail+"\",\"password\": \""+randomPass+"\"}";
		http.sendPost("http://35.187.194.28:8080/server/users/register", randomUser);
		
		// leave password blank
		randomUser = "{\"name\": \"yaling\",\"email\": \""+randomEmail+"\",\"password\": \""+""+"\"}";
		String output = http.sendPost("http://35.187.194.28:8080/server/users/login", randomUser);
		assertEquals(output, "Authentication Failed");
	}
	
	@Test
	public void testRegisterInvalidEmail() throws Exception{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String randomEmail = timeStamp;
		String randomPass = "true";
		String randomUser = "{\"name\": \"yaling\",\"email\": \""+randomEmail+"\",\"password\": \""+randomPass+"\"}";
		String output = http.sendPost("http://35.187.194.28:8080/server/users/register", randomUser);
		assertEquals(output, "Authentication Failed");
	}
	
	@Test
	public void testLoginInvalidEmail() throws Exception{
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String randomEmail = timeStamp;
		String randomPass = "true";
		String randomUser = "{\"email\": \""+randomEmail+"\",\"password\": \""+randomPass+"\"}";
		String output = http.sendPost("http://35.187.194.28:8080/server/users/login", randomUser);
		assertEquals(output, "Authentication Failed");
	}
}
