import './App.css';
import { Sidebar } from './chair_GUI/components/Sidebar';
import { AllPapers } from './chair_GUI/pages/AllPapers/AllPapers';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { AcceptedPapers } from './chair_GUI/pages/AcceptedPapers/AcceptedPapers';

function App() {
  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<AllPapers />}/>
          <Route path="/accepted" element={<AcceptedPapers />}/>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
