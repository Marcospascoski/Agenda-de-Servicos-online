/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.repository;

import com.agendame.model.Telefone;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Marcos-TSI
 */
public class Telefones implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager em;
    
    public Telefone guardar(Telefone telefone){
        EntityTransaction et = em.getTransaction();
        et.begin();
        telefone = em.merge(telefone);
        et.commit();
        return telefone;
    }
    
    public List<Telefone> raizes(){
        return em.createNamedQuery("from Telefone", Telefone.class).getResultList();    
    }
    
    
    
}
