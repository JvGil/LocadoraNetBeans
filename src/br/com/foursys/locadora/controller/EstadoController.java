package br.com.foursys.locadora.controller;

import br.com.foursys.locadora.dao.EstadoDAO;
import br.com.foursys.locadora.model.Estado;
import br.com.foursys.locadora.util.ConnectionFactory;
import com.mysql.jdbc.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsável por controlar o Estado
 *
 * @author jgil
 * @since 03/03/2020
 * @version 0.1
 */
public class EstadoController {

    public List<Estado> buscarEstados() {
        Connection bd = ConnectionFactory.getConnection();
        EstadoDAO dao = new EstadoDAO(bd);
        List<Estado> listaEstados = null;
        try {
            listaEstados = dao.buscarTodos();
            bd.close();
        } catch (SQLException ex) {
            Logger.getLogger(EstadoController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEstados;
    }
}
