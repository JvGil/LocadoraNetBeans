package br.com.foursys.locadora.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import br.com.foursys.locadora.model.Cidade;
import br.com.foursys.locadora.model.Cliente;
import br.com.foursys.locadora.model.Estado;

public class ClienteDAO {

	private Connection bd;

	public ClienteDAO(Connection bd) {
		this.bd = bd;
	}

	public void inserir(Cliente cliente) throws SQLException {
		String sql = "INSERT INTO cliente(nome,logradouro,numero_logradouro,bairro,cidade,estado,telefone,cpf,rg,sexo,data_nascimento,idade) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement comando = bd.prepareStatement(sql);

		comando.setString(1, cliente.getNome());
		comando.setString(2, cliente.getLogradouro());
		comando.setInt(3, cliente.getNumLogradouro());
		comando.setString(4, cliente.getBairro());
		comando.setObject(5, cliente.getCidade().toString());
		comando.setObject(6, cliente.getEstado().toString());
		comando.setString(7, cliente.getTelefone());
		comando.setString(8, cliente.getCpf());
		comando.setString(9, cliente.getRg());
		comando.setString(10, cliente.getSexo() + "");
		comando.setString(11, cliente.getDataNasc());
		comando.setInt(12, cliente.getIdade());
		comando.execute();

	}

	public void alterar(Cliente cliente) throws SQLException {
		String sql = "UPDATE cliente SET logradouro=?, numero_logradouro=?, bairro=?, cidade=?, estado=?, telefone=? WHERE nome=?";
		PreparedStatement comando = bd.prepareStatement(sql);

		comando.setString(1, cliente.getLogradouro());
		comando.setInt(2, cliente.getNumLogradouro());
		comando.setString(3, cliente.getBairro());
		comando.setObject(4, cliente.getCidade().toString());
		comando.setObject(5, cliente.getEstado().toString());
		comando.setString(6, cliente.getTelefone());
		comando.setString(7, cliente.getNome());
//		comando.setString(8, cliente.getRg());
//		comando.setString(9, cliente.getSexo() + "");
//		comando.setString(10, cliente.getDataNasc());
//		comando.setInt(11, cliente.getIdade());
//		comando.setString(12, cliente.getCpf());
		comando.execute();
	}

	public void excluir(Cliente cliente) throws SQLException {
		String sql = "DELETE FROM cliente WHERE nome=?";
		PreparedStatement comando = bd.prepareStatement(sql);

		comando.setString(1, cliente.getNome());
		comando.execute();
	}

	public List<Cliente> buscarTodos() throws SQLException {
		String sql = "SELECT * FROM cliente ORDER BY nome";
		PreparedStatement comando = bd.prepareStatement(sql);
		ResultSet cursor = comando.executeQuery();
		List<Cliente> listaClientes = new ArrayList<Cliente>();

		while (cursor.next()) {
			Cliente cliente = new Cliente();

			cliente.setNome(cursor.getString("nome"));
			cliente.setLogradouro(cursor.getString("logradouro"));
			cliente.setNumLogradouro(cursor.getInt("numero_logradouro"));
			cliente.setBairro(cursor.getString("bairro"));

			Cidade cidade = new Cidade(cursor.getString("cidade"));
			cliente.setCidade(cidade);

			String aux[] = (cursor.getString("estado")).split(" - ");
			String uf = aux[0];
			String nome = aux[1];
			Estado estado = new Estado(nome, uf);
			cliente.setEstado(estado);

			cliente.setTelefone(cursor.getString("telefone"));
			cliente.setCpf(cursor.getString("cpf"));
			cliente.setRg(cursor.getString("rg"));

			char sex = cursor.getString("sexo").charAt(0);
			cliente.setSexo(sex);

			cliente.setDataNasc(cursor.getString("data_nascimento"));
			cliente.setIdade(cursor.getInt("idade"));

			listaClientes.add(cliente);
		}
		return listaClientes;

	}
}
