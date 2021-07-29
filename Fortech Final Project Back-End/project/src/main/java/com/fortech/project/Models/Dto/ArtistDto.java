package com.fortech.project.Models.Dto;
import com.fortech.project.Models.Entity.Artist;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class ArtistDto implements Serializable {

    //Fields
    private Long artistId;
    private String artistName;
    private String artistCountry;
    private String artistPhoto;
    private String description;
    private List<AlbumDto> artistAlbums;

    //Constructors
    public ArtistDto(Artist artist) {
        this.artistId = artist.getArtistId();
        this.artistName = artist.getArtistsName();
        this.artistCountry = artist.getArtistsCountry();
        this.artistPhoto = artist.getArtistPhoto();
        this.description = artist.getDescription();
        this.artistAlbums = artist.getAlbums().stream().map(album -> new AlbumDto(album)).collect(Collectors.toList());
    }
    public ArtistDto() {

    }
    public ArtistDto(Long artistId, String artistName, String artistCountry, String artistPhoto, List<AlbumDto> artistAlbums) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistCountry = artistCountry;
        this.artistPhoto = artistPhoto;
        this.artistAlbums = artistAlbums;
    }
    public ArtistDto(Long artistId, String artistName, String artistCountry, String artistPhoto) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistCountry = artistCountry;
        this.artistPhoto = artistPhoto;
    }

    //Getters and Setters
    public long getArtistId() {
        return artistId;
    }
    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getArtistName() {
        return artistName;
    }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistCountry() {
        return artistCountry;
    }
    public void setArtistCountry(String artistCountry) {
        this.artistCountry = artistCountry;
    }

    public String getArtistPhoto() {
        return artistPhoto;
    }
    public void setArtistPhoto(String artistPhoto) {
        this.artistPhoto = artistPhoto;
    }

    public List<AlbumDto> getArtistAlbums() {
        return artistAlbums;
    }
    public void setArtistAlbums(List<AlbumDto> artistAlbums) {
        this.artistAlbums = artistAlbums;
    }
}
