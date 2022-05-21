import LeftBar from "./LeftBar";
import './mystyle.css';
export const Deadlines =()=> {
    return (
      <>
      <LeftBar/>
      <meta charset="utf-8"></meta>
      <p style={{position:'absolute',top:'-15px',left:'220px',fontSize:'60px',overflow:'hidden', overflowy:'scroll'}}>Selected conference name</p>
      <p style={{position:'absolute', top:'100px',left:'230px',fontSize:'45px', color:"purple"}}>Deadlines</p>
      <p style={{position:'absolute', top:'230px',left:'250px',fontSize:'35px'}}>Paper Submision</p>
      <p style={{position:'absolute', top:'330px',left:'250px',fontSize:'35px'}}>Paper review</p>
      <p style={{position:'absolute', top:'430px',left:'250px',fontSize:'35px'}}>Acceptance notif</p>
      <p style={{position:'absolute', top:'530px',left:'250px',fontSize:'35px'}}>Uploading acceptance<br></br> papers</p>

      <p style={{position:'absolute', top:'230px',left:'650px',fontSize:'35px'}}>dd/mm/yyyy hh:mm:ss</p>
      <p style={{position:'absolute', top:'330px',left:'650px',fontSize:'35px'}}>dd/mm/yyyy hh:mm:ss</p>
      <p style={{position:'absolute', top:'430px',left:'650px',fontSize:'35px'}}>dd/mm/yyyy hh:mm:ss</p>
      <p style={{position:'absolute', top:'530px',left:'650px',fontSize:'35px'}}>dd/mm/yyyy hh:mm:ss</p>
      </>
      );
    }
