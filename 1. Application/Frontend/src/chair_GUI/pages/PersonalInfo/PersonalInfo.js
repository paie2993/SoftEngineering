import React, { useState, useEffect } from 'react';
import { Sidebar } from '../../components/Sidebar';
import './PersonalInfo.css'

export const PersonalInfo = () => {

    return (
        <React.Fragment>
            <div class="ap-layout">
                <Sidebar />
                <p>Here is the personal information of the chair!</p>
            </div>
        </React.Fragment>
    )
}