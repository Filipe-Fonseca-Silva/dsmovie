import { ReactComponent as GithubIcon } from 'assets/img/github.svg';
import './style.css';

function Navbar() {

    return (
        <header>
            <nav className="container">
                <div className="dsmovie-nav-content">
                    <h1>DSMovie</h1>
                    <div style={{display:"flex"}}>
                        <a href="/relatorios/usuarios" style={{marginRight:"20px"}}>Relatório de Usuários</a>
                        <a href="/relatorios/filmes" style={{marginRight:"20px"}}>Relatório de Filmes</a>
                        <a href="https://github.com/devsuperior">
                            <div className="dsmovie-contact-container">
                                <GithubIcon />
                                <p className="dsmovie-contact-link">/devsuperior</p>
                            </div>
                        </a>
                    </div>
                </div>
            </nav>
        </header>
    );
}

export default Navbar;