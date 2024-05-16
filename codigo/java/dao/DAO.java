package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAO {
	protected Connection conn;

    public DAO() {
		conn = null;
	}

    public boolean conect_to_db() {
        String driverName = "org.postgresql.Driver";                    
		String serverName = "localhost";
        String dbname = "postgres";
		int porta = 5432;
        String url = "jdbc:postgresql://" + serverName + ":" + porta +"/" + dbname;
        String username = "postgres";
        String password = "Senha@123";
		boolean status = false;

        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, username, password);
            status = (conn == null);
			System.out.println("Conexão efetuada com o postgres!");
		} catch (ClassNotFoundException e) { 
			System.err.println("Conexão NÃO efetuada com o postgres -- Driver não encontrado -- " + e.getMessage());
		} catch (SQLException e) {
			System.err.println("Conexão NÃO efetuzada com o postgres -- " + e.getMessage());
		}
        return status;
    }

    public boolean close_connection() {
        boolean status = false;
        try {
            if (conn != null) {
                conn.close();
                status = true;
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return status;
    }
}