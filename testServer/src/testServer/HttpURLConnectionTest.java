package testServer;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

import javax.net.ssl.HttpsURLConnection;

public class HttpURLConnectionTest {

	private final String USER_AGENT = "Mozilla/5.0";

	public static void main(String[] args) throws Exception {

		HttpURLConnectionTest http = new HttpURLConnectionTest();

		System.out.println("Testing 1 - Send Http GET request");
		http.sendGet("http://35.187.194.28:8080/server/users");

		System.out.println("\nTesting 2 - Send Http POST request");
		String urlParams = "{\"name\": \"yaling\",\"email\": \"abctgygy@gmail.com\",\"password\": \"true\"}";
		http.sendPost("http://35.187.194.28:8080/server/users/register", urlParams);

	}

	// HTTP GET request
	public String sendGet(String url) throws Exception {

//		String url = "http://www.google.com/search?q=mkyong";
		

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
		
//		con.setDoOutput(true);
//		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
//		wr.writeBytes(urlParameters);
//		wr.flush();
//		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);
		


			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	
			//print result
			System.out.println(response.toString());
			return response.toString();


	}

	// HTTP POST request
	public String sendPost(String url,String urlParameters) throws Exception {

		//String url = "https://selfsolve.apple.com/wcResults.do";
		//String url ="https://1-dot-thinking-return-161419.appspot.com/userregistration";
		//String url ="https://localhost:8080/server/users/register";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type", "application/json");

		//String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
		//String urlParameters = "name=Ricket&email=anuanu79wwee39@gmail.com&password=bbt312rr33";
		//String urlParameters = "{\"name\":\"Ricket\",\"email\": \"anuanu79wwee39@gmail.com\",\"password\": \"bbt312rr33\"}";

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
		
//		String input = userstring;
//
//		OutputStream os = conn.getOutputStream();
//		os.write(input.getBytes());
//		os.flush();

		int responseCode = con.getResponseCode();
		

			
			System.out.println("\nSending 'POST' request to URL : " + url);
			System.out.println("Post parameters : " + urlParameters);
			System.out.println("Response Code : " + responseCode);
	
			BufferedReader in = new BufferedReader(
			        new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();
	
			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
	
			//print result
			System.out.println("response "+response.toString());
			
			return response.toString();


	}

}
