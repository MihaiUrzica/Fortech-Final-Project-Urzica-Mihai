package com.fortech.project.Repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fortech.project.Models.Entity.Song;
import org.springframework.data.jpa.repository.Query;

public interface SongsRepository extends JpaRepository<Song, Long> {
    //@Query("SELECT s FROM Songs s")
    List<Song> findSongBySongName(String songName);
    //@Query("SELECT s FROM Songs s")
    //List<Song> findSongByArtistName(String artistName);
    //@Query("SELECT s FROM Songs s")
    //List<Song> findSongByAlbumName(String albumName);
    //@Query("SELECT s FROM Songs s")
    //List<Song> findSongByGenre(String genre);
}
