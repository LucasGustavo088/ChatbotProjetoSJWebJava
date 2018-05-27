package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import model.Atendimento;

public class AtendimentoDAO {

	public int criar(Atendimento atendimento) {
		
		String sqlInsert = "INSERT INTO atendimento(ATIVO, STATUS, ID_CLIENTE, DATA_ATUALIZACAO, DATA_CRIACAO, QTD_TENTATIVA) VALUES (?, ?, ?, ?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, atendimento.getAtivo());
			stm.setString(2, atendimento.getStatus());
			stm.setInt(3, atendimento.getId_cliente());
			stm.setDate(4, new java.sql.Date( atendimento.getData_atualizacao().getTime() ));
			stm.setDate(5, new java.sql.Date( atendimento.getData_criacao().getTime() ));
			stm.setInt(6, atendimento.getQtd_tentativa());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					atendimento.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atendimento.getId();
	}
}
