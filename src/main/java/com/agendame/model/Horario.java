/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.model;

import java.io.Serializable;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
    private Time horaInicial;
    private Time horaFinal;
    private List<Agenda> agenda = new ArrayList<>();

    @Id //Chave primÃ¡ria
    @Column(name = "id_horario")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //valor Ã© gerado automaticamente
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull //Não pode ser nulo
    @Column(nullable = false, name = "hora_inicial") // coluna hora_inicial, nÃ£o pode ser nulo
    public Time getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(Time horaInicial) {
        this.horaInicial = horaInicial;
    }

    @NotNull //Não pode ser nulo
    @Column(nullable = false, name = "hora_final") // coluna hora_final, nÃ£o pode ser nulo
    public Time getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(Time horaFinal) {
        this.horaFinal = horaFinal;
    }

    @NotNull //Não pode ser nulo
    @OneToMany(mappedBy = "horario") // relacionamentos um para muitos, cada horÃ¡rio pode ter muitos agendamentos
    public List<Agenda> getAgenda() {
        return agenda;
    }

    public void setAgenda(List<Agenda> agenda) {
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
