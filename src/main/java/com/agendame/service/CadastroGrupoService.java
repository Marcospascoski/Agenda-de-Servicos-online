/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.service;

import com.agendame.model.Grupo;
import com.agendame.repository.Grupos;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Marcos-TSI
 */
public class CadastroGrupoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Grupos grupos;

    public Grupo salvar(Grupo grupo) {
        Grupo grupoExistente = grupos.porNome(grupo.getNome());

        if (grupoExistente != null && !grupoExistente.equals(grupo)) {
            throw new NegocioException("Já existe um usuário com o E-mail informado.");
        }
        
        return grupos.guardar(grupo);
    }

}
