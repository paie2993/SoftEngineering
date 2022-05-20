import React, { useState, useEffect } from 'react';
import { ConferenceDetailsHS } from '../../components/ConferenceDetailsHS';
import { ConferencesHS } from '../../components/ConferencesHS';
import { DeadlinesHS } from '../../components/DeadlinesHS';
import { HeaderText } from '../../components/HeaderText';
import { Sidebar } from '../../components/Sidebar';
import './Deadlines.css'

export const Deadlines = () => {

    const conference = JSON.parse(localStorage.getItem("selectedConference"));

    return (
        <React.Fragment>
            <div class="ap-layout">
                <Sidebar />
                <DeadlinesHS />
            </div>
        </React.Fragment>
    )
}