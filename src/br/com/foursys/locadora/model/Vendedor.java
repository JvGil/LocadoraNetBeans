package br.com.foursys.locadora.model;

/**
 * Classe responsável por indicar o que é um Vendedor
 * 
 * @author jgil
 * @since 28/02/2020
 * @version 0.1
 *
 */

public class Vendedor {
	private String nome;
	private String areaVenda;
	private Cidade cidade;
	private Estado estado;
	private char sexo;
	private int idade;
	private double salario;
	
	public Vendedor() {
	}

	public Vendedor(String nome, String areaVenda, Cidade cidade, Estado estado, char sexo, int idade, double salario) {
		this.nome = nome;
		this.areaVenda = areaVenda;
		this.cidade = cidade;
		this.estado = estado;
		this.sexo = sexo;
		this.idade = idade;
		this.salario = salario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAreaVenda() {
		return areaVenda;
	}

	public void setAreaVenda(String areaVenda) {
		this.areaVenda = areaVenda;
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

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}	
}
