/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.service;

import com.agendame.model.Cliente;
import com.agendame.repository.Clientes;
import com.agendame.util.jpa.Transactional;
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
    
    @Transactional
    public Cliente Salvar(Cliente cliente) {
        Cliente clienteExiste = clientes.porId(cliente.getId());

        if (clienteExiste != null && !clienteExiste.equals(cliente)) {
            throw new NegocioException("Ja existe um cliente com o Codigo Informado");
        }

        return clientes.guardar(cliente);
    }
    
}
