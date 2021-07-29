import React, { Component } from 'react';
import './App.css';
import Home from './Home';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import ArtistsList from './ArtistsList';
import Artist from './Artist';
import Album from './Album';
import ArtistEdit from './ArtistEdit';
import AlbumEdit from './AlbumEdit';
import ArtistUpdate from './ArtistUpdate';
import AddSong from './AddSong';
import AlbumUpdate from './AlbumUpdate';
import SongUpdate from './SongUpdate';



class App extends Component {
  render() {
    return (
        <Router>
          <Switch>
            <Route path='/' exact={true} component={Home}/>
            <Route path='/api/artists' exact={true} component={ArtistsList}/>
            <Route path='/api/artists/:artistId' exact={true} component={Artist}/>
            <Route path='/api/albums/:albumId' exact={true} component={Album}/>
            <Route path='/api/addArtists' exact={true} component={ArtistEdit}/> 
            <Route path='/api/addAlbums/:artistId' exact={true} component={AlbumEdit}/>
            <Route path='/api/updateArtist/:artistId' exact={true} component={ArtistUpdate}/>
            <Route path='/api/addSongs/:albumId' exact={true} component= {AddSong}/>
            <Route path='/api/updateAlbum/:albumId' exact={true} component={AlbumUpdate}/>
            <Route path='/api/updateSong/:songId' exact={true} component={SongUpdate}/>
          </Switch>
        </Router>
    )
  }
}

export default App;