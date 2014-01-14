/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.controller;

import com.agendame.model.Cliente;
import com.agendame.repository.Clientes;
import com.agendame.repository.filter.ClienteFilter;
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
@Named(value = "pesquisaClienteBean")
@ViewScoped
public class PesquisaClientesBean implements Serializable{

    private static final long serialVersionUID= 1L;
    
    @Inject
    private Clientes clientes;
    
    private ClienteFilter clienteFilter;
    
    private List<Cliente> clienteFiltrados;

    public PesquisaClientesBean(){
        clienteFilter = new ClienteFilter();
    }
    
    public void  pesquisar(){
        clienteFiltrados = clientes.filtrados(clienteFilter);
    }
    
    public List<Cliente> getClienteFiltrados() {
        return clienteFiltrados;
    }

    public ClienteFilter getClienteFilter() {
        return clienteFilter;
    }

}
