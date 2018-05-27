package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import model.Resposta;

public class RespostaDAO {

	public int criar(Resposta resposta) {
		
		String sqlInsert = "INSERT INTO resposta(DESCRICAO, ATIVO, DATA_ATUALIZACAO, DATA_CRIACAO) VALUES (?, ?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, resposta.getDescricao());
			stm.setInt(2, resposta.getAtivo());
			stm.setDate(3, new java.sql.Date( resposta.getData_atualizacao().getTime() ));
			stm.setDate(4, new java.sql.Date( resposta.getData_atualizacao().getTime() ));
			
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					resposta.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resposta.getId();
	}
}
