/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.service;

import com.agendame.model.Grupo;
import com.agendame.repository.Grupos;
import com.agendame.util.jpa.Transactional;
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

    @Transactional
    public Grupo salvar(Grupo grupo) {
        /*Grupo grupoExiste = grupos.porId(grupo.getId());

        if (grupoExiste != null && !grupoExiste.equals(grupo)) {
            throw new NegocioException("Ja existe um grupo com o Codigo Informado");
        }*/

        return grupos.guardar(grupo);
    }

}
