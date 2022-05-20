import "./App.css";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { Papers } from "./pages/Papers";
import { SelectedPaper } from "./pages/SelectedPaper";

function App() {
  return (
    <BrowserRouter>
      <Routes>
      <Route path="/" element={ <SelectedPaper/>}/>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
