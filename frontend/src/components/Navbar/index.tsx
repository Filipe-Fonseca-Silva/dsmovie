import { ReactComponent as GithubIcon } from 'assets/img/github.svg';
import { Dropdown, DropdownButton } from 'react-bootstrap';
import './style.css';

function Navbar() {
    let drop = document.getElementsByClassName('dropdown-toggle') as HTMLCollectionOf<any>;
    setTimeout(() =>{
        if(drop.length > 0){
    
            drop[0].classList.remove('btn-primary');
            drop[0].classList.add('dropDown-navbar');
        }
    }, 200);

    return (
        <header>
            <nav className="container">
                <div className="dsmovie-nav-content">
                    <h1>
                        <a href='/'>DSMovie</a>
                    </h1>
                    <div style={{ display: "flex" }}>

                        <DropdownButton id="dropdown-basic-button" title="Dropdown button" style={{marginRight:"20px"}}>
                            <Dropdown.Item href="/relatorios/usuarios">Relatórios de Usuários</Dropdown.Item>
                            <Dropdown.Item href="/relatorios/filmes">Relatórios de Filmes</Dropdown.Item>
                            <Dropdown.Item href="cadastro/usuarios">Cadastro de Usuários</Dropdown.Item>
                        </DropdownButton>

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