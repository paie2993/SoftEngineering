import React, { useState, useEffect } from 'react';
import { PapersHS } from '../../components/PapersHS';
import { Sidebar } from '../../components/Sidebar';
import './AcceptedPapers.css'

export const AcceptedPapers = (props) => {

    return (
        <React.Fragment>
            <div class="ap-layout">
                <Sidebar />
                <PapersHS accepted={true} />
            </div>
        </React.Fragment>
    )
}
