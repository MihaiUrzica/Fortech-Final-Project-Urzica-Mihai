import React, {Component} from 'react';
import {Navbar, NavbarBrand, NavLink} from 'reactstrap';
import {Link} from 'react-router-dom';
import { ProSidebar, Menu, MenuItem, SubMenu, SidebarHeader } from 'react-pro-sidebar';
import 'react-pro-sidebar/dist/css/styles.css';

export default class Sidebar extends Component {


    render() {
        return <div className="sidebar">
            <div className="sidebar-title text-light"> <Link to="/"> Home </Link></div>
            <div className="sidebar_link"> <Link to='/api/artists' className="link"> Artists </Link> </div>
        </div>
    }
}