/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Marcos
 */
@Entity //Entidade JPA
@Table(name = "pessoa") //tabela pessoa
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private String sobrenome;
    private String rg;
    private String cpf;
    private Date dataNascimento;
    private String sexo;
    private Telefone telefone;
    private List<Endereco> enderecos = new ArrayList<>();
    private TipoPessoa tipo;
    private List<Agenda> agenda;
    private Usuario usuario;

    @Id //Chave primária
    @Column(name = "id_pessoa")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //valor é gerado automaticamente
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, length = 20)// não pode ser nulo, aceita até 20 caracteres
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Column(nullable = false, length = 50)//Não pode ser nulo, aceita até 50 caracteres
    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    @Column(nullable = false, length = 10)// não pode ser nulo, aceita até 10 caracteres
    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    @Column(nullable = false, length = 14)// não pode ser nulo, aceita até 14 caracteres
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Column(nullable = false, name = "data_nascimento")// coluna data_nascimento, não pode ser nulo
    @Temporal(TemporalType.DATE)// guarda dados do tipo Data
    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Column(nullable = false, length = 9)// não pode ser nulo, aceita até 9 caracteres
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)// relacionamento um para muitos
    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @OneToOne(cascade = CascadeType.ALL)//relacionamento um para um
    @JoinColumn(nullable = false)//não pode ser nulo
    @PrimaryKeyJoinColumn//chave primária da tabela telefone é igual chave extrangeira que está na tebela pessoa
    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    @Enumerated(EnumType.STRING)//salva a String da classe TipoPessoa na coluna tipo
    @Column(name = "tipo", nullable = false, length = 10)//não pode ser nulo, aceita até 10 caracteres
    public TipoPessoa getTipo() {
        return tipo;
    }

    public void setTipo(TipoPessoa tipo) {
        this.tipo = tipo;
    }

    @OneToOne(cascade = CascadeType.ALL)//relacionamento um para um
    @JoinColumn(nullable = false)//não pode ser nulo
    @PrimaryKeyJoinColumn//chave primária de usuário é igual a chave extrangeira que está na tabela pessoa
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL)//relacionamento um para muitos
    public List<Agenda> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Agenda> agenda) {
        this.agenda = agenda;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        final Pessoa other = (Pessoa) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
