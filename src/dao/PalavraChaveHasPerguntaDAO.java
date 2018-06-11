package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.ConnectionFactory;
import model.PalavraChaveHasPergunta;
import model.PalavraChaveHasResposta;

public class PalavraChaveHasPerguntaDAO {
	
	public ArrayList<PalavraChaveHasPergunta> carregarCadastro(String query) {
		PalavraChaveHasPergunta tabela;
		ArrayList<PalavraChaveHasPergunta> lista = new ArrayList<>();

		String sqlSelect = "SELECT * FROM palavra_chave_has_pergunta " + query;

		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {

			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					tabela = new PalavraChaveHasPergunta();
					tabela.setId(rs.getInt("ID"));
					tabela.setId_pergunta(rs.getInt("ID_PERGUNTA"));
					tabela.setId_palavra_chave(rs.getInt("ID_PALAVRA_CHAVE"));
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
	
	public int criar(PalavraChaveHasPergunta palavraChaveHasPergunta) {

		String sqlInsert = "INSERT INTO palavra_chave_has_pergunta(ID_PERGUNTA, ID_PALAVRA_CHAVE) VALUES (?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, palavraChaveHasPergunta.getId_pergunta());
			stm.setInt(2, palavraChaveHasPergunta.getId_palavra_chave());

			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					palavraChaveHasPergunta.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return palavraChaveHasPergunta.getId();
	}
}
