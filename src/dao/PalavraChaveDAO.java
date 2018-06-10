package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.PalavraChave;
import utils.Helper;

public class PalavraChaveDAO {

public int criar(PalavraChave palavraChave) {
		
		String sqlInsert = "INSERT INTO pergunta_has_resposta(NOME, PALAVRA_CHAVE_PRINCIPAL, ATIVO, DATA_ATUALIZACAO, DATA_CRIACAO) VALUES (?, ?, ?, '" + Helper.dataAtual() + "', '" + Helper.dataAtual() + "')";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, palavraChave.getNome());
			stm.setInt(2, palavraChave.getPalavra_chave_principal());
			stm.setInt(3, palavraChave.getAtivo());
			
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					palavraChave.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return palavraChave.getId();
	}

	public ArrayList<PalavraChave> carregarCadastro(String query) {
		PalavraChave palavra_chave;
		ArrayList<PalavraChave> lista = new ArrayList<>();

		String sqlSelect = "SELECT * FROM palavra_chave " + query;


		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {

			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					palavra_chave = new PalavraChave();
					palavra_chave.setId(rs.getInt("ID"));
					palavra_chave.setNome(rs.getString("NOME"));
					lista.add(palavra_chave);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			System.out.print(e1.getStackTrace());
		}
		return lista;
	}
	
	public PalavraChave verificar_ja_existe_palavra_chave(PalavraChave palavra_chave) {
		String sqlSelect = "SELECT NOME FROM palavra_chave where NOME =? LIMIT 1 ";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {
			stm.setString(1, palavra_chave.getNome());
			stm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return palavra_chave;
	}
	
	//pegar o exemplo de carregar paisDAo ========================
}
