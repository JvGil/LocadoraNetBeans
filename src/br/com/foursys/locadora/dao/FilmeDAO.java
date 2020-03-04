package br.com.foursys.locadora.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Connection;

import br.com.foursys.locadora.model.Filme;

public class FilmeDAO {

    private Connection bd;

    public FilmeDAO(Connection bd) {
        this.bd = bd;
    }

    public void inserir(Filme filme) throws SQLException {
        String sql = "INSERT INTO filme(codigo,nome,genero,valor,disponivel,promocao,valor_promocao) VALUES(?,?,?,?,?,?,?)";
        PreparedStatement comando = bd.prepareStatement(sql);

        comando.setInt(1, filme.getCodigo());
        comando.setString(2, filme.getNome());
        comando.setString(3, filme.getGenero());
        comando.setDouble(4, filme.getValor());
        comando.setBoolean(5, filme.isDisponivel());
        comando.setBoolean(6, filme.isPromocao());
        comando.setDouble(7, filme.getValorPromocao());
        comando.execute();
    }

    public void alterar(Filme filme) throws SQLException {
        String sql = "UPDATE filme SET valor=?, disponivel=?, promocao=?, valor_promocao=?, genero=? WHERE codigo=?";
        PreparedStatement comando = bd.prepareStatement(sql);

        comando.setDouble(1, filme.getValor());
        comando.setBoolean(2, filme.isDisponivel());
        comando.setBoolean(3, filme.isPromocao());
        comando.setDouble(4, filme.getValorPromocao());
        comando.setString(5, filme.getGenero());
        comando.setInt(6, filme.getCodigo());
        comando.execute();
    }

    public void excluir(Filme filme) throws SQLException {
        String sql = "DELETE FROM filme WHERE codigo=?";
        PreparedStatement comando = bd.prepareStatement(sql);

        comando.setInt(1, filme.getCodigo());
        comando.execute();
    }

    public List<Filme> buscarTodos() throws SQLException {
        String sql = "SELECT * FROM filme ORDER BY nome";
        PreparedStatement comando = bd.prepareStatement(sql);
        ResultSet cursor = comando.executeQuery();
        List<Filme> listaFilmes = new ArrayList<Filme>();

        while (cursor.next()) {
            Filme filme = new Filme();
            // nome=?, genero=?, valor=?, disponivel=?, promocao=?, valor_promocao=?
            filme.setCodigo(cursor.getInt("codigo"));
            filme.setNome(cursor.getString("nome"));
            filme.setGenero(cursor.getString("genero"));
            filme.setValor(cursor.getDouble("valor"));
            filme.setDisponivel(cursor.getBoolean("disponivel"));
            filme.setPromocao(cursor.getBoolean("promocao"));
            filme.setValorPromocao(cursor.getDouble("valor_promocao"));

            listaFilmes.add(filme);
        }
        return listaFilmes;
    }
}
