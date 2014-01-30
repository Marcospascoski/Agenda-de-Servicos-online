/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.controller;

import com.agendame.model.Usuario;
import com.agendame.repository.Usuarios;
import com.agendame.repository.filter.UsuarioFilter;
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
@Named(value = "pesquisaUsuariosBean")
@ViewScoped
public class PesquisaUsuariosBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Usuarios usuarios;

    private UsuarioFilter filtro;

    private List<Usuario> usuarioFiltrados;

    private Usuario usuarioSelecionado;

    public PesquisaUsuariosBean() {
        filtro = new UsuarioFilter();
        usuarioFiltrados = new ArrayList<>();
    }

    public void excluir() {
        usuarios.remover(usuarioSelecionado);
        usuarioFiltrados.remove(usuarioSelecionado);

        FacesUtil.addInfoMessage("Usuario " + usuarioSelecionado.getNome()
                + " excluído com sucesso.");
    }

    public void pesquisar() {
        usuarioFiltrados = usuarios.filtrados(filtro);
    }

    public List<Usuario> getUsuarioFiltrados() {
        return usuarioFiltrados;
    }

    public UsuarioFilter getFiltro() {
        return filtro;
    }

    public Usuario getUsuarioSelecionado() {
        return usuarioSelecionado;
    }

    public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
        this.usuarioSelecionado = usuarioSelecionado;
    }

}
