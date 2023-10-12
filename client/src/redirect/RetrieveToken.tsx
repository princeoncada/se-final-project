import { useNavigate, useLocation } from 'react-router-dom';
import Cookies from 'js-cookie';
import {useEffect} from "react";
function setTokenAsCookie(token) {
    Cookies.set('JWT', token, { expires: 7 });
}

function RetrieveToken(props) {
    const navigate = useNavigate();
    const location = useLocation();
    const token = new URLSearchParams(location.search).get('token');
    setTokenAsCookie(token)

    useEffect(() => {
        props.setAuthenticated(true);
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
            <p>Retrieving token...</p>
        </main>
    )
}

export default RetrieveToken;