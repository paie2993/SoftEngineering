import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './SelectedPaperHS.css'
import { HeaderText } from './HeaderText'
import { Table } from './Table'

export const SelectedPaperHS = () => {

    const paper = JSON.parse(localStorage.getItem("selectedPaper"));

    return (
        <>
        <div className="hero">
            <HeaderText text={paper.title} />
        </div>
        </>
    )
}