import React, { useState, useEffect } from "react";
import { Link } from "react-router-dom";
import "./SelectedPaperHS.css";
import { HeaderText } from "./HeaderText";
import { Table } from "./Table";
import TextField from "@mui/material/TextField";
import Autocomplete from "@mui/material/Autocomplete";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormHelperText from "@mui/material/FormHelperText";
import FormControl from "@mui/material/FormControl";
import Select from "@mui/material/Select";



const columns = [
  { field: "idAuthor", headerName: "ID", width: 60 },
  { field: "nameAuthor", headerName: "Name", width: 120 },
  { field: "phoneNumber", headerName: "Phone Number", width: 150 },
  { field: "email", headerName: "Email", width: 200 },
  { field: "address", headerName: "Address", width: 250 },
];

export const SelectedPaperHS = () => {
  const paper = JSON.parse(localStorage.getItem("selectedPaper"));
  const [selectedReviewer, setSelectedReviewer] = useState(null);

  return (
    <div className="hero">
      <HeaderText text={"Paper title"} />
      <div class="details">
        <div class="paper-info">
          <p className="info-text">Name</p>
          <input className="info-input" value={"Name..."} />
        </div>
        <div class="paper-info">
          <p className="info-text">Abstract</p>
          <input className="info-input" value={"Abstract text..."} />
        </div>
        <div class="paper-info">
          <p className="info-text">Keywords</p>
          <input className="info-input" value={"Keywords..."} />
        </div>
        <div class="paper-info">
        <p className="info-text">Authors</p>
          <div className="table-sp">
            <Table
              personally_uploaded={false}
              content={[]}
              columns={columns}
              selected={[selectedReviewer, setSelectedReviewer]}
            />
          </div>
        </div>
      </div>
    </div>
  );
};
