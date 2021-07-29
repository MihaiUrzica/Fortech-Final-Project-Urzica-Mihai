import React, { Component, useState, useEffect } from "react";
import "./App.css";
import { Link } from "react-router-dom";
import axios from "axios";
import AppNavbar from "./AppNavBar";
import Sidebar from "./Siderbar";
import { Button } from "reactstrap";

const Artist = ({ match }) => {
  const [artist, setArtist] = useState([]);

  useEffect(() => {
    fetchArtist();
  }, []);

  const deleteArtist = (artistId) => {
    axios.delete(`/api/artists/${artistId}`).then (
      window.location = `/api/artists/`
    );
  }
  const fetchArtist = () => {
    axios
      .get(`/api/artists/${match.params.artistId}`)
      .then((res) => {
        setArtist(res.data);

      })
      .catch((err) => console.log(err));
  };
  console.log(typeof artist);
  console.log(artist);

  return (
    <div>
      <Sidebar />
      <div className="artistPage-container">
        {
          <div key={artist.artistId}>
            <div className="artistDetails">
              <img className="artist-image" src={artist.artistPhoto} alt="" />
              <div className="artistInfo">
                <p className="artistBigName">{artist.artistName}</p>
                <p className="artistCountry">{artist.artistCountry}</p>
                <p className="artistDescription">{artist.description}</p>
              </div>
            </div>
            <div className="artistButtons">
              <Button tag={Link} to={`/api/addAlbums/${artist.artistId}`}> Add Album </Button>
              <Button tag={Link} to={`/api/updateArtist/${artist.artistId}`}> Update Artist</Button>
              <Button onClick={()=> deleteArtist(artist.artistId)}> Delete Artist</Button>
            </div>
            <h3 className="albumsByArtist"> List of albums by {artist.artistName} </h3>
          </div>
        }
        {artist.artistAlbums &&
          artist.artistAlbums.map((album) => (
            <div className="artistAlbum" key={album.albumId}>
                <Link to={`/api/albums/${album.albumId}`}>
                <img src={album.albumPicture} alt="" className="album-picture" />
                  
                </Link>
              <h3 className="artistAlbumName">
              {album.albumName}
              </h3>
            </div>
          ))}
      </div>
    </div>
  );
};

export default Artist;
