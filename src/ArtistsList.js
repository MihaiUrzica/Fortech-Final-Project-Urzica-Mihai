import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import AppNavbar from "./AppNavBar";
import axios from "axios";
import { Button } from "reactstrap";
import "./App.css";
import Sidebar from "./Siderbar";

const ArtistsList = () => {
  const [artists, setArtists] = useState([]);

  useEffect(() => {
    fetchArtists();
  }, []);
  const fetchArtists = () => {
    axios
      .get("/api/artists")
      .then((res) => {
        console.log(res);
        setArtists(res.data);
      })
      .catch((err) => {
        console.log(err);
      });
  };
  console.log(artists);
  return (
    <div>
      <Sidebar />
      <div className="artist_List">
        <div className="artistList_title">
          <div className="Artist_Banner"> Artists </div>
          <div>
            <Button tag={Link} to={"/api/addArtists" } className="add_Button"> Add Artist </Button>
            {/* <Link to={"/api/addArtists"} className="add_Button">
              Add Artist
            </Link> */}
          </div>
        </div>
        <div className="artistList_container">
          <div>
            {artists.map((artist) => (
              <div className="artist-container " key={artists.artistId}>
                <Link to={`/api/artists/${artist.artistId}`}>
                  <img src={artist.artistPhoto} alt="" className="artist_photo" />
                </Link>
                <h3 className="artist_name">{artist.artistName}</h3>
                {/* <p>{artist.description}</p> */}
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  );
};
export default ArtistsList;
