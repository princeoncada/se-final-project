import Header from "../components/Header.tsx";
import Footer from "../components/Footer.tsx";
import Cookies from "js-cookie";

function Home(props) {

    function viewToken() {
        console.log(props.thisToken)
    }

    console.log(Cookies.get("JWT"))

    return (
        <>
            <Header/>
            <main>
                <h1>Home</h1>
                <p>This is the home page</p>
                <button onClick={viewToken}>View Token</button>
            </main>
            <Footer/>
        </>
    )
}

export default Home;