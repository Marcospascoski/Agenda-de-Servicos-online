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
 * @author Marcos-TSI
 */
@Named(value = "pesquisaServicoBean")
@ViewScoped
public class PesquisaServicoBean implements Serializable{

    private static final long serialVersionUID= 1L;
    
    private final List<Integer> servicosFiltrados;

    public PesquisaServicoBean() {
        servicosFiltrados = new ArrayList<Integer>();
        for (int i = 0; i < 50; i++) {
            servicosFiltrados.add(i);
        }
    }

    public List<Integer> getServicosFiltrados() {
        return servicosFiltrados;
    }

}
