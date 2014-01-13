/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.agendame.repository;

import com.agendame.model.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author Marcos-TSI
 */
public class Usuarios implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Inject
    private EntityManager em;
    
    public Usuario guardar(Usuario usuario){
        EntityTransaction et = em.getTransaction();
        et.begin();
        usuario = em.merge(usuario);
        et.commit();
        return usuario;
    }
    
    public List<Usuario> raizes(){
        return em.createNamedQuery("from Usuario", Usuario.class).getResultList();    
    }
    
    
    
}
