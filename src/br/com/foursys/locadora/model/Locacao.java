package br.com.foursys.locadora.model;

import java.util.List;

/**
 * Classe modelo para armazenar atributos de Locacao
 *
 * @author jgil
 * @since 05/03/2020
 * @version 0.1
 *
 */
public class Locacao {

    private Cliente cliente;
    private Vendedor vendedor;
    private List<Filme> listaFilmes;
    private Double valorTotal;
    private Double valorPago;
    private Double troco;
    private String formaPagamento;

    public Locacao() {

    }

    public Locacao(Cliente cliente, Vendedor vendedor, List<Filme> listaFilmes, Double valorTotal, Double valorPago, Double troco, String formaPagamento) {
        this.cliente = cliente;
        this.vendedor = vendedor;
        this.listaFilmes = listaFilmes;
        this.valorTotal = valorTotal;
        this.valorPago = valorPago;
        this.troco = troco;
        this.formaPagamento = formaPagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public List<Filme> getListaFilmes() {
        return listaFilmes;
    }

    public void setListaFilmes(List<Filme> listaFilmes) {
        this.listaFilmes = listaFilmes;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Double getValorPago() {
        return valorPago;
    }

    public void setValorPago(Double valorPago) {
        this.valorPago = valorPago;
    }

    public Double getTroco() {
        return troco;
    }

    public void setTroco(Double troco) {
        this.troco = troco;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
}
