package service;

import java.util.ArrayList;

import dao.UsersDAO;
import model.Users;

public class UsersService {
	
	public UsersDAO dao = new UsersDAO();
	
	public boolean logarUsuario(String email, String password) {
		ArrayList<Users> users = dao.carregarCadastro("WHERE email ='" + email + "' && password = '" + password + "'");
		
		if(users.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
}
