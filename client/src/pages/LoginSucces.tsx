import '../styles/LoginSuccess.css';
import {useEffect} from "react";

// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-ignore

function LoginSuccess(props) {
    useEffect(() => {
        fetch("http://localhost:8080/api/auth/user")
            .then(response => response.json())
            .then(data => {
                props.userState(data);
                props.loginState(true);
            })
            .catch(error => {
                console.log(error);
            });
    }, []);

    function handleDisplay() {
        console.log(props.user, props.isLoggedIn);
    }
    
    return (
        <main>
            <div className="login-success">
                <h2>Login Successful</h2>
                <p>Welcome to our app!</p>
                <button onClick={handleDisplay}>Display</button>
                <button className="logout-button">Logout</button>
            </div>
        </main>
    );
}

export default LoginSuccess;
