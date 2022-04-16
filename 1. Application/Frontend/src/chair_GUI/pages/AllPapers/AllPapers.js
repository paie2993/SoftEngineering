import React, { useState, useEffect } from 'react';
import { HeroSection } from '../../components/HeroSection';
import { Sidebar } from '../../components/Sidebar';
import './AllPapers.css'

export const AllPapers = () => {

    return (
        <React.Fragment>
            <div class="ap-layout">
                <Sidebar />
                <HeroSection />
            </div>
        </React.Fragment>
    )
}