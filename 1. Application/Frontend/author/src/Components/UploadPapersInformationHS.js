import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import { useFetch } from "./useFetch";
import "./UploadPapersInformationHS.css";
import { HeaderText } from "./HeaderText";
import { Table } from "./Table";
import TextField from "@mui/material/TextField";

const columns = [
  { field: "idAuthor", headerName: "ID", width: 60 },
  { field: "nameAuthor", headerName: "Name", width: 120 },
  { field: "phoneNumber", headerName: "Phone Number", width: 150 },
  { field: "email", headerName: "Email", width: 200 },
  { field: "address", headerName: "Address", width: 250 },
];

export const UploadPaperInformationHS = ({}) => {
  const paper = JSON.parse(localStorage.getItem("selectedPaper"));
  const [topic, setTopic] = useState(null);

  return (
    <div className="hero">
      <HeaderText text={"Upload paper"} />
      <p class="info-text-title">Paper information</p>

      <div className="container">
        <div className="details">
          <div className="paper-info">
            <p className="info-text">Title</p>
            <input className="info-input" />
          </div>
          <div className="paper-info">
            <p className="info-text">Abstract</p>
            <input className="info-input" />
          </div>
          <div className="paper-info">
            <p className="info-text">Topics</p>
            <input className="info-input" />
          </div>
          <div className="paper-info">
            <p className="info-text">Keywords</p>
            <input className="info-input" />
          </div>
          <div className="paper-info">
            <p className="info-text">Authors</p>
            <div className="table-sp">
              <Table
                personally_uploaded={false}
                content={[]}
                columns={columns}
                selected={[]}
              />
            </div>
          </div>
        </div>
      </div>
      <div className="topics-area">
        <div className="btn-area">
          <button className="detail-btn">Submit</button>
        </div>
      </div>
    </div>
  );
};
