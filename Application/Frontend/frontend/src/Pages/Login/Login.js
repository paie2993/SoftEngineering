import { Box, Typography, Avatar, Button, Container } from "@mui/material";
import LoginIcon from "@mui/icons-material/Login";
import { useState } from "react";
import { isUserLoggedIn } from "../../API/userService";
import { Navigate } from "react-router-dom";
import Header from "./Header";
import ErrorBanner from "./ErrorBanner";
import LoginForm from "./LoginForm";
import RegisterFormModal from "./RegisterFormModal";


// Login page - where the user can log in or sign up for a new account
function Login() {
    // State containing an error message
    let [errormsg, setErrormsg] = useState('');
    // State indicating wether the error alert is collapsed or not
    let [errorOpen, setErrorOpen] = useState(false);
    
    
    // State indicating wether the register modal form is open
    let [registerModalOpen, setRegisterModalOpen] = useState(false);

    if (isUserLoggedIn()) {
        return <Navigate to="/" replace />;
    }

    
    

    return (
        <Box>
            <Header />

            <ErrorBanner errorOpen={errorOpen} setErrorOpen={setErrorOpen} errorMsg={errormsg}/>


            <Container component="main" maxWidth="xs">

                <Box
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                        alignItems: 'center',
                    }}
                >
                    <Avatar>
                        <LoginIcon />
                    </Avatar>

                    <Typography component="h1" variant="h5" marginTop="10px">
                        Sign in
                    </Typography>

                    <LoginForm setErrorOpen={setErrorOpen} setErrormsg={setErrormsg} />
                    

                    <Button
                            fullWidth
                            variant="contained"
                            sx={{ mt: 3, mb: 2 }}
                            onClick={() => {
                                setRegisterModalOpen(true)
                            }}
                        >
                            Sign up
                    </Button>
                </Box>
            </Container>

            <RegisterFormModal open={registerModalOpen} setOpen={setRegisterModalOpen} />
        </Box>
    )
}


export default Login;