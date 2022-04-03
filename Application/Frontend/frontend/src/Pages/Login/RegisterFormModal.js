import { Box, Typography, Modal, TextField, Button, Card } from "@mui/material";
import { useReducer, useState } from "react";
import { useNavigate } from "react-router-dom";
import { registerUser } from "../../API/userService";
import ErrorBanner from "./ErrorBanner";

function RegisterFormModal(props) {
    // Navigate hook
    let navigate = useNavigate();

    let [errorOpen, setErrorOpen] = useState(false)
    let [errorMsg, setErrorMsg] = useState("")

    // Reducer that handles user input
    let [registerInput, setRegisterInput] = useReducer(
        (state, newState) => ({ ...state, ...newState }),
        {
            "username": "",
            "email": "",
            "password": "",
            "firstName": "",
            "lastName": "",
            "dateOfBirth": "1970-01-01",
            "phoneNumber": "",
            "country": "",
            "street": "",
            "streetNumber": 0,
            "city": "",
            "userType": 2
        }
    )

    let handleRegisterInput = (event) => {
        let name = event.target.name;
        let value = event.target.value;

        setRegisterInput({ [name]: value });
    }

    let handleRegisterSubmit = (event) => {
        event.preventDefault();

        registerInput["role"] = "USER";

        registerUser(registerInput)
            .then(() => navigate("/"))
            .catch(response => {
                response.text().then((text) => {
                    setErrorMsg(text);
                })
                setErrorOpen(true);
            })
    }

    return (
        <Modal
            open={props.open}
            onClose={() => { props.setOpen(false) }}
        >

            <Box
                sx={{
                    marginTop: 8,
                    display: 'flex',
                    flexDirection: 'column',
                    alignItems: 'center',
                }}
            >
                <ErrorBanner errorOpen={errorOpen} setErrorOpen={setErrorOpen} errorMsg={errorMsg} />
                <Card variant="outlined" onClose={() => { props.setOpen(false) }} sx={{overflow: 'auto', maxHeight: '80vh', maxWidth: '50vw'}}>

                    <Box
                        component="form"
                        onSubmit={handleRegisterSubmit}
                        sx={{
                            padding: '20px'
                        }}
                    >

                        <Typography variant="h5" align="center">Sign up</Typography>
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="username"
                            label="Username"
                            name="username"
                            autoFocus
                            onChange={handleRegisterInput}
                        />

                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="password"
                            label="Password"
                            name="password"
                            type="password"
                            onChange={handleRegisterInput}
                        />
                        
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="email"
                            label="Email"
                            name="email"
                            autoFocus
                            onChange={handleRegisterInput}
                        />
                        
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="firstName"
                            label="First Name"
                            name="firstName"
                            autoFocus
                            onChange={handleRegisterInput}
                        />
                        
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="lastName"
                            label="Last Name"
                            name="lastName"
                            autoFocus
                            onChange={handleRegisterInput}
                        />
                        
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="dateOfBirth"
                            label="Date of birth"
                            name="dateOfBirth"
                            defaultValue="2017-05-24"
                            type="date"
                            autoFocus
                            onChange={handleRegisterInput}
                        />
                        
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="phoneNumber"
                            label="Phone Number"
                            name="phoneNumber"
                            autoFocus
                            onChange={handleRegisterInput}
                        />
                        
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="country"
                            label="Country"
                            name="country"
                            autoFocus
                            onChange={handleRegisterInput}
                        />
                        
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="street"
                            label="Street"
                            name="street"
                            autoFocus
                            onChange={handleRegisterInput}
                        />
                        
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="streetNumber"
                            label="Street"
                            name="streetNumber"
                            autoFocus
                            onChange={handleRegisterInput}
                        />
                        
                        <TextField
                            margin="normal"
                            required
                            fullWidth
                            id="city"
                            label="City"
                            name="city"
                            autoFocus
                            onChange={handleRegisterInput}
                        />

                        <Button
                            type="submit"
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}
                        >
                            Register
                        </Button>
                    </Box>
                </Card>
            </Box>
        </Modal>
    )
}

export default RegisterFormModal