/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.service;

import com.agendame.model.Usuario;
import com.agendame.repository.Usuarios;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Marcos-TSI
 */
public class CadastroUsuarioService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Usuarios usuarios;

    public Usuario salvar(Usuario usuario) {
        Usuario usuarioExistente = usuarios.porEmail(usuario.getEmail());

        if (usuarioExistente != null && !usuarioExistente.equals(usuario)) {
            throw new NegocioException("Já existe um usuário com o E-mail informado.");
        }

        return usuarios.guardar(usuario);
    }

}
