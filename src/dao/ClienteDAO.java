package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.Cliente;
import model.Cliente;

public class ClienteDAO {

	public int criar(Cliente cliente) {
		String sqlInsert = "INSERT INTO cliente(NOME_CLIENTE, EMAIL_CLIENTE, ATIVO) VALUES (?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, cliente.getNome_cliente());
			stm.setString(2, cliente.getEmail_cliente());
			stm.setInt(3, cliente.getAtivo());

			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					cliente.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente.getId();
	}
	
	public ArrayList<Cliente> carregarCadastro(String query) {
		Cliente tabela;
		ArrayList<Cliente> lista = new ArrayList<>();

		String sqlSelect = "SELECT * FROM cliente " + query;

		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {

			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					tabela = new Cliente();
					tabela.setId(rs.getInt("ID"));
					tabela.setNome_cliente(rs.getString("NOME_CLIENTE"));
					tabela.setEmail_cliente(rs.getString("EMAIL_CLIENTE"));
					tabela.setAtivo(rs.getInt("ATIVO"));
					lista.add(tabela);
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
