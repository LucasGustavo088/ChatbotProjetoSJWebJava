package model;

import java.util.*;

public class Users {
	private int id;
	private String name;
	private String email;
	private String password;
	private Date data_criacao;
	private Date data_atualiacao;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getData_criacao() {
		return data_criacao;
	}
	public void setData_criacao(Date data_criacao) {
		this.data_criacao = data_criacao;
	}
	public Date getData_atualiacao() {
		return data_atualiacao;
	}
	public void setData_atualiacao(Date data_atualiacao) {
		this.data_atualiacao = data_atualiacao;
	}

	
}
