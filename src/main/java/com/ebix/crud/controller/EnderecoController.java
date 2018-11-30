package com.ebix.crud.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

import com.ebix.crud.entity.Endereco;

import br.com.correios.bsb.sigep.master.bean.cliente.AtendeClienteService;
import br.com.correios.bsb.sigep.master.bean.cliente.EnderecoERP;

@Controller
public class EnderecoController {
	final static Logger logger = Logger.getLogger(EnderecoController.class);
	
	/**
	 * Método responsável por retornar o endereço de acordo com o CEP. Utiliza o serviço dos Correios como fonte da informação.
	 * @param cep
	 * @return
	 */
	public Endereco getEndereco(String cep) {
		Endereco end = new Endereco();
		AtendeClienteService atendeClient = new AtendeClienteService();
 		try {
 			EnderecoERP endERP = atendeClient.getAtendeClientePort().consultaCEP(cep);
 			end.setBairro(endERP.getBairro());
 			end.setCep(endERP.getCep());
 			end.setCidade(endERP.getCidade());
 			end.setComplemento2(endERP.getComplemento2());
 			end.setEnd(endERP.getEnd());
 			end.setUf(endERP.getUf());
 		} catch (Exception e) {
 			logger.error("Error no método getEndereco: " + e);
 		}
		return end;
 	}

}
