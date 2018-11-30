package com.ebix.crud.repository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import io.spring.guides.gs_producing_web_service.Person;


@Component
public class PersonRepository {
	private static final Map<String, Person> persons = new HashMap<String, Person>();
	
	private final JdbcTemplate jdbcTemplate;

    
	@Autowired
    public PersonRepository(JdbcTemplate jdbcTemplate) {
	  this.jdbcTemplate = jdbcTemplate;

	  jdbcTemplate.execute("DROP TABLE person IF EXISTS");
	  jdbcTemplate.execute("CREATE TABLE person(" +
			  "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255), age INT)");
    }
    
	/**
	 * Método responsável por adicionar os dados de uma pessoa.
	 * @param person
	 */
    public void addPerson(Person person) {
	   String sql = "INSERT INTO person (first_name, last_name, age) values (?, ?, ?)";
	   jdbcTemplate.update(sql, person.getFirstName(), person.getLastName(), person.getAge());
	}
    
    /**
     * Método responsável por retornar os ddos da pessoa com base no id.
     * @param id
     * @return
     */
    public Person getPersonById(int id) {
    	String sql = "SELECT id, first_name, last_name, age FROM person WHERE id = ?";
    	RowMapper<Person> rowMapper = new BeanPropertyRowMapper<Person>(Person.class);	
    	Person article = jdbcTemplate.queryForObject(sql, rowMapper, id);
    	return article;
    }
    
    /**
     * Método responsável por atualizar os dados da pessoa.
     * @param person
     */
    public void updatePerson(Person person) {
        String sql = "UPDATE person SET first_name=?, last_name=?, age=? WHERE id=?";
        jdbcTemplate.update(sql, person.getFirstName(), person.getLastName(), person.getAge(), person.getId());
    }
    
    /**
     * Método responsável por deletar os dados da pessoa com base no id.
     * @param id
     */
    public void deletePerson(int id) {
    	String sql = "DELETE FROM person WHERE id=?";
    	jdbcTemplate.update(sql, id);
    } 

}
