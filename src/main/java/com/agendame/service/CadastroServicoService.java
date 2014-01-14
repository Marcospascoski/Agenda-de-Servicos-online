/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.service;

import com.agendame.model.Servico;
import com.agendame.repository.Servicos;
import com.agendame.util.jpa.Transactional;
import java.io.Serializable;
import javax.inject.Inject;

/**
 *
 * @author Marcos-TSI
 */
public class CadastroServicoService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private Servicos servicos;

    @Transactional
    public Servico Salvar(Servico servico) {
        Servico servicoExiste = servicos.porId(servico.getId());

        if (servicoExiste != null && !servicoExiste.equals(servico)) {
            throw new NegocioException("Ja existe um servico com o Codigo Informado");
        }

        return servicos.guardar(servico);
    }
}
