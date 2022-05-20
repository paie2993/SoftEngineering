import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useFetch } from '../../useFetch';
import './DeadlinesHS.css'
import { HeaderText } from './HeaderText'
import { Table } from './Table';
import TextField from '@mui/material/TextField';


export const DeadlinesHS = () => {

    const conference =  JSON.parse(localStorage.getItem("selectedConference"));

    return (
        <div className="hero">
            <HeaderText text={conference.name} />
            <h2 className='label-deadline'>Deadlines</h2>
            <div className='deadlines'>
                <div className='deadline'>
                    <p className='dl-text'>Paper Submission</p>
                    <input type="text" id="ps" className='dl-input'/>
                </div>
                <div className='deadline'>
                    <p className='dl-text'>Paper Review</p>
                    <input type="text" id="pr" className='dl-input' />
                </div>
                <div className='deadline'>
                    <p className='dl-text'>Acceptance Notification</p>
                    <input type="text" id='an' className='dl-input'/>
                </div>
                <div className='deadline'>
                    <p className='dl-text'>Uploading Accepted Papers</p>
                    <input type="text" id="uap" className='dl-input'/>
                </div>
                <button className='detail-btn'>
                    Update
                </button>
            </div>
        </div>
    )
}
