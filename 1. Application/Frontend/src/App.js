import './App.css';
import { Sidebar } from './chair_GUI/components/Sidebar';
import { AllPapers } from './chair_GUI/pages/AllPapers/AllPapers';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { AcceptedPapers } from './chair_GUI/pages/AcceptedPapers/AcceptedPapers';
import { PersonalInfo } from './chair_GUI/pages/PersonalInfo/PersonalInfo';
import { AllConferences } from './chair_GUI/pages/AllConferences/AllConferences'
import { PaperStatus } from './chair_GUI/pages/PaperStatus/PaperStatus';
import { useState } from 'react';
import { ConferenceDetails } from './chair_GUI/pages/ConferenceDetails/ConferenceDetails';
import { Deadlines } from './chair_GUI/pages/ConferenceDeadlines/Deadlines'
import { AssignPapers } from './chair_GUI/pages/AssignPapers/AssignPapers';

function App() {

  // It is considered that the user logged in is a Chair
  localStorage.setItem('userType', 'chair');
  // that has the id 1
  localStorage.setItem('userId', 2);

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<AllPapers />}/>
          <Route path="/accepted" element={<AcceptedPapers />}/>
          <Route path="/personalinfo" element={<PersonalInfo />}/>
          <Route path="/conferences" element={ <AllConferences />}/>
          <Route path="/paperstatus" element={ <PaperStatus/>}/>
          <Route path="/conferencedetails" element={ <ConferenceDetails/>}/>
          <Route path="/deadlines" element={ <Deadlines/>}/>
          <Route path="/session-assign" element={ <AssignPapers />}/>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
