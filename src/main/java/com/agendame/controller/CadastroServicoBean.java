/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.controller;

import com.agendame.model.Agenda;
import com.agendame.model.Servico;
import com.agendame.repository.Agendas;
import com.agendame.repository.Servicos;
import com.agendame.repository.filter.ServicoFilter;
import com.agendame.service.CadastroServicoService;
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
@Named(value = "cadastroServicoBean")
@ViewScoped
public class CadastroServicoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CadastroServicoService cadastroServicoService;

    @Inject
    private Servicos servicos;

    private Servico servico;

    private Servico servicoSelecionado;

    private List<Servico> servicosRaizes;

    public CadastroServicoBean() {
        limpar();
    }

    public void inicializar() {
        if (FacesUtil.isNotPostback()) {
            servicosRaizes = servicos.raizes();
        }
    }

    public void salvar() {
        this.cadastroServicoService.salvar(this.servico);

        limpar();
        FacesUtil.addInfoMessage("Serviço Salvo com Sucesso");
    }

    public boolean isEditando() {
        return this.servico.getId() != null;
    }

    private void limpar() {
        this.servico = new Servico();
    }

    public List<Servico> getServicosRaizes() {
        return servicosRaizes;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public CadastroServicoService getCadastroServicoService() {
        return cadastroServicoService;
    }

    public void setCadastroServicoService(CadastroServicoService cadastroServicoService) {
        this.cadastroServicoService = cadastroServicoService;
    }

    public Servico getServicoSelecionado() {
        return servicoSelecionado;
    }

    public void setServicoSelecionado(Servico servicoSelecionado) {
        this.servicoSelecionado = servicoSelecionado;
    }

}
