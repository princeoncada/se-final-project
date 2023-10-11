import {BrowserRouter, Routes, Route, Navigate} from "react-router-dom";
import Home from "./pages/Home.tsx";
import Login from "./pages/Login.tsx";
import {useState} from "react";
import LoginSuccess from "./pages/LoginSucces.tsx";

function Routing() {
    const [user, setUser] = useState(null);
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={
                    isLoggedIn ? <Home/> : <Navigate to="/login"/>
                }/>
                <Route path="/login" element={<Login />}/>
                {/*<Route path="/about" element={*/}
                {/*    user == null ? <Login/> : <About/>*/}
                {/*}/>*/}
                {/*<Route path="/contact" element={*/}
                {/*    user == null ? <Login/> : <Contact/>*/}
                {/*}/>*/}
                <Route path="/login-success" element={<LoginSuccess user={user} isLoggedIn={isLoggedIn} userState={setUser} loginState={setIsLoggedIn}/>}/>
            </Routes>
        </BrowserRouter>
    )
}

export default Routing;