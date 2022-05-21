import React, { Component } from 'react'
import './mystyle.css'
export class LeftBar extends Component {
  render() {
    return (
      <>
      <div style={{background: '#6c0882', height: '800px', width:'200px',
      border:'solid'}}>
        <button class="homeButton" role="button">HOME</button>
        <div style={{background: '#b839d4 ',position:'absolute',top:'125px',left:'22px', height: '400px', width:'170px',
         border:'solid'}}>
             <button class="insideButton" role="button">Conferences</button>
             <button class="insideButton" role="button" style={{top:'300px',left:'0px',maxWidth:'170px'}}>Personal Information</button>
        </div>
         <button id="x" class="homeButton" role="button" style={{top:'680px',backgroundColor:'#b3225e'}}>LOG OUT</button>

      </div>
      </>
    )
  }
}

export default LeftBar