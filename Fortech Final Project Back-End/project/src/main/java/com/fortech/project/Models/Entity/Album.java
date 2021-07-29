package com.fortech.project.Models.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Albums")

public class Album implements Serializable {

    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long albumId;

    @Column(name = "AlbumName")
    private String albumName;

    @Column(name = "AlbumPicture")
    private String albumPicture;

    @ManyToOne
    @JoinColumn(name = "artistId")
    private Artist artist;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "album")
    private List<Song> songs = new ArrayList<>();

    //Constructors
    public Album(String albumName, String albumPicture) {
        setAlbumName(albumName);
        setAlbumPicture(albumPicture);
        setAlbumId(0L);
    }
    public Album() {

    }

    //Getters and Setters
    public Long getAlbumId() {
        return this.albumId;
    }
    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
    public String getAlbumName() {
        return this.albumName;
    }

    public void setAlbumPicture(String albumPicture) {
        this.albumPicture = albumPicture;
    }
    public String getAlbumPicture() {
        return this.albumPicture;
    }

    public List<Song> getSongs() {
        return songs;
    }
    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public void setArtists(Artist artist) {
        this.artist = artist;
    }
    public Artist getArtists(){
        return artist;
    }


}
