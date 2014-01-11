/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenerationTime;

/**
 *
 * @author Marcos
 */
@Entity
@Table(name = "agenda") // tabela agendamento
public class Agenda implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Date data;
    private Date dataReserva;
    private Servico servico;
    private Horario horario;
    private Pessoa pessoa;

    @Id // Chave Primária da tabela
    @Column(name = "id_agenda")
    @GeneratedValue(strategy = GenerationType.IDENTITY)// valor é gerado automaticamente
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false) // não pode ser nulo
    @Temporal(TemporalType.DATE) // guarda dados do tipo Data
    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    @Temporal(TemporalType.DATE) // guarda dados do tipo data
    @Column(name = "data_reserva", nullable = false) //coluna data_reserva não pode ser nulo
    public Date getDataReserva() {
        return dataReserva;
    }

    public void setDataReserva(Date dataReserva) {
        this.dataReserva = dataReserva;
    }

    @ManyToOne // relacionamento muitos para um(tabela possui muitos agendamentos, e cada agendamento possui somente um serviço)
    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    @ManyToOne // relacionamento muitos para um(tabela possui muitos agendamentos, e cada agendamento possui um horário)
    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    @ManyToOne // relacionamento muitos para um (tabela possui muitos agendamentos, e cada agendamento possui um cliente)
    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
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
