package com.fortech.project.Models.Entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Songs")

public class Song implements Serializable {

    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long songId;

    @Column(name = "SongName")
    private String songName;

    @Column(name = "Genre")
    private String genre;

    @Column(name = "Length")
    private String length;

    @Column(name = "Plays")
    private int plays;

    @ManyToOne
    @JoinColumn(name = "albumId")
    private Album album;

    //Constructors
    public Song(String songName, String genre, String length, int plays) {
        setSongId(0L);
        setSongName(songName);
        setGenre(genre);
        setLength(length);
        setPlays(plays);
    }
    public Song() {
    }

    //Getters and Setters
    public String getLength() {
        return length;
    }
    public void setLength(String length) {
        this.length = length;
    }

    public int getPlays() {
        return plays;
    }
    public void setPlays(int plays) {
        this.plays = plays;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }
    public Long getSongId() {
        return songId;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }
    public String getSongName() {
        return this.songName;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getGenre() {
        return this.genre;
    }

    public Album getAlbums() {
        return album;
    }
    public void setAlbums(Album album) {
        this.album = album;
    }


}
