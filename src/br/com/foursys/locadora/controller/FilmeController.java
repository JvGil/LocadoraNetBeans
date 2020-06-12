package br.com.foursys.locadora.controller;

import br.com.foursys.locadora.dao.FilmeDAO;
import br.com.foursys.locadora.model.Filme;
import br.com.foursys.locadora.util.ConnectionFactory;
import br.com.foursys.locadora.view.FilmeView;
import com.mysql.jdbc.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Classe responsável por controlar o Filme
 *
 * @author jgil
 * @since 04/03/2020
 * @version 0.1
 */

public class FilmeController {

    private FilmeView viewFilme;
    private Filme filme = new Filme();
    private List<Filme> listaFilmes;
    private boolean alterar;

    public FilmeController(FilmeView viewFilme) {
        this.viewFilme = viewFilme;
    }
    
    public FilmeController() {
        this.viewFilme = viewFilme;
    }
    
    public void salvarFilme() {
        if (this.alterar == false) {
            //inserir um registro
            if (validarSalvar()) {
                Filme filme = new Filme();
                filme.setCodigo(Integer.parseInt(this.viewFilme.getJtfCodigo().getText()));
                filme.setNome(this.viewFilme.getJtfNome().getText());
                filme.setValor(Double.parseDouble(this.viewFilme.getJtfValor().getText().replace(',', '.')));
                filme.setValorPromocao(Double.parseDouble(this.viewFilme.getJtfValorPromocao().getText()));
                filme.setDisponivel((this.viewFilme.getJcbDisponivel().getSelectedItem().toString() == "SIM")?true:false);
                filme.setPromocao((this.viewFilme.getJcbPromocao().getSelectedItem().toString() == "SIM")?true:false);
                
                // Utilizo para pegar o campo de gênero do filme
                String genero = verificaGenero();
                filme.setGenero(genero);
                
                Connection bd = ConnectionFactory.getConnection();
                FilmeDAO dao = new FilmeDAO(bd);
                try {
                    dao.inserir(filme);
                    JOptionPane.showMessageDialog(null, "Filme inserido com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao inserir o filme.");
                    Logger.getLogger(FilmeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                limparCampos();
                bloqueioInicial();
                listarFilmes();
            }
        } else {
            //alterando o registro
            if (validarSalvar()) {
                filme.setValor(Double.parseDouble(this.viewFilme.getJtfValor().getText()));
                filme.setValorPromocao(Double.parseDouble(this.viewFilme.getJtfValorPromocao().getText()));
                filme.setDisponivel((this.viewFilme.getJcbDisponivel().getSelectedItem().toString() == "SIM")?true:false);
                filme.setPromocao((this.viewFilme.getJcbPromocao().getSelectedItem().toString() == "SIM")?true:false);
                
                // Utilizo para pegar o campo de gênero do filme
                String genero = verificaGenero();
                filme.setGenero(genero);
                
                Connection bd = ConnectionFactory.getConnection();
                FilmeDAO dao = new FilmeDAO(bd);
                try {
                    dao.alterar(filme);
                    JOptionPane.showMessageDialog(null, "Filme alterado com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao alterar o filme.");
                    Logger.getLogger(FilmeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                limparCampos();
                bloqueioInicial();
                listarFilmes();
            }
        }
    }
     
     public boolean validarSalvar() {
        if (this.viewFilme.getJtfCodigo().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código, campo obrigatório.");
            return false;
        }

        if (this.viewFilme.getJtfNome().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o nome, campo obrigatório.");
            return false;
        }

        if (this.viewFilme.getJtfValor().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o valor, campo obrigatório.");
            return false;
        }

        if (this.viewFilme.getJtfValorPromocao().getText().trim().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o valor quando estiver em pormoção, campo obrigatório (caso o filme não esteja em promoção, coloque 0 reais no valor).");
            return false;
        }

        if (this.viewFilme.getJcbDisponivel().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe se filme está disponível, campo obrigatório.");
            return false;
        }

        if (this.viewFilme.getJcbPromocao().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe se filme está em promoção, campo obrigatório.");
            return false;
        }

        if (!this.viewFilme.getJchAcao().isSelected() && !this.viewFilme.getJchFiccao().isSelected() && !this.viewFilme.getJchTerror().isSelected() && !this.viewFilme.getJchComedia().isSelected() && !this.viewFilme.getJchInfantil().isSelected() && !this.viewFilme.getJchAnimacao().isSelected() && !this.viewFilme.getJchAventura().isSelected() && !this.viewFilme.getJchOutro().isSelected()) {
            JOptionPane.showMessageDialog(null, "Informe pelo menos um gênero, campo obrigatório.");
            return false;
        }
        return true;
    }
     
     public void alterarFilme() {
          DefaultTableModel modelo = (DefaultTableModel) this.viewFilme.getTabelaFilme().getModel();
        if (this.viewFilme.getTabelaFilme().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "É necessário selecionar um filme");
        } else {
            filme = listaFilmes.get(this.viewFilme.getTabelaFilme().getSelectedRow());
            this.viewFilme.getJtfCodigo().setText(filme.getCodigo() + "");
            this.viewFilme.getJtfNome().setText(filme.getNome());
            this.viewFilme.getJtfValor().setText(filme.getValor() + "");
            this.viewFilme.getJtfValorPromocao().setText(filme.getValorPromocao() + "");
            this.viewFilme.getJcbDisponivel().setSelectedItem((filme.isDisponivel())?"SIM":"NÃO");
            this.viewFilme.getJcbPromocao().setSelectedItem((filme.isPromocao())?"SIM":"NÃO");
            carregarCheckBoxGenero();
            this.alterar = true;
            acaoBotaoAlterar();
        }
     }

    public void excluirFilme() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewFilme.getTabelaFilme().getModel();
        if (this.viewFilme.getTabelaFilme().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "É necessário selecionar um filme");
        } else {
            filme = listaFilmes.get(this.viewFilme.getTabelaFilme().getSelectedRow());
            int opcao = JOptionPane.showConfirmDialog(null, "Confirma em excluir este registro?", "Atenção", 
                    JOptionPane.YES_OPTION, JOptionPane.CANCEL_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                Connection bd = ConnectionFactory.getConnection();
                FilmeDAO dao = new FilmeDAO(bd);
                try {
                    dao.excluir(filme);
                    JOptionPane.showMessageDialog(null, "Filme excluido com sucesso!");
                    listarFilmes();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir o cliente!");
                    Logger.getLogger(FilmeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    public void listarFilmes() {
        Connection bd = ConnectionFactory.getConnection();
        FilmeDAO dao = new FilmeDAO(bd);
        try {
            listaFilmes = dao.buscarTodos();
            carregarTabela();
        } catch (SQLException ex) {
            Logger.getLogger(FilmeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Filme> buscarTodos() {
        Connection bd = ConnectionFactory.getConnection();
        FilmeDAO dao = new FilmeDAO(bd);
        try {
            listaFilmes = dao.buscarTodos();
        } catch (SQLException ex) {
            Logger.getLogger(FilmeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaFilmes;
    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewFilme.getTabelaFilme().getModel();
        modelo.setRowCount(0);
        for (Filme listaFilme : listaFilmes) {
            String genero = carregarGeneroTabela(listaFilme.getGenero());
            
            modelo.addRow(new String[]{listaFilme.getNome(), genero, "R$" + listaFilme.getValor(),
                (listaFilme.isDisponivel()) ? "SIM" : "NÃO", (listaFilme.isPromocao()) ? "SIM" : "NÃO", "R$" + listaFilme.getValorPromocao()});
        }
    }

    public void bloqueioInicial() {
        this.viewFilme.getJbtNovo().setEnabled(true);
        this.viewFilme.getJbtAlterar().setEnabled(true);
        this.viewFilme.getJbtExcluir().setEnabled(true);
        this.viewFilme.getJbtSair().setEnabled(true);
        this.viewFilme.getJbtSalvar().setEnabled(false);
        this.viewFilme.getJbtCancelar().setEnabled(false);
        bloquearCampos();
    }

    public void bloquearCampos() {
        this.viewFilme.getJtfPesquisarNome().setEditable(true);
        this.viewFilme.getJtfPesquisarNome().grabFocus();
        this.viewFilme.getJtfCodigo().setEditable(false);
        this.viewFilme.getJtfNome().setEditable(false);
        this.viewFilme.getJtfValor().setEditable(false);
        this.viewFilme.getJtfValorPromocao().setEditable(false);
        this.viewFilme.getJcbDisponivel().setEnabled(false);
        this.viewFilme.getJcbPromocao().setEnabled(false);
        this.viewFilme.getJchAcao().setEnabled(false);
        this.viewFilme.getJchFiccao().setEnabled(false);
        this.viewFilme.getJchTerror().setEnabled(false);
        this.viewFilme.getJchComedia().setEnabled(false);
        this.viewFilme.getJchInfantil().setEnabled(false);
        this.viewFilme.getJchAnimacao().setEnabled(false);
        this.viewFilme.getJchAventura().setEnabled(false);
        this.viewFilme.getJchOutro().setEnabled(false);
    }

    public void acaoBotaoNovo() {
        this.viewFilme.getJbtNovo().setEnabled(false);
        this.viewFilme.getJbtAlterar().setEnabled(false);
        this.viewFilme.getJbtExcluir().setEnabled(false);
        this.viewFilme.getJbtSair().setEnabled(false);
        this.viewFilme.getJbtSalvar().setEnabled(true);
        this.viewFilme.getJbtCancelar().setEnabled(true);
        liberarCampos();
        this.alterar = false;
    }

    public void acaoBotaoCancelar() {
        this.viewFilme.getJbtNovo().setEnabled(true);
        this.viewFilme.getJbtAlterar().setEnabled(true);
        this.viewFilme.getJbtExcluir().setEnabled(true);
        this.viewFilme.getJbtSair().setEnabled(true);
        this.viewFilme.getJbtSalvar().setEnabled(false);
        this.viewFilme.getJbtCancelar().setEnabled(false);
        limparCampos();
        bloquearCampos();
    }
    
    public void acaoBotaoAlterar() {
        this.viewFilme.getJbtNovo().setEnabled(false);
        this.viewFilme.getJbtAlterar().setEnabled(false);
        this.viewFilme.getJbtExcluir().setEnabled(false);
        this.viewFilme.getJbtSair().setEnabled(false);
        this.viewFilme.getJbtSalvar().setEnabled(true);
        this.viewFilme.getJbtCancelar().setEnabled(true);
        liberarCampos();
        this.viewFilme.getJtfCodigo().setEditable(false);
        this.viewFilme.getJtfNome().setEditable(false);
    }

    public void liberarCampos() {
        this.viewFilme.getJtfPesquisarNome().setEditable(false);
        this.viewFilme.getJtfCodigo().grabFocus();
        this.viewFilme.getJtfCodigo().setEditable(true);
        this.viewFilme.getJtfNome().setEditable(true);
        this.viewFilme.getJtfValor().setEditable(true);
        this.viewFilme.getJtfValorPromocao().setEditable(true);
        this.viewFilme.getJcbDisponivel().setEnabled(true);
        this.viewFilme.getJcbPromocao().setEnabled(true);
        this.viewFilme.getJchAcao().setEnabled(true);
        this.viewFilme.getJchFiccao().setEnabled(true);
        this.viewFilme.getJchTerror().setEnabled(true);
        this.viewFilme.getJchComedia().setEnabled(true);
        this.viewFilme.getJchInfantil().setEnabled(true);
        this.viewFilme.getJchAnimacao().setEnabled(true);
        this.viewFilme.getJchAventura().setEnabled(true);
        this.viewFilme.getJchOutro().setEnabled(true);
    }

    public void limparCampos() {
        this.viewFilme.getJtfCodigo().setText(null);
        this.viewFilme.getJtfNome().setText(null);
        this.viewFilme.getJtfValor().setText(null);
        this.viewFilme.getJtfValorPromocao().setText(null);
        this.viewFilme.getJcbDisponivel().setSelectedIndex(0);
        this.viewFilme.getJcbPromocao().setSelectedIndex(0);
        this.viewFilme.getJchAcao().setSelected(false);
        this.viewFilme.getJchFiccao().setSelected(false);
        this.viewFilme.getJchTerror().setSelected(false);
        this.viewFilme.getJchComedia().setSelected(false);
        this.viewFilme.getJchInfantil().setSelected(false);
        this.viewFilme.getJchAnimacao().setSelected(false);
        this.viewFilme.getJchAventura().setSelected(false);
        this.viewFilme.getJchOutro().setSelected(false);
    }
    
    public String verificaGenero() {
        String retorno = "";
        
        if (this.viewFilme.getJchAcao().isSelected()) {
            retorno += "Ação;";    
        } else {
            retorno += " ;";
        }
        
        if (this.viewFilme.getJchFiccao().isSelected()) {
            retorno += "Ficcao;";    
        } else {
            retorno += " ;";
        }
        
        if (this.viewFilme.getJchTerror().isSelected()) {
            retorno += "Terror;";    
        } else {
            retorno += " ;";
        }
        
        if (this.viewFilme.getJchComedia().isSelected()) {
            retorno += "Comedia;";    
        } else {
            retorno += " ;";
        }
        
        if (this.viewFilme.getJchInfantil().isSelected()) {
            retorno += "Infantil;";    
        } else {
            retorno += " ;";
        }
        
        if (this.viewFilme.getJchAnimacao().isSelected()) {
            retorno += "Animacao;";    
        } else {
            retorno += " ;";
        }
        
        if (this.viewFilme.getJchAventura().isSelected()) {
            retorno += "Aventura;";    
        } else {
            retorno += " ;";
        }
        
        if (this.viewFilme.getJchOutro().isSelected()) {
            retorno += "Outro";    
        } else {
            retorno += " ;";
        }
        
        return retorno;
    }
    
    public void carregarCheckBoxGenero() {
        String genero[] = filme.getGenero().split(";");
        
        if (!genero[0].equals(" ")) {
            this.viewFilme.getJchAcao().setSelected(true);
        }
        
        if (!genero[1].equals(" ")) {
            this.viewFilme.getJchFiccao().setSelected(true);
        }
        
        if (!genero[2].equals(" ")) {
            this.viewFilme.getJchTerror().setSelected(true);
        }
        
        if (!genero[3].equals(" ")) {
            this.viewFilme.getJchComedia().setSelected(true);
        }
        
        if (!genero[4].equals(" ")) {
            this.viewFilme.getJchInfantil().setSelected(true);
        }
        
        if (!genero[5].equals(" ")) {
            this.viewFilme.getJchAnimacao().setSelected(true);
        }
        
        if (!genero[6].equals(" ")) {
            this.viewFilme.getJchAventura().setSelected(true);
        }
        
        if (!genero[7].equals(" ")) {
            this.viewFilme.getJchOutro().setSelected(true);
        }
    }
    
    public String carregarGeneroTabela(String aux) {
        String genero[] = aux.split(";");
        String retorno = "";
        
        if (!genero[0].equals(" ")) {
            retorno += "Ação";
        }
        
        if (!genero[1].equals(" ")) {
            retorno += " Ficcao";
        }
        
        if (!genero[2].equals(" ")) {
            retorno += " Terror";
        }
        
        if (!genero[3].equals(" ")) {
            retorno += " Comédia";
        }
        
        if (!genero[4].equals(" ")) {
            retorno += " Infantil";
        }
        
        if (!genero[5].equals(" ")) {
            retorno += " Animação";
        }
        
        if (!genero[6].equals(" ")) {
            retorno += " Aventura";
        }
        
        if (!genero[7].equals(" ")) {
            retorno += " Outro";
        }
        return retorno;
    }
}
