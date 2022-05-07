import React, { useState, useEffect } from 'react';
import { PapersHS } from '../../components/PapersHS';
import { Sidebar } from '../../components/Sidebar';
import './AllPapers.css'

export const AllPapers = (props) => {

    return (
        <React.Fragment>
            <div class="ap-layout">
                <Sidebar />
                <PapersHS accepted={false}/>
            </div>
        </React.Fragment>
    )
}