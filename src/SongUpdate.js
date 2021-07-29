import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavBar';
import axios from 'axios';
import Sidebar from './Siderbar';

const a = window.location.pathname;
const path = a.split("/");
const id =path[3];

let emptySong = {
    songName: '',
    genre: '',
    length: '',
    plays:'',
    albumId: id
};

class SongUpdate extends Component {


    constructor(props) {
        super(props);
        this.state = {
            song: emptySong
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        console.log("Test");
            const song = await (await fetch(`/api/songs/${this.props.match.params.songId}`)).json();
            this.emptySong = song;
            this.setState({song: song});
        
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let song = {...this.state.song};
        song[name] = value;
        this.setState({song});
    }

    async handleSubmit(event) {
        event.preventDefault();
        axios.put('/api/songs/'+ this.props.match.params.songId, this.state.song)
        .then(res=>{
        console.log(res);
        console.log(res.data);
        //window.location = "/api/artists" + id; 
      })
    }
    render() {
        const {song} = this.state;
        const title = <h2 className="addAlbumTitle"> Edit Song </h2>;
        console.log("Test Render");

        return <div>
            <Sidebar/>
            <div className="addAlbumForm">
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="songName" className="form_label">Song Name</Label>
                        <Input type="text" name="songName" id="songName" value={song.songName || ''}
                               onChange={this.handleChange} autoComplete="songName"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="genre" className="form_label">Genre</Label>
                        <Input type="text" name="genre" id="genre" value={song.genre || ''}
                               onChange={this.handleChange} autoComplete="genre"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="length" className="form_label">Length</Label>
                        <Input type="text" name="length" id="length" value={song.length || ''}
                               onChange={this.handleChange} autoComplete="length"/>
                    </FormGroup>
                    {/* <FormGroup>
                        <Label for="plays" className="form_label">Plays</Label>
                        <Input type="text" name="plays" id="plays" value={song.plays || ''}
                               onChange={this.handleChange} autoComplete="plays"/>
                    </FormGroup> */}
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/api/albums">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
            </div>
            
        </div>
    }
}
export default SongUpdate