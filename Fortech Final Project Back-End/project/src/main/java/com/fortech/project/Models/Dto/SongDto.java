package com.fortech.project.Models.Dto;

import com.fortech.project.Models.Entity.Song;

import java.io.Serializable;

public class SongDto implements Serializable {

    //Fields
    private Long songId;
    private String songName;
    private String genre;
    private Long albumId;
    private String length;
    private int plays;

    //Constructors
    public SongDto(Long songId, String songName, String genre, Long albumId,String length, int plays) {
        this.songId = songId;
        this.songName = songName;
        this.genre = genre;
        this.length = length;
        this.albumId = albumId;
        this.plays = plays;
    }
    public SongDto(Song song) {
        this.songId = song.getSongId();
        this.songName = song.getSongName();
        this.genre = song.getGenre();
        this.length = song.getLength();
        this.plays = song.getPlays();
        this.albumId = song.getAlbums().getAlbumId();
    }
    public SongDto() {
    }

    //Getters and Setters
    public Long getSongId() {
        return songId;
    }
    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public int getPlays() {
        return plays;
    }
    public void setPlays(int plays) {
        this.plays = plays;
    }

    public String getLength() {
        return length;
    }
    public void setLength(String length) {
        this.length = length;
    }

    public String getSongName() {
        return songName;
    }
    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Long getAlbumId() {
        return albumId;
    }
    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

}
