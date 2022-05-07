import './App.css';
import { Sidebar } from './chair_GUI/components/Sidebar';
import { AllPapers } from './chair_GUI/pages/AllPapers/AllPapers';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { AcceptedPapers } from './chair_GUI/pages/AcceptedPapers/AcceptedPapers';
import { PersonalInfo } from './chair_GUI/pages/PersonalInfo/PersonalInfo';
import { AllConferences } from './chair_GUI/pages/AllConferences/AllConferences'
import { PaperStatus } from './chair_GUI/pages/PaperStatus/PaperStatus';
import { useState } from 'react';

function App() {

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<AllPapers />}/>
          <Route path="/accepted" element={<AcceptedPapers />}/>
          <Route path="/personalinfo" element={<PersonalInfo />}/>
          <Route path="/conferences" element={ <AllConferences />}/>
          <Route path="/paperstatus" element={ <PaperStatus/>}/>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
