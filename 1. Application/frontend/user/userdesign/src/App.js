import LeftBar from "./LeftBar";
import './mystyle.css';
import { Conferences } from "./Conferences";
import { PersInfo } from "./PersonalInfo";
import { Deadlines } from "./Deadlines";
import { Details } from "./Details";
import { BrowserRouter, Routes, Route } from "react-router-dom";
function App() {
  return (
    <div style={{backgroundColor:'#f8ffd6'}}>
    <>
    <BrowserRouter>
    <Routes>
    <Route path='/'element={<Conferences/>}/>
    <Route path='/info'element={<PersInfo/>}/>
    <Route path='/deadlines' element={<Deadlines/>}/>
    <Route path='/details' element={<Details/>}/>

    </Routes> 
    </BrowserRouter>

    </>
    </div>
    );
}

export default App;