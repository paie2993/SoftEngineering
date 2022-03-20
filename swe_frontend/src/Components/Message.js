import axios from 'axios';
import React, { useEffect, useState } from 'react'


export const Message = () => {
    const api_url = 'http://localhost:8080/helloworld'
    const [message, setMessage] = useState('');

    useEffect(() => () => {
        axios.get(api_url).then(response => {
            setMessage(response.data.text)
            console.log(response.data.text)
        }
        )
        .catch(error => console.error(`Error: ${error}`));
    }, [])


    return (
        <>
            <h1>{message}</h1>
        </>
    )
}

export default Message;