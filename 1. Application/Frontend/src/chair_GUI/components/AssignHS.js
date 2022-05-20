import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { HeaderText } from './HeaderText'
import { Table } from './Table';
import TextField from '@mui/material/TextField';
import './AssignHS.css'
import { useFetch } from '../../useFetch';
import ArrowCircleRightOutlinedIcon from '@mui/icons-material/ArrowCircleRightOutlined';

const conferencesURL = "https://mocki.io/v1/6fde9b8c-b9eb-47f6-9c17-c7566225521f";
const sessionsURL = "https://mocki.io/v1/43a7c566-9502-4c28-b6e4-bda66758cc0f";

export const AssignHS = () => {

    const paper =  JSON.parse(localStorage.getItem("selectedPaper"));
    const label = 'Assign paper "' + paper.title + '" to a session of the respective conferences'

    const allConferences = useFetch(conferencesURL);
    const allSessions = useFetch(sessionsURL);

    const [selectedConference, setSelectedConference] = useState(null);
    const [myConferences, setMyConferences] = useState([]);
    const [sessionsShown, setSessionsShown] = useState([]);
    const [selectedSession, setSelectedSession] = useState(null);

    const columns = [
        { field: 'id', headerName: 'ID', width: 70 },
        { field: 'name', headerName: 'Conference Name', width: 270 },
      ];

    const columnsSesh = [
    { field: 'id', headerName: 'ID', width: 70 },
    { field: 'eventDate', headerName: 'Event Date', width: 120 },
    { field: 'startTime', headerName: 'Start Time', width: 120 },
    { field: 'endTime', headerName: 'End Time', width: 120 }
    ];

    function filterConferences(conferences) {
        const result = conferences.filter(conference => conference.chairId == localStorage.getItem('userId'));
        return result;
    }

    function filterSessionsByConference(sessions) {
        const result = sessions.filter(sesh => sesh.conferenceId == selectedConference.id)
        return result;
    }

    useEffect(() => {
        const confs = filterConferences(allConferences);
        setMyConferences(confs);
    }, [allConferences])

    useEffect(() => {
        if (selectedConference != null) {
            const sessions = filterSessionsByConference(allSessions);
            setSessionsShown(sessions);
        }
    }, [selectedConference])

    useEffect(() => {
        if(selectedConference == null || selectedSession == null) {
            document.getElementById('assign').disabled = true;
        }
        else {
            document.getElementById('assign').disabled = false;
        }
    }, [selectedConference, selectedSession])

    return (
        <div className="hero">
            <div className='header-text'>
                Paper Assignment to Conference Session
            </div>
            <p className='label-assign'>{label}</p>
            <div className='tables-area'>
                <div className="table-conf">
                    <Table 
                    accepted={false}
                    content={myConferences}
                    columns={columns}
                    selected={[selectedConference, setSelectedConference]}
                    />
                </div>   
                <ArrowCircleRightOutlinedIcon className='arrow-sign'/>
                <div className="table-conf">
                    <Table 
                    accepted={false}
                    content={sessionsShown}
                    columns={columnsSesh}
                    selected={[selectedSession, setSelectedSession]}
                    />
                </div>   
            </div>
            <div className='assign-btn'>   
                <button className='btn-assign-paper' id='assign' disabled={true} >
                    Assign Paper
                </button>
            </div>
        </div>
    )
}
