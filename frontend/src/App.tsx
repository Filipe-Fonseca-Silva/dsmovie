import {
  BrowserRouter,
  Routes,
  Route
} from "react-router-dom";
import Listing from 'pages/Listing';
import Form from 'pages/Form';
import Navbar from "components/Navbar";
import RelatoriosUsuarios from "components/Relatorios/usuarios";
import RelatoriosFilmes from "components/Relatorios/filmes";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Listing />} />
        <Route path="/form">
          <Route path=":movieId" element={<Form />} />
        </Route>
        <Route path="/relatorios/usuarios" element={<RelatoriosUsuarios/>}/>
        <Route path="/relatorios/filmes" element={<RelatoriosFilmes />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;