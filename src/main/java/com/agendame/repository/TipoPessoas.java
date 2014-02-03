 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.repository;

import com.agendame.model.TipoPessoa;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;


/**
 *
 * @author Marcos-TSI
 */
public class TipoPessoas implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    public List<TipoPessoa> raizes() {
        return em.createQuery("from TipoPessoa", TipoPessoa.class).getResultList();
    }

    
}
