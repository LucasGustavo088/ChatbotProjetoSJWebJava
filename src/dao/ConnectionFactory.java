package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

	//singleton da conex√£o - thread safe
	private static final ThreadLocal<Connection> conn = new ThreadLocal<>();
		
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}
	// ObtÈm conex„o com o banco de dados
	public static Connection obtemConexao() throws SQLException {
		return DriverManager
				.getConnection("jdbc:mysql://localhost/prat_prog?user=root&password=");
	}
	
	//Fecha a conex√£o - usado no servlet destroy
	public static void fecharConexao() throws SQLException {
		if(conn.get() != null){
			conn.get().close();
			conn.set(null);
		}
	}
}