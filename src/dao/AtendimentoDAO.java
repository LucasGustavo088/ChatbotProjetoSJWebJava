package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import model.Atendimento;
import utils.*;

public class AtendimentoDAO extends Dao{

	public int criar(Atendimento atendimento) {
		
		String sqlInsert = "INSERT INTO atendimento(ATIVO, STATUS, ID_CLIENTE, QTD_TENTATIVA, DATA_ATUALIZACAO, DATA_CRIACAO) VALUES (?, ?, ?, 0, '" + Helper.dataAtual() + "', '" + Helper.dataAtual() + "')";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, atendimento.getAtivo());
			stm.setString(2, atendimento.getStatus());
			stm.setInt(3, atendimento.getId_cliente());
			
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
	
	public void finalizar_atendimento(Atendimento atendimento) {
		String sqlUpdate = "UPDATE atendimento SET STATUS=?, DATA_FINALIZACAO=?  WHERE id=? ";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlUpdate);) {
			stm.setString(1, atendimento.getStatus());
			stm.setDate(2, new java.sql.Date(atendimento.getData_finalizacao().getTime()));
			stm.setInt(3, atendimento.getId());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
