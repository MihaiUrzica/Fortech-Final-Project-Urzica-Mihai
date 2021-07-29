package com.fortech.project.Models.Entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Playlists")

public class Playlists implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playlistId;

    @Column(name = "PlaylistName")
    private String playlistName;

    @ManyToMany
    private List<Song> songs = new ArrayList<>();

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistName() {
        return this.playlistName;
    }

    public Playlists(String playlistName) {
        setPlaylistName(playlistName);
    }

}
