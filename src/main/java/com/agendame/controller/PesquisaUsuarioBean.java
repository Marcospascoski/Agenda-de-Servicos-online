/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.controller;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Marcos-TSI
 */
@Named(value = "pesquisaUsuariosBean")
@ViewScoped
public class PesquisaUsuarioBean {

    private final List<Integer> usuarioFiltrados;

    public PesquisaUsuarioBean() {
        usuarioFiltrados = new ArrayList<Integer>();
        for (int i = 0; i < 50; i++) {
            usuarioFiltrados.add(i);
        }
    }

    public List<Integer> getUsuarioFiltrados() {
        return usuarioFiltrados;
    }
    
}
