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
import CadastroUsuarios from "components/CadastroUsuarios";

function App() {
  return (
    <BrowserRouter>
      <Navbar />
      <Routes>
        <Route path="/" element={<Listing />} />
        <Route path="/form">
          <Route path=":movieId" element={<Form />} />
        </Route>
        <Route path="/relatorios/usuarios" element={<RelatoriosUsuarios/>} />
        <Route path="/relatorios/filmes" element={<RelatoriosFilmes />} />
        <Route path="/cadastro/usuarios" element={<CadastroUsuarios/>} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;