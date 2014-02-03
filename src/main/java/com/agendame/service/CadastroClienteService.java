/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.service;

import com.agendame.model.Cliente;
import com.agendame.repository.Clientes;
import java.io.Serializable;
import javax.inject.Inject;



/**
 *
 * @author Marcos-TSI
 */

public class CadastroClienteService implements Serializable{

    private static final long serialVersionUID= 1L; 
    
    @Inject
    private Clientes clientes;
    
    public Cliente salvar(Cliente cliente) {
        return clientes.guardar(cliente);
    }
    
}
