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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Marcos
 */
@Entity // Entidade JPA
@Table(name = "servico") //tabela 'servico'
public class Servico implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private List<Agenda> agenda = new ArrayList<>();

    @Id //Chave primária
    @Column(name = "id_servico")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //valor é gerado automaticamente
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(nullable = false, length = 45) // não pode ser nulo, aceita até 45 caracteres
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @OneToMany(mappedBy = "servico") // relacionamento um para muitos, cada serviço possui muitos agendamentos
    public List<Agenda> getAgenda() {
        return agenda;
    }

    public void setAgendamentos(List<Agenda> agenda) {
        this.agenda = agenda;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
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
        final Servico other = (Servico) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
