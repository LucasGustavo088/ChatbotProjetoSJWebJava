package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import model.Topico;
import utils.Helper;
import model.Resposta;
import model.Topico;


public class TopicoDAO extends Dao{

	public int criar(Topico topico) {
		
		String sqlInsert = "INSERT INTO topico(NOME, ATIVO, DATA_ATUALIZACAO, DATA_CRIACAO) VALUES (?, ?, '" + Helper.dataAtual() + "', '" + Helper.dataAtual() + "')";
		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlInsert);) {
			stm.setString(1, topico.getNome());
			stm.setInt(2, topico.getAtivo());
			
			stm.execute();
			String sqlQuery = "SELECT LAST_INSERT_ID()";
			try (PreparedStatement stm2 = conn.prepareStatement(sqlQuery);
					ResultSet rs = stm2.executeQuery();) {
				if (rs.next()) {
					topico.setId(rs.getInt(1));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return topico.getId();
	}
	
	public ArrayList<Topico> verificar_nome_topico_existente(String query) {
		Topico tabela;
		ArrayList<Topico> lista = new ArrayList<>();
		String sqlSelect = "SELECT * FROM topico " + query;

		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {

			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					tabela = new Topico();
					tabela.setId(rs.getInt("ID"));
					tabela.setNome(rs.getString("NOME"));
					tabela.setAtivo(rs.getInt("ATIVO"));
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
	
	public ArrayList<Topico> carregarCadastro(String query) {
		Topico tabela;
		ArrayList<Topico> lista = new ArrayList<>();

		String sqlSelect = "SELECT * FROM topico " + query;

		// usando o try with resources do Java 7, que fecha o que abriu
		try (Connection conn = ConnectionFactory.obtemConexao();
				PreparedStatement stm = conn.prepareStatement(sqlSelect);) {

			try (ResultSet rs = stm.executeQuery();) {
				while (rs.next()) {
					tabela = new Topico();
					tabela.setId(rs.getInt("ID"));
					tabela.setAtivo(rs.getInt("ATIVO"));
					tabela.setNome(rs.getString("NOME"));
					tabela.setData_atualizacao(rs.getTimestamp("DATA_ATUALIZACAO"));
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
