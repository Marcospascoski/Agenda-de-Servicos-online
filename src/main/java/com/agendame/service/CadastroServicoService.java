/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.service;

import com.agendame.model.Servico;
import com.agendame.repository.Servicos;
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

    public Servico salvar(Servico servico) {
        Servico servicoExistente = servicos.porNome(servico.getNome());

        if (servicoExistente != null && !servicoExistente.equals(servico)) {
            throw new NegocioException("Ja existe um serviço com o nome informado.");
        }
        return servicos.guardar(servico);
    }
}