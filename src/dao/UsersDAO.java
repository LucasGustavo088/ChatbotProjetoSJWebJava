package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import model.Atendimento;
import model.Users;
import utils.*;

public class UsersDAO extends Dao{
	public ArrayList<Users> carregarCadastro(String query) {
		Users palavra_chave;
		ArrayList<Users> lista = new ArrayList<>();

		String sqlSelect = "SELECT * FROM users " + query;


		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {

			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					palavra_chave = new Users();
					palavra_chave.setId(rs.getInt("id"));
					palavra_chave.setEmail(rs.getString("email"));
					palavra_chave.setPassword(rs.getString("password"));
					lista.add(palavra_chave);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}
}
