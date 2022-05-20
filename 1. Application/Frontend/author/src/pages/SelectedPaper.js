import React, { useState, useEffect } from 'react';
import { SelectedPaperHS } from '../Components/SelectedPaperHS';
import { Sidebar } from '../Components/Sidebar';
import "./SelectedPaper.css";

export const SelectedPaper = () => {
    //const paper = JSON.parse(localStorage.getItem("selectedPaper"));
    return (
        <React.Fragment>
            <div class="ap-layout">
                <Sidebar></Sidebar>
            </div>
        </React.Fragment>
    )

}
