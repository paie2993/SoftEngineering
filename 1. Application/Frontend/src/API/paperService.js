import { useEffect, useState } from "react";

const apiLink = "https://backend-1653064679221.azurewebsites.net/";

export async function getAllPapers(URL, ID) {
    const response = await fetch(URL + 'conference/' + ID + '/papers', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjaGFpciIsImV4cCI6MTY1MzI0Mjk4NCwiaWF0IjoxNjUzMjA2OTg0fQ.l3yosNb9erOdl6tvzwivta8O_3pprydmx8cSgUiHmOfGrBHOb2bRcyUb-rCZnJLJdgBcEwJ_khLCh_ROZOpiSA',
            "Content-Type": "application/json"
        }
    });

    if (response.status != 200) {
        throw response;
    }
    const papers = await response.json();
    return papers;
}

export const useGetPapers = (url, id) => {
    const [data, setData] = useState([])
   
    useEffect(() => {
        getAllPapers(url, id).
        then(response => 
            { setData(response)
              console.log(response)} )
    }, [url])
    return data;
};