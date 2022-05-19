package com.devsuperior.dsmovie.relatorio.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsmovie.dto.MovieDTO;
import com.devsuperior.dsmovie.dto.UserDTO;
import com.devsuperior.dsmovie.relatorio.CreateRelatorio;
import com.devsuperior.dsmovie.relatorio.repositories.RelatorioRepository;
import com.devsuperior.dsmovie.repositories.UserRepository;

import net.sf.jasperreports.engine.JRException;

@RestController
@RequestMapping(value = "/relatorio")
public class RelatorioController {
	
	@Autowired
	private RelatorioRepository repository;
	
	@Autowired
	private CreateRelatorio relatorio;
	
	@Autowired
	private UserRepository userRepository;
	
	/// :: Configura os parâmetros do relatorio.
	Map<String, Object> parametros = new HashMap<>();

	/// :: Relatorios de movies
	@GetMapping("/movies")
	public Void relatorioContabilDiario(
			@RequestParam(name = "nomeRelatorio", defaultValue = "") String nomeRelatorio, 
			@RequestParam(name = "nomeEmpresa", defaultValue = "") String nomeEmpresa, 
			@RequestParam(name = "cnpj", defaultValue = "") String cnpj,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "empresa", defaultValue = "1") Long empresa,
			@RequestParam(name = "titulo", defaultValue = "") String titulo, 
			@RequestParam(name = "autor", defaultValue = "") String autor, 
			@RequestParam(name = "data", defaultValue = "") Date data, 
			@RequestParam(name = "showLogo", defaultValue = "true") Boolean showLogo,
			@RequestParam(name = "sendEmail", defaultValue = "true") Boolean sendEmail,
			@RequestParam(name = "showData", defaultValue = "true") Boolean showData,
			@RequestParam(name = "showGrupo", defaultValue = "false") Boolean showGrupo, 
			@RequestParam(name = "grupoPage", defaultValue = "false") Boolean grupoPage, 
			@RequestParam(name = "filmeId", defaultValue = "0") Long filmeId,
			@RequestParam(name = "pontuacao", defaultValue = "0") Integer pontuacao,
			@RequestParam(name = "limit", defaultValue = "0") Integer limit, HttpServletResponse response) throws IOException{
		
		/// :: Carregar lista com dados do relatorio.
		List<MovieDTO> list = null;
		
		if(filmeId == 0 && limit == 0 && pontuacao == 0) {
			list = repository.findAll().stream().map(x -> new MovieDTO(x)).collect(Collectors.toList());
		}
		else if(filmeId > 0 && limit == 0 && pontuacao == 0) {
			list = repository.findById(filmeId).stream().map(x -> new MovieDTO(x)).collect(Collectors.toList());
		}
		else if(filmeId == 0 && limit > 0 && pontuacao == 0) {
			list = repository.limitFilmes(limit).stream().map(x -> new MovieDTO(x)).collect(Collectors.toList());
		}
		else if(filmeId == 0 && limit == 0 && pontuacao > 0 && pontuacao <= 5) {
			list = repository.pontuacaoFilmes(pontuacao).stream().map(x -> new MovieDTO(x)).collect(Collectors.toList());
		}

		try {		
			
			/// :: Add os parâmetros do relatorio.
			parametros.put("titulo", titulo);
			parametros.put("nomeEmpresa", nomeEmpresa);
			parametros.put("cnpj", cnpj);
			parametros.put("email", email);
		    parametros.put("autor", autor);
		    parametros.put("data", data);
		    parametros.put("showData", showData);
			
			/// :: Pasta do relatorio atual.
			String folder = "movies/";
			
			String logo = list.get(0).getImage();
			
			/// :: Verificar tipo de criação do relatorio
		    if(showGrupo && grupoPage || !showLogo) {
		    	relatorio.gerarRelatorioEditado(list, response, parametros, nomeRelatorio, folder, grupoPage, showLogo, empresa, sendEmail);
		    }
		    else {
		    	relatorio.gerarRelatorioCompilado(list, response, parametros, nomeRelatorio, folder, empresa, sendEmail);		    	
		    }
			
		} catch (JRException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@GetMapping(value = "/users")
	public Void relatorioUser(
			@RequestParam(name = "nomeRelatorio", defaultValue = "") String nomeRelatorio, 
			@RequestParam(name = "nomeEmpresa", defaultValue = "") String nomeEmpresa, 
			@RequestParam(name = "cnpj", defaultValue = "") String cnpj,
			@RequestParam(name = "email", defaultValue = "") String email,
			@RequestParam(name = "empresa", defaultValue = "1") Long empresa,
			@RequestParam(name = "titulo", defaultValue = "") String titulo, 
			@RequestParam(name = "autor", defaultValue = "") String autor,
			@RequestParam(name = "showData", defaultValue = "true") Boolean showData,
			@RequestParam(name = "limit", defaultValue = "0") Integer limit,
			@RequestParam(name = "data", defaultValue = "") Date data, HttpServletResponse response) {
		
		List<UserDTO> list = null;
		
		if(limit == 0) {
			list = userRepository.findAll().stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		}
		else {
			list = userRepository.buscaLimite(limit).stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		}
		
		try {
			
			/// :: Add os parâmetros do relatorio.
			parametros.put("titulo", titulo);
			parametros.put("nomeEmpresa", nomeEmpresa);
			parametros.put("cnpj", cnpj);
			parametros.put("email", email);
		    parametros.put("autor", autor);
		    parametros.put("data", data);
		    parametros.put("showData", showData);
		    
		    /// :: Pasta do relatorio atual.
			String folder = "users/";
			
			/// :: Verificar tipo de criação do relatorio
		    relatorio.gerarRelatorioCompilado(list, response, parametros, nomeRelatorio, folder, empresa, false);		    	
			
		} catch (Exception e) {
			//
		}
		
		return null;
	}
}
