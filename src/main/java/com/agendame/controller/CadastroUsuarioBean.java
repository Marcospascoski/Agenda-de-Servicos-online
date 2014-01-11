/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.controller;

import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
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
    
    public void Salvar(){
        throw new RuntimeException("Teste Exceção");
    }
}
