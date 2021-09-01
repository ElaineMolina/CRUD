package br.com.molina.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoUtil {
	
	private static ConexaoUtil conexaoUtil;
	public static ConexaoUtil getInstance() {
		if (conexaoUtil == null) {
			conexaoUtil = new ConexaoUtil();
		}
		return conexaoUtil;
	}
	
	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost/test?useTimezone=true&serverTimezone=UTC", "root", "abobora"); //ou pode ser  "jdbc:mysql://127.0.0.1"
	}
	
	public static void main(String[] args) {//verificando conexão
		try {
			System.out.println(getInstance().getConnection());
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
