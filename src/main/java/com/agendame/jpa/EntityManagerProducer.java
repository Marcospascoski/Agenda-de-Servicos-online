/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.util.jpa;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Marcos-TSI
 */
@ApplicationScoped
public class EntityManagerProducer {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("AgendaMEPU");
    
    @Produces @RequestScoped
    public EntityManager createEntityManager(){
        return emf.createEntityManager();
    }
    
    public void closeEntityManager(@Disposes EntityManager em){
        em.close();
    }
    
}
