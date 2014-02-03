/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.repository;

import com.agendame.model.Endereco;
import com.agendame.service.NegocioException;
import com.agendame.util.jpa.Transactional;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceException;

/**
 *
 * @author Marcos-TSI
 */
public class Enderecos implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager em;
    @Transactional
    public Endereco guardar(Endereco endereco){
         return em.merge(endereco);
    }
    
    @Transactional
    public void remover(Endereco endereco) {
        try {
            endereco = porLogradouro(endereco.getLogradouro());
            em.remove(endereco);
            em.flush();
        } catch (PersistenceException e) {
            throw new NegocioException("Servico não pode ser excluído.");
        }
    }
    
    public List<Endereco> raizes(){
        return em.createQuery("from Endereco", Endereco.class).getResultList();    
    }
    
    public Endereco porId(Long id) {
        return em.find(Endereco.class, id);
    }

    private Endereco porLogradouro(String logradouro) {
        return em.find(Endereco.class, logradouro);
    }
}
