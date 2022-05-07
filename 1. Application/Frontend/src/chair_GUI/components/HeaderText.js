import React, { useState, useEffect } from 'react';
import './HeaderText.css'

export const HeaderText = (props) => {

    const text = props.text;
    return (
        <div className='title'>
            {text}
        </div>
    )
}