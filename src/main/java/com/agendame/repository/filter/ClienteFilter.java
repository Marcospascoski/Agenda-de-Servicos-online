/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.repository.filter;

import java.io.Serializable;

/**
 *
 * @author Marcos-TSI
 */
public class ClienteFilter implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
