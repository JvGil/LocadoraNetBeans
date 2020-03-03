package br.com.foursys.locadora.model;

/**
 * Classe responsável por indicar o que é um Estado
 * 
 * @author jgil
 * @since 28/02/2020
 * @version 0.1
 *
 */

public class Estado {
	private String nome;
	private String uf;
	
	public Estado() {
	}
	public Estado(String nome, String uf) {
		this.nome = nome;
		this.uf = uf;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	
	@Override
		public String toString() {
			return uf + " - " + nome;
		}
}
