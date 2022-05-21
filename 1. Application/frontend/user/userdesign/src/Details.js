import LeftBar from "./LeftBar";
import './mystyle.css';
export const Details =()=> {
    return (
      <>
      <LeftBar/>
      <meta charset="utf-8"></meta>
      <p style={{position:'absolute',top:'-15px',left:'220px',fontSize:'60px',overflow:'hidden', overflowy:'scroll'}}>Selected conference name</p>
      <p style={{position:'absolute', top:'130px',left:'250px',fontSize:'35px'}}>Name</p>
      <p style={{position:'absolute', top:'230px',left:'250px',fontSize:'35px'}}>URL</p>
      <p style={{position:'absolute', top:'330px',left:'250px',fontSize:'35px'}}>Subtitles</p>
      <p style={{position:'absolute', top:'430px',left:'250px',fontSize:'35px'}}>Organizer</p>

      <p style={{position:'absolute', top:'130px',left:'550px',fontSize:'35px'}}>Conference Name</p>
      <p style={{position:'absolute', top:'230px',left:'550px',fontSize:'35px'}}>Conference URL</p>
      <p style={{position:'absolute', top:'330px',left:'550px',fontSize:'35px'}}>Conference Subtitles</p>
      <p style={{position:'absolute', top:'450px',left:'550px',fontSize:'20px'}}>Name</p>
      <p style={{position:'absolute', top:'525px',left:'550px',fontSize:'20px'}}>Email</p>
      <p style={{position:'absolute', top:'600px',left:'550px',fontSize:'20px'}}>Phone Number</p>

      <p style={{position:'absolute', top:'450px',left:'750px',fontSize:'20px'}}>John Doe</p>
      <p style={{position:'absolute', top:'525px',left:'750px',fontSize:'20px'}}>username@gmail.com</p>
      <p style={{position:'absolute', top:'600px',left:'750px',fontSize:'20px'}}>07n-am telefon</p>
      <p style={{position:'absolute', top:'125px',left:'1050px',fontSize:'50px'}}>Topics of interest</p>
      <select style={{position:'absolute', top:'250px',left:'1050px',fontSize:'30px'}}>
      <option value="volvo">First topic of interest</option>
      <option value="saab">Second topic of interest</option>
      <option value="mercedes">Third topic of interest</option>
      <option value="audi">Add a new topic of interest</option>
      </select>
      
      <button id="delbutton">Delete selected topic</button>
      <button id='selectbutton'>Deadlines</button>
      </>
      );
    }
