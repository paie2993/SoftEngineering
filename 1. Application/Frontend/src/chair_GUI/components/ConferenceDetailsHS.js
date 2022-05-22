import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useFetch } from '../../useFetch';
import './ConferenceDetailsHS.css'
import { HeaderText } from './HeaderText'
import { Table } from './Table';
import TextField from '@mui/material/TextField';
import EditOutlinedIcon from '@mui/icons-material/EditOutlined';
import { getConference, updateConference } from '../../API/conferenceService'

const apiLink = "https://backend-1653064679221.azurewebsites.net/";

export const ConferenceDetailsHS = () => {

    const confID = JSON.parse(localStorage.getItem("conferenceID"));
    const columns = [
        { field: 'topic', headerName: 'Topics of Interest', width: 290}
      ];

    const [topic, setTopic] = useState(null);
    const [conference, setConference] = useState(null);
    const [topics, setTopics] = useState([]);

    const [name, setName] = useState('');
    const [URL, setURL] = useState('');
    const [orgName, setOrgName] = useState('');
    const [email, setEmail] = useState('');
    const [phoneNb, setPhoneNb] = useState('');
    const [editMode, setEditMode] = useState(false);
    const [canEdit, setCanEdit] = useState(false);

    function initConference() {
        getConference(confID)
        .then(response => 
            { setConference(response)
            localStorage.setItem('conferenceCopy', JSON.stringify(response))
            setTopics(response.topics)
            setName(response.name)
            setURL(response.url)
            setOrgName(response.organizerName)
            setEmail(response.organizerEmail)
            setPhoneNb(response.organizerPhoneNumber)
            const chairID = response.chairId;
            if (localStorage.getItem('userId') == chairID) {
                setCanEdit(true);
            }
            console.log(response)}
        )
    }

    useEffect(() => {
        initConference();
    }, [])

    function makeFieldEditable(fieldId) {
        const input = document.getElementById(fieldId);
        input.disabled = false;
        setEditMode(true);
    }

    function changeName(event) {
        const name = event.target.value;
        setName(name);
    }

    function changeURL(event) {
        setURL(event.target.value);
    }

    function changeOrgName(event) {
        setOrgName(event.target.value);
    }

    function changeEmail(event) {
        setEmail(event.target.value);
    }

    function changePhoneNb(event) {
        setPhoneNb(event.target.value);
    }

    function updateInfo() {
        if (editMode === true){
            let updated = {}
            if (name.length > 0){
                updated.name = name;
            }
            if (URL.length > 0) {
                updated.url = URL;
            }
            if (orgName.length > 0) {
                updated.organizerName = orgName;
            }
            if (email.length > 0) {
                updated.organizerEmail = email;
            }
            if (phoneNb.length > 0) {
                updated.organizerPhoneNumber = phoneNb;
            }
            updateConference(confID, JSON.stringify(updated))
                .then(response => {
                    initConference();
                })
        }
    }

    return (
        <div className="hero">
            {
                conference == null ||
                <>
                <HeaderText text={conference.name} />
                <div className='container'>
                <div className='details'>
                    <div className='conf-info'>
                        <p className='info-text'>Name</p>
                        <input id='i1'
                        type="text"
                        className='info-input' 
                        value={name} 
                        disabled 
                        onChange={e => changeName(e)}/>
                        {
                        canEdit &&
                        <EditOutlinedIcon id='edit-icon' className='edit-icon conf' onClick={() => makeFieldEditable('i1')}/>
                        }
                    </div>
                    <div className='conf-info'>
                        <p className='info-text'>URL</p>
                        <input id='i2' 
                        className='info-input'  
                        value={URL} 
                        disabled
                        onChange={e => changeURL(e)}/>
                        {
                        canEdit &&
                        <EditOutlinedIcon id='edit-icon' className='edit-icon conf'  onClick={() => makeFieldEditable('i2')}/>
                        }
                        
                    </div>
                    <div className='organizer-area'>
                        <p className='info-text'>Organizer</p>
                        <div className='organizer-info'>
                            <div className='org-info'>
                                <p className='org-info-text'>Name</p>
                                <input id='i3'
                                className='org-input' 
                                value={orgName} 
                                disabled
                                onChange={e => changeOrgName(e)}/>
                                {
                                canEdit &&
                                <EditOutlinedIcon id='edit-icon' className='edit-icon org' onClick={() => makeFieldEditable('i3')}/>
                                }
                            </div>
                            <div className='org-info'>
                                <p className='org-info-text'>Email</p>
                                <input id='i4'
                                className='org-input' 
                                value={email} 
                                disabled
                                onChange={e => changeEmail(e)}/>
                                {
                                canEdit &&
                                <EditOutlinedIcon id='edit-icon' className='edit-icon org' onClick={() => makeFieldEditable('i4')}/>
                                }
                            </div>
                            <div className='org-info'>
                                <p className='org-info-text'>Phone Number</p>
                                <input id='i5'
                                className='org-input' 
                                value={phoneNb} 
                                disabled
                                onChange={e => changePhoneNb(e)}/>
                                {
                                canEdit &&
                                <EditOutlinedIcon id='edit-icon' className='edit-icon org' onClick={() => makeFieldEditable('i5')}/>
                                }
                            </div>
                        </div>
                    </div>
                </div>
                <div className='topics-area'>
                    <div className='topics'>
                    <Table 
                    accepted={false}
                    content={topics}
                    columns={columns}
                    selected={[topic, setTopic]}
                    />
                    </div>
                    {
                    canEdit &&
                    <input className='topic-input'/>
                    }
                    <div className='btn-area'>
                        {
                         canEdit &&
                         <button 
                         className='detail-btn'
                         onClick={() => updateInfo()}>
                             Submit
                         </button>
                         }
                        <Link to="/deadlines" className="noSelect">
                            <button className='detail-btn'>
                                Deadlines
                            </button>
                        </Link>
                    </div>
                </div>   
                </div>             
                </>
            }
        </div>
    )
}

