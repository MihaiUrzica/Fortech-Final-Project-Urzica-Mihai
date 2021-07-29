import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavBar';
import axios from 'axios';
import Sidebar from './Siderbar';

const a = window.location.pathname;
const path = a.split("/");
const id =path[3];

class ArtistUpdate extends Component {

    emptyArtist = {
        artistName: '',
        artistCountry: '',
        artistPhoto: '',
        description: ''
    };

    constructor(props) {
        super(props);
        this.state = {
            artist: this.emptyArtist
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        
            const artist = await (await fetch(`/api/artists/${this.props.match.params.artistId}`)).json();
            console.log(artist);
            this.setState({artist: artist});
        
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let artist = {...this.state.artist};
        artist[name] = value;
        this.setState({artist});
    }
    async handleSubmit(event) {
        event.preventDefault();
        axios.put('/api/artists/'+ this.props.match.params.artistId, this.state.artist)
        .then(res=>{
        console.log(res);
        console.log(res.data);
        window.location = "/api/artists/" + id; 
      })
    }
    render() {
        const {artist} = this.state;
        const title = <h2 className="addAlbumTitle">Edit Artist</h2>;
        return <div>
            <Sidebar/>
            <div  className="addAlbumForm">
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="artistName" className="form_label">Artist Name</Label>
                        <Input type="text" name="artistName" id="artistName" value={artist.artistName || ''}
                               onChange={this.handleChange} autoComplete="artistName"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="artistCountry" className="form_label">Artist Country</Label>
                        <Input type="text" name="artistCountry" id="artistCountry" value={artist.artistCountry || ''}
                               onChange={this.handleChange} autoComplete="artistCountry"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="artistPhoto" className="form_label">Artist Photo</Label>
                        <Input type="text" name="artistPhoto" id="artistPhoto" value={artist.artistPhoto || ''}
                               onChange={this.handleChange} autoComplete="artistPhoto"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="description" className="form_label">Description</Label>
                        <Input type="text" name="description" id="description" value={artist.description || ''}
                               onChange={this.handleChange} autoComplete="description"/>
                    </FormGroup>
                    <FormGroup>
                        <Button color="primary" type="submit">Save</Button>{' '}
                        <Button color="secondary" tag={Link} to="/api/artists">Cancel</Button>
                    </FormGroup>
                </Form>
            </Container>
            </div>
        </div>
    }
}
export default ArtistUpdate