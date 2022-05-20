import React, { useState, useEffect } from "react";
import "./AllPapersHS.css";
import { Link } from 'react-router-dom';
import { useFetch } from './useFetch';
import { HeaderText } from './HeaderText'
import { Table } from './Table'

export const AllPapersHS = (props) => {
  const columns = [
    { field: 'id', headerName: 'ID', width: 100 },
    { field: 'title', headerName: 'Paper Name', width: 400 },
    { field: 'status', headerName: 'Status', width: 290}
  ];

const personally_uploaded = props.personally_uploaded;
const apiURL = "https://mocki.io/v1/b286fae1-d341-4d28-ad65-ad8613c2e064";
const papers = useFetch(apiURL);

const [buttonText, setButtonText] = useState("");
const [paper, setPaper] = useState(null);

useEffect(() => {
  if (personally_uploaded === true) {
    setButtonText("All Papers");
  }
  else {
    setButtonText("Personally Uploaded");
  }
}, [personally_uploaded])

function enableButtons(item) {
  const btn1 = document.getElementById("btn-assign");
  if (item.status === "personally_uploaded"){
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
    localStorage.setItem("selectedPaper", JSON.stringify(paper));
  }
}, [paper])

return (
    <React.Fragment>
    <div className="hero">
        <HeaderText text={`${personally_uploaded ? 'Personally uploaded' : 'All Papers'}`} />
        <div className="table-section">
          <div className="table">
            <Table 
              personally_uploaded={personally_uploaded}
              content={papers}
              columns={columns}
              selected={[paper, setPaper]}
              />
          </div>
          <div className="button-area">
            <Link to={`${personally_uploaded ? '/' : '/personally_uploaded'}`} className="noSelect">
              <button className="button" >
                {buttonText}
              </button>
            </Link>
            <Link to="/" className="noSelect">
              <button id='btn-details' className='button' disabled={true}>
                Details
              </button>
            </Link>
          </div>
        </div>
    </div>
    </React.Fragment>
)
};
