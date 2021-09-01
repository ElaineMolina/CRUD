package br.com.agenda.aplicacao;

import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

//MVC
/*
 * Model
 * View
 * Controller
 */

public class Main {

	public static void main(String[] args) {

		ContatoDAO contatoDao = new ContatoDAO();

		Contato contato = new Contato();
		contato.setNome("Cassia de Paula");
		contato.setIdade(65);
		contato.setDataCadastro(new Date());
		// contatoDao.save(contato);

		// Atualizar o contato.
		Contato c1 = new Contato();
		c1.setNome("Pedro Manoel");
		c1.setIdade(33);
		c1.setDataCadastro(new Date());
		c1.setId(1);// É o número que está no banco de dados da PK
		// contatoDao.update(c1);

		// Deletar o contato pelo seu número de ID
		//contatoDao.deleteByID(9);

		// Visualização dos registros do bando de dados TODOS
		for (Contato c : contatoDao.getContatos()) {
			System.out.println("Contatos: " + c.getNome() + "| Id: " + c.getId());
		}
	}

}
