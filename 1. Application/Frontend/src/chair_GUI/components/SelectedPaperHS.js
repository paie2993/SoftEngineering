import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './SelectedPaperHS.css'
import { HeaderText } from './HeaderText'
import { Table } from './Table'
import TextField from '@mui/material/TextField';
import Autocomplete from '@mui/material/Autocomplete';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormHelperText from '@mui/material/FormHelperText';
import FormControl from '@mui/material/FormControl';
import Select from '@mui/material/Select';

export const VerdictSelect = () => {
  const [verdict, setVerdict] = React.useState('');

  const handleChange = (event) => {
    setVerdict(event.target.value);
  };

  return (
    <div>
      <FormControl sx={{ m: 1, minWidth: 465, marginTop:2, marginRight: 6 }}>
        <InputLabel id="demo-simple-select-helper-label">Verdict</InputLabel>
        <Select
          labelId="demo-simple-select-helper-label"
          id="demo-simple-select-helper"
          value={verdict}
          label="Verdict"
          onChange={handleChange}
        >
          <MenuItem value="">
            <em>None</em>
          </MenuItem>
          <MenuItem value={'accepted'}>Accepted</MenuItem>
          <MenuItem value={'rejected'}>Rejected</MenuItem>
        </Select>
      </FormControl>
    </div>
  );
}
export default function ComboBox() {
  return (
    <Autocomplete
      disablePortal
      id="combo-box-demo"
      options={conferencesForPaper}
      sx={{ width: 650, marginTop: '20px' }}
      renderInput={(params) => <TextField {...params} label="Conference for which status is displayed" />}
    />
  );
}

const conferencesForPaper = [
{label: "Conference1"},
{label: "Conference2"},
{label: "Conference3"},
{label: "Conference4"}
]

const columns = [
  { field: 'idReviewer', headerName: 'ID', width: 70 },
  { field: 'name', headerName: 'Name', width: 300 },
  { field: 'verdict', headerName: 'Verdict', width: 250 }
];


export const SelectedPaperHS = () => {

    const paper = JSON.parse(localStorage.getItem("selectedPaper"));
    const [selectedReviewer, setSelectedReviewer] = useState(null);
    const [age, setAge] = React.useState('');

    const handleChange = (event) => {
      setAge(event.target.value);
      console.log(age)
    };


    return (
        <>
        <div className="hero">
            <HeaderText text={paper.title} />
            <ComboBox />
            <h2 className='label'>Reviewers' evaluation</h2>
            <div className="table-sp">
                    <Table 
                    accepted={false}
                    content={[]}
                    columns={columns}
                    selected={[selectedReviewer, setSelectedReviewer]}
                    />
            </div>
            <div className='chair-area'>
              <h2 className='label'>My evaluation</h2>
              <VerdictSelect></VerdictSelect>
              <button className='button'>
                    Submit
              </button>
            </div>
        </div>
        </>
    )
}