/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.controller;

import com.agendame.model.Usuario;
import com.agendame.repository.Usuarios;
import com.agendame.repository.filter.UsuarioFilter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Marcos-TSI
 */
@Named(value = "pesquisaUsuariosBean")
@ViewScoped
public class PesquisaUsuariosBean implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Inject
    private Usuarios usuarios;
    
    private UsuarioFilter usuarioFilter;
    
    private List<Usuario> usuarioFiltrados;

    public PesquisaUsuariosBean(){
        usuarioFilter = new UsuarioFilter();
    }
    
    public void  pesquisar(){
        usuarioFiltrados = usuarios.filtrados(usuarioFilter);
    }
    
    public List<Usuario> getUsuarioFiltrados() {
        return usuarioFiltrados;
    }

    public UsuarioFilter getUsuarioFilter() {
        return usuarioFilter;
    }
    
}
