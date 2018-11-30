package com.ebix.crud.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ebix.crud.controller.EnderecoController;
import com.ebix.crud.entity.Endereco;

@RestController
@RequestMapping(value = "/api/endereco")
public class EnderecoResource {

	@Autowired
	EnderecoController enderecoController;
	
	/**
	 * Método responsável por retornar um endereço de acordo com os dados do webservice dos Correios via chamada Rest. 
	 * @param cep
	 * @return
	 */
	@RequestMapping(method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<Endereco> getEndereco(@RequestParam(value = "cep", required = false) String cep) {
		return ResponseEntity.ok().body(enderecoController.getEndereco(cep));
	}

}
