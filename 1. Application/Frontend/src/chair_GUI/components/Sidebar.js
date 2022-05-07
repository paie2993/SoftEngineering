import React, { useState, useEffect } from 'react';
import './Sidebar.css'
import { Link } from 'react-router-dom';
import HomeRoundedIcon from '@mui/icons-material/HomeRounded';
import LogoutRoundedIcon from '@mui/icons-material/LogoutRounded';

export const Sidebar = () => {

    return (
        <React.Fragment>
        <div className="sidebar-container">
            <div className='home-section'>
                <div className="home btn">
                <HomeRoundedIcon/>
                Home</div>
            </div>
            <div className="menu-section">
                <Link to='/conferences' className='noSelect'>
                    <div className="menu btn">Conferences</div>
                </Link>
                <Link to="/"  className="noSelect">
                    <div className="menu btn">All papers</div>
                </Link>
                <Link to="/personalinfo"  className="noSelect">
                    <div className="menu btn">Personal Info</div>
                </Link>
            </div>
            <div className="logout btn">
                <LogoutRoundedIcon/>
                Log Out
                </div>
        </div>
        </React.Fragment>
    )
}