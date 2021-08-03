package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	//Nome do usu�rio do mysql
	private static final String USERNAME = "root";
	
	//Senha do banco
	private static final String PASSWORD = "suasenha";
	
	//Caminho do banco de dados, porta, nome do banco de dados				caminho padr�o
	private static final String DATABASE_URL =  "jdbc:mysql://localhost:3306/agenda";
	
	
	/*
	 * Conex�o com o banco de dados
	 */
	public static Connection createConnectionToMySQL() throws Exception {
		//faz com que a classe seja carregada com a JVM
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Cria a conex�o com o banco de dados
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		return connection;
	}
	public static void main(String[] args) throws Exception {
		//Recuperar uma conex�o com o banco de dados
		Connection con = createConnectionToMySQL(); 
		
		//Testar se a conex�o � nula, assim garanto que nunca vai ter uma conex�o a mais do que necess�rio
		if(con!= null) {
			System.out.println("Conex�o obtida com sucesso");
			con.close();
		}
	}
}
