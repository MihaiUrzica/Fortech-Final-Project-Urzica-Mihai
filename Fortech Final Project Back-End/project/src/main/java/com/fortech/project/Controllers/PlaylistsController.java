package com.fortech.project.Controllers;

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

import com.fortech.project.Models.Entity.Playlists;
import com.fortech.project.Repositories.PlaylistsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")

public class PlaylistsController {

    @Autowired
    PlaylistsRepository playlistsRepository;

    @GetMapping("/playlists")
    public ResponseEntity<List<Playlists>> getAllPlaylists(@RequestParam(required = false) String playlistName) {
        try {
            List<Playlists> playlists = new ArrayList<Playlists>();

            if (playlistName == null)
                playlistsRepository.findAll().forEach(playlists::add);
            else
                playlistsRepository.findPlaylistByPlaylistName(playlistName).forEach(playlists::add);
            if (playlists.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(playlists, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/playlists/{id}")
    public ResponseEntity<Playlists> getPlaylistsById(@PathVariable("playlistId") long playlistId) {
        Optional<Playlists> playlistsData = playlistsRepository.findById(playlistId);

        if (playlistsData.isPresent()) {
            return new ResponseEntity<>(playlistsData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/playlists")
    public ResponseEntity<Playlists> createPlaylists(@RequestBody Playlists playlists) {
        try {
            Playlists _playlists = playlistsRepository
                    .save(new Playlists(playlists.getPlaylistName()));
            return new ResponseEntity<>(_playlists, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping("/playlists/{id}")
    public ResponseEntity<Playlists> updatePlaylists(@PathVariable("playlistId") long playlistId, @RequestBody Playlists playlists) {
        Optional<Playlists> playlistsData = playlistsRepository.findById(playlistId);

        if (playlistsData.isPresent()) {
            Playlists _playlists = playlistsData.get();
            _playlists.setPlaylistName(playlists.getPlaylistName());
            return new ResponseEntity<>(playlistsRepository.save(_playlists), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/playlists/{id}")
    public ResponseEntity<HttpStatus> deletePlaylists(@PathVariable("playlistId") long playlistId) {
        try {
            playlistsRepository.deleteById(playlistId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
