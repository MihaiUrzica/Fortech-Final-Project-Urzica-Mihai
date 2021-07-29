import React, { Component } from 'react';
import './App.css';
import AppNavbar from './AppNavBar';
import { Link } from 'react-router-dom';
import { Button, Container } from 'reactstrap';
import Sidebar from './Siderbar';


class Home extends Component{
    render() {
        return (
                <Sidebar/>
        );
    }
}
export default Home;