/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.controller;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;


/**
 *
 * @author Marcos-TSI
 */
@Named(value = "cadastroUsuarioBean")
@RequestScoped
public class CadastroUsuarioBean {

    public void Salvar(){
        throw new RuntimeException("Teste Exceção");
    }
}
