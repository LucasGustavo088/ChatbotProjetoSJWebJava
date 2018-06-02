package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.AtendimentoHasResposta;
import utils.Helper;
import model.AtendimentoHasResposta;

public class AtendimentoHasRespostaDAO {

	public int criar(AtendimentoHasResposta atendimento_has_resposta) {
		
		String sqlInsert = "INSERT INTO atendimento_has_resposta(ID_RESPOSTA, ID_ATENDIMENTO, DATA_ATUALIZACAO, DATA_CRIACAO) VALUES (?, ?, '" + Helper.dataAtual() + "', '" + Helper.dataAtual() + "')";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, atendimento_has_resposta.getId_resposta());
			stm.setInt(2, atendimento_has_resposta.getId_atendimento());
			stm.execute();
			
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					
					atendimento_has_resposta.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return atendimento_has_resposta.getId();
	}
	
	public ArrayList<AtendimentoHasResposta> carregarCadastro(String query) {
		AtendimentoHasResposta tabela;
		ArrayList<AtendimentoHasResposta> lista = new ArrayList<>();

		String sqlSelect = "SELECT * FROM atendimento_has_resposta " + query;

		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {

			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					tabela = new AtendimentoHasResposta();
					tabela.setId(rs.getInt("ID"));
					tabela.setId_resposta(rs.getInt("ID_RESPOSTA"));
					tabela.setId_atendimento(rs.getInt("ID_ATENDIMENTO"));
					tabela.setData_criacao(rs.getTimestamp("DATA_CRIACAO"));
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
