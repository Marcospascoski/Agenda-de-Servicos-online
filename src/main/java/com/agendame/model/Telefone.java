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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Marcos-TSI
 */
@Entity //Entidade JPA
@Table(name = "telefone") // tabela telefone
public class Telefone implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String numero;
    private Cliente cliente;

    @Id //Chave primária
    @Column(name = "id_telefone")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //valor é gerado automaticamente
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank //Não pode estar em branco
    @Size(max = 16, min = 10)//Tamanho máximo de 16 e mínimo de 10 caracteres
    @Column(nullable = false, length = 16) //não pode ser nulo, aceita até 16 caracteres
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @NotNull //Não pode ser nulo
    @OneToOne(mappedBy = "telefone") //relacionamento um para um( a chave extrangeira ficara somente na tabela Cliente)
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final Telefone other = (Telefone) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
