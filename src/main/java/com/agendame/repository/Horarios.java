/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.repository;

import com.agendame.model.Horario;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Marcos-TSI
 */
public class Horarios implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager em;
    
    public Horario guardar(Horario horario){
        EntityTransaction et = em.getTransaction();
        et.begin();
        horario = em.merge(horario);
        et.commit();
        return horario;
    }
    
    public List<Horario> raizes(){
        return em.createNamedQuery("from Horario", Horario.class).getResultList();    
    }
    
    
    
}
