const { properties } = require("../properties");

// Attempts to log in to the api using the given credentials
function loginUser(credentials) {
    return fetch(properties.api_link + '/login', {
        method: 'POST',
        body: JSON.stringify(credentials),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => {
            // if login failed, throw the response in order for it
            // to be catched in the caller 
            if (response.status != 200) {
                throw response;
            }

            return response.json();
        })
        .then(response => {
            // if login didn't fail, add the current login info
            // to the local storage
            let local_user = JSON.stringify(response);
            console.log("Successfully logged in");
            console.log(local_user);

            localStorage.setItem("local_user", local_user);
        })
}

function registerUser(user_info) {
    return fetch(properties.api_link + '/register', {
        method: 'POST',
        body: JSON.stringify(user_info),
        headers: {
            "Content-Type": "application/json"
        }
    })
        .then(response => {
            if(response.status != 200) {
                throw response;
            }
        })
}

function isUserLoggedIn() {
    return "local_user" in localStorage;
}

function logUserOut() {
    localStorage.removeItem("local_user");
}

export default loginUser;
export { loginUser, isUserLoggedIn, logUserOut, registerUser };