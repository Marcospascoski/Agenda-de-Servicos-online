/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.model;

/**
 *
 * @author Marcos
 */
public enum TipoPessoa {

    FISICA("Física"), JURIDICA("Jurídica");

    private String descricao;
    

    TipoPessoa(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
}
}
