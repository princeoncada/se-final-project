import '../styles/Login.css';

function Login() {
    const handleGoogleLogin = () => {
        // Redirect to the Spring Boot backend's Google OAuth login endpoint
        window.location.href = 'http://localhost:8080/oauth2/authorization/google'; // Replace with your actual backend URL
    };

    return (
        <main>
            <button className="google-button" onClick={handleGoogleLogin}>Login with Google</button>
        </main>
    );
}

export default Login;