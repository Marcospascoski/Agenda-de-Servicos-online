/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    private Servico servico;
    private Horario horario;
    private Cliente cliente;
    private Usuario Usuario;

    @Id // Chave Primária da tabela
    @Column(name = "id_agenda")
    @GeneratedValue(strategy = GenerationType.IDENTITY)// valor é gerado automaticamente
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull // Não pode ser nulo
    @ManyToOne // relacionamento muitos para um(tabela possui muitos agendamentos, e cada agendamento possui somente um serviço)
    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    @NotNull //Não pode ser nulo
    @Future // O campo deve ser uma data no futuro
    @ManyToOne // relacionamento muitos para um(tabela possui muitos agendamentos, e cada agendamento possui um horário)
    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    @NotNull //Não pode ser nulo
    @ManyToOne // relacionamento muitos para um (tabela possui muitos agendamentos, e cada agendamento possui um cliente)
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @NotNull //Não pode ser nulo
    @ManyToOne // relacionamento muitos para um (tabela possui muitos agendamentos, e cada agendamento possui um usuario)
    public Usuario getUsuario() {
        return Usuario;
    }

    public void setUsuario(Usuario Usuario) {
        this.Usuario = Usuario;
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
