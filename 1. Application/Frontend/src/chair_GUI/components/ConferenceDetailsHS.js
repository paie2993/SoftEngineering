import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useFetch } from '../../useFetch';
import './ConferenceDetailsHS.css'
import { HeaderText } from './HeaderText'
import { Table } from './Table';
import TextField from '@mui/material/TextField';


export const ConferenceDetailsHS = () => {

    const conference =  JSON.parse(localStorage.getItem("selectedConference"));

    const columns = [
        { field: 'topic', headerName: 'Topics of Interest', width: 290}
      ];

    const [topic, setTopic] = useState(null);

    return (
        <div className="hero">
            <HeaderText text={conference.name} />
            <div className='container'>
                <div className='details'>
                    <div className='conf-info'>
                        <p className='info-text'>Name</p>
                        <input className='info-input' value={conference.name}/>
                    </div>
                    <div className='conf-info'>
                        <p className='info-text'>URL</p>
                        <input className='info-input' value={conference.URL}/>
                    </div>
                    <div className='conf-info'>
                        <p className='info-text'>Subtitles</p>
                        <input className='info-input'/>
                    </div>
                    <div className='organizer-area'>
                        <p className='info-text'>Organizer</p>
                        <div className='organizer-info'>
                            <div className='org-info'>
                                <p className='org-info-text'>Name</p>
                                <input className='org-input'/>
                            </div>
                            <div className='org-info'>
                                <p className='org-info-text'>Email</p>
                                <input className='org-input'/>
                            </div>
                            <div className='org-info'>
                                <p className='org-info-text'>Phone Number</p>
                                <input className='org-input'/>
                            </div>
                        </div>
                    </div>
                </div>
                <div className='topics-area'>
                    <div className='topics'>
                    <Table 
                    accepted={false}
                    content={[]}
                    columns={columns}
                    selected={[[topic, setTopic]]}
                    />
                    </div>
                    <input className='topic-input'/>
                    <div className='btn-area'>
                        <button className='detail-btn'>
                            Submit
                        </button>
                        <Link to="/deadlines" className="noSelect">
                            <button className='detail-btn'>
                                Deadlines
                            </button>
                        </Link>
                    </div>
                </div>
            </div>
        </div>
    )
}

