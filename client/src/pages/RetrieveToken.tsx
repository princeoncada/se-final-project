import { useLocation } from 'react-router-dom';
import Cookies from 'js-cookie';
function setTokenAsCookie(token) {
    // Set the token as a cookie with a specified name (e.g., 'myTokenCookie')
    Cookies.set('JWT', token, { expires: 7 }); // 'expires' is optional and defines the cookie's expiration time in days
}

function RetrieveToken() {
    const location = useLocation();
    const token = new URLSearchParams(location.search).get('token');
    setTokenAsCookie(token)
    setTimeout(() => {
        window.location.href = "/";
    }, 2000);
}

export default RetrieveToken;