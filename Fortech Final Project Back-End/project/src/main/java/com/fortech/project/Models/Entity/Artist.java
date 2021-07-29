package com.fortech.project.Models.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="Artists")

public class Artist implements Serializable {
    //Fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long artistId;

    @Column(name = "ArtistName")
    private String artistName;

    @Column(name = "ArtistCountry")
    private String artistCountry;

    @Column(name = "ArtistPhoto")
    private String artistPhoto;

    @Column(name = "Description")
    private String description;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "artist")
    private List<Album> albums = new ArrayList<>();

    //Constructors
    public Artist() {
    }
    public Artist(String name, String country, String artistPhoto, String description) {
        setArtistsName(name);
        setArtistsCountry(country);
        setArtistPhoto(artistPhoto);
        setDescription(description);
        setArtistId(0L);
    }

    //Getters and Setters
    public List<Album> getAlbums(){
        return this.albums;
    }
    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public void setArtistsCountry(String artistCountry) {
        this.artistCountry = artistCountry;
    }
    public String getArtistsCountry() {
        return artistCountry;
    }

    public void setArtistsName (String artistName) {
        this.artistName = artistName;
    }
    public String getArtistsName() {
        return this.artistName;
    }

    public void setArtistPhoto (String artistPhoto) {
        this.artistPhoto = artistPhoto;
    }
    public String getArtistPhoto() {
        return this.artistPhoto;
    }

    public Long getArtistId() {
        return artistId;
    }
    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

}
