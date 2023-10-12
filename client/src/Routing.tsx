import {BrowserRouter, Routes, Route} from "react-router-dom";
import Login from "./pages/Login.tsx";
import React, {useState} from "react";
import Home from "./pages/Home.tsx";
import About from "./pages/About.tsx";
import Contact from "./pages/Contact.tsx";
import RetrieveToken from "./pages/RetrieveToken.tsx";
import Cookies from "js-cookie";


function Routing() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={
                    Cookies.get("JWT") == undefined ? <Login /> : <Home thisToken={Cookies.get("JWT")}/>
                }/>
                <Route path="/about" element={<About />}/>
                <Route path="/contact" element={<Contact />}/>
                <Route path="/retrieve-token" element={<RetrieveToken />}/>
            </Routes>
        </BrowserRouter>
    )
}

export default Routing;