/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.controller;

import com.agendame.model.Usuario;
import com.agendame.service.CadastroUsuarioService;
import com.agendame.util.jsf.FacesUtil;
import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;


/**
 *
 * @author Marcos-TSI
 */
@Named(value = "cadastroUsuarioBean")
@ViewScoped
public class CadastroUsuarioBean implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager em;
    @Inject
    private CadastroUsuarioService cadastroUsuarioService;
    
    private Usuario usuario;

    public CadastroUsuarioBean() {
        limpar();
    }
        
    public void Salvar(){
        this.cadastroUsuarioService.Salvar(usuario);
        limpar();
        FacesUtil.addInfoMessage("Usuario Salvo com Sucesso");
    }
    
    private void limpar(){
        this.usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
      
}
