import Header from "../components/Header.tsx";
import Footer from "../components/Footer.tsx";

function Home() {
    return (
        <>
            <Header/>
            <main>
                <h1>Home</h1>
                <p>This is the home page</p>
            </main>
            <Footer/>
        </>
    )
}

export default Home;