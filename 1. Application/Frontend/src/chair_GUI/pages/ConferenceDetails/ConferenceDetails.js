import React, { useState, useEffect } from 'react';
import { ConferenceDetailsHS } from '../../components/ConferenceDetailsHS';
import { ConferencesHS } from '../../components/ConferencesHS';
import { HeaderText } from '../../components/HeaderText';
import { Sidebar } from '../../components/Sidebar';
import './ConferenceDetails.css'

export const ConferenceDetails = () => {

    const conference = JSON.parse(localStorage.getItem("selectedConference"));

    return (
        <React.Fragment>
            <div class="ap-layout">
                <Sidebar />
                <ConferenceDetailsHS />
            </div>
        </React.Fragment>
    )
}