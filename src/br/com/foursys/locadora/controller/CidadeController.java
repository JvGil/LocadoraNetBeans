package br.com.foursys.locadora.controller;

import br.com.foursys.locadora.dao.CidadeDAO;
import br.com.foursys.locadora.model.Cidade;
import br.com.foursys.locadora.util.ConnectionFactory;
import com.mysql.jdbc.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsável por controlar a Cidade
 *
 * @author jgil
 * @since 03/03/2020
 * @version 0.1
 */
public class CidadeController {

    public List<Cidade> buscarCidades() {
        Connection bd = ConnectionFactory.getConnection();
        CidadeDAO dao = new CidadeDAO(bd);
        List<Cidade> listaCidades = null;
        try {
            listaCidades = dao.buscarTodos();
            bd.close();
        } catch (SQLException ex) {
            Logger.getLogger(CidadeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaCidades;
    }
}
