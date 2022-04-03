import { Collapse, Alert } from "@mui/material";

function ErrorBanner(props) {
    return (
    <Collapse in={props.errorOpen}>
        <Alert onClose={() => { props.setErrorOpen(false) }} severity="error">{props.errorMsg}</Alert>
    </Collapse>
    )
}

export default ErrorBanner;