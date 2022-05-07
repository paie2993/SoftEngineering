import React, { useState, useEffect } from 'react';
import { ConferencesHS } from '../../components/ConferencesHS';
import { Sidebar } from '../../components/Sidebar';
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