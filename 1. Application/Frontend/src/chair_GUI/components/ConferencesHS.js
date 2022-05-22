import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useFetch } from '../../useFetch';
import './ConferencesHS.css'
import { HeaderText } from './HeaderText'
import { Table } from './Table'
import { useGetConferences } from '../../API/conferenceService'


const apiLink = "https://backend-1653064679221.azurewebsites.net/";

export const ConferencesHS = () => {

    const columns = [
        { field: 'id', headerName: 'ID', width: 110 },
        { field: 'name', headerName: 'Conference Name', width: 680 },
      ];

    const conferences = useGetConferences(apiLink);
    const [allConferences, setAllConferences] = useState([]);
    const [conference, setConference] = useState(null);

    function enableButton() {
        const btn2 = document.getElementById("btn-details");
        btn2.disabled = false;
      }

    // useEffect(() => {
    //     getAllConferences()
    //       .then(response => 
    //       {setAllConferences(response)})
    // })

    useEffect(() => {
      if (conference != null) {
        enableButton();
        localStorage.setItem("conferenceID", JSON.stringify(conference.id));
      }
    }, [conference])
    
    return (
        <>
        <div className="hero">
            <HeaderText text="Conferences" />
            <div className="table-section">
                <div className="table">
                    <Table 
                    accepted={false}
                    content={conferences}
                    columns={columns}
                    selected={[conference, setConference]}
                    />
                </div>
                <div className="button-area">
                <Link to="/conferencedetails" className="noSelect">
                  <button id='btn-details' className='button' disabled={true} >
                    See Details
                  </button>
                </Link> 
                </div>
            </div>
        </div>
        </>
    )
}
