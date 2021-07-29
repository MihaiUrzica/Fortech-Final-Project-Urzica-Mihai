import React, { Component, useState } from "react";
import { Link, useParams, withRouter } from "react-router-dom";
import { Button, Container, Form, FormGroup, Input, Label } from "reactstrap";
import AppNavbar from "./AppNavBar";
import axios from "axios";
import Sidebar from "./Siderbar";
import "./App.css";

const a = window.location.pathname;
const path = a.split("/");
const id = path[3];

let emptySong = {
  songName: "",
  genre: "",
  length: "",
  plays: "",
  albumId: id,
};

class AddSong extends Component {
  constructor(props) {
    super(props);
    this.state = {
      song: emptySong,
    };
    this.handleChange = this.handleChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  async componentDidMount() {
    console.log("Test");
    const song = await (
      await fetch(`/api/albums/${this.props.match.params.songId}`)
    ).json();
    this.emptySong = song;
    this.setState({ song: song });
  }

  handleChange(event) {
    const target = event.target;
    const value = target.value;
    const name = target.name;
    let song = { ...this.state.song };
    song[name] = value;
    this.setState({ song });
  }
  async handleSubmit(event) {
    event.preventDefault();
    console.log(this.state.song);
    emptySong.songName = this.state.song.songName;
    emptySong.genre = this.state.song.genre;
    emptySong.length = this.state.song.length;
    emptySong.plays = this.state.song.plays;
    emptySong.albumId = id;
    axios.post("/api/songs", emptySong).then((res) => {
      console.log(res);
      console.log(res.data);
      window.location = "/api/albums/" + id; //This line of code will redirect you once the submission is succeed
    });
  }
  render() {
    const { song } = this.state;
    const title = <h2 className="addAlbumTitle"> Add Song </h2>;
    console.log("Test Render");

    return (
      <div>
        <Sidebar />
        <div className="addAlbumForm">
          <Container>
            {title}
            <Form onSubmit={this.handleSubmit}>
              <FormGroup>
                <Label for="songName" className="form_label">
                  Song Name
                </Label>
                <Input
                  type="text"
                  name="songName"
                  id="songName"
                  value={song.songName || ""}
                  onChange={this.handleChange}
                  autoComplete="songName"
                />
              </FormGroup>
              <FormGroup>
                <Label for="genre" className="form_label">
                  Genre
                </Label>
                <Input
                  type="text"
                  name="genre"
                  id="genre"
                  value={song.genre || ""}
                  onChange={this.handleChange}
                  autoComplete="genre"
                />
              </FormGroup>
              <FormGroup>
                <Label for="length" className="form_label">
                  Length
                </Label>
                <Input
                  type="text"
                  name="length"
                  id="length"
                  value={song.length || ""}
                  onChange={this.handleChange}
                  autoComplete="length"
                />
              </FormGroup>
              <FormGroup>
                <Button color="primary" type="submit">
                  Save
                </Button>{" "}
                <Button color="secondary" tag={Link} to="/api/albums">
                  Cancel
                </Button>
              </FormGroup>
            </Form>
          </Container>
        </div>
      </div>
    );
  }
}

export default AddSong;
