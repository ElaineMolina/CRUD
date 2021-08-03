package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

//aqui voc� faz com o Java se molde a uma estrutura relacional que � o banco
//aqui nesta classe ir�o acontecer todas as regras de neg�cios
public class ContatoDAO {
	
	/*
	 * CRUD
	 * c: cread
	 * r: read
	 * u: updade
	 * d: delete
	 */
								//inst�ncia de contato
	public void save(Contato contato) {
		
		//que  � uma query
		String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?,?,?)";
		
		//temos agora que conectar
		Connection conn = null ;
		
		//extrutura que executa uma estrutura de  java com o mysql
		PreparedStatement pstm = null;
		
		//tentando se conectar  ao banco com o try catch
		try {
			//criar uma conex�o com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			//criamos uma PreparedStatement, para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			//adicionar os valores que s�o esperados pela query
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			//executar a query
			pstm.execute();
			
		}catch (Exception e ) {
			e.printStackTrace();
		}finally {
			//fechar as conex�es
			try {
				if(pstm!=null) {
					pstm.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	public void update (Contato contato) {
		String sql = "UPDATE contatos SET nome = ?, idade = ?, datacadastro = ?" + 
				"WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Criar conex�o com o banco
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Criar a classe para executar a query
			pstm = conn.prepareStatement(sql);
			
			//Adicionar os valores para atualizar
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			//Qual o ID do registro que deseja  atualizar?
			pstm.setInt(4, contato.getId());
			
			//Executara query
			pstm.execute();
			
		}catch (Exception e ) {
			e.printStackTrace();
		}finally {
			try {
				if (pstm!=null) {
					pstm.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
				
			}
		}
	}
	
	public void deleteByID(int id) {
		String sql = "DELETE FROM contatos WHERE id = ?";
		Connection conn = null;
		PreparedStatement pstm = null;
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, id);
			pstm.execute();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e ) {
				e.printStackTrace();
			}
		}
	}
	public List<Contato> getContatos(){
		String sql = "SELECT * FROM contatos";
		List<Contato>  contatos = new ArrayList<Contato>();
		Connection conn = null;
		PreparedStatement pstm =null;
		
		//Classeque vai recuperasr os dados do bvancol. ****SDEWLECT****
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				
				Contato contato = new Contato();
				
				//Recuperar o id
				contato.setId(rset.getInt("id"));
				//Recuperar o nome
				contato.setNome(rset.getString("nome"));
				//Recuparar a idade
				contato.setIdade(rset.getInt("idade"));
				//Recuperar a data de cadastrado
				contato.setDataCadastro(rset.getDate("datacadastro"));
				
				
				contatos.add(contato);
			}
			}catch (Exception e ) {
				e.printStackTrace();
			}finally {
				try {
				if(rset!=null) {
					rset.close();
				}
				if(pstm!=null){
					pstm.close();
				}
				if(conn!=null) {
					conn.close();
				}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		
			return contatos;
	}
		
}