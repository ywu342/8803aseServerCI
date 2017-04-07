package org.cs8803.server.apis;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.cs8803.server.models.Song;


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
		return s;
	}

	@Path("/songtitles/{songtitle}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Song> getSongWithTitle(@PathParam("songtitle") String title){	
		//Fetch the song list from database	
		return songlist;
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






