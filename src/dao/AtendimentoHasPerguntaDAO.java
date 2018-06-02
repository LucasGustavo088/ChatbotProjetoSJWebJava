package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.AtendimentoHasPergunta;
import utils.Helper;
import model.AtendimentoHasPergunta;

public class AtendimentoHasPerguntaDAO {

	public int criar(AtendimentoHasPergunta atendimento_has_pergunta) {
		
		String sqlInsert = "INSERT INTO atendimento_has_pergunta(ID_PERGUNTA, ID_ATENDIMENTO, DATA_ATUALIZACAO, DATA_CRIACAO) VALUES (?, ?, '" + Helper.dataAtual() + "', '" + Helper.dataAtual() + "')";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, atendimento_has_pergunta.getId_pergunta());
			stm.setInt(2, atendimento_has_pergunta.getId_atendimento());
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
	
	public ArrayList<AtendimentoHasPergunta> carregarCadastro(String query) {
		AtendimentoHasPergunta tabela;
		ArrayList<AtendimentoHasPergunta> lista = new ArrayList<>();

		String sqlSelect = "SELECT * FROM atendimento_has_pergunta " + query;

		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {

			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					tabela = new AtendimentoHasPergunta();
					tabela.setId(rs.getInt("ID"));
					tabela.setId_pergunta(rs.getInt("ID_PERGUNTA"));
					tabela.setId_atendimento(rs.getInt("ID_ATENDIMENTO"));
					tabela.setData_criacao(rs.getDate("DATA_CRIACAO"));
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
