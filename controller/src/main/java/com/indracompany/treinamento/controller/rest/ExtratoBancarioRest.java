package com.indracompany.treinamento.controller.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.indracompany.treinamento.model.dto.ExtratoBancarioDTO;
import com.indracompany.treinamento.model.entity.ExtratoBancario;
import com.indracompany.treinamento.model.service.ExtratoBancarioService;





@RestController
@RequestMapping("rest/extratoBancario")
public class ExtratoBancarioRest extends GenericCrudRest<ExtratoBancario, Long, ExtratoBancarioService>{

	@Autowired
	private ExtratoBancarioService extratoBancarioService;
	
	@GetMapping(value = "/buscarPorData/{dataInicial}/{dataFinal}/{agencia}/{nrConta}", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<ExtratoBancario>> buscarExtratoPorData(@PathVariable("dataInicial")String dataInicialString, 
																					@PathVariable("dataFinal") String dataFinalString, 
																					@PathVariable String agencia, 
																					@PathVariable String nrConta) throws Exception {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dataInicial =  LocalDate.parse(dataInicialString, formatter);
		LocalDate dataFinal = LocalDate.parse(dataFinalString, formatter);
		
		List<ExtratoBancario> lista = extratoBancarioService.buscarExtratoPorData(dataInicial, dataFinal, agencia, nrConta);
		if (lista == null || lista.isEmpty()) {
			return new ResponseEntity<>( HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(lista, HttpStatus.OK);
	}
}
