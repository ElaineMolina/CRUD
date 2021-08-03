package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	
	//Nome do usuário do mysql
	private static final String USERNAME = "root";
	
	//Senha do banco
	private static final String PASSWORD = "suasenha";
	
	//Caminho do banco de dados, porta, nome do banco de dados				caminho padrão
	private static final String DATABASE_URL =  "jdbc:mysql://localhost:3306/agenda";
	
	
	/*
	 * Conexão com o banco de dados
	 */
	public static Connection createConnectionToMySQL() throws Exception {
		//faz com que a classe seja carregada com a JVM
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		//Cria a conexão com o banco de dados
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		return connection;
	}
	public static void main(String[] args) throws Exception {
		//Recuperar uma conexão com o banco de dados
		Connection con = createConnectionToMySQL(); 
		
		//Testar se a conexão é nula, assim garanto que nunca vai ter uma conexão a mais do que necessário
		if(con!= null) {
			System.out.println("Conexão obtida com sucesso");
			con.close();
		}
	}
}
