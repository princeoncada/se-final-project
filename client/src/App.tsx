import Routing from "./Routing.tsx";
import {GoogleOAuthProvider} from "@react-oauth/google";

function App() {
    return (
        <GoogleOAuthProvider clientId="422166082454-aobdq209mqk5dviq247o0pmahmkgbu0d.apps.googleusercontent.com">
            <div className="body">
                <Routing/>
            </div>
        </GoogleOAuthProvider>
    )
}

export default App
