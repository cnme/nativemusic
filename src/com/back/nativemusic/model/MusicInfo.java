package com.back.nativemusic.model;

import android.R.string;

public class MusicInfo {
	private long id;
	private String MusicName;
	private String Aritst;
	private String Duration;
	private String Album;
	private long Albumid;
	private String Url;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getMusicName() {
		return MusicName;
	}
	public void setMusicName(String musicName) {
		MusicName = musicName;
	}
	public String getAritst() {
		return Aritst;
	}
	public void setAritst(String aritst) {
		Aritst = aritst;
	}
	public String getDuration() {
		return Duration;
	}
	public void setDuration(String Duration) {
		this.Duration = Duration;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public String getAlbum() {
		return Album;
	}
	public void setAlbum(String album) {
		Album = album;
	}
	public long getAlbumid() {
		return Albumid;
	}
	public void setAlbumid(long albumid) {
		Albumid = albumid;
	}
	

}
