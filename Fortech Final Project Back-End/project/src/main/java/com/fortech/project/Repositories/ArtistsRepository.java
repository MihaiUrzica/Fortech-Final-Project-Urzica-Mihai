package com.fortech.project.Repositories;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fortech.project.Models.Entity.Artist;
import org.springframework.data.jpa.repository.Query;

public interface ArtistsRepository extends JpaRepository<Artist, Long> {
    //@Query("SELECT a FROM Artists a")
    List<Artist> findArtistByArtistName(String artistName);
    //@Query("SELECT a FROM Artists a")
    List<Artist> findArtistByArtistCountry(String artistCountry);
}
