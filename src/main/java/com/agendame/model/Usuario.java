/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author Marcos-TSI
 */
@Entity// Entidade JPA
@Table(name = "usuario") //Tabela usuário
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String email;
    private String senha;
    private List<Grupo> grupos = new ArrayList<>();

    @Id //Chave primária
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //valor é gerado automaticamente
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @NotBlank //Não pode estar em Branco
    @Size(max = 50, min = 5) //Tamanho máximo de 50 e mínimo de 5 caracteres
    @Column(nullable = false, length = 50) //não pode ser nulo, aceita até 50 caracteres
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @NotBlank //Não pode estar em Branco
    @Email //Tem que ser um email válido
    @Size(max = 50, min = 10) //Tamanho máximo de 50 e mínimo de 10 caracteres
    @Column(nullable = false, length = 50) //não pode ser nulo, aceita até 50 caracteres
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotBlank //Não pode estar em branco
    @Size(max = 15, min = 6) //Tamanho máximo de 15 e mínimo de 6 caracteres
    @Column(nullable = false, length = 15) //não pode ser nulo, aceita até 15 caracteres
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "usuario_has_grupos", joinColumns = {
        @JoinColumn(name = "usuario_id")}, inverseJoinColumns = {
        @JoinColumn(name = "grupo_id")})
    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.id);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @PreRemove
    private void removerGrupos() {
        getGrupos().clear();
    }

}
