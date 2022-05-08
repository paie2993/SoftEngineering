import React, { useState, useEffect } from 'react';
import { HeaderText } from '../../components/HeaderText';
import { PapersHS } from '../../components/PapersHS';
import { SelectedPaperHS } from '../../components/SelectedPaperHS';
import { Sidebar } from '../../components/Sidebar';
import './PaperStatus.css'

export const PaperStatus = () => {

    return (
        <React.Fragment>
            <div class="ap-layout">
                <Sidebar />
                <SelectedPaperHS />
            </div>
        </React.Fragment>
    )
}