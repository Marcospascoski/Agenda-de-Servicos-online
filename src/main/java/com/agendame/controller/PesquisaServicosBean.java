/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.controller;

import com.agendame.model.Servico;
import com.agendame.repository.Servicos;
import com.agendame.repository.filter.ServicoFilter;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Marcos-TSI
 */
@Named(value = "pesquisaServicoBean")
@ViewScoped
public class PesquisaServicosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Servicos servicos;

    private ServicoFilter servicoFilter;

    private List<Servico> servicoFiltrados;

    public PesquisaServicosBean() {
        servicoFilter = new ServicoFilter();
    }

    public void pesquisar() {
        servicoFiltrados = servicos.filtrados(servicoFilter);
    }

    public List<Servico> getServicoFiltrados() {
        return servicoFiltrados;
    }

    public ServicoFilter getServicoFilter() {
        return servicoFilter;
    }

}
