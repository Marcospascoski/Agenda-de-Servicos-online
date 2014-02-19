package com.agendame.security;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Named
@RequestScoped
public class Seguranca {

    @Inject
    private ExternalContext externalContext;

    public String getNomeUsuario() {
        String nome = null;

        UsuarioSistema usuarioLogado = getUsuarioLogado();

        if (usuarioLogado != null) {
            nome = usuarioLogado.getUsuario().getNome();
        }

        return nome;
    }

    private UsuarioSistema getUsuarioLogado() {
        UsuarioSistema usuario = null;

        UsernamePasswordAuthenticationToken auth = (UsernamePasswordAuthenticationToken) FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();

        if (auth != null && auth.getPrincipal() != null) {
            usuario = (UsuarioSistema) auth.getPrincipal();
        }

        return usuario;
    }

    public boolean isCadastroPermitido() {
        return externalContext.isUserInRole("ADMINISTRADORES") || externalContext.isUserInRole("PROFISSIONAIS");
    }

    public boolean isConfigAgendaPermitido() {
        return externalContext.isUserInRole("ADMINISTRADORES");
    }

    public boolean isAgendaSelecioneProfissional() {
        return externalContext.isUserInRole("ADMINISTRADORES") || externalContext.isUserInRole("CLIENTES");
    }

    public boolean isAgendaSelecioneCliente() {
        return externalContext.isUserInRole("ADMINISTRADORES") || externalContext.isUserInRole("PROFISSIONAIS");
    }

    public boolean isAgendaAjaxMove() {
        return externalContext.isUserInRole("ADMINISTRADORES") || externalContext.isUserInRole("PROFISSIONAIS");
    }

    public boolean isAgendaAjaxResize() {
        return externalContext.isUserInRole("ADMINISTRADORES") || externalContext.isUserInRole("PROFISSIONAIS");
    }
    
    public boolean isAgendaAjaxEventSelect() {
        return externalContext.isUserInRole("ADMINISTRADORES") || externalContext.isUserInRole("PROFISSIONAIS");
    }
    
    

}
