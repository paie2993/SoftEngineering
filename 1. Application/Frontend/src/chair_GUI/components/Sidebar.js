import React, { useState, useEffect } from 'react';
import './Sidebar.css'
import HomeRoundedIcon from '@mui/icons-material/HomeRounded';
import LogoutRoundedIcon from '@mui/icons-material/LogoutRounded';

export const Sidebar = () => {

    return (
        <React.Fragment>
        <div class="sidebar-container">
            <div class='home-section'>
                <div class="home btn">
                <HomeRoundedIcon/>
                Home</div>
            </div>
            <div class="menu-section">
                <div class="menu btn">Conferences</div>
                <div class="menu btn">All papers</div>
                <div class="menu btn">Personal Info</div>
            </div>
            <div class="logout btn">
                <LogoutRoundedIcon/>
                Log Out
                </div>
        </div>
        </React.Fragment>
    )
}