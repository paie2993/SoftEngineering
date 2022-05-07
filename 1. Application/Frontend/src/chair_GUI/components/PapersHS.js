import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './PapersHS.css'
import { useFetch } from '../../useFetch';
import { HeaderText } from './HeaderText'
import { Table } from './Table'

export const PapersHS = (props) => {

    const columns = [
        { field: 'id', headerName: 'ID', width: 70 },
        { field: 'title', headerName: 'Paper Name', width: 270 },
        { field: 'abstract_text', headerName: 'Abstract', width: 300 },
        { field: 'status', headerName: 'Status', width: 300}
      ];

    const accepted = props.accepted;
    const apiURL = "https://mocki.io/v1/b286fae1-d341-4d28-ad65-ad8613c2e064";
    const papers = useFetch(apiURL);
    
    const [buttonText, setButtonText] = useState("");
    const [paper, setPaper] = useState(null);

    useEffect(() => {
      if (accepted === true) {
        setButtonText("All Papers");
      }
      else {
        setButtonText("Accepted Papers");
      }
    }, [accepted])

    return (
        <React.Fragment>
        <div className="hero">
            <HeaderText text={`${accepted ? 'Accepted Papers' : 'Papers'}`} />
            <div className="table-section">
              <Table 
              accepted={accepted}
              content={papers}
              columns={columns}
              selected={[paper, setPaper]}
              />
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
                <Link to="/" className="noSelect">
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