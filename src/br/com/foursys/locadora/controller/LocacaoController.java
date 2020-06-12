package br.com.foursys.locadora.controller;

import br.com.foursys.locadora.model.Cliente;
import br.com.foursys.locadora.model.Filme;
import br.com.foursys.locadora.model.Locacao;
import br.com.foursys.locadora.model.Vendedor;
import br.com.foursys.locadora.view.LocacaoView;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 * Classe responsável por controlar as locações
 *
 * @author jgil
 * @since 05/03/2020
 * @version 0.1
 */
public class LocacaoController {

    private LocacaoView viewLocacao;
    private Locacao locacao = new Locacao();
    private List<Cliente> listaClientes;
    private List<Vendedor> listaVendedores;
    private List<Filme> listaFilmes;
    private Cliente cliente = new Cliente();
    private Vendedor vendedor = new Vendedor();
    private Filme filme = new Filme();
    private List<Filme> listaFilmeLocacao = new ArrayList<Filme>();
    private Double valorTotal = 0.00;
    private Double valorPago = 0.00;
    private Double troco = 0.00;
    private String formaPagamento;
    private static final String fileName = "C:/Teste/Relatório locação.xls";

    public LocacaoController(LocacaoView viewLocacao) {
        this.viewLocacao = viewLocacao;
    }

    public void acaoBotaoSalvar() {
        if (validaSalvar()) {
            locacao.setCliente(this.listaClientes.get(this.viewLocacao.getJcbCliente().getSelectedIndex() - 1));
            locacao.setListaFilmes(listaFilmeLocacao);
            locacao.setVendedor(this.listaVendedores.get(this.viewLocacao.getJcbVendedor().getSelectedIndex() - 1));
            locacao.setValorTotal(valorTotal);
            locacao.setValorPago(valorPago);
            locacao.setTroco(troco);
            locacao.setFormaPagamento(verificaPagamento());

//            gerarArquivo(locacao);
//            geraExcel(locacao);
            verificaArquivo(locacao);
            JOptionPane.showMessageDialog(null, "Locação feita com sucesso!");
            limparCampos();
            limparTabela();
            zerarVariaveis();

        }
    }

