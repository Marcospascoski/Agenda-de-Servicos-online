/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.repository;

import com.agendame.model.Endereco;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Marcos-TSI
 */
public class Enderecos implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager em;
    
    public Endereco guardar(Endereco endereco){
        EntityTransaction et = em.getTransaction();
        et.begin();
        endereco = em.merge(endereco);
        et.commit();
        return endereco;
    }
    
    public List<Endereco> raizes(){
        return em.createNamedQuery("from Endereco", Endereco.class).getResultList();    
    }
    
    
    
}
