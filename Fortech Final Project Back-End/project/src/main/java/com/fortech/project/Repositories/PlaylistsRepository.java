package com.fortech.project.Repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fortech.project.Models.Entity.Playlists;
import org.springframework.data.jpa.repository.Query;

public interface PlaylistsRepository extends JpaRepository<Playlists, Long>{
    @Query("SELECT p FROM Playlists p")
    List<Playlists> findPlaylistByPlaylistName(String playlistName);
}
