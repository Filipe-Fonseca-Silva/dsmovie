import axios from 'axios';
import { url } from 'inspector';
import { useState } from 'react';
import { BASE_URL } from 'utils/requests';
import './style.css';

type Usuario = {
    email: string,
    name: string,
    cpf: string
}

const CadastroUsuarios = () => {

    const [mensagem, setMensagem] = useState("")

    const [formData, setFormData] = useState<Usuario>({email:"", name:"", cpf:""} as Usuario)

    const handleChange = (event: any) => {

        const name = event.target.name;
        const value = event.target.value;

        setFormData({...formData, [name]:value});
    }

    const handleSubmit = (event: any) => {

        event.preventDefault();
debugger
        const userData = JSON.stringify(formData);

        const headers = {
            "Content-Type": "application/json"
        }

        axios({
            method:"post",
            url: `${BASE_URL}/users`,
            data: userData,
            headers: headers
        }).then((response)=>{
            setMensagem('Cadastro com sucesso')

        }).catch((erro)=>{
            setMensagem('Cadastro com erro')
        })
    }

    return (
        <>

        <div style={{display:'flex', justifyContent:'center', marginTop:'20px'}}>
        <h3>{mensagem}</h3>
        </div>
            <div className='container-cadastro'>
                <div className="container-tela-login">
                    <h3>Crie sua conta</h3>
                    <div className="container-inputs">
                        <form onSubmit={handleSubmit} >
                            <div className='row' style={{ marginTop:"50px"}}>
                                <div className='col-md-8 offset-md-2'>
                                    <input className='form-control' type="text" placeholder="Nome" name="name" value={formData && formData.name} onChange={handleChange}/><br></br>
                                </div>
                                <div className='col-md-8 offset-md-2'>
                                    <input className='form-control' type="text" placeholder="CPF" name="cpf" value={formData && formData.cpf} onChange={handleChange}/><br></br>
                                </div>
                                <div className='col-md-8 offset-md-2'>
                                    <input className='form-control' type="email" placeholder="Email" name="email" value={formData && formData.email} onChange={handleChange}/><br></br>
                                </div>
                                <div className='col-md-8 offset-md-2'>
                                    <button className='btn btn-primary' type='submit' style={{backgroundColor:"#4D41C0", borderColor:"#fff"}}>Cadastrar</button>
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