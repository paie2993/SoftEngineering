//c. Author functionalities:
//•    An author can see all the papers they have personally submitted.x
//•    GET: /author/papers
//•    An author can see the status (pending/accepted/rejected) of any paper they have personally submitted.x
//•    Same as above
//•    An author can see the paper information of any paper they have personally submitted.x
//•    Same as above
//•    An author can upload the camera-ready copy of a paper.
//•    An author can upload the full paper (PDF or Word).
//•    An author can submit paper information (title, abstract, topics, keywords, other authors, their emails, their addresses, their phone numbers).x
//•    POST: /paper/


const {properties} = require("./properties")

export async function getAllPapers(){
    try{
        const response = await fetch(properties.api_link + '/author/papers');
        return await response.json();
    }
    catch(error){
        return [];
    }
}

export async function createPaper(data){
    const response = await fetch(properties.api_link + '/paper', {
        method: 'POST',
        headers: {
            "Content-Type": "application/json"
        }
    }).then(response => {
        if(response.status != 200){
            throw response;
        }
    })
}