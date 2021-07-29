package com.fortech.project.Controllers;

import com.fortech.project.Models.Dto.SongDto;
import com.fortech.project.Models.Entity.Album;
import com.fortech.project.Repositories.AlbumsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fortech.project.Models.Entity.Song;
import com.fortech.project.Repositories.SongsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class SongController {

    @Autowired
    SongsRepository songsRepository;

    @Autowired
    AlbumsRepository albumsRepository;

    @GetMapping("/songs")
    public ResponseEntity<List<SongDto>> getAllSongs(@RequestParam(required = false) String songName) {
        try {
            List<SongDto> songDto = new ArrayList<>();
            if (songName == null)
                songsRepository.findAll().forEach(song-> songDto.add(new SongDto(song)));
            else
                songsRepository.findSongBySongName(songName).forEach(song-> songDto.add(new SongDto(song)));
            if (songDto.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(songDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/songs/{songId}")
    public ResponseEntity<SongDto> getSongsById(@PathVariable("songId") long songId) {
        Optional<Song> songsData = songsRepository.findById(songId);

        if (songsData.isPresent()) {
            return new ResponseEntity<>(new SongDto(songsData.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/songs")
    public ResponseEntity<SongDto> createSongs(@RequestBody SongDto songs) {
        try {
            Optional<Album> optionalAlbums = albumsRepository.findById(songs.getAlbumId());
            Song newSong = new Song(songs.getSongName(), songs.getGenre(), songs.getLength(), songs.getPlays());
            if (optionalAlbums.isPresent()) {
                newSong.setAlbums(optionalAlbums.get());
            }
            Song _song = songsRepository
                    .save(newSong);
            return new ResponseEntity<>(new SongDto(_song), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/songs/{songId}")
    public ResponseEntity<SongDto> updateSongs(@PathVariable("songId") long songId, @RequestBody SongDto song) {
        Optional<Song> songsData = songsRepository.findById(songId);

        if (songsData.isPresent()) {
            Song _song = songsData.get();
            _song.setSongName(song.getSongName());
            _song.setGenre(song.getGenre());
            _song.setLength(song.getLength());
            _song.setPlays(song.getPlays());
        songsRepository.save(_song);
            return new ResponseEntity<>(new SongDto(_song), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/songs/{songId}")
    public ResponseEntity<HttpStatus> deleteSongs(@PathVariable("songId") long songId) {
        try {
            songsRepository.deleteById(songId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
