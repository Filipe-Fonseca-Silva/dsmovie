import { useState } from "react";

type FormData = {
    nomeRelatorio: string;
    empresa: number;
    showData: boolean;
    limit: number;
    data: string;
    nomeEmpresa: string;
    cnpj: string;
    email: string;
    titulo: string;
    autor: string;
}

const RelatoriosUsuarios = () => {

    const [formData, setFormData] = useState<FormData>({showData: true} as FormData);

    const handleChange = (event: any) => {

        const name = event.target.name;
        const value = event.target.value;

        setFormData({...formData, [name]:value});
    }

    const handleSubmit = (event: any) => {

        event.preventDefault();

        let url = `http://localhost:8080/relatorio/users?nomeRelatorio=${formData.nomeRelatorio}&nomeEmpresa=${formData.nomeEmpresa}&cnpj=${formData.cnpj}&email=${formData.email}&empresa=${formData.empresa}&titulo=${formData.titulo}&autor=${formData.autor}&data=${formData.data}&limit=${formData.limit}`;

        window.open(url, "_blank");
    }

    return (

        <>
        <div className="container" style={{marginTop: "50px"}}>
            <form onSubmit={handleSubmit}>
                <div className="row">
                    <div className="col-md-6">
                        <label>Nome do relatório</label>
                        <input type="text" className="form-control" name="nomeRelatorio" value={formData && formData.nomeRelatorio} onChange={handleChange}/>
                    </div>
                    <div className="col-md-6">
                        <label>Nome da empresa</label>
                        <input type="text" className="form-control" name="nomeEmpresa" value={formData && formData.nomeEmpresa} onChange={handleChange}/>
                    </div>
                    <div className="col-md-3">
                        <label>CNPJ</label>
                        <input type="text" className="form-control" name="cnpj" value={formData && formData.cnpj} onChange={handleChange}/>
                    </div>
                    <div className="col-md-3">
                        <label>Email</label>
                        <input type="text" className="form-control" name="email" value={formData && formData.email} onChange={handleChange}/>
                    </div>
                    <div className="col-md-3">
                        <label>Empresa</label>
                        <input type="text" className="form-control" name="empresa" value={formData && formData.empresa} onChange={handleChange}/>
                    </div>
                    <div className="col-md-3">
                        <label>Título</label>
                        <input type="text" className="form-control" name="titulo" value={formData && formData.titulo} onChange={handleChange}/>
                    </div>
                    <div className="col-md-3">
                        <label>Autor</label>
                        <input type="text" className="form-control" name="autor" value={formData && formData.autor} onChange={handleChange}/>
                    </div>
                    <div className="col-md-3">
                        <label>Data</label>
                        <input type="text" className="form-control" name="data" value={formData && formData.data} onChange={handleChange}/>
                    </div>
                    <div className="col-md-3">
                        <label>Limite</label>
                        <input type="text" className="form-control" name="limit" value={formData && formData.limit} onChange={handleChange}/>
                    </div>
                    <div style={{marginTop:"20px"}}>
                        <button className="btn btn-outline-primary" type="submit">Abrir Relatório</button>
                    </div>
                </div>
            </form>
        </div>
        </>
    );
}

export default RelatoriosUsuarios;