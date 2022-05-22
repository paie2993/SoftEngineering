import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import { useFetch } from '../../useFetch';
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

  
export const ComboBox = (props) => {

  const apiURL = "https://mocki.io/v1/6fde9b8c-b9eb-47f6-9c17-c7566225521f";
  const conferences = useFetch(apiURL);
  const [conferencesForPaper, setConferencesForPaper] = props.conferenceState;

  useEffect(() => {
    const labels = []
    conferences.map((conf) => labels.push({label:conf.name}));
    setConferencesForPaper(labels);
    console.log(conferencesForPaper);
  }, [conferences]);

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

const columns = [
  { field: 'idReviewer', headerName: 'ID', width: 70 },
  { field: 'name', headerName: 'Name', width: 300 },
  { field: 'verdict', headerName: 'Verdict', width: 250 }
];


export const SelectedPaperHS = () => {

    const paper = JSON.parse(localStorage.getItem("selectedPaper"));
    const [selectedReviewer, setSelectedReviewer] = useState(null);
    const [conferencesForPaper, setConferencesForPaper] = useState(null);

    // useEffect(() => {
    //   if (conferencesForPaper !== null) {
    //     document.getElementById('verdict-chair').visibility('visible');
    //   }
    //   else {
    //     document.getElementById('verdict-chair').visibility('hidden');
    //   }
    // }, [conferencesForPaper])

    return (
        <>
        <div className="hero">
            <HeaderText text={paper.title} />
            <ComboBox conferenceState={[conferencesForPaper, setConferencesForPaper]}/>
            <h2 className='label'>Reviewers' evaluation</h2>
            <div className="table-sp">
                    <Table 
                    accepted={false}
                    content={[]}
                    columns={columns}
                    selected={[selectedReviewer, setSelectedReviewer]}
                    />
            </div>
            <div className='chair-area' id='verdict-chair'>
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