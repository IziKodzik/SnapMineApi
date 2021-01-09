package com.snapmine.SnapMineApi.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public class Client{

	private int id;
	private String password;
	private String name;
	private	String email;
	private String tokenID;


	public Client(@JsonProperty("id") int id,
				  @JsonProperty("password") String password,
				  @JsonProperty("name") String name,
				  @JsonProperty("email") String email) {
		this.id = id;
		this.password = password;
		this.name = name;
		this.email = email;
	}

	public Client(int id, String name, String password) {
		this.id = id;
		this.password = password;
		this.name = name;
	}

	public Client() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTokenID() {
		return tokenID;
	}

	public void setTokenID(String tokenID) {
		this.tokenID = tokenID;
	}

	@Override
	public String toString() {
		return "Client{" +
				"id=" + id +
				", password='" + password + '\'' +
				", name='" + name + '\'' +
				", email='" + email + '\'' +
				'}';
	}

	public static SQLMapper<Client> getMapper(){
		return resultSet ->
			new Client(resultSet.getInt("id"), resultSet.getString("name"),
					resultSet.getString("password"), resultSet.getString("email"));

	}

}
