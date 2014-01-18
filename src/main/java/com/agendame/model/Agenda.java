/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Marcos-TSI
 */
@Entity
@Table(name = "agenda") // tabela agenda
public class Agenda implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String descrisao;
    private List<Horario> horarios = new ArrayList<>();
    private Usuario profissional;
    private Cliente cliente;

    @Id // Chave Primária da tabela
    @Column(name = "id_agenda")
    @GeneratedValue(strategy = GenerationType.IDENTITY)// valor é gerado automaticamente
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    @NotNull
    @Column(name = "descrisao_agenda")
    public String getDescrisao() {
        return descrisao;
    }

    public void setDescrisao(String descrisao) {
        this.descrisao = descrisao;
    }

    @NotNull //Não pode ser nulo
    //@Future // O campo deve ser uma data no futuro
    @OneToMany(mappedBy = "agenda", targetEntity = Horario.class, cascade = CascadeType.ALL)// relacionamento um para muitos(chave extrangeira fica na tabela "Endereco")
    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    //@NotNull //Não pode ser nulo
    @ManyToOne // relacionamento muitos para um (tabela possui muitos agendamentos, e cada agendamento possui um usuario)
    public Usuario getUsuario() {
        return profissional;
    }

    public void setUsuario(Usuario profissional) {
        this.profissional = profissional;
    }

    //@NotNull //Não pode ser nulo
    @ManyToOne // relacionamento muitos para um (tabela possui muitos agendamentos, e cada agendamento possui um cliente)
    @JoinColumn(name = "id_cliente") // Não pode ser nulo
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Agenda other = (Agenda) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

}
