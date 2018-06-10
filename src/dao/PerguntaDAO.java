package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.Pergunta;
import utils.Helper;


public class PerguntaDAO {

	public int criar(Pergunta pergunta) {
		
		String sqlInsert = "INSERT INTO pergunta(DESCRICAO, ATIVO, DATA_ATUALIZACAO, DATA_CRIACAO) VALUES (?, ?, '" + Helper.dataAtual() + "', '" + Helper.dataAtual() + "')";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, pergunta.getDescricao());
			stm.setInt(2, pergunta.getAtivo());
			
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
	}
	
	public ArrayList<Pergunta> carregarCadastro(String query) {
		Pergunta tabela;
		ArrayList<Pergunta> lista = new ArrayList<>();

		String sqlSelect = "SELECT * FROM pergunta " + query;

		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {

			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					tabela = new Pergunta();
					tabela.setId(rs.getInt("ID"));
					tabela.setDescricao(rs.getString("DESCRICAO"));
					tabela.setAtivo(rs.getInt("ATIVO"));
					tabela.setUsuario_externo(rs.getInt("USUARIO_EXTERNO"));
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
