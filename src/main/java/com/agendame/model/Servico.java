/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Marcos-TSI
 */
@Named(value = "servico")
@ViewScoped
public class Servico implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private long id;
    private List<String> nome = new ArrayList<String>();
    /**
     * Creates a new instance of Servico
     */
    public Servico() {
        nome = new ArrayList<String>();
        nome.add("Formatação");
        nome.add("Remoção de virus");
        nome.add("Instalar todos Driver do pc");
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<String> getNome() {
        return nome;
    }

    public void setNome(List<String> nome) {
        this.nome = nome;
    }

    public List<String> buscarTodasServicos() {
        return nome;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + (int) (this.id ^ (this.id >>> 32));
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
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
}
