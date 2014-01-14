/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agendame.repository;

import com.agendame.model.Usuario;
import com.agendame.repository.filter.UsuarioFilter;
import java.io.Serializable;
import java.util.List;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Marcos-TSI
 */
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private EntityManager em;

    public Usuario guardar(Usuario usuario) {
        return em.merge(usuario);
    }

    public List<Usuario> raizes() {
        return em.createNamedQuery("from Usuario", Usuario.class).getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Usuario> filtrados(UsuarioFilter usuarioFilter) {
        Session session = em.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Usuario.class);
        
        // where nome like '%marcos%'
        if (StringUtils.isNotBlank(usuarioFilter.getNome())) {
            criteria.add(Restrictions.ilike("nome", usuarioFilter.getNome(), MatchMode.ANYWHERE));
        }
        return criteria.addOrder(Order.asc("nome")).list();
    }

    public Usuario porId(Long id) {
        return em.find(Usuario.class, id);
    }

}
