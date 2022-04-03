import { AppBar, Toolbar, Typography, Button, Box } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { isUserLoggedIn, logUserOut } from "../../API/userService";
import { Navigate } from "react-router-dom";


// The home page of the website, which displays recent posts
function Home() {
    // Navigate hook used to navigate to redirect the user to other pages
    let navigate = useNavigate();

    
    if(!isUserLoggedIn()) {
        return <Navigate to="/login" replace />;
    }

    // Login button handler
    const loginButtonHandler = () => {
        let path = '/login';

        logUserOut();
        // Redirect user to the login page
        navigate(path)
    }

    return (
        <Box sx={{ flexGrow: 1 }}>
            <AppBar position="static">
                <Toolbar>
                    <Typography
                        variant="h6"
                        component="div"
                        sx={{ flexGrow: 1 }}
                    >
                        Home
                    </Typography>
                    <Button
                        color="inherit"
                        onClick={loginButtonHandler}
                    >
                        Logout
                    </Button>
                </Toolbar>
            </AppBar>
        </Box>
    )
}


export default Home;