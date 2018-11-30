package com.ebix.crud.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import io.spring.guides.gs_producing_web_service.Person;

public class PersonRowMapper implements RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet row, int rowNum) throws SQLException {
		Person person = new Person();
		person.setFirstName((row.getString("first_name")));
		person.setLastName((row.getString("last_name")));
		person.setAge((row.getInt("age")));
		return person;
	}

}
