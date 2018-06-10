package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.Resposta;
import utils.Helper;
import model.Resposta;

public class RespostaDAO {

	public int criar(Resposta resposta) {
		
		String sqlInsert = "INSERT INTO resposta(DESCRICAO, ATIVO, DATA_ATUALIZACAO, DATA_CRIACAO) VALUES (?, ?, '" + Helper.dataAtual() + "', '" + Helper.dataAtual() + "')";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, resposta.getDescricao());
			stm.setInt(2, resposta.getAtivo());
			
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
	
	public ArrayList<Resposta> carregarCadastro(String query) {
		Resposta tabela;
		ArrayList<Resposta> lista = new ArrayList<>();

		String sqlSelect = "SELECT * FROM resposta " + query;

		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {

			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					tabela = new Resposta();
					tabela.setId(rs.getInt("ID"));
					tabela.setDescricao(rs.getString("DESCRICAO"));
					tabela.setAtivo(rs.getInt("ATIVO"));
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
