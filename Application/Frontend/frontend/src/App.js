import './App.css';
import {BrowserRouter, Route, Routes} from "react-router-dom"
import Login from './Pages/Login/Login';
import Home from './Pages/Home/Home';
import Layout from './Pages/Layout/Layout';

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="login" element={<Login />} />
        </Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
