import { useEffect, useState } from "react";

const apiLink = "https://backend-1653064679221.azurewebsites.net/";

export async function getAllConferences(URL) {
    const response = await fetch(URL+ 'conference/', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaGFpciIsImV4cCI6MTY1MzI0Mjk4NCwiaWF0IjoxNjUzMjA2OTg0fQ.l3yosNb9erOdl6tvzwivta8O_3pprydmx8cSgUiHmOfGrBHOb2bRcyUb-rCZnJLJdgBcEwJ_khLCh_ROZOpiSA',
            "Content-Type": "application/json"
        }
    });

    if (response.status != 200) {
        throw response;
    }
    const conferences = await response.json();
    return conferences;
}

export const useGetConferences = (url) => {
    const [data, setData] = useState([])
   
    useEffect(() => {
        getAllConferences(url).
        then(response => 
            { setData(response)
              console.log(response)} )
    }, [url])
    return data;
};

export async function getConference(ID) {
    const response = await fetch(apiLink + 'conference/' + ID, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaGFpciIsImV4cCI6MTY1MzI0Mjk4NCwiaWF0IjoxNjUzMjA2OTg0fQ.l3yosNb9erOdl6tvzwivta8O_3pprydmx8cSgUiHmOfGrBHOb2bRcyUb-rCZnJLJdgBcEwJ_khLCh_ROZOpiSA',
            "Content-Type": "application/json"
        }
    });

    if (response.status != 200) {
        throw response;
    }
    const conference = await response.json();
    return conference;
}

export const useGetConference = (url, ID) => {
    const [data, setData] = useState([])
   
    useEffect(() => {
        getConference(url, ID).
        then(response => 
            { setData(response)
              console.log(response)} )
    }, [url])
    return data;
};

export async function updateConference(ID, body) {
    const response = await fetch(apiLink + 'conference/' + ID, {
        method: 'PUT',
        headers: {
            'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaGFpciIsImV4cCI6MTY1MzI0Mjk4NCwiaWF0IjoxNjUzMjA2OTg0fQ.l3yosNb9erOdl6tvzwivta8O_3pprydmx8cSgUiHmOfGrBHOb2bRcyUb-rCZnJLJdgBcEwJ_khLCh_ROZOpiSA',
            'Content-Type': 'application/json'
        },
        body: body
    });

    if (response.status != 200) {
        throw response;
    }

    return response;
}