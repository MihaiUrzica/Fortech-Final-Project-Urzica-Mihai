package com.fortech.project.Repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fortech.project.Models.Entity.Album;

public interface AlbumsRepository extends JpaRepository<Album, Long>{
    //@Query("SELECT a FROM Albums a")
    List<Album> findAlbumByAlbumName(String albumName);
    //@Query("SELECT a FROM Albums a")
    //List<Albums> findAlbumByArtistName(String artistName);
}
