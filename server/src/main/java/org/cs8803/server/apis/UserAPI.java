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
import org.cs8803.server.services.NetClient;
import org.cs8803.server.services.UserAuth;

import com.google.gson.Gson;


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

		  
		  //String userstring = user.toJsonString();
		  
		  System.out.println(userstring);
		  //userAuth.storeTokens(user);
		  System.out.println("---------------------------------------output-----------------");
		  //String output = netClient.makePostCall("http://1-dot-thinking-return-161419.appspot.com/userregistration",userstring);
		  

		  String output="";
		  try{
			  
			  output = httpob.sendPost("https://1-dot-thinking-return-161419.appspot.com/userregistration", userstring);
			  if(output.equals("Your account has been created."))
			  {
				  String token = userAuth.storeTokens(user);
				  String jsonToken = "{\"token\":\"" + token +"\"}";
				  System.out.println("Madhu");
				  return Response.status(201).entity(jsonToken).build();
				  
			  }
		  }
		  catch(Exception e)
		  {
			  System.out.println(e);
		  }
		  
		  
		  System.out.println("Output : "+output);
		  //Depends on what the backend returns
		  return Response.status(200).entity(output).build();
	  }
	  
	  //LOGIN
	  @Path("/login")
	  @POST
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response loginUser(User user){
	  
		  String urlParameters = "name=Ricket&email=anuanu79wwee39@gmail.com&password=bbt312rr33";
		  
		  //System.out.println(userstring);
		  //userAuth.storeTokens(user);
		  System.out.println("---------------------------------------login-----------------");
		  //String output = netClient.makePostCall("http://1-dot-thinking-return-161419.appspot.com/userregistration",userstring);
		  

		  String output="";
		  try{
			  
			  output = httpob.sendGet("https://1-dot-thinking-return-161419.appspot.com/userregistration?"+urlParameters);
			  if(output.startsWith("User Found"))
			  {
				  String token = userAuth.storeTokens(user);
				  String jsonToken = "{\"token\":\"" + token +"\"}";
				  return Response.status(201).entity(jsonToken).build();			  
			  }
			  
		  }
		  catch(Exception e)
		  {
			  System.out.println(e);
		  }
		  
		  
		  System.out.println("Output hi: "+output);
		  //Depends on what the backend returns  
		  return Response.status(200).entity(output).build();
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