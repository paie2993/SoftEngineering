import LeftBar from "./LeftBar";
import './mystyle.css';
export const PersInfo =()=> {
    return (
      <>
      <LeftBar/>
      <p style={{position:'absolute',top:'-15px',left:'220px',fontSize:'60px',overflow:'hidden', overflowy:'scroll'}}>Personal Information</p>
      <p style={{position:'absolute', top:'150px',left:'250px',fontSize:'30px'}}>First Name</p>
      <p style={{position:'absolute', top:'230px',left:'250px',fontSize:'30px'}}>Last Name</p>
      <p style={{position:'absolute', top:'310px',left:'250px',fontSize:'30px'}}>Phone Number</p>
      <p style={{position:'absolute', top:'390px',left:'250px',fontSize:'30px'}}>Date of Birth</p>
      <p style={{position:'absolute', top:'470px',left:'250px',fontSize:'30px'}}>Country</p>
      <p style={{position:'absolute', top:'550px',left:'250px',fontSize:'30px'}}>City</p>
      <p style={{position:'absolute', top:'630px',left:'250px',fontSize:'30px'}}>Street</p>
      <p style={{position:'absolute', top:'710px',left:'250px',fontSize:'30px'}}>Street number</p>
      <input style={{position:'absolute', top:'175px',left:'550px',fontSize:'30px'}} type="text" ></input>
      <input style={{position:'absolute', top:'255px',left:'550px',fontSize:'30px'}} type="text" ></input>
      <input style={{position:'absolute', top:'335px',left:'550px',fontSize:'30px'}} type="text" ></input>
      <input style={{position:'absolute', top:'415px',left:'550px',fontSize:'30px'}} type="text" ></input>
      <input style={{position:'absolute', top:'495px',left:'550px',fontSize:'30px'}} type="text" ></input>
      <input style={{position:'absolute', top:'575px',left:'550px',fontSize:'30px'}} type="text" ></input>
      <input style={{position:'absolute', top:'655px',left:'550px',fontSize:'30px'}} type="text" ></input>
      <input style={{position:'absolute', top:'735px',left:'550px',fontSize:'30px'}} type="text" ></input>
      <button id='selectbutton'>Update</button>
      </>
      );
    }
