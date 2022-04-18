import React, { useState, useEffect } from 'react';
import { HeroSection } from '../../components/HeroSection';
import { Sidebar } from '../../components/Sidebar';
import './AcceptedPapers.css'

export const AcceptedPapers = () => {

    return (
        <React.Fragment>
            <div class="ap-layout">
                <Sidebar />
                <HeroSection accepted={true}/>
            </div>
        </React.Fragment>
    )
}
