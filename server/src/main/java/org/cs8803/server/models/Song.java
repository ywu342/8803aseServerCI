package org.cs8803.server.models;

public class Song {
	
	String id;
	String title;
	String artist;
	String genre;
	String url;
	
	public Song() {}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String toJsonString(){
		return "{\"id\":\""+this.getId()+"\","
				+"\"title\":\""+this.getTitle()+"\","
				+"\"artist\":\""+this.getArtist()+"\","
				+"\"genre\":\""+this.getGenre()+"\","
				+"\"url\":\""+this.getUrl()+"\"}";
	}
}
