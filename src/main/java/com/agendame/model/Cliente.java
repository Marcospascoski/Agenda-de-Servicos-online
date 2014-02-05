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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;


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
    private Date dataNascimento;
    private String docReceitaFederal;
    private String telefone;
    private List<Endereco> enderecos = new ArrayList<>();
    private TipoPessoa tipo;
    private List<Agenda> agendamentos = new ArrayList<>();

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
    @Size(max = 80, min = 4) //Tamanho máximo de 80 caracteres
    @Column(nullable = false, length = 80)// não pode ser nulo, aceita até 80 caracteres
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @NotNull
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, name = "data_nascimento")
    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @NotBlank
    @Size(max = 18, min = 11) //Tamanho Máximo de 18 caracteres
    @Column(nullable = false, length = 18)// não pode ser nulo, aceita até 18 caracteres
    public String getDocReceitaFederal() {
        return docReceitaFederal;
    }

    public void setDocReceitaFederal(String docReceitaFederal) {
        this.docReceitaFederal = docReceitaFederal;
    }

    @NotNull //Não pode ser nulo
    @Size(max = 13, min = 8)
    @Column(nullable = false, length = 11)// não pode ser nulo, aceita até 11 caracteres
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @NotNull //Não pode ser nulo
    @OneToMany(mappedBy = "cliente", targetEntity = Endereco.class, cascade = CascadeType.ALL)// relacionamento um para muitos(chave extrangeira fica na tabela "Endereco")
    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    @NotNull //Não pode ser nulo
    @Enumerated(EnumType.STRING)//salva a String da classe TipoPessoa na coluna tipo
    @Column(name = "tipo", nullable = false)//não pode ser nulo,
    public TipoPessoa getTipo() {
        return tipo;
    }

    public void setTipo(TipoPessoa tipo) {
        this.tipo = tipo;
    }

    @NotNull
    @OneToMany(mappedBy = "cliente", targetEntity = Agenda.class, cascade = CascadeType.ALL)// relacionamento um para muitos(chave extrangeira fica na tabela "Agenda")
    public List<Agenda> getAgendamentos() {
        return agendamentos;
    }

    public void setAgendamentos(List<Agenda> agendamentos) {
        this.agendamentos = agendamentos;
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
