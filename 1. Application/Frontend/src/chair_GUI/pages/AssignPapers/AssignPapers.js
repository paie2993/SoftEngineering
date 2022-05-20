import React, { useState, useEffect } from 'react';
import { PapersHS } from '../../components/PapersHS';
import { Sidebar } from '../../components/Sidebar';
import './AssignPapers.css'
import { AssignHS } from '../../components/AssignHS'

export const AssignPapers = (props) => {

    return (
        <React.Fragment>
            <div class="ap-layout">
                <Sidebar />
                <AssignHS />
            </div>
        </React.Fragment>
    )
}