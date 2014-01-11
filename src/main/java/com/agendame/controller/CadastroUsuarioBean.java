/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.controller;

import com.agendame.model.Usuario;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author Marcos-TSI
 */
@Named(value = "cadastroUsuarioBean")
@ViewScoped
public class CadastroUsuarioBean implements Serializable{

    private static final long serialVersionUID = 1L;
    
    private Usuario usuario;

    public CadastroUsuarioBean() {
        this.usuario = new Usuario();
    }
    
    public void Salvar(){
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
