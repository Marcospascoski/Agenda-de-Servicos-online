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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Marcos-TSI
 */
@Entity
@Table(name = "horario")
public class Horario implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Date horaInicial;
    private Date horaFinal;
    private Agenda agenda;
    
    @Id //Chave primaria
    @Column(name = "id_horario")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //valor é gerado automaticamente
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull //Não pode ser nulo
    @Temporal(TemporalType.TIME)
    @Column(nullable = false, name = "hora_inicial") // coluna hora_inicial, não pode ser nulo
    public Date getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(Date horaInicial) {
        this.horaInicial = horaInicial;
    }
    

    @NotNull //Não pode ser nulo
    @Temporal(TemporalType.TIME)
    @Column(nullable = false, name = "hora_final") // coluna hora_final, não pode ser nulo
    public Date getHoraFinal() {
        return horaFinal;
    }


    public void setHoraFinal(Date horaFinal) {
        this.horaFinal = horaFinal;
    }

    @NotNull //Não pode ser nulo
    @ManyToOne
    @JoinColumn(name = "id_agenda")
    public Agenda getAgenda() {
        return agenda;
    }

    public void setAgenda(Agenda agenda) {
        this.agenda = agenda;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Horario other = (Horario) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
