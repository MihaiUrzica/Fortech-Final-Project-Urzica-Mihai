package com.fortech.project.Models.Dto;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import com.fortech.project.Models.Entity.Album;


public class AlbumDto implements Serializable {

    //Fields
    private Long albumId;
    private String albumName;
    private String albumPicture;
    private Long artistId;
    private List<SongDto> albumSongs;

    //Constructors
    public AlbumDto(String albumName, String albumPicture, Long artistId) {
        this.albumName = albumName;
        this.albumPicture = albumPicture;
        this.artistId = artistId;
    }
    public AlbumDto(Album album) {
        this.albumId = album.getAlbumId();
        this.albumName = album.getAlbumName();
        this.albumPicture = album.getAlbumPicture();
        this.artistId = album.getArtists().getArtistId();
        this.albumSongs = album.getSongs().stream().map(song -> new SongDto(song)).collect(Collectors.toList());
    }
    public AlbumDto() {

    }
    public AlbumDto(Long albumId, String albumName, String albumPicture, Long artistId, List<SongDto> albumSongs) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.albumPicture = albumPicture;
        this.artistId = artistId;
        this.albumSongs = albumSongs;
    }

    //Getters and Setters
    public Long getAlbumId() {
        return albumId;
    }
    public void setAlbumId(Long albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getAlbumPicture() {
        return albumPicture;
    }
    public void setAlbumPicture(String albumPicture) {
        this.albumPicture = albumPicture;
    }

    public Long getArtistId() {
        return artistId;
    }
    public void setArtistId(Long artistId) {
        this.artistId = artistId;
    }

    public List<SongDto> getAlbumSongs() {
        return albumSongs;
    }
    public void setAlbumSongs(List<SongDto> albumSongs) {
        this.albumSongs = albumSongs;
    }
}