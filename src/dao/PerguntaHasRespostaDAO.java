package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.PerguntaHasResposta;
import model.PerguntaHasResposta;

public class PerguntaHasRespostaDAO {

	/*public int criar(PerguntaHasResposta pergunta) {
		
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
	}*/
	
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
