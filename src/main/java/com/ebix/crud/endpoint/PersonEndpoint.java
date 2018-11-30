package com.ebix.crud.endpoint;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.ebix.crud.repository.PersonRepository;

import br.com.correios.bsb.sigep.master.bean.cliente.AtendeClienteService;
import br.com.correios.bsb.sigep.master.bean.cliente.ConsultaCEP;
import br.com.correios.bsb.sigep.master.bean.cliente.ConsultaCEPResponse;
import br.com.correios.bsb.sigep.master.bean.cliente.EnderecoERP;
import io.spring.guides.gs_producing_web_service.AddPersonRequest;
import io.spring.guides.gs_producing_web_service.AddPersonResponse;
import io.spring.guides.gs_producing_web_service.DeletePersonRequest;
import io.spring.guides.gs_producing_web_service.DeletePersonResponse;
import io.spring.guides.gs_producing_web_service.GetPersonRequest;
import io.spring.guides.gs_producing_web_service.GetPersonResponse;
import io.spring.guides.gs_producing_web_service.Person;
import io.spring.guides.gs_producing_web_service.UpdatePersonRequest;
import io.spring.guides.gs_producing_web_service.UpdatePersonResponse;

@Endpoint
public class PersonEndpoint {
	final static Logger logger = Logger.getLogger(PersonEndpoint.class);
	
	private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";
	
	private PersonRepository personRepository;
	
	@Autowired
	public PersonEndpoint(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}
	
	/**
	 * Método responsável por retornar o xml com os dados da pessoa via chamada SOAP.
	 * @param request
	 * @return
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPersonRequest")
	@ResponsePayload
	public GetPersonResponse getPerson(@RequestPayload GetPersonRequest request) {
		GetPersonResponse response = new GetPersonResponse();
		try {
			response.setPerson(personRepository.getPersonById(request.getId()));
		} catch (Exception e) {
			logger.error("Error no método getPerson: " + e );
		}
		
		return response;

	}
	
	/**
	 * Método responsável por adicionar os dados de uma pessoa via chamada SOAP.
	 * @param request
	 * @return
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "addPersonRequest")
	@ResponsePayload
	public AddPersonResponse addPerson(@RequestPayload AddPersonRequest request) {
		AddPersonResponse response = new AddPersonResponse();
		Person person = new Person();
		person.setAge(request.getAge());
		person.setFirstName(request.getFirstName());
		person.setLastName(request.getLastName());
		
		try {
			personRepository.addPerson(person);
			response.setResult("Adicionado!");
		} catch (Exception e) {
			logger.error("Error no método addPerson: " + e );
			response.setResult("Houve um problema! Entre em contato com o administrador");
		}
		
		return response;
	}
	
	/**
	 * Método responsável por atualizar os dados de uma pessoa via chamada SOAP.
	 * @param request
	 * @return
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updatePersonRequest")
	@ResponsePayload
	public UpdatePersonResponse updatePerson(@RequestPayload UpdatePersonRequest request) {
		UpdatePersonResponse response = new UpdatePersonResponse();
		Person person = new Person();
		person.setId(request.getId());
		person.setAge(request.getAge());
		person.setFirstName(request.getFirstName());
		person.setLastName(request.getLastName());

		try {
			personRepository.updatePerson(person);
			response.setResult("Atualizado!");
		} catch (Exception e) {
			logger.error("Error no método updatePerson: " + e );
			response.setResult("Houve um problema! Entre em contato com o administrador");
		}
		
		return response;
	}
	
	
	/**
	 * método responsável por deletar os dados de uma pessoa via chamada SOAP.
	 * @param request
	 * @return
	 */
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePersonRequest")
	@ResponsePayload
	public DeletePersonResponse updatePerson(@RequestPayload DeletePersonRequest request) {
		DeletePersonResponse response = new DeletePersonResponse();
		try {
			personRepository.deletePerson(request.getId());
			response.setResult("Deletado!");
		} catch (Exception e) {
			logger.error("Error no método updatePerson: " + e );
			response.setResult("Houve um problema! Entre em contato com o administrador");
		}
		
		return response;
	}
	
	
	
	

}
