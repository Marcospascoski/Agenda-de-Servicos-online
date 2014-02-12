/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.controller;

import com.agendame.model.Servico;
import com.agendame.repository.Servicos;
import com.agendame.repository.filter.ServicoFilter;
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
@Named(value = "pesquisaServicosBean")
@ViewScoped
public class PesquisaServicosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Servicos servicos;

    private ServicoFilter filtro;

    private List<Servico> servicoFiltrados;

    private Servico servicoSelecionado;

    public PesquisaServicosBean() {
        filtro = new ServicoFilter();
        servicoFiltrados = new ArrayList<>();
    }

    public void excluir() {
        servicos.remover(servicoSelecionado);
        servicoFiltrados.remove(servicoSelecionado);

        FacesUtil.addInfoMessage("Servico " + servicoSelecionado.getNome()
                + " excluído com sucesso.");
    }

    public void pesquisar() {
        servicoFiltrados = servicos.filtrados(filtro);
    }

    public List<Servico> getServicoFiltrados() {
        return servicoFiltrados;
    }

    public ServicoFilter getFiltro() {
        return filtro;
    }

    public Servico getServicoSelecionado() {
        return servicoSelecionado;
    }

    public void setServicoSelecionado(Servico servicoSelecionado) {
        this.servicoSelecionado = servicoSelecionado;
    }

}
