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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author Marcos-TSI
 */
@Entity //Entidade JPA
@Table(name = "cliente") //tabela cliente
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
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
    @Column(name = "id_cliente")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //valor é gerado automaticamente
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotBlank // Não pode estar em branco
    @Size(max = 50) //Tamanho máximo de 50 caracteres
    @Column(nullable = false, length = 50)// não pode ser nulo, aceita até 50 caracteres
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @NotBlank //Não pode estar em branco
    @Min(6) //Mínimo de 6 caracteres
    @Size(max = 10) //Tamanho máximo de 10 caracteres
    @Column(nullable = false, length = 10)// não pode ser nulo, aceita até 10 caracteres
    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    @NotBlank //Não pode estar em branco
    @CPF //Deve ser um CPF Válido
    @Size(max = 14) //Tamanho Máximo de 14 caracteres
    @Column(nullable = false, length = 14)// não pode ser nulo, aceita até 14 caracteres
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @NotNull //Não pode ser nulo
    @Past //Deve ser uma data no passado
    @Column(nullable = false, name = "data_nascimento")// coluna data_nascimento, não pode ser nulo
    @Temporal(TemporalType.DATE)// guarda dados do tipo Data
    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @NotNull //Não pode ser nulo
    @Size(max = 9, min = 8) //Tamanho máximo de 9 e no minimo 8 caracteres
    @Column(nullable = false, length = 9)// não pode ser nulo, aceita até 9 caracteres
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @NotNull //Não pode ser nulo
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)// relacionamento um para muitos(chave extrangeira fica na tabela "Endereco")
    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @NotNull //Não pode ser nulo
    @OneToOne //Relacionamento um para um
    @JoinColumn(nullable = false)//não pode ser nulo
    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    @NotNull //Não pode ser nulo
    @Enumerated(EnumType.STRING)//salva a String da classe TipoPessoa na coluna tipo
    @Column(name = "tipo", nullable = false, length = 10)//não pode ser nulo, aceita até 10 caracteres
    public TipoPessoa getTipo() {
        return tipo;
    }

    public void setTipo(TipoPessoa tipo) {
        this.tipo = tipo;
    }

    @NotNull //Não pode ser nulo
    @OneToOne//relacionamento um para um
    @JoinColumn(nullable = false)//não pode ser nulo
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @NotNull //Não pode ser nulo
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)//relacionamento um para muitos(Chave extrangeira fica na tabela "Agenda")
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
