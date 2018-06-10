package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.PalavraChaveHasResposta;

public class PalavraChaveHasRespostaDAO {
	
	
	public int criar(PalavraChaveHasResposta palavraChaveHasResposta) {

		String sqlInsert = "INSERT INTO palavra_chave_has_resposta(ID_PALAVRA_CHAVE, ID_RESPOSTA, PONT_RESPOSTA) VALUES (?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, palavraChaveHasResposta.getId_palavra_chave());
			stm.setInt(2, palavraChaveHasResposta.getId_resposta());
			stm.setInt(3, palavraChaveHasResposta.getPont_respsota());

			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					palavraChaveHasResposta.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return palavraChaveHasResposta.getId();
	}

}
