/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.controller;

import com.agendame.model.Cliente;
import com.agendame.repository.Clientes;
import com.agendame.repository.filter.ClienteFilter;
import com.agendame.util.jsf.FacesUtil;
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
@Named(value = "pesquisaClientesBean")
@ViewScoped
public class PesquisaClientesBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Clientes clientes;

    private ClienteFilter filtro;

    private List<Cliente> clienteFiltrados;
    
    private Cliente clienteSelecionado;

    public PesquisaClientesBean() {
        filtro = new ClienteFilter();
        clienteFiltrados = new ArrayList<>();
    }

    public void excluir() {
            clientes.remover(clienteSelecionado);
            clienteFiltrados.remove(clienteSelecionado);

            FacesUtil.addInfoMessage("Cliente " + clienteSelecionado.getNome()
                    + " excluído com sucesso.");
        }

    public void pesquisar() {
        clienteFiltrados = clientes.filtrados(filtro);
    }

    public List<Cliente> getClienteFiltrados() {
        return clienteFiltrados;
    }

    public ClienteFilter getFiltro() {
        return filtro;
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

}
