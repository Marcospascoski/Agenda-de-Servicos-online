/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.repository;

import com.agendame.model.Grupo;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Marcos-TSI
 */
public class Grupos implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager em;
    
    public Grupo guardar(Grupo grupo){
        EntityTransaction et = em.getTransaction();
        et.begin();
        grupo = em.merge(grupo);
        et.commit();
        return grupo;
    }
    
    public List<Grupo> raizes(){
        return em.createQuery("from Grupo", Grupo.class).getResultList();    
    }
    
    public Grupo porId(Long id) {
        return em.find(Grupo.class, id);
    }
    
}
