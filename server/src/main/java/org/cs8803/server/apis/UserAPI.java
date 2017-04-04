package org.cs8803.server.apis;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.cs8803.server.models.Playlist;
import org.cs8803.server.models.User;
import org.cs8803.server.services.HttpURLConnectionExample;
import org.cs8803.server.services.UserAuth;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Path("/users")
public class UserAPI {

	User user = new User();
	UserAuth userAuth = new UserAuth();
	HttpURLConnectionExample httpob = new HttpURLConnectionExample();
	
	  // This method is called if TEXT_PLAIN is request
	  @GET
	  @Produces(MediaType.TEXT_PLAIN)
	  public String sayPlainTextHello() {
	    return "Hello It's users.";
	  }
	  
	 
	  @Path("/{userid}")
	  @GET
	  @Produces("application/json")
	  public User getUser(@PathParam("userid") String id) {
		  //Get the user with a particular user id (restricted access)
		  User u = new User();
		  u.setId(id);
		  u.setName("Yaling");
		  u.setPassword("true");
		  u.setEmail("email");
		  return u;
	  }
	  
	  // FOR REGISTRATION
	  @Path("/register")
	  @POST
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response addUser(User user){

		  Gson gson = new Gson();
		  String userstring = gson.toJson(user);  		  
		  String output="";
		  try{
			  
			  output = httpob.sendPost("https://1-dot-thinking-return-161419.appspot.com/userregistration", userstring);

			  JsonObject jsonObject = (new JsonParser()).parse(output).getAsJsonObject();
			  //System.out.println("output 2: "+jsonObject.toString());
			  System.out.println("code "+jsonObject.get("code"));
			  
			  if (jsonObject.get("code").getAsInt()==200)
			  {
//				  System.out.println("Entering");
				  String token = userAuth.storeTokens(user);
//				  JsonElement jsonElement = stringToJsonElement(token);
				  
				  //jsonObject.add("token", token);
				  String jsonToken = "{\"token\":\"" + token +"\"}";
				  return Response.status(201).entity(jsonToken).header("Access-Control-Allow-Origin", "*")
					      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
					      .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
				  
			  }
		  }
		  catch(Exception e)
		  {
			  System.out.println(e);
		  }
		  
		  //Depends on what the backend returns
		  return Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*")
			      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
			      .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	  }
	  
	  //LOGIN
	  @Path("/login")
	  @POST
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response loginUser(User user){
	  
		  String urlParameters = "name="+user.getName()+"&email="+user.getEmail()+"&password="+user.getPassword();
		  
		  String output="";
		  try{
			  
			  output = httpob.sendGet("https://1-dot-thinking-return-161419.appspot.com/userregistration?"+urlParameters);
			  JsonObject jsonObject = (new JsonParser()).parse(output).getAsJsonObject();
			  if(jsonObject.get("code").getAsInt()==200)
			  {
				  String token = userAuth.storeTokens(user);
				  String jsonToken = "{\"token\":\"" + token +"\"}";
				  return Response.status(201).entity(jsonToken).header("Access-Control-Allow-Origin", "*")
					      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
					      .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();			  
			  }
			  
		  }
		  catch(Exception e)
		  {
			  System.out.println(e);
		  }
		  
		  
		  System.out.println("Output hi: "+output);
		  //Depends on what the back end returns  
		  return Response.status(200).header("Access-Control-Allow-Origin", "*")
			      .header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
			      .header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With").build();
	  }
	  
	  
	  
	  @Path("/{userid}/member/playlist")
	  @GET
	  @Produces("application/json")
	  public ArrayList<Playlist> getPlaylistsOfUser(@PathParam("userid") String id) {
		  
		  //Get a list of play lists associated with the user
		  ArrayList<Playlist> temp = new ArrayList<Playlist>();
		  return temp;
	  }
	  
	  @Path("/{userid}/owner/playlist")
	  @GET
	  @Produces("application/json")
	  public ArrayList<Playlist> getPlaylistsOfOwner(@PathParam("userid") String id) {
		  
		  //Get a list of play lists associated with the owner
		  ArrayList<Playlist> temp = new ArrayList<Playlist>();
		  return temp;
	  }
	  
	  
		@Path("/{userid}")
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		public User updateUser(@PathParam("userid") String id,User u){
			u.setId(id);
			//fetch functionality to update 
			return u;
		}
		
		
}