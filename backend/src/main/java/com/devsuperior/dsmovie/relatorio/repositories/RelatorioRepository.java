package com.devsuperior.dsmovie.relatorio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.devsuperior.dsmovie.entities.Movie;

@Repository
public interface RelatorioRepository extends JpaRepository<Movie, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM TB_MOVIE limit :qtd")
	public List<Movie> limitFilmes(Integer qtd);
	
	@Query(nativeQuery = true, value = "SELECT * FROM tb_movie "
			+ "WHERE SCORE > :pontuacao ")
	public List<Movie> pontuacaoFilmes(Integer pontuacao);
}
