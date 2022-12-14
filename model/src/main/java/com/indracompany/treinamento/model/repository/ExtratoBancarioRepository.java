package com.indracompany.treinamento.model.repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.indracompany.treinamento.model.entity.ExtratoBancario;

public interface ExtratoBancarioRepository extends GenericCrudRepository<ExtratoBancario, Long>{
	
	
	public List<ExtratoBancario> findByContaNumero(String number);
	
	// procurar por data
	@Query("select e from ExtratoBancario e where e.data = :data")
	public List<ExtratoBancario> findByDate(@Param("data") LocalDate data);
	
	//procurar por data inicial e final, numero e agencia
	@Query("select e from ExtratoBancario e "
			+ "inner join ContaBancaria c on e.conta = c.id "
			+ "where e.data between :dataInicio and :dataFim and c.agencia = :agencia and c.numero = :nrConta" )
	public List<ExtratoBancario> findByExtratoPorFiltro(LocalDate dataInicio, LocalDate dataFim, String agencia, String nrConta);

}
