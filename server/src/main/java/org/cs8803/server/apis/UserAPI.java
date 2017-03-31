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
import org.cs8803.server.services.NetClient;
import org.cs8803.server.services.UserAuth;


@Path("/users")
public class UserAPI {

	User user = new User();
	UserAuth userAuth = new UserAuth();
	NetClient netClient = new NetClient();
	
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
		  //Create a new user
		  //return Response.ok().build();
//		  Gson gson = new Gson();
//		  String jsonString = gson.toJson(user);
//		  JsonObject request = new JsonObject(jsonString);
//		  netClient.makePostCall("http://localhost:8888/userregistration", object);
		  userAuth.storeTokens(user);
		  return Response.status(200).entity("ok").build();
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