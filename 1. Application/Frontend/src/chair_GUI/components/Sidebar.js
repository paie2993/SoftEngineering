import React, { useState, useEffect } from 'react';
import './Sidebar.css'
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
                <div className="menu btn">Conferences</div>
                <div className="menu btn">All papers</div>
                <div className="menu btn">Personal Info</div>
            </div>
            <div className="logout btn">
                <LogoutRoundedIcon/>
                Log Out
                </div>
        </div>
        </React.Fragment>
    )
}