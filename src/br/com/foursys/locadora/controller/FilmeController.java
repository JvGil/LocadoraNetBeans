package br.com.foursys.locadora.controller;

import br.com.foursys.locadora.dao.FilmeDAO;
import br.com.foursys.locadora.model.Cidade;
import br.com.foursys.locadora.model.Estado;
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
 *
 * @author dmunhoz
 */
public class FilmeController {

    private FilmeView viewFilme;
    private Filme filme = new Filme();
    private List<Filme> listaFilmes;
    private List<Cidade> cidades;
    private List<Estado> estados;
    private boolean alterar;

    public FilmeController(FilmeView viewFilme) {
        this.viewFilme = viewFilme;
    }
    
//    public void alterarFilme() {
//        DefaultTableModel modelo = (DefaultTableModel) this.viewCliente.getTabelaCliente().getModel();
//        if (this.viewCliente.getTabelaCliente().getSelectedRow() < 0) {
//            JOptionPane.showMessageDialog(null, "É necessário selecionar um cliente");
//        } else {
//            cliente = listaClientes.get(this.viewCliente.getTabelaCliente().getSelectedRow());
//            this.viewCliente.getJtfCpf().setText(cliente.getCpf());
//            this.viewCliente.getJtfRg().setText(cliente.getRg());
//            this.viewCliente.getCbSexo().setSelectedItem(cliente.getSexo() + "");
//            this.viewCliente.getJtfIdade().setText(cliente.getIdade() + "");
//            this.viewCliente.getJtfNome().setText(cliente.getNome());
//            this.viewCliente.getJtfLogradouro().setText(cliente.getLogradouro());
//            this.viewCliente.getJtfNumeroLogradouro().setText(cliente.getNumLogradouro()+ "");
//            this.viewCliente.getJtfBairro().setText(cliente.getBairro());
//            this.viewCliente.getCbCidade().setSelectedItem(cliente.getCidade().toString());
//            this.viewCliente.getCbEstado().setSelectedItem(cliente.getEstado().getNome());
//            this.viewCliente.getJtfTelefone().setText(cliente.getTelefone());
//            this.viewCliente.getJtfDataNascimento().setText(cliente.getDataNasc());
//            this.alterar = true;
//            acaoBotaoAlterar();
//        }
//    }

