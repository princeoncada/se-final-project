import '../styles/Login.css';
function Login() {
    function handleGoogleLogin() {
        window.location.href = "http://localhost:8080/oauth2/authorization/google"
    }

    return (
        <main>
            <h1>Login</h1>
            <button className="google-button" onClick={handleGoogleLogin}>
                Login with Google
            </button>
        </main>
    )
}

export default Login;