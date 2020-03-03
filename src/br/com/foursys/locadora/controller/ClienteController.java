package br.com.foursys.locadora.controller;

import br.com.foursys.locadora.dao.ClienteDAO;
import br.com.foursys.locadora.model.Cidade;
import br.com.foursys.locadora.model.Cliente;
import br.com.foursys.locadora.model.Estado;
import br.com.foursys.locadora.util.ConnectionFactory;
import br.com.foursys.locadora.view.ClienteView;
import com.mysql.jdbc.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * 
 * 
 * @author dmunhoz
 */
public class ClienteController {

    private ClienteView viewCliente;
    private Cliente cliente = new Cliente();
    private List<Cliente> listaClientes;
    private List<Cidade> cidades;
    private List<Estado> estados;
    private boolean alterar;

    public ClienteController(ClienteView viewCliente) {
        this.viewCliente = viewCliente;
    }
    
    public void alterarCliente() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewCliente.getTabelaCliente().getModel();
        if (this.viewCliente.getTabelaCliente().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "É necessário selecionar um cliente");
        } else {
            cliente = listaClientes.get(this.viewCliente.getTabelaCliente().getSelectedRow());
            this.viewCliente.getJtfCpf().setText(cliente.getCpf());
            this.viewCliente.getJtfRg().setText(cliente.getRg());
            this.viewCliente.getCbSexo().setSelectedItem(cliente.getSexo() + "");
            this.viewCliente.getJtfIdade().setText(cliente.getIdade() + "");
            this.viewCliente.getJtfNome().setText(cliente.getNome());
            this.viewCliente.getJtfLogradouro().setText(cliente.getLogradouro());
            this.viewCliente.getJtfNumeroLogradouro().setText(cliente.getNumLogradouro()+ "");
            this.viewCliente.getJtfBairro().setText(cliente.getBairro());
            this.viewCliente.getCbCidade().setSelectedItem(cliente.getCidade().toString());
            this.viewCliente.getCbEstado().setSelectedItem(cliente.getEstado().getNome());
            this.viewCliente.getJtfTelefone().setText(cliente.getTelefone());
            this.viewCliente.getJtfDataNascimento().setText(cliente.getDataNasc());
            this.alterar = true;
            acaoBotaoAlterar();
        }
    }

    public void excluirCliente(){
        DefaultTableModel modelo = (DefaultTableModel) this.viewCliente.getTabelaCliente().getModel();
        if (this.viewCliente.getTabelaCliente().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "É necessário selecionar um cliente");
        } else {
            cliente = listaClientes.get(this.viewCliente.getTabelaCliente().getSelectedRow());
            int opcao = JOptionPane.showConfirmDialog(null, "Confirma em excluir este registro?","Atenção",
                                                      JOptionPane.YES_OPTION,
                                                      JOptionPane.CANCEL_OPTION);
            if (opcao == JOptionPane.YES_OPTION) {
                 Connection bd = ConnectionFactory.getConnection();
                ClienteDAO dao = new ClienteDAO(bd);
                try {
                    dao.excluir(cliente);
                    JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!");
                    listarClientes();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir o cliente!");
                    Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public void salvarCliente() {
        if (this.alterar == false) {
            //inserir um registro
            if (validarSalvar()) {
                Cliente cliente = new Cliente();
                cliente.setNome(this.viewCliente.getJtfNome().getText());
                cliente.setLogradouro(this.viewCliente.getJtfLogradouro().getText());
                cliente.setNumLogradouro(Integer.parseInt(this.viewCliente.getJtfNumeroLogradouro().getText()));
                cliente.setBairro(this.viewCliente.getJtfBairro().getText());
                Cidade cidade = new Cidade(this.viewCliente.getCbCidade().getSelectedItem().toString());
                cliente.setCidade(cidade);
                Estado estado = new Estado(this.viewCliente.getCbEstado().getSelectedItem().toString(), "");
                cliente.setEstado(estado);
                cliente.setTelefone(this.viewCliente.getJtfTelefone().getText());
                cliente.setCpf(this.viewCliente.getJtfCpf().getText());
                cliente.setRg(this.viewCliente.getJtfRg().getText());
                cliente.setSexo(this.viewCliente.getCbSexo().getSelectedItem().toString().charAt(0));
                cliente.setDataNasc(this.viewCliente.getJtfDataNascimento().getText());
                cliente.setIdade(Integer.parseInt(this.viewCliente.getJtfIdade().getText()));
                Connection bd = ConnectionFactory.getConnection();
                ClienteDAO dao = new ClienteDAO(bd);
                try {
                    dao.inserir(cliente);
                    JOptionPane.showMessageDialog(null, "Cliente inserido com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao inserir o cliente.");
                    Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                limparCampos();
                bloqueioInicial();
                listarClientes();
            }
        } else {
            //alterando o registro
            if (validarSalvar()) {
                cliente.setLogradouro(this.viewCliente.getJtfLogradouro().getText());
                cliente.setNumLogradouro(Integer.parseInt(this.viewCliente.getJtfNumeroLogradouro().getText()));
                cliente.setBairro(this.viewCliente.getJtfBairro().getText());
                Cidade cidade = new Cidade(this.viewCliente.getCbCidade().getSelectedItem().toString());
                cliente.setCidade(cidade);
                Estado estado = new Estado(this.viewCliente.getCbEstado().getSelectedItem().toString(), "");
                cliente.setEstado(estado);
                cliente.setTelefone(this.viewCliente.getJtfTelefone().getText());
                Connection bd = ConnectionFactory.getConnection();
                ClienteDAO dao = new ClienteDAO(bd);
                try {
                    dao.alterar(cliente);
                    JOptionPane.showMessageDialog(null, "Cliente alterado com sucesso!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao alterado o cliente.");
                    Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
                }
                limparCampos();
                bloqueioInicial();
                listarClientes();
            }
        }
    }

    public boolean validarSalvar() {
                
        if (this.viewCliente.getJtfCpf().getText().equals("   .   .   -  ")) {
            JOptionPane.showMessageDialog(null, "Informe o CPF, campo obrigatório.");
            return false;
        }
        
        if (this.viewCliente.getJtfRg().getText().equals("  .   .   ")) {
            JOptionPane.showMessageDialog(null, "Informe o RG, campo obrigatório.");
            return false;
        }
        
        if (this.viewCliente.getJtfDataNascimento().getText().equals("  /  /    ")) {
            JOptionPane.showMessageDialog(null, "Informe a data de nascimento, campo obrigatório.");
            return false;
        }
        
        if (this.viewCliente.getJtfNome().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o nome, campo obrigatório.");
            return false;
        }
        
        if (this.viewCliente.getJtfIdade().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe a idade, campo obrigatório.");
            return false;
        }

        if (this.viewCliente.getCbSexo().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe o sexo, campo obrigatório.");
            return false;
        }
        
        if (this.viewCliente.getJtfLogradouro().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o endereço, campo obrigatório.");
            return false;
        }
        
        if (this.viewCliente.getJtfNumeroLogradouro().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o número, campo obrigatório.");
            return false;
        }
        
        if (this.viewCliente.getJtfBairro().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o bairro, campo obrigatório.");
            return false;
        }
        
        if (this.viewCliente.getJtfTelefone().getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Informe o telefone, campo obrigatório.");
            return false;
        }
        
        if (this.viewCliente.getCbCidade().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe a Cidade, campo obrigatório.");
            return false;
        }
        
        if (this.viewCliente.getCbEstado().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Informe o Estado, campo obrigatório.");
            return false;
        }

        return true;
    }

    public void listarClientes() {
        Connection bd = ConnectionFactory.getConnection();
        ClienteDAO dao = new ClienteDAO(bd);
        try {
            listaClientes = dao.buscarTodos();
            carregarTabela();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void carregarTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewCliente.getTabelaCliente().getModel();
        modelo.setRowCount(0);
        for (Cliente listaCliente : listaClientes) {
            modelo.addRow(new String[]{listaCliente.getNome(), listaCliente.getCidade().toString(), listaCliente.getTelefone(), listaCliente.getIdade() + " anos"});
        }
    }

    public void carregarComboCidade() {
        CidadeController controller = new CidadeController();
        cidades = controller.buscarCidades();
        this.viewCliente.getCbCidade().removeAllItems();
        this.viewCliente.getCbCidade().addItem("-Escolha Cidade-");
        for (Cidade listaCidade : cidades) {
            this.viewCliente.getCbCidade().addItem(listaCidade.getNome());
        }
    }

    public void carregarComboEstado() {
        EstadoController controller = new EstadoController();
        estados = controller.buscarEstados();
        this.viewCliente.getCbEstado().removeAllItems();
        this.viewCliente.getCbEstado().addItem("-Escolha UF-");
        for (Estado listaEstado : estados) {
            this.viewCliente.getCbEstado().addItem(listaEstado.getNome());
        }
    }

    public void bloqueioInicial() {
        this.viewCliente.getJbtNovo().setEnabled(true);
        this.viewCliente.getJbtAlterar().setEnabled(true);
        this.viewCliente.getJbtExcluir().setEnabled(true);
        this.viewCliente.getJbtSair().setEnabled(true);
        this.viewCliente.getJbtSalvar().setEnabled(false);
        this.viewCliente.getJbtCancelar().setEnabled(false);
        bloquearCampos();
    }

    public void bloquearCampos() {
        this.viewCliente.getJtfPesquisarNome().setEditable(true);
        this.viewCliente.getJtfPesquisarNome().grabFocus();
        this.viewCliente.getJtfCpf().setEditable(false);
        this.viewCliente.getJtfRg().setEditable(false);
        this.viewCliente.getJtfIdade().setEditable(false);
        this.viewCliente.getCbSexo().setEnabled(false);
        this.viewCliente.getJtfNome().setEditable(false);
        this.viewCliente.getJtfLogradouro().setEditable(false);
        this.viewCliente.getJtfNumeroLogradouro().setEditable(false);
        this.viewCliente.getJtfBairro().setEditable(false);
        this.viewCliente.getCbCidade().setEnabled(false);
        this.viewCliente.getCbEstado().setEnabled(false);
        this.viewCliente.getJtfTelefone().setEditable(false);
        this.viewCliente.getJtfDataNascimento().setEditable(false);
    }

    public void liberarCampos() {
        this.viewCliente.getJtfPesquisarNome().setEditable(false);
        this.viewCliente.getJtfCpf().grabFocus();
        this.viewCliente.getJtfCpf().setEditable(true);
        this.viewCliente.getJtfRg().setEditable(true);
        this.viewCliente.getJtfIdade().setEditable(true);
        this.viewCliente.getCbSexo().setEnabled(true);
        this.viewCliente.getJtfNome().setEditable(true);
        this.viewCliente.getJtfLogradouro().setEditable(true);
        this.viewCliente.getJtfNumeroLogradouro().setEditable(true);
        this.viewCliente.getJtfBairro().setEditable(true);
        this.viewCliente.getCbCidade().setEnabled(true);
        this.viewCliente.getCbEstado().setEnabled(true);
        this.viewCliente.getJtfTelefone().setEditable(true);
        this.viewCliente.getJtfDataNascimento().setEditable(true);
    }

    public void limparCampos() {
        this.viewCliente.getJtfCpf().setText(null);
        this.viewCliente.getJtfRg().setText(null);
        this.viewCliente.getJtfIdade().setText(null);
        this.viewCliente.getCbSexo().setSelectedIndex(0);
        this.viewCliente.getJtfNome().setText(null);
        this.viewCliente.getJtfLogradouro().setText(null);
        this.viewCliente.getJtfNumeroLogradouro().setText(null);
        this.viewCliente.getJtfBairro().setText(null);
        this.viewCliente.getCbCidade().setSelectedIndex(0);
        this.viewCliente.getCbEstado().setSelectedIndex(0);
        this.viewCliente.getJtfTelefone().setText(null);
        this.viewCliente.getJtfDataNascimento().setText(null);
    }

    public void acaoBotaoCancelar() {
        this.viewCliente.getJbtNovo().setEnabled(true);
        this.viewCliente.getJbtAlterar().setEnabled(true);
        this.viewCliente.getJbtExcluir().setEnabled(true);
        this.viewCliente.getJbtSair().setEnabled(true);
        this.viewCliente.getJbtSalvar().setEnabled(false);
        this.viewCliente.getJbtCancelar().setEnabled(false);
        limparCampos();
        bloquearCampos();
    }

    public void acaoBotaoAlterar() {
        this.viewCliente.getJbtNovo().setEnabled(false);
        this.viewCliente.getJbtAlterar().setEnabled(false);
        this.viewCliente.getJbtExcluir().setEnabled(false);
        this.viewCliente.getJbtSair().setEnabled(false);
        this.viewCliente.getJbtSalvar().setEnabled(true);
        this.viewCliente.getJbtCancelar().setEnabled(true);
        liberarCampos();
        this.viewCliente.getJtfCpf().setEditable(false);
        this.viewCliente.getJtfRg().setEditable(false);
        this.viewCliente.getJtfNome().setEditable(false);
        this.viewCliente.getJtfIdade().setEditable(false);
        this.viewCliente.getJtfLogradouro().grabFocus();
        this.viewCliente.getCbSexo().setEnabled(false);
    }

    public void acaoBotaoNovo() {
        this.viewCliente.getJbtNovo().setEnabled(false);
        this.viewCliente.getJbtAlterar().setEnabled(false);
        this.viewCliente.getJbtExcluir().setEnabled(false);
        this.viewCliente.getJbtSair().setEnabled(false);
        this.viewCliente.getJbtSalvar().setEnabled(true);
        this.viewCliente.getJbtCancelar().setEnabled(true);
        liberarCampos();
        this.alterar = false;
    }
}
