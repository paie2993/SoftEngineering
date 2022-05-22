import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './PapersHS.css'
import { useFetch } from '../../useFetch';
import { HeaderText } from './HeaderText'
import { Table } from './Table'
import { getAllPapers, useGetPapers } from '../../API/paperService';
import { getConference, useGetConference, useGetConferences } from '../../API/conferenceService'


const apiLink = "https://backend-1653064679221.azurewebsites.net/";

export const PapersHS = (props) => {

    const columns = [
        { field: 'id', headerName: 'ID', width: 100 },
        { field: 'title', headerName: 'Paper Name', width: 400 },
        { field: 'status', headerName: 'Status', width: 300}
      ];

    const accepted = props.accepted;
    const allConferences = useGetConferences(apiLink);
    const [myConferences, setMyConferences] = useState([]);
    const [papers, setPapers] = useState([]);
    const [buttonText, setButtonText] = useState("");
    const [paper, setPaper] = useState(null);


    function getPapers() {
      console.log(localStorage.getItem('userId'))
      let prs = [];
      for (var i = 0 ; i < allConferences.length; i++) {
        getConference(allConferences[i].id)
            .then(response => {
              if(response.chairId == localStorage.getItem('userId')) {
                console.log("It matches!")
                getAllPapers(apiLink, response.id)
                    .then((resp) => {
                        prs = resp.map((paper) => paper)
                        })
                    .finally(() => {
                        setPapers(prs);
                    })
              }
            })
      }
    }

    useEffect(() => {
      getPapers();
    }, [allConferences])


    useEffect(() => {
      if (accepted === true) {
        setButtonText("All Papers");
      }
      else {
        setButtonText("Accepted Papers");
      }
    }, [accepted])

    function enableButtons(item) {
      const btn1 = document.getElementById("btn-assign");
      if (item.status === "accepted"){
        btn1.disabled = false;
      }
      else {
        btn1.disabled = true;
      }
      const btn2 = document.getElementById("btn-status");
      btn2.disabled = false;
    }

    useEffect(() => {
      if (paper != null) {
        enableButtons(paper);
        localStorage.setItem("paperId", JSON.stringify(paper));
        localStorage.setItem("selectedPaper", JSON.stringify(paper))
      }
    }, [paper])

    return (
        <React.Fragment>
        <div className="hero">
            <HeaderText text={`${accepted ? 'Accepted Papers' : 'Papers'}`} />
            <div className="table-section">
              <div className="table">
                <Table 
                  accepted={accepted}
                  content={papers}
                  columns={columns}
                  selected={[paper, setPaper]}
                  />
              </div>
              <div className="button-area">
                <Link to={`${accepted ? '/' : '/accepted'}`} className="noSelect">
                  <button className="button" >
                    {buttonText}
                  </button>
                </Link>
                <Link to="/paperstatus" className="noSelect">
                  <button id='btn-status' className='button' disabled={true} >
                    Paper Status
                  </button>
                </Link>
                <Link to="/session-assign" className="noSelect">
                  <button id='btn-assign' className='button' disabled={true}>
                    Assign Accepted Papers
                  </button>
                </Link>
              </div>
            </div>
        </div>
        </React.Fragment>
    )
}