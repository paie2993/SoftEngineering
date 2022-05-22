import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useFetch } from '../../useFetch';
import './DeadlinesHS.css'
import { HeaderText } from './HeaderText'
import { Table } from './Table';
import TextField from '@mui/material/TextField';
import { getConference } from '../../API/conferenceService'
import { updateConference } from '../../API/conferenceService'
import EditOutlinedIcon from '@mui/icons-material/EditOutlined';


export const DeadlinesHS = () => {

    const confID = JSON.parse(localStorage.getItem("conferenceID"));

    const [conference, setConference] = useState(null);

    const [paperSub, setPaperSub] = useState('');
    const [paperRev, setPaperRev] = useState('');
    const [acceptanceNotif, setAcceptanceNotif] = useState('');
    const [uploadAccepted, setUploadAccepted] = useState('');

    function initDeadlines() {
        getConference(confID)
        .then(response => 
            { 
            console.log(response)
            setConference(response)
            setPaperSub(response.paperSubmissionDeadline);
            setPaperRev(response.paperReviewDeadline);
            setAcceptanceNotif(response.acceptanceNotificationDeadline);
            setUploadAccepted(response.uploadingPaperDeadline); }
        )
    }

    useEffect(() => {
        initDeadlines();
    }, [])

    function changePaperReview(event) {
        const pr = event.target.value;
        setPaperRev(pr);
    }

    function changePaperSubmission(event) {
        const ps = event.target.value;
        setPaperSub(ps);
    }

    function changeAcceptedNotif(event) {
        const an = event.target.value;
        setAcceptanceNotif(an);
    }

    function changeUploadingAccepted(event) {
        const ua = event.target.value;
        setUploadAccepted(ua);
    }

    function updateInfo() {
        let updated = {}
        if (paperSub.length > 0){
            updated.paperSubmissionDeadline = paperSub;
        }
        if (paperRev.length > 0) {
            updated.paperReviewDeadline = paperRev;
        }
        if (acceptanceNotif.length > 0) {
            updated.acceptanceNotificationDeadline = acceptanceNotif;
        }
        if (uploadAccepted.length > 0) {
            updated.uploadingPaperDeadline = uploadAccepted;
        }
        updateConference(confID, JSON.stringify(updated))
            .then(response => {
                console.log(response);
                initDeadlines();
            })
    }

    return (
        <div className="hero">
            {
                conference == null ||
                <>
                <HeaderText text={conference.name} />
                <h2 className='label-deadline'>Deadlines</h2>
                <div className='deadlines'>
                    <div className='deadline'>
                        <p className='dl-text'>Paper Submission</p>
                        <input type="text" 
                        id="ps" 
                        className='dl-input' 
                        value={paperSub}
                        disabled
                        onChange={e => changePaperSubmission(e)}/>
                        <EditOutlinedIcon className='edit-icon dl' onClick={() => document.getElementById('ps').removeAttribute('disabled')}/>
                    </div>
                    <div className='deadline'>
                        <p className='dl-text'>Paper Review</p>
                        <input type="text" 
                        id="pr" 
                        className='dl-input' 
                        value={paperRev}
                        disabled
                        onChange={e => changePaperReview(e)}/>
                        <EditOutlinedIcon className='edit-icon dl' onClick={() => document.getElementById('pr').removeAttribute('disabled')}/>
                    </div>
                    <div className='deadline'>
                        <p className='dl-text'>Acceptance Notification</p>
                        <input type="text" 
                        id='an' 
                        className='dl-input'  
                        value={acceptanceNotif}
                        disabled
                        onChange={e => changeAcceptedNotif(e)}/>
                        <EditOutlinedIcon className='edit-icon dl' onClick={() => document.getElementById('an').removeAttribute('disabled')}/>
                    </div>
                    <div className='deadline'>
                        <p className='dl-text'>Uploading Accepted Papers</p>
                        <input type="text" 
                        id="uap" 
                        className='dl-input' 
                        value={uploadAccepted}
                        disabled
                        onChange={e => changeUploadingAccepted(e)}/>
                        <EditOutlinedIcon className='edit-icon dl' onClick={() => document.getElementById('uap').removeAttribute('disabled')}/>
                    </div>
                    <button className='detail-btn' onClick={() => updateInfo()}>
                        Update
                    </button>
                </div>
                </>
            }
        </div>
    )
}
