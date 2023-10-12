import { useNavigate } from "react-router-dom";
import Cookies from "js-cookie";
import { useEffect } from "react";

function Logout(props) {
    const navigate = useNavigate();
    Cookies.remove("JWT");

    useEffect(() => {
        props.setAuthenticated(false);
    }, [props]);

    useEffect(() => {
        const delayDuration = 2000;
        const timeoutId = setTimeout(() => {
            navigate("/");
        }, delayDuration);
        return () => clearTimeout(timeoutId);
    }, [navigate]);

    return (
        <main>
            <p>Logging out...</p>
        </main>
    )
}

export default Logout;