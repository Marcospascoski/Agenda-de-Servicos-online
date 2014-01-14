/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.controller;

import com.agendame.model.Cliente;
import com.agendame.service.CadastroClienteService;
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
@Named(value = "cadastroClienteBean")
@ViewScoped
public class CadastroClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;
    @Inject
    private CadastroClienteService cadastroClienteService;

    private Cliente cliente;

    public CadastroClienteBean() {
        limpar();
    }

    public void Salvar() {
        this.cadastroClienteService.Salvar(cliente);
        limpar();
        FacesUtil.addInfoMessage("Cliente Salvo com Sucesso");
    }

    public boolean estaEditando() {
        return this.cliente.getId() != null;
    }

    private void limpar() {
        this.cliente = new Cliente();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
