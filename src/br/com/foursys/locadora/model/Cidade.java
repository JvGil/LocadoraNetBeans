package br.com.foursys.locadora.model;

/**
 * Classe modelo para armazenar atributos de Cidade
 *
 * @author jgil
 * @since 28/02/2020
 * @version 0.1
 *
 */
public class Cidade {

    private String nome;

    public Cidade() {
    }

    public Cidade(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
