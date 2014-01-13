/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.repository;

import com.agendame.model.Pessoa;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Marcos-TSI
 */
public class Pessoas implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager em;
    
    public Pessoa guardar(Pessoa pessoa){
        EntityTransaction et = em.getTransaction();
        et.begin();
        pessoa = em.merge(pessoa);
        et.commit();
        return pessoa;
    }
    
    public List<Pessoa> raizes(){
        return em.createNamedQuery("from Pessoas", Pessoa.class).getResultList();    
    }
    
    
    
}
