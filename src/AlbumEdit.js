import React, { Component, useState } from 'react';
import { Link, useParams, withRouter } from 'react-router-dom';
import { Button, Container, Form, FormGroup, Input, Label } from 'reactstrap';
import AppNavbar from './AppNavBar';
import axios from 'axios';
import Sidebar from './Siderbar';

const a = window.location.pathname;
const path = a.split("/");
const id =path[3];

let emptyAlbum = {
    albumName: '',
    albumPicture: '',
    artistId: id
};

class AlbumEdit extends Component {   

    constructor(props) {
        super(props);
        this.state = {
            album: emptyAlbum
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        console.log("Test");
            const album = await (await fetch(`/api/albums/${this.props.match.params.albumId}`)).json();
            this.emptyAlbum = album;
            this.setState({album: album});
        
    }

    handleChange(event) {
        const target = event.target;
        const value = target.value;
        const name = target.name;
        let album = {...this.state.album};
        album[name] = value;
        this.setState({album});
    }
    async handleSubmit(event) {
        event.preventDefault();
        console.log(this.state.album);
        emptyAlbum.albumName = this.state.album.albumName;
        emptyAlbum.albumPicture = this.state.album.albumPicture;
        emptyAlbum.artistId = id;
        axios.post('/api/albums', emptyAlbum)
        .then(res=>{
        console.log(res);
        console.log(res.data);
        window.location = "/api/artists/"+id 
      })
    }
    render() {
        const {album} = this.state;
        const title = <h2 className="addAlbumTitle"> Add Album </h2>;
        console.log("Test Render");
    
        return <div>
            <Sidebar/>
            <div className="addAlbumForm">
            <Container>
                {title}
                <Form onSubmit={this.handleSubmit}>
                    <FormGroup>
                        <Label for="albumName" className="form_label">Album Name</Label>
                        <Input type="text" name="albumName" id="albumName" value={album.albumName || ''}
                               onChange={this.handleChange} autoComplete="albumName"/>
                    </FormGroup>
                    <FormGroup>
                        <Label for="albumPicture" className="form_label">Album Picture</Label>
                        <Input type="text" name="albumPicture" id="albumPicture" value={album.albumPicture || ''}
                               onChange={this.handleChange} autoComplete="albumPicture"/>
                    </FormGroup>
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

export default AlbumEdit;