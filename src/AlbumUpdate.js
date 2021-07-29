import React, { Component } from 'react';
import { Link, withRouter } from 'react-router-dom';
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

class AlbumUpdate extends Component {


    constructor(props) {
        super(props);
        this.state = {
            album: emptyAlbum
        };
        this.handleChange = this.handleChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    async componentDidMount() {
        
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
        axios.put('/api/albums/'+ this.props.match.params.albumId, this.state.album)
        .then(res=>{
        console.log(res);
        console.log(res.data);
        window.location = "/api/albums/" + this.state.album.albumId; 
      })
    }
    render() {
        const {album} = this.state;
        const title = <h2 className="addAlbumTitle"> Edit Album </h2>;
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
export default AlbumUpdate