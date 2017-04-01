package org.cs8803.server.models;

import java.util.ArrayList;

public class Playlist {
	
	String playlistId;
	String playlistName;
	String playlistOwnerId;	
	ArrayList<Song> songlist;
	
	public String getPlaylistId() {
		return playlistId;
	}
	public void setPlaylistId(String playlistId) {
		this.playlistId = playlistId;
	}
	public String getPlaylistName() {
		return playlistName;
	}
	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}
	public String getPlaylistOwnerId() {
		return playlistOwnerId;
	}
	public void setPlaylistOwnerId(String playlistOwnerId) {
		this.playlistOwnerId = playlistOwnerId;
	}
	public ArrayList<Song> getSonglist() {
		return songlist;
	}
	public void setSonglist(ArrayList<Song> songlist) {
		this.songlist = songlist;
	}
	
}
