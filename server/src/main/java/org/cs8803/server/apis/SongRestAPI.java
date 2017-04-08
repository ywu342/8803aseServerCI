package org.cs8803.server.apis;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.cs8803.server.models.Song;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Path("/songs")
public class SongRestAPI {
	
	Song s = new Song();
	ArrayList<Song> songlist = new ArrayList<Song>();
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String hw(){
		return "hello songs!";
	}
	
	@Path("/{songid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Song getSongWithId(@PathParam("songid") String id){	
		//Fetch the song from database	
		//String testSong = "[{\"id\":\"1\",\"title\":\"Wonderful\",\"artist\":\"Yaling\",\"genre\":\"pop\",\"url\":\"yaling.com/Wonderful.mp3\",{\"id\":\"1\",\"title\":\"Wonderful\",\"artist\":\"Yaling\",\"genre\":\"pop\",\"url\":\"yaling.com/Wonderful.mp3\"}]";
		String testSong = "{\"id\":\"1\",\"title\":\"Wonderful\",\"artist\":\"Yaling\",\"genre\":\"classic\",\"url\":\"yaling.com/Wonderful.mp3\"}";
		try {
			JsonObject jsonObject = (new JsonParser()).parse(testSong).getAsJsonObject();
			System.out.println("test song api: songs to json object -> "+jsonObject.toString());
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		Song song = new Song();
		song.setArtist("Yaling");
		song.setGenre("classic");
		song.setId("1");
		song.setTitle("Wonderful");
		song.setUrl("yaling.com/Wonderful.mp3");
		Gson gson = new Gson();
		String songstring = gson.toJson(song);  
		//System.out.println("test song api: song to json -> "+songstring);
		return song;
	}

	@Path("/songtitles/{songtitle}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getSongWithTitle(@PathParam("songtitle") String title){	
		//Fetch the song list from database	
		Song song = new Song();
		song.setArtist("Yaling");
		song.setGenre("classic");
		song.setId("1");
		song.setTitle("Wonderful");
		song.setUrl("yaling.com/Wonderful.mp3");
		Song song1 = new Song();
		song1.setArtist("Blah");
		song1.setGenre("classic");
		song1.setId("2");
		song1.setTitle("Wonderful");
		song1.setUrl("yaling.com/Wonderful.mp3");

		ArrayList<Object> list = new ArrayList();
		list.add(song);
		list.add(song1);
		// convert list of songs to Json string
		Gson gson = new Gson();
		String songstring = gson.toJson(list);
		//System.out.println(songstring);
		// convert list json string to json array
		JsonArray jsonArr = (new JsonParser()).parse(songstring).getAsJsonArray();
		//System.out.println(jsonArr.toString());
		return Response.status(200).entity(songstring).build();
		//return songlist;
	}
	
	@Path("/genres/{genre}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Song> getSongWithGenre(@PathParam("genre") String genre){	
		//Fetch the song list from database	
		return songlist;
	}
	
	@Path("/artists/{artist}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Song> getSongWithArtist(@PathParam("artist") String artist){	
		//Fetch the song list from database	
		return songlist;
	}

}






