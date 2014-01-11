/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.model;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Marcos
 */
@Entity // Entidade JPA
@Table(name = "endereco") //Tabela endereco
public class Endereco implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;
    private String cep;
    private Pessoa pessoa;

    @Id //Chave primária
    @Column(name = "id_horario")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //valor é gerado automaticamente
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, length = 100) //não pode ser nulo, aceita até 100 caracteres
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @Column(nullable = false, length = 7) // não pode ser nulo, aceita até 7 caracteres
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Column(length = 60) // aceita nulo, pode ter até 60 caracteres
    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @Column(nullable = false, length = 45) // não pode ser nulo, aceita até 45 caracteres
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @Column(nullable = false, length = 25) // não pode ser nulo, aceita até 25 caracteres
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @Column(nullable = false, length = 25) // não pode ser nulo, aceita até 25 caracteres
    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Column(nullable = false, length = 9) // não pode ser nulo, aceita até 9 caracteres
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @ManyToOne // relacionamento muitos para um( tabela possui vários endereços, e cada endereço pertence a uma pessoa)
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Endereco other = (Endereco) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
