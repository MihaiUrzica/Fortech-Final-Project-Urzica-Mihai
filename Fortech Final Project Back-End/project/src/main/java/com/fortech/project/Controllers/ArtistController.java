package com.fortech.project.Controllers;

import com.fortech.project.Models.Dto.ArtistDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fortech.project.Models.Entity.Artist;
import com.fortech.project.Repositories.ArtistsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class ArtistController {
    @Autowired
    ArtistsRepository artistsRepository;

    @GetMapping("/artists")
    public ResponseEntity<List<ArtistDto>> getAllArtists(@RequestParam(required = false) String ArtistName) {
        try {
            List<ArtistDto> artistDto = new ArrayList<>();

            if (ArtistName == null)
                artistsRepository.findAll().forEach(artist-> artistDto.add(new ArtistDto(artist)));
            else
                artistsRepository.findArtistByArtistName(ArtistName).forEach(artist-> artistDto.add(new ArtistDto(artist)));
            if (artistDto.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(artistDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/artists/{artistId}")
    public ResponseEntity<ArtistDto> getArtistsById(@PathVariable("artistId") long artistId) {
        Optional<Artist> artistsData = artistsRepository.findById(artistId);

        if (artistsData.isPresent()) {
            return new ResponseEntity<>(new ArtistDto(artistsData.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/artists")
    public ResponseEntity<Artist> createArtists(@RequestBody ArtistDto artists) {
        try {
            Artist newArtist = new Artist(artists.getArtistName(),artists.getArtistCountry(), artists.getArtistPhoto(), artists.getDescription());
            Artist _artist = artistsRepository
                    .save(newArtist);
            return new ResponseEntity<>(_artist, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/artists/{artistId}")
    public ResponseEntity<ArtistDto> updateArtists(@PathVariable("artistId") long artistId, @RequestBody ArtistDto artist) {
        Optional<Artist> artistsData = artistsRepository.findById(artistId);

        if (artistsData.isPresent()) {
            Artist _artist = artistsData.get();
            _artist.setArtistsName(artist.getArtistName());
            _artist.setArtistsCountry(artist.getArtistCountry());
            _artist.setArtistPhoto(artist.getArtistPhoto());
            _artist.setDescription(artist.getDescription());

        artistsRepository.save(_artist);
            return new ResponseEntity<>(new ArtistDto(_artist), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/artists/{artistId}")
    public ResponseEntity<HttpStatus> deleteArtists(@PathVariable("artistId") long artistId) {
        try {
            artistsRepository.deleteById(artistId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
