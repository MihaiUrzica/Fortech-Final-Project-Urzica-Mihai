import React, { Component, useState, useEffect } from "react";
import "./App.css";
import { Link } from "react-router-dom";
import axios from "axios";
import AppNavbar from "./AppNavBar";
import Sidebar from "./Siderbar";
import { Button } from "reactstrap";

const Album = ({ match }) => {
  const [album, setAlbum] = useState([]);

  useEffect(() => {
    fetchAlbum();
  }, []);

  const deleteSong = (songId) => {
    axios.delete(`/api/songs/${songId}`).then (fetchAlbum);
  }

  const deleteAlbum = (albumId) => {
    axios.delete(`/api/albums/${albumId}`).then (
      window.location = `/api/artists/${album.artistId}`
    );
  }

  const fetchAlbum = () => {
    axios
      .get(`/api/albums/${match.params.albumId}`)
      .then((res) => {
        setAlbum(res.data);
        //console.log(res.data);
        //console.log(artists);
      })
      .catch((err) => console.log(err));
  };

  return (
    <div>
      <Sidebar />
      <div className="albumPage-container">
        {
          <div key={album.albumId}>
            <div className="albumDetails">
              <img className="albumPicture" src={album.albumPicture} alt="" />
              <div className="artistInfo">
                <h1 className="artistBigName" id="albumName" >{album.albumName}</h1>
                <Link to={`/api/artists/${album.artistId}`}>Back to artist</Link>
              </div>
            </div>
            <div className="artistButtons">
              <Button tag={Link} to={`/api/addSongs/${album.albumId}`}> Add Songs </Button>
              <Button tag={Link} to={`/api/updateAlbum/${album.albumId}`}> Update Album </Button>
              <Button onClick={()=> deleteAlbum(album.albumId)}> Delete Album </Button>
            </div>

          </div>
        }
        <h3 className="albumsByArtist">List of Songs from album: {album.albumName} </h3>
     
        <div className="songList">
                <div className="songLineTable"> Song Name </div>
                <div className="songLineTable"> Genre </div>
                <div className="songLineTable"> Length </div>
                <div className="songLineTable"> Actions </div>
        </div>
        {album.albumSongs &&
          album.albumSongs.map((song) => (
            <div>
              <div className="songList" key={song.songId}>
                <div className="songLine"> {song.songName} </div>
                <div className="songLine"> {song.genre} </div>
                <div className="songLine"> {song.length} </div>
                <div className="songLine"> 
                  <Button tag={Link} to={`/api/updateSong/${song.songId}`}> Edit </Button>
                  {/* <Link to={`/api/updateSong/${song.songId}`}> Edit </Link>  */}
                  <Button onClick={() => deleteSong(song.songId)}>Delete</Button>
                  </div>
              </div>
            </div>
          ))}

    </div>
    </div>
  );
};

export default Album;
