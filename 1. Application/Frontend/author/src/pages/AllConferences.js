import React, { useState, useEffect } from 'react';
import { ConferencesHS } from '../Components/ConferencesHS';
import { Sidebar } from '../Components/Sidebar';
import './AllConferences.css'

export const AllConferences = () => {

    return (
        <React.Fragment>
            <div class="ap-layout">
                <Sidebar />
                <ConferencesHS />
            </div>
        </React.Fragment>
    )
}