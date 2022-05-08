import "./App.css";
import { Papers } from "./papers/Papers";
import { BrowserRouter, Route, Routes } from "react-router-dom";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Papers></Papers>}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
