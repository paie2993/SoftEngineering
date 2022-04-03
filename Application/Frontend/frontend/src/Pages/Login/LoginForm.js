import { Box, TextField, Button } from "@mui/material";
import { useReducer } from "react";
import { loginUser } from "../../API/userService";
import { useNavigate} from "react-router-dom";

function LoginForm(props) {
    // Navigate hook
    let navigate = useNavigate();
    // Reducer hook - contains the login info input from the login form
    let [loginInput, setLoginInput] = useReducer(
        (state, newState) => ({ ...state, ...newState }),
        {
            "username": "",
            "password": ""
        }
    )

    // Handle the submission of the login info 
    let handleLoginSubmit = (event) => {
        // prevent the submit button from refreshing the page
        event.preventDefault();

        // attempt login
        loginUser(loginInput)
            // if login succeeded, no error was thrown
            .then(() => navigate("/"))
            // login failed, show an error
            .catch(response => {
                if (response.status === 401) {
                    props.setErrormsg("Incorrect credentials.");
                    props.setErrorOpen(true);
                } else {
                    props.setErrormsg("An error ocurred.");
                    props.setErrorOpen(true);
                }
            })
    }

    // Handle input change - update the login input state
    let handleLoginInput = (event) => {
        let name = event.target.name;
        let value = event.target.value;

        setLoginInput({ [name]: value });
    }


    return (
        <Box
            component="form"
            onSubmit={handleLoginSubmit}
            sx={{ mt: 1 }}
        >
            <TextField
                margin="normal"
                required
                fullWidth
                id="username"
                label="Username"
                name="username"
                autoComplete="username"
                autoFocus
                onChange={handleLoginInput}
            />

            <TextField
                margin="normal"
                required
                fullWidth
                id="password"
                label="Password"
                name="password"
                type="password"
                autoComplete="current-password"
                onChange={handleLoginInput}
            />
            <Button
                type="submit"
                fullWidth
                variant="contained"
                sx={{ mt: 3, mb: 2 }}
            >
                Sign in
            </Button>
        </Box>
    )
}

export default LoginForm