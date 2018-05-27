package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import model.AtendimentoHasResposta;

public class AtendimentoHasRespostaDAO {

	public int criar(AtendimentoHasResposta atendimento_has_perguta) {
		
		String sqlInsert = "INSERT INTO atendimento_has_perguta(ID_PERGUNTA, ID_ATENDIMENTO, DATA_ATUALIZACAO, DATA_CRIACAO) VALUES (?, ?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, atendimento_has_perguta.getId_resposta());
			stm.setInt(2, atendimento_has_perguta.getId_atendimento());
			stm.setDate(3, new java.sql.Date( atendimento_has_perguta.getData_atualizacao().getTime() ));
			stm.setDate(4, new java.sql.Date( atendimento_has_perguta.getData_criacao().getTime() ));
			stm.execute();
			
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					atendimento_has_perguta.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atendimento_has_perguta.getId();
	}
}
