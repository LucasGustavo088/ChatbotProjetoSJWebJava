package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.Atendimento;
import model.PerguntaHasResposta;
import model.Topico;
import utils.Helper;
import model.PerguntaHasResposta;

public class PerguntaHasRespostaDAO extends Dao {

public int criar(PerguntaHasResposta perguntaHasResposta) {
		
		String sqlInsert = "INSERT INTO pergunta_has_resposta(ID_PERGUNTA, ID_RESPOSTA, PONTUACAO, ID_PALAVRA_CHAVE, DATA_ATUALIZACAO, DATA_CRIACAO, ID_TOPICO) VALUES (?, ?, 0, ?, '" + Helper.dataAtual() + "', '" + Helper.dataAtual() + "', ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, perguntaHasResposta.getId_pergunta());
			stm.setInt(2, perguntaHasResposta.getId_resposta());
			stm.setInt(3, perguntaHasResposta.getPontuacao());
			stm.setInt(4, perguntaHasResposta.getId_palavra_chave());
			stm.setInt(7, perguntaHasResposta.getId_palavra_chave());
			
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					perguntaHasResposta.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return perguntaHasResposta.getId();
	}
	
	public ArrayList<PerguntaHasResposta> carregarCadastro(String query) {
		PerguntaHasResposta tabela;
		ArrayList<PerguntaHasResposta> lista = new ArrayList<>();

		String sqlSelect = "SELECT * FROM pergunta_has_resposta " + query;

		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {

			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					tabela = new PerguntaHasResposta();
					tabela.setId(rs.getInt("ID"));
					tabela.setId_pergunta(rs.getInt("ID_PERGUNTA"));
					tabela.setId_resposta(rs.getInt("ID_RESPOSTA"));
					tabela.setPontuacao(rs.getInt("PONTUACAO"));
					tabela.setId_palavra_chave(rs.getInt("ID_PALAVRA_CHAVE"));
					tabela.setId_topico(rs.getInt("ID_TOPICO"));
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
