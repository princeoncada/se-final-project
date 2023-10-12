import {BrowserRouter, Routes, Route} from "react-router-dom";
import Login from "./pages/Login.tsx";
import Home from "./pages/Home.tsx";
import About from "./pages/About.tsx";
import Contact from "./pages/Contact.tsx";
import RetrieveToken from "./redirect/RetrieveToken.tsx";
import Cookies from "js-cookie";
import Logout from "./redirect/Logout.tsx";
import {useState} from "react";


function Routing() {
    const [authenticated, setAuthenticated] = useState(Boolean(Cookies.get("JWT")));

    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={
                    authenticated ? <Home /> : <Login />
                }/>
                <Route path="/about" element={<About />}/>
                <Route path="/contact" element={<Contact />}/>
                <Route path="/retrieve-token" element={<RetrieveToken setAuthenticated={setAuthenticated}/>}/>
                <Route path="/logout" element={<Logout setAuthenticated={setAuthenticated}/>}/>
            </Routes>
        </BrowserRouter>
    )
}

export default Routing;