/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.repository;

import com.agendame.model.Agenda;
import com.agendame.util.jpa.Transactional;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Marcos-TSI
 */
public class Agendas implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager em;
    
    @Transactional
    public Agenda guardar(Agenda agenda){
        return em.merge(agenda);
    }
    
    public List<Agenda> raizes(){
        return em.createQuery("from Agenda", Agenda.class).getResultList();    
    }
    
        public Agenda porId(Long id) {
        return em.find(Agenda.class, id);
    }
    
}
