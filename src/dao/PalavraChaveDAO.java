package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.PalavraChave;

public class PalavraChaveDAO {

	/*public int criar(PalavraChave palavra_chave) {

		String sqlInsert = "INSERT INTO palavra_chave(ATIVO, STATUS, ID_CLIENTE, DATA_ATUALIZACAO, DATA_CRIACAO, QTD_TENTATIVA) VALUES (?, ?, ?, ?, ?, ?)";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setInt(1, palavra_chave.getAtivo());
			stm.setString(2, palavra_chave.getStatus());
			stm.setInt(3, palavra_chave.getId_cliente());
			stm.setDate(4, new java.sql.Date( palavra_chave.getData_atualizacao().getTime() ));
			stm.setDate(5, new java.sql.Date( palavra_chave.getData_criacao().getTime() ));
			stm.setInt(6, palavra_chave.getQtd_tentativa());
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					palavra_chave.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return palavra_chave.getId();
	}*/

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
}