    public void excluirFilme(){
        DefaultTableModel modelo = (DefaultTableModel) this.viewFilme.getTabelaFilme().getModel();
        if (this.viewFilme.getTabelaFilme().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "É necessário selecionar um filme");
        } else {
            filme = listaFilmes.get(this.viewFilme.getTabelaFilme().getSelectedRow());
            int opcao = JOptionPane.showConfirmDialog(null, "Confirma em excluir este registro?","Atenção",
                                                      JOptionPane.YES_OPTION,
                                                      JOptionPane.CANCEL_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                 Connection bd = ConnectionFactory.getConnection();
                FilmeDAO dao = new FilmeDAO(bd);
                try {
                    dao.excluir(filme);
                    JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!");
                    listarFilmes();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir o cliente!");
                    Logger.getLogger(FilmeController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public void salvarFilme() {
        if (this.alterar == false) {
            //inserir um registro
            if (validarSalvar()) {
                Filme filme = new Filme();
                filme.setCodigo(Integer.parseInt(this.viewFilme.getJtfCodigo().getText()));
                filme.setNome(this.viewFilme.getJtfNome().getText());
                filme.setValor(Double.parseDouble(this.viewFilme.getJtfValor().getText()));
                filme.setValorPromocao(Double.parseDouble(this.viewFilme.getJtfValorPromocao().getText()));
                
                if (this.viewFilme.getCbDisponivel().getSelectedIndex() == 1) {
                    filme.setDisponivel(true);
                } else {
                    filme.setDisponivel(false);
                }
                
                if (this.viewFilme.getCbPromocao().getSelectedIndex() == 1) {
                    filme.setPromocao(true);
                } else {
                    filme.setPromocao(false);
                }
                
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
                
                if (this.viewFilme.getCbDisponivel().getSelectedIndex() == 1) {
                    filme.setDisponivel(true);
                } else {
                    filme.setDisponivel(false);
                }
                
                if (this.viewFilme.getCbPromocao().getSelectedIndex() == 1) {
                    filme.setPromocao(true);
                } else {
                    filme.setPromocao(false);
                }
                Connection bd = ConnectionFactory.getConnection();
                FilmeDAO dao = new FilmeDAO(bd);
                try {
                    dao.alterar(filme);
                    JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao alterado o cliente.");
                    Logger.getLogger(FilmeController.class.getName()).log(Level.SEVERE, null, ex);
                }
                limparCampos();
                bloqueioInicial();
                listarFilmes();
            }
        }
    }

    public boolean validarSalvar() {
        if (this.viewFilme.getJtfCodigo().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o código, campo obrigatório.");
            return false;
        }
        
        if (this.viewFilme.getJtfNome().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o nome, campo obrigatório.");
            return false;
        }
        
        if (this.viewFilme.getJtfValor().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o valor, campo obrigatório.");
            return false;
        }
        
        if (this.viewFilme.getJtfValorPromocao().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o valor quando estiver em pormoção, campo obrigatório (caso o filme não esteja em promoção, coloque 0 reais no valor).");
            return false;
        }

        if (this.viewFilme.getCbDisponivel().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe se filme está disponível, campo obrigatório.");
            return false;
        }
        
        if (this.viewFilme.getCbPromocao().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe se filme está em promoção, campo obrigatório.");
            return false;
        }
        
        if (!this.viewFilme.getJcbAcao().isSelected() && !this.viewFilme.getJcbFiccao().isSelected() && !this.viewFilme.getJcbTerror().isSelected()&& !this.viewFilme.getJcbComedia().isSelected() && !this.viewFilme.getJcbInfantil().isSelected() && !this.viewFilme.getJcbAnimacao().isSelected() && !this.viewFilme.getJcbAventura().isSelected() && !this.viewFilme.getJcbOutro().isSelected()) {
            JOptionPane.showMessageDialog(null, "Informe pelo menos um gênero, campo obrigatório.");
            return false;
        }            
        return true;
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

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewFilme.getTabelaFilme().getModel();
        modelo.setRowCount(0);
        for (Filme listaFilme : listaFilmes) {
            modelo.addRow(new String[]{listaFilme.getNome(), listaFilme.getGenero(), listaFilme.getValor()+"",(listaFilme.isDisponivel())?"Sim":"Não", (listaFilme.isPromocao())?"Sim":"Não", listaFilme.getValorPromocao()+""});
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
        this.viewFilme.getCbDisponivel().setEnabled(false);
        this.viewFilme.getCbPromocao().setEnabled(false);
        this.viewFilme.getJcbAcao().setEnabled(false);
        this.viewFilme.getJcbFiccao().setEnabled(false);
        this.viewFilme.getJcbTerror().setEnabled(false);
        this.viewFilme.getJcbComedia().setEnabled(false);
        this.viewFilme.getJcbInfantil().setEnabled(false);
        this.viewFilme.getJcbAnimacao().setEnabled(false);
        this.viewFilme.getJcbAventura().setEnabled(false);
        this.viewFilme.getJcbOutro().setEnabled(false);
    }

    public void liberarCampos() {
        this.viewFilme.getJtfPesquisarNome().setEditable(false);
        this.viewFilme.getJtfCodigo().grabFocus();
        this.viewFilme.getJtfCodigo().setEditable(true);
        this.viewFilme.getJtfNome().setEditable(true);
        this.viewFilme.getJtfValor().setEditable(true);
        this.viewFilme.getJtfValorPromocao().setEditable(true);
        this.viewFilme.getCbDisponivel().setEnabled(true);
        this.viewFilme.getCbPromocao().setEnabled(true);
        this.viewFilme.getJcbAcao().setEnabled(true);
        this.viewFilme.getJcbFiccao().setEnabled(true);
        this.viewFilme.getJcbTerror().setEnabled(true);
        this.viewFilme.getJcbComedia().setEnabled(true);
        this.viewFilme.getJcbInfantil().setEnabled(true);
        this.viewFilme.getJcbAnimacao().setEnabled(true);
        this.viewFilme.getJcbAventura().setEnabled(true);
        this.viewFilme.getJcbOutro().setEnabled(true);
    }

    public void limparCampos() {
        this.viewFilme.getJtfCodigo().setText(null);
        this.viewFilme.getJtfNome().setText(null);
        this.viewFilme.getJtfValor().setText(null);
        this.viewFilme.getJtfValorPromocao().setText(null);
        this.viewFilme.getCbDisponivel().setSelectedIndex(0);
        this.viewFilme.getCbPromocao().setSelectedIndex(0);
        this.viewFilme.getJcbAcao().setSelected(false);
        this.viewFilme.getJcbFiccao().setSelected(false);
        this.viewFilme.getJcbTerror().setSelected(false);
        this.viewFilme.getJcbComedia().setSelected(false);
        this.viewFilme.getJcbInfantil().setSelected(false);
        this.viewFilme.getJcbAnimacao().setSelected(false);
        this.viewFilme.getJcbAventura().setSelected(false);
        this.viewFilme.getJcbOutro().setSelected(false);
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

//    public void acaoBotaoAlterar() {
//        this.viewCliente.getJbtNovo().setEnabled(false);
//        this.viewCliente.getJbtAlterar().setEnabled(false);
//        this.viewCliente.getJbtExcluir().setEnabled(false);
//        this.viewCliente.getJbtSair().setEnabled(false);
//        this.viewCliente.getJbtSalvar().setEnabled(true);
//        this.viewCliente.getJbtCancelar().setEnabled(true);
//        liberarCampos();
//        this.viewCliente.getJtfCpf().setEditable(false);
//        this.viewCliente.getJtfRg().setEditable(false);
//        this.viewCliente.getJtfNome().setEditable(false);
//        this.viewCliente.getJtfIdade().setEditable(false);
//        this.viewCliente.getJtfLogradouro().grabFocus();
//        this.viewCliente.getCbSexo().setEnabled(false);
//    }

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
}
