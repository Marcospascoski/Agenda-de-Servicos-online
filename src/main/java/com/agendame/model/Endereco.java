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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Marcos-TSI
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
    private Cliente cliente;

    @Id //Chave primária
    @Column(name = "id_horario")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //valor é gerado automaticamente
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank //Não pode estar em branco
    @Size(max = 100) // Tamanho Máximo de 100 caracteres
    @Column(nullable = false, length = 100) //não pode ser nulo, aceita até 100 caracteres
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    @NotBlank //Não pode estar em branco
    @Size(max = 7) //Tamanho Máximo 7 caracteres
    @Column(nullable = false, length = 7) // não pode ser nulo, aceita até 7 caracteres
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Size(max = 60) //Tamanho Máximo de 60 caracteres
    @Column(length = 60) // aceita nulo, pode ter até 60 caracteres
    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    @NotBlank //Não pode estar em branco
    @Size(max = 45) // Tamanho máximo de 45 caracteres
    @Column(nullable = false, length = 45) // não pode ser nulo, aceita até 45 caracteres
    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    @NotBlank //Não pode estar em branco
    @Size(max = 40) // Tamanho Máximo de 40 caracteres
    @Column(nullable = false, length = 40) // não pode ser nulo, aceita até 25 caracteres
    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    @NotBlank //Não pode estar em branco
    @Size(max = 2) //Tamanho máximo de 2 caracteres
    @Column(nullable = false, length = 2) // não pode ser nulo, aceita até 25 caracteres
    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @NotBlank //Não pode estar em branco
    @Size(max = 9) //Tamanho máximo de 9 caracteres
    @Column(nullable = false, length = 9) // não pode ser nulo, aceita até 9 caracteres
    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    @NotNull //Não pode ser nulo
    @ManyToOne // relacionamento muitos para um( tabela possui vários endereços, e cada endereço pertence a um cliente)
    @JoinColumn(name="id_cliente", nullable = false)
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