    public void verificaArquivo(Locacao locacao) {
        int opc = 0;
        try {
            while (opc != 1 && opc != 2) {
                opc = Integer.parseInt(JOptionPane.showInputDialog("Digite 1 ou 2 para escolher as opções\n1 - XLS\n2 - TXT?"));

                switch (opc) {
                    case 1:
                        gerarArquivo(locacao);
                        break;
                    case 2:
                        geraExcel(locacao);
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Digite um valor válido");
                        break;

                }

            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Digite um valor válido");
        }
    }

    public void gerarArquivo(Locacao l1) {
        File arquivo = new File("Relatório locação.txt");

        try {
            FileOutputStream arquivoOutput = new FileOutputStream(arquivo, true);
            PrintStream gravador = new PrintStream(arquivoOutput);

            gravador.print(l1.getCliente().getNome() + ";");
            gravador.print(l1.getCliente().getCpf() + ";");
            gravador.print(l1.getVendedor().getNome() + ";");
            gravador.print(l1.getVendedor().getAreaVenda() + ";");
            gravador.print(l1.getFormaPagamento() + ";");

            for (int i = 0; i < listaFilmeLocacao.size(); i++) {
                gravador.print(listaFilmeLocacao.get(i).getCodigo() + ";");
                gravador.print(listaFilmeLocacao.get(i).getNome() + ";");
                gravador.print(listaFilmeLocacao.get(i).getValor() + ";");
                gravador.print(listaFilmeLocacao.get(i).isPromocao() + ";");
                gravador.print(listaFilmeLocacao.get(i).getValorPromocao() + ";");
            }

            gravador.print("\n");

            gravador.close();
            arquivoOutput.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void geraExcel(Locacao l1) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheetLocacao = workbook.createSheet("Locação");

        int rownum = 0;
        Row row = sheetLocacao.createRow(rownum++);

        int cellnum = 0;

        Cell cellNome = row.createCell(cellnum++);
        cellNome.setCellValue("Nome:");
        Cell cellNome1 = row.createCell(cellnum++);
        cellNome1.setCellValue(l1.getCliente().getNome());

        Cell cellCpf = row.createCell(cellnum++);
        cellCpf.setCellValue("CPF:");
        Cell cellCpf1 = row.createCell(cellnum++);
        cellCpf1.setCellValue(l1.getCliente().getCpf());

        row = sheetLocacao.createRow(rownum++);
        cellnum = 0;

        Cell cellVendedor = row.createCell(cellnum++);
        cellVendedor.setCellValue("Vendedor");
        Cell cellVendedor1 = row.createCell(cellnum++);
        cellVendedor1.setCellValue(l1.getVendedor().getNome());

        Cell cellArea = row.createCell(cellnum++);
        cellArea.setCellValue("Area");
        Cell cellArea1 = row.createCell(cellnum++);
        cellArea1.setCellValue(l1.getVendedor().getAreaVenda());

        row = sheetLocacao.createRow(rownum++);
        cellnum = 0;

        Cell cellFormaPagamento = row.createCell(cellnum++);
        cellFormaPagamento.setCellValue("Forma pagamento");
        Cell cellFormaPagamento1 = row.createCell(cellnum++);
        cellFormaPagamento1.setCellValue(l1.getFormaPagamento());

        row = sheetLocacao.createRow(rownum++);
        Cell cellVazio = row.createCell(cellnum++);
        cellVazio.setCellValue("");
        row = sheetLocacao.createRow(rownum++);
        cellnum = 0;

        Cell cellCodigoFilme = row.createCell(cellnum++);
        cellCodigoFilme.setCellValue("Codigo");
        Cell cellNomeFilme = row.createCell(cellnum++);
        cellNomeFilme.setCellValue("Nome");
        Cell cellValorFilme = row.createCell(cellnum++);
        cellValorFilme.setCellValue("Valor");
        Cell cellPromocaoFilme = row.createCell(cellnum++);
        cellPromocaoFilme.setCellValue("Promoção");
        Cell cellValorPromocaoFilme = row.createCell(cellnum++);
        cellValorPromocaoFilme.setCellValue("Valor promoção");

        row = sheetLocacao.createRow(rownum++);
        cellnum = 0;

        for (int i = 0; i < listaFilmeLocacao.size(); i++) {
            Cell cellCodigoFilme1 = row.createCell(cellnum++);
            cellCodigoFilme1.setCellValue(listaFilmeLocacao.get(i).getCodigo());
            Cell cellNomeFilme1 = row.createCell(cellnum++);
            cellNomeFilme1.setCellValue(listaFilmeLocacao.get(i).getNome());

            Cell cellValorFilme1 = row.createCell(cellnum++);
            String valorFormatado = new DecimalFormat("R$#,##0.00").format(listaFilmeLocacao.get(i).getValor());
            cellValorFilme1.setCellValue(valorFormatado);

            Cell cellPromocaoFilme1 = row.createCell(cellnum++);
            cellPromocaoFilme1.setCellValue(verificaPromocao(listaFilmeLocacao.get(i).isPromocao()));

            Cell cellValorPromocaoFilme1 = row.createCell(cellnum++);
            String valorFormatado1 = new DecimalFormat("R$#,##0.00").format(listaFilmeLocacao.get(i).getValorPromocao());
            cellValorPromocaoFilme1.setCellValue(valorFormatado1);

            row = sheetLocacao.createRow(rownum++);
            cellnum = 0;
        }

        try {
            FileOutputStream out = new FileOutputStream(new File(LocacaoController.fileName));
            workbook.write(out);
            out.close();
            System.out.println("Arquivo gerado com sucesso");
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Erro na criação do arquivo");
            e.printStackTrace();
        }
    }

    public String verificaPromocao(boolean promocao) {
        if (promocao) {
            return "Sim";
        }

        return "Não";
    }

    public String verificaPagamento() {
        if (this.viewLocacao.getJrbCheque().isSelected()) {
            return this.viewLocacao.getJrbCheque().getText();
        }

        if (this.viewLocacao.getJrbCredito().isSelected()) {
            return this.viewLocacao.getJrbCredito().getText();
        }

        if (this.viewLocacao.getJrbDebito().isSelected()) {
            return this.viewLocacao.getJrbDebito().getText();
        }
        return this.viewLocacao.getJrbDinheiro().getText();
    }

    public boolean validaSalvar() {
        if (this.viewLocacao.getJcbCliente().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Insira o cliente, campo obrigatório");
            return false;
        }

        if (this.viewLocacao.getJcbVendedor().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Insira o vendedor, campo obrigatório");
            return false;
        }

        DefaultTableModel modelo = (DefaultTableModel) this.viewLocacao.getTabelaLocacao().getModel();
        if (listaFilmeLocacao.size() == 0) {
            JOptionPane.showMessageDialog(null, "Insira pelo menos um filme");
            return false;
        }

        if (valorPago < valorTotal) {
            JOptionPane.showMessageDialog(null, "Valor pago insuficiente!");
            return false;
        }

        if (this.viewLocacao.getJbgGrupoRadio().getSelection() == null) {
            JOptionPane.showMessageDialog(null, "Insira a forma de pagamento!");
            return false;
        }

        return true;
    }

    public void acaoBotaoCancelar() {
        limparCampos();
        limparTabela();
        zerarVariaveis();
    }

    public void limparTabela() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewLocacao.getTabelaLocacao().getModel();
        modelo.setRowCount(0);
    }

    public void zerarVariaveis() {
        valorTotal = 0.0;
        valorPago = 0.0;
        troco = 0.0;
        listaFilmeLocacao.clear();
    }

    public void carregarComboCliente() {
        ClienteController controller = new ClienteController();
        listaClientes = controller.buscarTodos();
        this.viewLocacao.getJcbCliente().removeAllItems();
        this.viewLocacao.getJcbCliente().addItem("- Selecione Cliente -");
        for (Cliente cliente : listaClientes) {
            this.viewLocacao.getJcbCliente().addItem(cliente.getNome());
        }
    }

    public void carregarComboVendedor() {
        VendedorController controller = new VendedorController();
        listaVendedores = controller.buscarTodos();
        this.viewLocacao.getJcbVendedor().removeAllItems();
        this.viewLocacao.getJcbVendedor().addItem("- Selecione Vendedor -");
        for (Vendedor vendedor : listaVendedores) {
            this.viewLocacao.getJcbVendedor().addItem(vendedor.getNome());
        }
    }

    public void carregarComboFilme() {
        FilmeController controller = new FilmeController();
        listaFilmes = controller.buscarTodos();
        this.viewLocacao.getJcbFilme().removeAllItems();
        this.viewLocacao.getJcbFilme().addItem("- Selecione Filme -");
        for (Filme filme : listaFilmes) {
            this.viewLocacao.getJcbFilme().addItem(filme.getNome());
        }
    }

    public void incluirFilme() {
        if (this.viewLocacao.getJcbFilme().getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(null, "Selecione um filme");
        } else {
            DefaultTableModel modelo = (DefaultTableModel) this.viewLocacao.getTabelaLocacao().getModel();
            filme = listaFilmes.get(this.viewLocacao.getJcbFilme().getSelectedIndex() - 1);
            listaFilmeLocacao.add(filme);

            if (filme.isPromocao()) {
                valorTotal += filme.getValorPromocao();
            } else {
                valorTotal += filme.getValor();
            }
            String valorFormatado = new DecimalFormat("R$#,##0.00").format(valorTotal);
            this.viewLocacao.getJtfValorTotal().setText(valorFormatado);
            modelo.addRow(new String[]{filme.getCodigo() + "", filme.getNome(), "R$" + filme.getValor(), (filme.isPromocao()) ? "SIM" : "NÃO", "R$" + filme.getValorPromocao()});
        }
    }

    public void excluirFilme() {
        DefaultTableModel modelo = (DefaultTableModel) this.viewLocacao.getTabelaLocacao().getModel();
        if (this.viewLocacao.getTabelaLocacao().getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(null, "É necessário selecionar um filme");
        } else {
            filme = listaFilmeLocacao.get(this.viewLocacao.getTabelaLocacao().getSelectedRow());
            if (filme.isPromocao()) {
                valorTotal -= filme.getValorPromocao();
            } else {
                valorTotal -= filme.getValor();
            }
            String valorFormatado = new DecimalFormat("R$#,##0.00").format(valorTotal);
            this.viewLocacao.getJtfValorTotal().setText(valorFormatado);
            listaFilmeLocacao.remove(this.viewLocacao.getTabelaLocacao().getSelectedRow());
            modelo.removeRow(this.viewLocacao.getTabelaLocacao().getSelectedRow());
        }
    }

    public void limparCampos() {
        this.viewLocacao.getJtfValorTotal().setText(null);
        this.viewLocacao.getJtfValorPago().setText(null);
        this.viewLocacao.getJtfTroco().setText(null);
        this.viewLocacao.getJcbCliente().setSelectedIndex(0);
        this.viewLocacao.getJcbFilme().setSelectedIndex(0);
        this.viewLocacao.getJcbVendedor().setSelectedIndex(0);
        this.viewLocacao.getJbgGrupoRadio().clearSelection();
    }

    public void calculaTroco() {
        boolean erro = false;

        if (this.viewLocacao.getJtfValorPago().getText().equals("") || this.viewLocacao.getJtfValorPago().getText().equals("")) {
            this.viewLocacao.getJtfTroco().setText(null);
        } else {
            try {
                valorPago = Double.parseDouble(this.viewLocacao.getJtfValorPago().getText().replace(',', '.'));
            } catch (NumberFormatException e) {
                erro = true;
            }

            if (valorPago > valorTotal && !erro && valorTotal > 0) {
                troco = valorPago - valorTotal;
                String valorFormatado = new DecimalFormat("R$#,##0.00").format(valorPago - valorTotal);
                this.viewLocacao.getJtfTroco().setText(valorFormatado);
            } else if (!erro && valorTotal != 0) {
                this.viewLocacao.getJtfTroco().setText("Valor insuficiente");
            } else if (!erro) {
                this.viewLocacao.getJtfTroco().setText("R$0,00");
            } else {
                this.viewLocacao.getJtfTroco().setText("Erro!");
            }
        }
    }

}
