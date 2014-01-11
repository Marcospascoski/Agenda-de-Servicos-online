/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Marcos
 */
@Named(value = "pesquisaClienteBean")
@ViewScoped
public class PesquisaClienteBean implements Serializable{

    private static final long serialVersionUID= 1L;
    
    private final List<Integer> clientesFiltrados;

    public PesquisaClienteBean() {
        clientesFiltrados = new ArrayList<Integer>();
        for (int i = 0; i < 50; i++) {
            clientesFiltrados.add(i);
        }
    }

    public List<Integer> getClientesFiltrados() {
        return clientesFiltrados;
    }

}
