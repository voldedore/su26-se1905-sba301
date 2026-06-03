import './App.css'
import {Header} from "./components/Composition.jsx";
import {Routes, Route} from 'react-router-dom';
import {HomePage} from "./components/HomePage.jsx";
import {ArtistList} from "./components/artists/ArtistList.jsx";
import {ArtistForm} from "./components/artists/ArtistForm.jsx";

function App() {
    return (
        <Header>
            <Routes>
                <Route path={'/'} element={<HomePage />}/>
                <Route path={'/artists'} element={<ArtistList />}/>
                <Route path={'/artists/new'} element={<ArtistForm />}/>
            </Routes>
        </Header>
    );
}

export default App
