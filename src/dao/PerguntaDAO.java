package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import model.Pergunta;

public class PerguntaDAO {

	public int criar(Pergunta pergunta) {
		
		String sqlInsert = "INSERT INTO pergunta(DESCRICAO, ATIVO, DATA_ATUALIZACAO, DATA_CRIACAO) VALUES (?, ?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, pergunta.getDescricao());
			stm.setInt(2, pergunta.getAtivo());
			stm.setDate(3, new java.sql.Date( pergunta.getData_atualizacao().getTime() ));
			stm.setDate(4, new java.sql.Date( pergunta.getData_atualizacao().getTime() ));
			
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					pergunta.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pergunta.getId();
	}
}
