package br.com.foursys.locadora.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import br.com.foursys.locadora.model.Cidade;
import br.com.foursys.locadora.model.Estado;
import br.com.foursys.locadora.model.Vendedor;

public class VendedorDAO {
	
	private Connection bd;
	
	public VendedorDAO(Connection bd) {
		this.bd = bd;
	}
	
	public void inserir(Vendedor vendedor) throws SQLException {
		String sql = "INSERT INTO vendedor(nome,area_venda,cidade,estado,sexo,idade,salario) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement comando = bd.prepareStatement(sql);
		
		comando.setString(1, vendedor.getNome());
		comando.setString(2, vendedor.getAreaVenda());
		comando.setObject(3, vendedor.getCidade().toString());
		comando.setObject(4, vendedor.getEstado().toString());
		comando.setString(5, vendedor.getSexo()+"");
		comando.setInt(6, vendedor.getIdade());
		comando.setDouble(7, vendedor.getSalario());
		comando.execute();
		
	}
	
	public void alterar(Vendedor vendedor) throws SQLException {
		String sql = "UPDATE vendedor SET area_venda=?, cidade=?, estado=?, salario=? WHERE nome=?";
		PreparedStatement comando = bd.prepareStatement(sql);
		
		comando.setString(1, vendedor.getAreaVenda());
		comando.setObject(2, vendedor.getCidade().toString());
		comando.setObject(3, vendedor.getEstado().toString());
		comando.setDouble(4, vendedor.getSalario());
		comando.setString(5, vendedor.getNome());
//		comando.setString(4, vendedor.getSexo()+"");
//		comando.setInt(5, vendedor.getIdade());
		comando.execute();
	}
	
	public void excluir(Vendedor vendedor) throws SQLException {
		String sql = "DELETE FROM vendedor WHERE nome=?";
		PreparedStatement comando = bd.prepareStatement(sql);
		
		comando.setString(1, vendedor.getNome());
		comando.execute();
	}
	
	public List<Vendedor> buscarTodos() throws SQLException {
		String sql = "SELECT * FROM vendedor ORDER BY nome";
		PreparedStatement comando = bd.prepareStatement(sql);
		ResultSet cursor = comando.executeQuery();
		List<Vendedor> listaVendedor = new ArrayList<Vendedor>();
		
		while (cursor.next()) {
			Vendedor vendedor = new Vendedor();
			
			vendedor.setNome(cursor.getString("nome"));
			vendedor.setAreaVenda(cursor.getString("area_venda"));
			
			Cidade cidade = new Cidade(cursor.getString("cidade"));
			vendedor.setCidade(cidade);
			
//			String aux[] = (cursor.getString("estado")).split(" - ");
//			String uf = aux[0];
//			String nome = aux[1];
			Estado estado = new Estado(cursor.getString("estado"), "");
			vendedor.setEstado(estado);
			
			char sex = cursor.getString("sexo").charAt(0);
			vendedor.setSexo(sex);
			
			vendedor.setIdade(cursor.getInt("idade"));
			vendedor.setSalario(cursor.getDouble("salario"));
			
			listaVendedor.add(vendedor);
		}
		return listaVendedor;
	}
}
