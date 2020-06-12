package br.com.foursys.locadora.model;

/**
 * Classe modelo para armazenar atributos de Cliente
 *
 * @author jgil
 * @since 28/02/2020
 * @version 0.1
 *
 */
public class Cliente {

    private String nome;
    private String logradouro;
    private int numLogradouro;
    private String bairro;
    private Cidade cidade;
    private Estado estado;
    private String telefone;
    private String cpf;
    private String rg;
    private char sexo;
    private String dataNasc;
    private int idade;

    public Cliente() {
    }

    public Cliente(String nome, String logradouro, int numLogradouro, String bairro, Cidade cidade, Estado estado,
            String telefone, String cpf, String rg, char sexo, String dataNasc, int idade) {
        this.nome = nome;
        this.logradouro = logradouro;
        this.numLogradouro = numLogradouro;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.telefone = telefone;
        this.cpf = cpf;
        this.rg = rg;
        this.sexo = sexo;
        this.dataNasc = dataNasc;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumLogradouro() {
        return numLogradouro;
    }

    public void setNumLogradouro(int numLogradouro) {
        this.numLogradouro = numLogradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(String dataNasc) {
        this.dataNasc = dataNasc;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

}
