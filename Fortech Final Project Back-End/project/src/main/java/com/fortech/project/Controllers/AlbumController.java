package com.fortech.project.Controllers;

import com.fortech.project.Models.Dto.AlbumDto;
import com.fortech.project.Models.Entity.Artist;
import com.fortech.project.Repositories.ArtistsRepository;
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

import com.fortech.project.Models.Entity.Album;
import com.fortech.project.Repositories.AlbumsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class AlbumController {

    @Autowired
    AlbumsRepository albumsRepository;

    @Autowired
    ArtistsRepository artistsRepository;

    @GetMapping("/albums")
    public ResponseEntity<List<AlbumDto>> getAllAlbums(@RequestParam(required = false) String albumName) {
        try {
            List<AlbumDto> albumDto = new ArrayList<>();
            if (albumName == null)
                albumsRepository.findAll().forEach(album-> albumDto.add(new AlbumDto(album)));
            else
                albumsRepository.findAlbumByAlbumName(albumName).forEach(album-> albumDto.add(new AlbumDto(album)));
            if (albumDto.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(albumDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/albums/{albumId}")
    public ResponseEntity<AlbumDto> getAlbumsById(@PathVariable("albumId") long albumId) {

        Optional<Album> albumsData = albumsRepository.findById(albumId);

        if (albumsData.isPresent()) {
            return new ResponseEntity<>(new AlbumDto(albumsData.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/albums")
    public ResponseEntity<AlbumDto> createAlbums(@RequestBody AlbumDto albums) {
        try {
            Optional<Artist>  optionalArtists = artistsRepository.findById(albums.getArtistId());
            Album newAlbum = new Album(albums.getAlbumName(), albums.getAlbumPicture());
             if(optionalArtists.isPresent()){
                newAlbum.setArtists(optionalArtists.get());
            }

            Album _album = albumsRepository
                    .save(newAlbum);

            return new ResponseEntity<>(new AlbumDto(_album), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/albums/{albumId}")
    public ResponseEntity<AlbumDto> updateAlbums(@PathVariable("albumId") long albumId, @RequestBody AlbumDto album) {
        Optional<Album> albumsData = albumsRepository.findById(albumId);

        if (albumsData.isPresent()) {
            Album _album = albumsData.get();
            _album.setAlbumName(album.getAlbumName());
            _album.setAlbumPicture(album.getAlbumPicture());
        albumsRepository.save(_album);
            return new ResponseEntity<>(new AlbumDto(_album), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/albums/{albumId}")
    public ResponseEntity<HttpStatus> deleteAlbums(@PathVariable("albumId") long albumId) {
        try {
            albumsRepository.deleteById(albumId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
