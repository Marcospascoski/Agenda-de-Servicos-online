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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    private Date dataInicio;
    private Date dataFim;
    private String observacao;
    private boolean diaTodo;
    private Servico servico;
    private Usuario profissional;
    private Cliente cliente;

    @Id // Chave Primária da tabela
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)// valor é gerado automaticamente
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    @Column(name = "descrisao_agenda")
    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Column(name = "diaTodo_agenda", nullable = false)
    public boolean isDiaTodo() {
        return diaTodo;
    }

    public void setDiaTodo(boolean diaTodo) {
        this.diaTodo = diaTodo;
    }

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dataInicio_agenda")
    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "dataFim_agenda")
    public Date getDataFim() {
        return dataFim;
    }

    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    //@NotNull //Não pode ser nulo
    @ManyToOne
    @JoinColumn(name = "servico_id")
    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    @ManyToOne // relacionamento muitos para um (tabela possui muitos agendamentos, e cada agendamento possui um usuario)
    @JoinColumn(name = "usuario_id")
    public Usuario getProfissional() {
        return profissional;
    }

    public void setProfissional(Usuario profissional) {
        this.profissional = profissional;
    }

    @ManyToOne // relacionamento muitos para um (tabela possui muitos agendamentos, e cada agendamento possui um cliente)
    @JoinColumn(name = "cliente_id") // Não pode ser nulo
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
