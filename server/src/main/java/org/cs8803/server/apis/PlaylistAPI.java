package org.cs8803.server.apis;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.cs8803.server.models.Playlist;
import org.cs8803.server.models.Song;
import org.cs8803.server.models.User;

@Path("/playlist")
public class PlaylistAPI {
	
	  // This method is called if TEXT_PLAIN is request
	  @GET
	  @Produces(MediaType.TEXT_PLAIN)
	  public String sayPlainTextHello() {
	    return "Hello playlists.";
	  }
	  
	  @POST
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Playlist addPlaylist(Playlist pl){
		  //Create a new playlist
		  Playlist pl_n = new Playlist();
		  return pl_n;
	  }
	  
	  @Path("/{playlistid}")
	  @DELETE
	  @Produces(MediaType.APPLICATION_JSON)
	  public Playlist removePlaylist(@PathParam("playlistid") String id){
		  //Delete an existing playlist
		  Playlist pl_n = new Playlist();
		  return pl_n;
	  }
	  
	  @PUT
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Playlist updatePlaylist(@PathParam("playlistid") String id,Playlist pl){
		//Edit an existing playlist
		pl.setPlaylistId(id);
		return pl;
	  }
	  
	  @Path("/{playlistid}/song")
	  @GET
	  @Produces("application/json")
	  public ArrayList<Song> getSongs(@PathParam("playlistid") String id) {
		  //Get all the songs of a particular play list
		  ArrayList<Song> songlist = new ArrayList<Song>();
		  return songlist;
	  }	  
	  
	  @Path("/{playlistid}/user")
	  @GET
	  @Produces("application/json")
	  public ArrayList<User> getUsers(@PathParam("playlistid") String id) {
		  //Get all the songs of a particular play list
		  ArrayList<User> userlist = new ArrayList<User>();
		  return userlist;
	  }
	  
	  @Path("/{playlistid}/song/nextsong")
	  @GET
	  @Produces("application/json")
	  public Song getNextSong(@PathParam("playlistid") String id) {
		  //Get all the songs of a particular play list
		  Song s = new Song();
		  return s;
	  }
	  
	  @Path("/{playlistid}/song")
	  @POST
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Playlist addSong(Song s){
		  //Create a new song under playlist
		  Playlist pl_n = new Playlist();
		  return pl_n;
	  }
	  
	  @Path("/{playlistid}/user")
	  @POST
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Playlist addUser(User s){
		  //Create a new user under playlist
		  Playlist pl_n = new Playlist();
		  return pl_n;
	  }
	  
	  @Path("/{playlistid}/song/{song_id}")
	  @PUT
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Playlist upvote(@PathParam("playlistid") String id,Playlist pl){
		//Sending upvote/unvote for songs
		pl.setPlaylistId(id);
		return pl;
	  }
	  
	  @Path("/{playlistid}/song/{songid}")
	  @DELETE
	  @Produces(MediaType.APPLICATION_JSON)
	  public Song removeSongFromPlaylist(@PathParam("playlistid") String plid,@PathParam("songid") String songid){
		  //Remove a song from the play list
		  Song s = new Song();
		  return s;
	  }
	  
	  @Path("/{playlistid}/users/{userid}")
	  @DELETE
	  @Produces(MediaType.APPLICATION_JSON)
	  public User removeUserFromPlaylist(@PathParam("playlistid") String plid,@PathParam("userid") String userid){
		  //Remove a user from the play list
		  User u = new User();
		  return u;
	  }
	  
	  @Path("/{playlistid}/song/{song_id}/played")
	  @PUT
	  @Consumes(MediaType.APPLICATION_JSON)
	  @Produces(MediaType.APPLICATION_JSON)
	  public Playlist played(@PathParam("playlistid") String id,Playlist pl){
		//Marking song as played
		pl.setPlaylistId(id);
		return pl;
	  }
	  

}
