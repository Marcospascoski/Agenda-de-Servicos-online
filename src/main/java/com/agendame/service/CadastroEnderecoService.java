/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.service;

import com.agendame.model.Endereco;
import com.agendame.repository.Enderecos;
import com.agendame.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;


/**
 *
 * @author Marcos-TSI
 */

public class CadastroEnderecoService implements Serializable{

    private static final long serialVersionUID= 1L; 
    
    @Inject
    private Enderecos enderecos;
    
    public Endereco salvar(Endereco endereco){
        return enderecos.guardar(endereco);
    }
    
}
