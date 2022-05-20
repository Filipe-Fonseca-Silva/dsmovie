import './style.css';

const CadastroUsuarios = () => {

    return (
        <>
            <div className='container-cadastro'>
                <div className="container-tela-login">
                    <h3>Crie sua conta</h3>
                    <div className="container-inputs">
                        <form>
                            <div className='row' style={{ marginTop: "50px" }}>
                                <div className='col-md-8 offset-md-2'>
                                    <input className='form-control' type="text" placeholder="Nome" /><br></br>
                                </div>
                                <div className='col-md-8 offset-md-2'>
                                    <input className='form-control' type="text" placeholder="CPF" /><br></br>
                                </div>
                                <div className='col-md-8 offset-md-2'>
                                    <input className='form-control' type="text" placeholder="Email" /><br></br>
                                </div>
                                <div className='col-md-8 offset-md-2'>
                                    <button className='btn btn-primary' type='submit'>Cadastrar</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </>
    );
}

export default CadastroUsuarios;