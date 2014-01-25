/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.controller;

import com.agendame.model.Grupo;
import com.agendame.model.Usuario;
import com.agendame.repository.Grupos;
import com.agendame.service.CadastroUsuarioService;
import com.agendame.util.jsf.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Marcos-TSI
 */
@Named(value = "cadastroUsuarioBean")
@ViewScoped
public class CadastroUsuarioBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private CadastroUsuarioService cadastroUsuarioService;

    @Inject
    private Grupos grupos;

    private Usuario usuario;
    private Grupo grupoPai;

    private List<Grupo> gruposRaizes;

    public CadastroUsuarioBean() {
        limpar();
    }

    public void salvar() {
        this.cadastroUsuarioService.salvar(usuario);
        limpar();
        FacesUtil.addInfoMessage("Usuario Salvo com Sucesso");
    }

    public void inicializar() {
        if (FacesUtil.isNotPostback()) {
            gruposRaizes = grupos.raizes();
        }
    }

    public boolean estaEditando() {
        return this.usuario.getId() != null;
    }

    private void limpar() {
        this.usuario = new Usuario();
        gruposRaizes = new ArrayList<>();
    }

    @NotNull
    public Grupo getGrupoPai() {
        return grupoPai;
    }

    public void setGrupoPai(Grupo grupoPai) {
        this.grupoPai = grupoPai;
    }

    public List<Grupo> getGruposRaizes() {
        return gruposRaizes;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;

    }

}
