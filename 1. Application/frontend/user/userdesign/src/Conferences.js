import LeftBar from "./LeftBar";
import './mystyle.css';
export const Conferences =()=> {
    return (
      <>
      <LeftBar/>
      <meta charset="utf-8"></meta>
      <p style={{position:'absolute',top:'-15px',left:'220px',fontSize:'60px',overflow:'hidden', overflowy:'scroll'}}>Conferences</p>
      <div class="listbox-area" style={{overflow:'hidden',overflowy:'scroll',maxHeight:'100px'}}>
          <nav style={{position:'absolute',top:'155px',left:'220px',fontSize:'40px',maxHeight:'100px'}}>      
              <ul>
                  <li tabindex="1" style={{padding:'15px'}}>Conference 1</li>
                  <li tabindex="1" style={{padding:'15px'}}>Conference 2</li>
                  <li tabindex="1" style={{padding:'15px'}}>Conference 3</li>
                  <li tabindex="1" style={{padding:'15px'}}>Conference 4</li>
                  <li tabindex="1" style={{padding:'15px'}}>Conference 5</li>
                  <li tabindex="1" style={{padding:'15px'}}>Conference 6</li> 
                  <li tabindex="1" style={{padding:'15px'}}>Conference 7</li> 
                  <li tabindex="1" style={{padding:'15px'}}>Conference 8</li>
                  <li tabindex="1" style={{padding:'15px'}}>Conference 9</li>
                  <li tabindex="1" style={{padding:'15px'}}>Conference 10</li>
                  <li tabindex="1" style={{padding:'15px'}}>Conference 11</li>
                  <li tabindex="1" style={{padding:'15px'}}>Conference 12</li>
                  <li tabindex="1" style={{padding:'15px'}}>Conference 13</li>
  
              </ul>
          </nav>  
      </div>
      <button id='selectbutton'>Select</button>
  
      </>
      );
    }
