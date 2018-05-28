package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;

public class PalavraChaveHasPerguntaDAO {
	public ArrayList<PalavraChaveHasPergunta> carregarCadastro(String query) {
		PalavraChaveHasPergunta tabela;
		ArrayList<PalavraChaveHasPergunta> lista = new ArrayList<>();

		String sqlSelect = "SELECT * FROM tabela " + query;

		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {

			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					tabela = new PalavraChaveHasPergunta();
					tabela.setId(rs.getInt("ID"));
					tabela.setId(rs.getInt("ID_PERGUNTA"));
					tabela.setId(rs.getInt("ID_PALAVRA_CHAVE"));
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
