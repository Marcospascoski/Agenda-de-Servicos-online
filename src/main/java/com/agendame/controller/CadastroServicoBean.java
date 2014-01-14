/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.controller;

import com.agendame.model.Servico;
import com.agendame.service.CadastroServicoService;
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
@Named(value = "cadastroServicoBean")
@ViewScoped
public class CadastroServicoBean implements Serializable{

    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager em;
    @Inject
    private CadastroServicoService cadastroServicoService;
    
    private Servico servico;

    public CadastroServicoBean() {
        limpar();
    }
        
    public void Salvar(){
        this.cadastroServicoService.Salvar(servico);
        limpar();
        FacesUtil.addInfoMessage("Servico Salvo com Sucesso");
    }
    
    public boolean estaEditando(){
        return this.servico.getId() != null;
    }
    
    private void limpar(){
        this.servico = new Servico();
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }
      
}
