package com.ebix.crud;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.correios.bsb.sigep.master.bean.cliente.AtendeClienteService;
import br.com.correios.bsb.sigep.master.bean.cliente.EnderecoERP;
import br.com.correios.bsb.sigep.master.bean.cliente.SQLException_Exception;
import br.com.correios.bsb.sigep.master.bean.cliente.SigepClienteException;

public class EnderecoControllerTest {
	
	@Test
	public void testGetEndereco() throws SQLException_Exception, SigepClienteException {
		String bairro = "Campo Grande";
		String cepRes = "23080100";
		String cidade = "Rio de Janeiro";
		String complemento2 = "";
		String end = "Rua Alfredo de Morais";
		String uf = "RJ";
		
		AtendeClienteService atendeClient = new AtendeClienteService();
		EnderecoERP endERP = atendeClient.getAtendeClientePort().consultaCEP(cepRes);
		
		assertEquals(bairro, endERP.getBairro());
		assertEquals(cepRes, endERP.getCep());
		assertEquals(cidade, endERP.getCidade());
		assertEquals(complemento2, endERP.getComplemento2());
		assertEquals(end, endERP.getEnd());
		assertEquals(uf, endERP.getUf());
 	}

}
