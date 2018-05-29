package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import model.AtendimentoHasPergunta;

public class AtendimentoHasPerguntaDAO {

	public int criar(AtendimentoHasPergunta atendimento_has_pergunta) {
		
		String sqlInsert = "INSERT INTO atendimento_has_pergunta(ID_PERGUNTA, ID_ATENDIMENTO, DATA_ATUALIZACAO, DATA_CRIACAO) VALUES (?, ?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, atendimento_has_pergunta.getId_pergunta());
			stm.setInt(2, atendimento_has_pergunta.getId_atendimento());
			stm.setDate(3, new java.sql.Date( atendimento_has_pergunta.getData_atualizacao().getTime() ));
			stm.setDate(4, new java.sql.Date( atendimento_has_pergunta.getData_criacao().getTime() ));
			stm.execute();
			
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					
					atendimento_has_pergunta.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atendimento_has_pergunta.getId();
	}
}
